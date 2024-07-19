package ma.zs.zyn.service.impl.collaborator.util;

import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

@Service
public class GitHubValidationService {

    private final RestTemplate restTemplate;

    public GitHubValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void gitPushFunction(String localPath, String remotePath, String username, String password) {
        try {
            File localDir = new File(localPath);
            Git git;


            if (new File(localDir, ".git").exists()) {
                git = Git.open(localDir);
                System.out.println("Dépôt Git existant ouvert.");
            } else {
                git = Git.init().setDirectory(localDir).call();
                System.out.println("Nouveau dépôt Git initialisé.");
            }

            try {
                System.out.println("Ajout de tous les fichiers...");
                git.add().addFilepattern(".").call();

                Status status = git.status().call();
                if (!status.isClean()) {
                    System.out.println("Création d'un nouveau commit...");
                    git.commit().setMessage("Commit automatique").call();
                } else {
                    System.out.println("Aucun changement à commiter.");
                }

                System.out.println("Configuration du dépôt distant...");
                try {
                    git.remoteAdd()
                            .setName("origin")
                            .setUri(new URIish(remotePath))
                            .call();
                    System.out.println("Remote 'origin' ajouté.");
                } catch (Exception e) {
                    System.out.println("Le remote 'origin' existe peut-être déjà : " + e.getMessage());
                }

                System.out.println("Tentative de push vers " + remotePath);
                PushCommand pushCommand = git.push();
                pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
                pushCommand.setRemote("origin");
                pushCommand.call();

                System.out.println("Push réussi !");
            } finally {
                git.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du push Git: " + e.getMessage(), e);
        }
    }


    private boolean validateUser(RemoteRepoInfo remoteRepoInfo, String url, String tokenPrefix, String usernameKey) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", tokenPrefix + " " + remoteRepoInfo.getToken());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody() != null && response.getBody().contains("\"" + usernameKey + "\":\"" + remoteRepoInfo.getUsername() + "\"");
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    private boolean validateRepo(RemoteRepoInfo remoteRepoInfo, String url, String tokenPrefix) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", tokenPrefix + " " + remoteRepoInfo.getToken());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return true; // If no exception is thrown, the repo exists
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    public boolean validateGitHubUser(RemoteRepoInfo remoteRepoInfo) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user").toUriString();
        return validateUser(remoteRepoInfo, url, "token", "login");
    }

    public boolean validateGitLabUser(RemoteRepoInfo remoteRepoInfo) {
        String url = UriComponentsBuilder.fromHttpUrl("https://gitlab.com/api/v4/user").toUriString();
        return validateUser(remoteRepoInfo, url, "Bearer", "username");
    }

    public boolean validateGitHubRepo(RemoteRepoInfo remoteRepoInfo) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/repos/" + remoteRepoInfo.getUsername() + "/" + remoteRepoInfo.getName()).toUriString();
        return validateRepo(remoteRepoInfo, url, "token");
    }

    public boolean validateGitLabRepo(RemoteRepoInfo remoteRepoInfo) {
        String url = UriComponentsBuilder.fromHttpUrl("https://gitlab.com/api/v4/projects/" + remoteRepoInfo.getUsername() + "%2F" + remoteRepoInfo.getName()).toUriString();
        return validateRepo(remoteRepoInfo, url, "Bearer");
    }

    public void createGitHubRepo(RemoteRepoInfo remoteRepoInfo) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user/repos").toUriString();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "token " + remoteRepoInfo.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("name", remoteRepoInfo.getName());
            requestBody.put("private", false); // or true if you want to create a private repo

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Failed to create GitHub repository: " + e.getMessage());
        }
    }

    public void createGitLabRepo(RemoteRepoInfo remoteRepoInfo) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl("https://gitlab.com/api/v4/projects").toUriString();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + remoteRepoInfo.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("name", remoteRepoInfo.getName());
            requestBody.put("namespace_id", remoteRepoInfo.getUsername()); // assuming the username is the namespace

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Failed to create GitLab repository: " + e.getMessage());
        }
    }

    public ProcessResult validateRepoInfo(RemoteRepoInfo remoteRepoInfo) {
        ProcessResult processResult = new ProcessResult(1);
        switch (remoteRepoInfo.getRemoteRepoType().getLibelle().toLowerCase()) {
            case "github":
                boolean validatedGitHubUser = validateGitHubUser(remoteRepoInfo);
                if (!validatedGitHubUser) {
                    processResult.setCode(-1);
                    processResult.getErrors().add(new ProcessResultMessage("Invalid GitHub user", -1));
                    return processResult;
                }
                boolean githubRepoExists = validateGitHubRepo(remoteRepoInfo);
                if (!githubRepoExists) {
                    createGitHubRepo(remoteRepoInfo);
                }
                return processResult;
            case "gitlab":
                boolean validatedGitLabUser = validateGitLabUser(remoteRepoInfo);
                if (!validatedGitLabUser) {
                    processResult.setCode(-2);
                    processResult.getErrors().add(new ProcessResultMessage("Invalid GitLab user", -2));
                    return processResult;
                }
                boolean gitlabRepoExists = validateGitLabRepo(remoteRepoInfo);
                if (!gitlabRepoExists) {
                    createGitLabRepo(remoteRepoInfo);
                }
                return processResult;
            default:
                processResult.setCode(-3);
                processResult.getErrors().add(new ProcessResultMessage("Unsupported platform: " + remoteRepoInfo.getRemoteRepoType().getCode(), -3));
                return processResult;
        }
    }
}
