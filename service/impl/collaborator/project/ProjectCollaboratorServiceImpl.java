package ma.zs.zyn.service.impl.collaborator.project;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.bean.core.project.*;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectDao;
import ma.zs.zyn.dao.specification.core.project.ProjectSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.project.*;
import ma.zs.zyn.service.impl.collaborator.util.GitHubValidationService;
import ma.zs.zyn.service.impl.collaborator.util.ProcessResult;
import ma.zs.zyn.service.impl.collaborator.util.ProcessResultMessage;
import ma.zs.zyn.service.impl.collaborator.util.UserConfig;
import ma.zs.zyn.service.util.PaymentStateConstant;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ma.zs.zyn.service.util.PaymentStateConstant.PAYMENT_EXPIRED;
import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.Yaml;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.LongStream;

import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;
@Service
public class ProjectCollaboratorServiceImpl implements ProjectCollaboratorService {
    private final String API_URL_TEMPLATE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s";

    @Value("${zyenerator.url}")
    private String zyeneratorUrl ;
    //private String zyeneratorUrl = "http://localhost:8090/back/generator/one-step";
    //private String zyeneratorUrl = "http://85.31.236.253:8090/back/generator/one-step";

    @Autowired
    private ChatGeminiMessageBuilder chatGeminiMessageBuilder;

    @Override
    @Transactional
    public Conversation callApi(Conversation conversation) throws JsonProcessingException {
        String prompt = conversation.getPrompt();
        Conversation saved = null;
        String historyPrompt = null;
        String historyResponse = null;
        Collaborator collaborator = collaboratorService.getCurrentUser();
        InscriptionCollaborator inscriptionCollaborator = inscriptionCollaboratorService.findByCollaboratorId(collaborator.getId());
        inscriptionCollaboratorService.resetInscription(collaborator);


        String message = validateChat(inscriptionCollaborator);

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        } else {
            if (conversation.getProject().getId() == null) {
                handleNewProject(conversation, collaborator);
            } else {
                String[] results = handleExistingProject(conversation, historyPrompt, historyResponse);
                historyPrompt = results[0];
                historyResponse = results[1];
            }
            int countTokenInput = countTokens(prompt);
            inscriptionCollaboratorService.incrementConsumedTokenInput(inscriptionCollaborator, countTokenInput);
            prompt = chatGeminiMessageBuilder.generateYamlDescription(prompt, conversation.getProject().getTitleChat(), historyPrompt, historyResponse);

            ResponseEntity<String> response = sendPrompt(prompt);
            String yamlFromResponse = extractYamlFromResponse(response.getBody());
            int countTokenOutput = countTokens(yamlFromResponse);
            inscriptionCollaboratorService.incrementConsumedTokenOutput(inscriptionCollaborator, countTokenOutput);
            conversation.setResponse(yamlFromResponse);
            saved = conversationService.create(conversation);
        }
        return saved;
    }
    private String validateChat(InscriptionCollaborator inscriptionCollaborator) {
        InscriptionCollaboratorState inscriptionCollaboratorState = inscriptionCollaborator.getInscriptionCollaboratorState();
        Packaging packaging = packagingService.findByReferenceEntity(inscriptionCollaborator.getPackaging());

        boolean isFree = inscriptionCollaboratorService.isFreeInscription(inscriptionCollaborator.getPackaging());
        String message = "";
        if (inscriptionCollaboratorState != null && PAYMENT_EXPIRED.equals(inscriptionCollaboratorState.getCode())) {
            message += "Your payment has expired, you can pay again and the service will be activated\n";
        }
        boolean tokenOutputCollaborator = inscriptionCollaboratorService.checkTokenOutputCollaborator(inscriptionCollaborator, packaging);
        boolean tokenInputCollaborator = inscriptionCollaboratorService.checkTokenInputCollaborator(inscriptionCollaborator, packaging);
        String inscriptionStateCode = inscriptionCollaboratorState.getCode();
        if (!tokenInputCollaborator) {
            message += "You don't have enough tokens input to use this service, you can upgrade your account to get more tokens\n";
        } else if (!tokenOutputCollaborator) {
            message += "You don't have enough tokens output to use this service, you can upgrade your account to get more tokens\n";
        } else if (!isFree && PaymentStateConstant.PENDING.equals(inscriptionStateCode)) {
            message += "Your inscription is pending !! Once the admin will validated you could start generating your projects\n";
        }
        return message;
    }

    public int countTokens(String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            return 0;
        }

        String[] tokens = prompt.split("\\s+");
        return tokens.length;
    }


    public ResponseEntity<String> sendPrompt(String prompt) {
        String geminiKey = "AIzaSyDaHXMcBdke9w7rhsxy0N3eGjBvK4xrkZg"; //"AIzaSyDAnoh-_RiSliRQjNskbz0lDzmKvDHk2nU";
        String apiUrl = String.format(API_URL_TEMPLATE, geminiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partsNode = objectMapper.createObjectNode();
        partsNode.put("text", prompt);
        contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestBodyNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct JSON request body", e);
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        return response;
    }

    private void handleNewProject(Conversation conversation, Collaborator collaborator) {
        String projectTitle = generateTitle(conversation.getPrompt());
        conversation.getProject().setTitleChat(projectTitle);
        conversation.getProject().setCollaborator(collaborator);
        Project project = dao.save(conversation.getProject());
        conversation.setProject(project);
    }

    private String[] handleExistingProject(Conversation conversation, String historyPrompt, String historyResponse) {
        conversation.setProject(findById(conversation.getProject().getId()));

        List<Conversation> lastConversations = conversationService.findByProjectTitleChat(conversation.getProject().getTitleChat());
        if (lastConversations != null && !lastConversations.isEmpty()) {
            Conversation lastConversation = lastConversations.get(lastConversations.size() - 1);
            historyPrompt = lastConversation.getResponse();
            historyResponse = lastConversation.getResponse();
        }
        return new String[]{historyPrompt, historyResponse};
    }

    @Override
    public Project newProject() {
        Project project = new Project();
        Collaborator collaborator = collaboratorService.getCurrentUser();
        inscriptionCollaboratorService.resetInscription(collaborator);
        project.setCollaborator(collaborator);
        project.setChatDateStart(LocalDate.now());
        project.setTitleChat("zyn-gpt " + LocalDate.now());
        return project;
    }


    public String extractYamlFromResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode textNode = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");

            // The text is expected to be in YAML format enclosed in code blocks (```)
            String yamlText = textNode.asText();
            // Remove the surrounding Markdown code block notation if present
            yamlText = yamlText.replace("```yaml\n", "").replace("```", "").trim();

            return yamlText;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing JSON response: " + e.getMessage();
        }
    }

    public static String extractTitle(String description) {
        return
                "give me a title  for this project description : " + description + " make sure you give me just the title not surrounded by any symbol please , and juste a short title in two word max , and give one title \n" +
                        "for example if the project description is : \n" +
                        "I want a car wash project which contains a car, a person, bank account, worker, land, product \n" +
                        "the title should be : Car Wash ";

    }

   @Override
    public String generateTitle(String projectDescription) {
        String title = extractTitle(projectDescription);
        ResponseEntity<String> response = sendPrompt(title);
        return extractResponse(response.getBody());

    }

    public static String extractResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode textNode = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");
            // The text is expected to be in YAML format enclosed in code blocks (```)
            String title = textNode.asText();
            // Remove the surrounding Markdown code block notation if present
            title = title.replace("\n", "").trim();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing JSON response: " + e.getMessage();
        }
    }

    private ProcessResult generateFromZynerator(String yamlText) {
        ResponseEntity<ProcessResult> generationProjectResultResponseEntity = restTemplate.postForEntity(zyeneratorUrl, new UserConfig(yamlText), ProcessResult.class);
        return generationProjectResultResponseEntity.getBody();
    }

    @Override
    public ProcessResult generate(YamlFile yamlFile) {
        Collaborator collaborator = collaboratorService.getCurrentUser();
        Project project = yamlFile.getProject();
        project.setCollaborator(collaborator);
        RemoteRepoInfo repoInfo = project.getRemoteRepoInfo();
        repoInfo.setCollaborator(collaborator);
        ProcessResult validateGitInfo = remoteRepoInfoService.validateGitInfo(repoInfo);
        if (validateGitInfo.getCode() < 0) {
            validateGitInfo.setYamlFileId(-1L);
            return validateGitInfo;
        }
        repoInfo.setTitle(project.getTitle());
        RemoteRepoInfo remoteRepoInfo = remoteRepoInfoService.create(repoInfo);
        if (remoteRepoInfo == null) {
            ProcessResult processResult = new ProcessResult(-6);
            processResult.getErrors().add(new ProcessResultMessage("Error while creating remote repository", -6));
            processResult.setYamlFileId(-1L);
            return processResult;
        }
        deleteByProjectId(project.getId());
        String yaml = yamlFileService.addConfigurationToYaml(yamlFile, remoteRepoInfo, project.getProjectDetails());
        yamlFile.setContent(yaml);
        InscriptionCollaborator inscriptionCollaborator = inscriptionCollaboratorService.findByCollaboratorId(collaborator.getId());
        long[] counted = countEntityAndAttribut(yamlFile.getContent());
        ProcessResult processResult = validateGeneration(collaborator, inscriptionCollaborator, yamlFile,counted);
        if (processResult.getCode() < 0) {
            processResult.setYamlFileId(-1L);
            return processResult;
        } else {
            inscriptionCollaboratorService.incrementConsumed(inscriptionCollaborator, BigDecimal.valueOf(counted[0]), BigDecimal.valueOf(counted[1]), BigDecimal.ONE);

            project.setRemoteRepoInfo(remoteRepoInfo);
            project.setCollaborator(collaborator);
            project.setGeneratedDate(LocalDateTime.now());
            Project saved = create(project);
            yamlFile.setProject(saved);
            YamlFile yamlFileSaved = yamlFileService.create(yamlFile);
            System.out.println("just before generateFromZynerator");
            ProcessResult generationProcessResult = generateFromZynerator(yamlFile.getContent());
            System.out.println("just after generateFromZynerator");
            String remoteRepoUrl = "https://github.com/"+ remoteRepoInfo.getUsername()+ "/" + remoteRepoInfo.getName()+".git";
            gitHubValidationService.gitPushFunction("src/main/java/ma/zs/zyn",remoteRepoUrl, remoteRepoInfo.getUsername(), remoteRepoInfo.getToken());
            generationProcessResult.setYamlFileId(yamlFileSaved.getId());
            return generationProcessResult;
        }
    }

    private ProcessResult validateGeneration(Collaborator collaborator, InscriptionCollaborator inscriptionCollaborator, YamlFile yamlFile,long[] counted) {
        ProcessResult processResult = new ProcessResult(1);
        if (collaborator == null) {
            int code = -1;
            processResult.setCode(code);
            processResult.getErrors().add(new ProcessResultMessage("Current user not found.", code));
        }
        if (inscriptionCollaborator == null) {
            int code = -2;
            processResult.setCode(code);
            processResult.getErrors().add(new ProcessResultMessage("Inscription not found for the current collaborator.", code));
        }
        Packaging packaging = inscriptionCollaborator.getPackaging();
        if (packaging.getMaxProjet().compareTo(inscriptionCollaborator.getConsumedProjet()) < 0) {
            int code = -3;
            processResult.setCode(code);
            processResult.getErrors().add(new ProcessResultMessage("Maximum number of projects exceeded.", code));
        }

        BigDecimal maxEntity = packaging.getMaxEntity().multiply(packaging.getMaxProjet());
        BigDecimal consumedEntity = inscriptionCollaborator.getConsumedEntity().add(BigDecimal.valueOf(counted[0]));
        if (maxEntity.compareTo(consumedEntity) < 0) {
            int code = -4;
            processResult.setCode(code);
            processResult.getErrors().add(new ProcessResultMessage("Maximum number of entities exceeded.", code));
        }
        BigDecimal maxAttribut = packaging.getMaxAttribut().multiply(maxEntity);
        BigDecimal consumedAttribut = inscriptionCollaborator.getConsumedAttribut().add(BigDecimal.valueOf(counted[1]));
        if (maxAttribut.compareTo(consumedAttribut) < 0) {
            int code = -5;
            processResult.setCode(code);
            processResult.getErrors().add(new ProcessResultMessage("Maximum number of attributes exceeded.", code));
        }

        return processResult;
    }

    private long[] countEntityAndAttribut(String yamlFile) {
        yamlFile = yamlFile.replaceAll("^\\s*\n", "");
        Yaml yaml = new Yaml();
        Map<String, Map<String, String>> yamlPojos = (Map<String, Map<String, String>>) yaml.load(yamlFile);
        if (yamlPojos != null) {
            yamlPojos.remove("$CONFIG");
        }
        long numberEntities = yamlPojos.size();
        long numberAttributes = LongStream.of(yamlPojos.values().stream()
                .mapToLong(Map::size)
                .toArray()).sum(); //
        return new long[]{numberEntities, numberAttributes};
    }
    @Override
    public List<Project> findByCollaboratorUsername(String username) {
        List<Project> projects = dao.findByCollaboratorUsername(username);
        return projects;
    }

    @Override
    public Map<String, List<Project>> classifyProjects() {
        Collaborator collaborator = collaboratorService.getCurrentUser();
        List<Project> projects = dao.findByCollaboratorUsername(collaborator.getUsername());

        LocalDate today = LocalDate.now();
        Map<String, List<Project>> categories = new HashMap<>();
        categories.put("today", new ArrayList<>());
        categories.put("yesterday", new ArrayList<>());
        categories.put("last7Days", new ArrayList<>());
        categories.put("last30Days", new ArrayList<>());
        categories.put("beforeLast30Days", new ArrayList<>());

        for (Project project : projects) {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(project.getChatDateStart(), today);

            if (daysBetween < 1) {
                categories.get("today").add(project);
            } else if (daysBetween < 2) {
                categories.get("yesterday").add(project);
            } else if (daysBetween < 9) {
                categories.get("last7Days").add(project);
            } else if (daysBetween < 39) {
                categories.get("last30Days").add(project);
            } else {
                categories.get("beforeLast30Days").add(project);
            }
        }
        return categories;
    }

    @Override
    public Project findByTitleChat(String titleChat) {
        return dao.findByTitleChat(titleChat);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project update(Project t) {
        Project loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Project.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Project findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Project findOrSave(Project t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Project result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Project> findAll() {
        return dao.findAll();
    }

    public List<Project> findByCriteria(ProjectCriteria criteria) {
        List<Project> content = null;
        if (criteria != null) {
            ProjectSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ProjectSpecification constructSpecification(ProjectCriteria criteria) {
        ProjectSpecification mySpecification =  (ProjectSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectSpecification.class, criteria);
        return mySpecification;
    }

    public List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectCriteria criteria) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Project> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<Project> findByRemoteRepoInfoId(Long id){
        return dao.findByRemoteRepoInfoId(id);
    }
    public int deleteByRemoteRepoInfoId(Long id){
        return dao.deleteByRemoteRepoInfoId(id);
    }
    public long countByRemoteRepoInfoId(Long id){
        return dao.countByRemoteRepoInfoId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        conversationService.deleteByProjectId(id);
        projectDetailService.deleteByProjectId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> delete(List<Project> list) {
        List<Project> result = new ArrayList();
        if (list != null) {
            for (Project t : list) {
                if(dao.findById(t.getId()).isEmpty()){
                    result.add(t);
                }
            }
        }
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteByProjectId(Long id){
        projectDetailService.deleteByProjectId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project create(Project t) {
        Project saved;
            saved = dao.save(t);
            if (t.getProjectDetails() != null) {
                t.getProjectDetails().forEach(element-> {
                    element.setProject(saved);
                    projectDetailService.create(element);
                });
            }
        return saved;
    }

    public Project findWithAssociatedLists(Long id){
        Project result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setConversations(conversationService.findByProjectId(id));
            result.setProjectDetails(projectDetailService.findByProjectId(id));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> update(List<Project> ts, boolean createIfNotExist) {
        List<Project> result = new ArrayList<>();
        if (ts != null) {
            for (Project t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Project loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Project t, Project loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Project project){
        if(project !=null && project.getId() != null){
            List<List<Conversation>> resultConversations= conversationService.getToBeSavedAndToBeDeleted(conversationService.findByProjectId(project.getId()),project.getConversations());
            conversationService.delete(resultConversations.get(1));
            emptyIfNull(resultConversations.get(0)).forEach(e -> e.setProject(project));
            conversationService.update(resultConversations.get(0),true);
            List<List<ProjectDetail>> resultProjectDetails= projectDetailService.getToBeSavedAndToBeDeleted(projectDetailService.findByProjectId(project.getId()),project.getProjectDetails());
            projectDetailService.delete(resultProjectDetails.get(1));
            emptyIfNull(resultProjectDetails.get(0)).forEach(e -> e.setProject(project));
            projectDetailService.update(resultProjectDetails.get(0),true);
        }
    }








    public Project findByReferenceEntity(Project t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Project t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setRemoteRepoInfo(remoteRepoInfoService.findOrSave(t.getRemoteRepoInfo()));
        }
    }



    public List<Project> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList) {
        List<List<Project>> result = new ArrayList<>();
        List<Project> resultDelete = new ArrayList<>();
        List<Project> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
            extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Project> oldList, List<Project> newList, List<Project> resultUpdateOrSave, List<Project> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            Project myOld = oldList.get(i);
            Project t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            Project myNew = newList.get(i);
            Project t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew); // create
            }
        }
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CollaboratorCollaboratorService collaboratorService ;
    @Autowired
    private RemoteRepoInfoCollaboratorService remoteRepoInfoService ;
    @Autowired
    private ConversationCollaboratorService conversationService ;
    @Autowired
    private ProjectDetailCollaboratorService projectDetailService ;
    @Autowired
    private InscriptionCollaboratorCollaboratorService inscriptionCollaboratorService;
    @Autowired
    private YamlFileCollaboratorService yamlFileService;
    @Autowired
    private PackagingCollaboratorService packagingService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GitHubValidationService gitHubValidationService;

    public ProjectCollaboratorServiceImpl(ProjectDao dao) {
        this.dao = dao;
    }

    private ProjectDao dao;
}
