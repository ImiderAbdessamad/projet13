package ma.zs.zyn.service.impl.collaborator.project;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.YamlFileCriteria;
import ma.zs.zyn.dao.facade.core.project.YamlFileDao;
import ma.zs.zyn.dao.specification.core.project.YamlFileSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.project.ProjectCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.project.YamlFileCollaboratorService;
import ma.zs.zyn.service.util.ProjectTechnologyTypeConstant;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;

@Service
public class YamlFileCollaboratorServiceImpl implements YamlFileCollaboratorService {
    public  int countUniqueMicroservices(YamlFile yamlContent) {
        // Define the pattern to match microservices with their identifiers (e.g., "_MS(ms1)")
        String msPattern = "_MS\\((.*?)\\)";
        Pattern pattern = Pattern.compile(msPattern);
        Matcher matcher = pattern.matcher(yamlContent.getContent());

        // Use a set to store unique microservice identifiers
        Set<String> uniqueMicroservices = new HashSet<>();

        // Find and add unique microservice identifiers to the set
        while (matcher.find()) {
            String msIdentifier = matcher.group(1);
            uniqueMicroservices.add(msIdentifier);
        }

        return uniqueMicroservices.size();
    }

    @Override
    public String addConfigurationToYaml(YamlFile yamlFile, RemoteRepoInfo remoteRepoInfo, List<ProjectDetail> projectDetails) {
        validateYamlRequest(yamlFile, remoteRepoInfo);

        StringBuilder configTemplate = new StringBuilder("$CONFIG:\n");

        for (ProjectDetail detail : projectDetails) {
            if (detail.getProjectTechnology().getProjectTechnologyType().getCode().equals(ProjectTechnologyTypeConstant.FRONT)) {
                configTemplate.append("  ms1-front: '{tech=").append(detail.getProjectTechnology().getCode().toLowerCase()).append(", template=default-").append(detail.getProjectTechnologyProfile().getLibelle().toLowerCase()).append(" ,enable=true, port=").append(detail.getPort()).append("}'\n");
            }
            else if (detail.getProjectTechnology().getProjectTechnologyType().getCode().equals(ProjectTechnologyTypeConstant.BACK)) {
                String msName = "ms1";
                String[] basePackage = splitBasePackage(detail.getBasePackage());
                configTemplate.append("  ")
                        .append(msName)
                        .append("-back: '{tech=").append(detail.getProjectTechnology().getCode().toLowerCase()).append(", template=default-").append(detail.getProjectTechnologyProfile().getLibelle().toLowerCase()).append(" ,enable=true, domain=").append(basePackage[0].toLowerCase()).append(", groupId=")
                        .append(formatProjectName(basePackage[1].toLowerCase()))
                        .append(", projectName=")
                        .append(formatProjectName(basePackage[2].toLowerCase()))
                        .append(", port=")
                        .append(detail.getPort())
                        .append(", msName=")
                        .append(formatProjectName(detail.getMsName()))
                        .append("}'\n");
            }
            else if (detail.getProjectTechnology().getProjectTechnologyType().getCode().equals(ProjectTechnologyTypeConstant.BD)) {
                configTemplate.append("  ")
                        .append("ms1-db: '{name=")
                        .append(detail.getDbName())
                        .append(", type=").append(detail.getProjectTechnology().getCode().toLowerCase()).append("}'\n");
            }
        }
        configTemplate.append("  repository-info: '{name=${repoName}, type=${remoteRepoType}, username=${username}, token=${token}}'\n");

        String newConfigSection = replaceVariables(configTemplate.toString(), remoteRepoInfo);
        String content = processContent(yamlFile.getContent());

        return content + "\n\n\n" + newConfigSection;
    }
    public String processContent(String content) {
        String configSection = "$CONFIG:";
        int configIndex = content.indexOf(configSection);

        if (configIndex != -1) {
            // Find the end of the config section
            int endOfConfigIndex = content.indexOf("\n", configIndex);
            if (endOfConfigIndex == -1) {
                endOfConfigIndex = content.length();
            }
            // Remove the config section
            content = content.substring(0, configIndex).trim();
        }

        return content;
    }

    private String replaceVariables(String template, RemoteRepoInfo remoteRepoInfo) {
        Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String variable = matcher.group(1);
            String replacement = getFieldValue(remoteRepoInfo, variable);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    private String getFieldValue(RemoteRepoInfo remoteRepoInfo, String fieldName) {
        switch (fieldName) {
            case "projectName":
                return formatProjectName(remoteRepoInfo.getTitle());
            case "repoName":
                return remoteRepoInfo.getName();
            case "remoteRepoType":
                return remoteRepoInfo.getRemoteRepoType().getCode();
            case "username":
                return remoteRepoInfo.getUsername();
            case "token":
                return remoteRepoInfo.getToken();
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }

    private String formatProjectName(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null or empty");
        }
        // Remove spaces and hyphens, then capitalize each word except the first
        String[] words = projectName.split("[ -]");
        StringBuilder formattedName = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                formattedName.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1).toLowerCase());
            }
        }

        return formattedName.toString();
    }
    private static void validateYamlRequest(YamlFile yamlFile , RemoteRepoInfo repoInfo) {
        if (yamlFile.getContent() == null || yamlFile.getContent() .isEmpty()) {
            throw new IllegalArgumentException("Original Yaml File content cannot be null or empty");
        }
        if (repoInfo.getTitle() == null || repoInfo.getTitle() .isEmpty()) {
            throw new IllegalArgumentException("Remote Repo title be null or empty");
        }

        if (repoInfo.getName() == null) {
            throw new IllegalArgumentException("Remote Repo Name cannot be null");
        }
        if (repoInfo.getUsername() == null) {
            throw new IllegalArgumentException("Remote Repo Username cannot be null");
        }
        if (repoInfo.getToken() == null) {
            throw new IllegalArgumentException("Remote Repo Token cannot be null");
        }
        if (repoInfo.getRemoteRepoType() == null || repoInfo.getRemoteRepoType().getCode() == null) {
            throw new IllegalArgumentException("Remote Repo Type cannot be null");
        }

    }
    public  String[] splitBasePackage(String basePackage) {
        String[] parts = basePackage.split("\\.");

        if (parts.length == 3) {
            return parts;
        } else {
            return null; // La chaîne de caractères n'a pas pu être divisée en trois parties
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public YamlFile update(YamlFile t) {
        YamlFile loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{YamlFile.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public YamlFile findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public YamlFile findOrSave(YamlFile t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            YamlFile result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<YamlFile> findAll() {
        return dao.findAll();
    }

    public List<YamlFile> findByCollaboratorUsername() {
        Collaborator collaborator = collaboratorService.getCurrentUser();
        return dao.findByProjectCollaboratorUsername(collaborator.getUsername());

    }

    public List<YamlFile> findByCriteria(YamlFileCriteria criteria) {
        List<YamlFile> content = null;
        if (criteria != null) {
            YamlFileSpecification mySpecification = constructSpecification(criteria);
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


    private YamlFileSpecification constructSpecification(YamlFileCriteria criteria) {
        YamlFileSpecification mySpecification =  (YamlFileSpecification) RefelexivityUtil.constructObjectUsingOneParam(YamlFileSpecification.class, criteria);
        return mySpecification;
    }

    public List<YamlFile> findPaginatedByCriteria(YamlFileCriteria criteria, int page, int pageSize, String order, String sortField) {
        YamlFileSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(YamlFileCriteria criteria) {
        YamlFileSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<YamlFile> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectId(Long id){
        return dao.countByProjectId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<YamlFile> delete(List<YamlFile> list) {
		List<YamlFile> result = new ArrayList();
        if (list != null) {
            for (YamlFile t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public YamlFile create(YamlFile t) {
        YamlFile loaded = findByReferenceEntity(t);
        YamlFile saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public YamlFile findWithAssociatedLists(Long id){
        YamlFile result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<YamlFile> update(List<YamlFile> ts, boolean createIfNotExist) {
        List<YamlFile> result = new ArrayList<>();
        if (ts != null) {
            for (YamlFile t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    YamlFile loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, YamlFile t, YamlFile loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public YamlFile findByReferenceEntity(YamlFile t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(YamlFile t){
        if( t != null) {
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<YamlFile> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<YamlFile>> getToBeSavedAndToBeDeleted(List<YamlFile> oldList, List<YamlFile> newList) {
        List<List<YamlFile>> result = new ArrayList<>();
        List<YamlFile> resultDelete = new ArrayList<>();
        List<YamlFile> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<YamlFile> oldList, List<YamlFile> newList, List<YamlFile> resultUpdateOrSave, List<YamlFile> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                YamlFile myOld = oldList.get(i);
                YamlFile t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                YamlFile myNew = newList.get(i);
                YamlFile t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectCollaboratorService projectService ;
    @Autowired
    private CollaboratorCollaboratorService collaboratorService ;

    public YamlFileCollaboratorServiceImpl(YamlFileDao dao) {
        this.dao = dao;
    }

    private YamlFileDao dao;
}
