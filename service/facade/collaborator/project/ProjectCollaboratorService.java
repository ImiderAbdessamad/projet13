package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.service.impl.collaborator.util.ProcessResult;


public interface ProjectCollaboratorService {
    Conversation callApi(Conversation conversation) throws JsonProcessingException;

    String generateTitle(String projectDescription);

    Project newProject();
    Project findByTitleChat(String titleChat);
    Map<String, List<Project>> classifyProjects();



    ProcessResult generate(YamlFile yamlFile);
    List<Project> findByCollaboratorUsername(String username);
    List<Project> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Project> findByRemoteRepoInfoId(Long id);
    int deleteByRemoteRepoInfoId(Long id);
    long countByRemoteRepoInfoId(Long id);




    Project create(Project t);

    Project update(Project t);

    List<Project> update(List<Project> ts,boolean createIfNotExist);

    Project findById(Long id);

    Project findOrSave(Project t);

    Project findByReferenceEntity(Project t);

    Project findWithAssociatedLists(Long id);

    List<Project> findAllOptimized();

    List<Project> findAll();

    List<Project> findByCriteria(ProjectCriteria criteria);

    List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectCriteria criteria);

    List<Project> delete(List<Project> ts);

    boolean deleteById(Long id);

    List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
