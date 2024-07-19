package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;

import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.YamlFileCriteria;


public interface YamlFileCollaboratorService {

    int countUniqueMicroservices(YamlFile yamlContent);
    String addConfigurationToYaml(YamlFile yamlFile, RemoteRepoInfo remoteRepoInfo, List<ProjectDetail> projectDetails);
    List<YamlFile> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);
    List<YamlFile> findByCollaboratorUsername();



	YamlFile create(YamlFile t);

    YamlFile update(YamlFile t);

    List<YamlFile> update(List<YamlFile> ts,boolean createIfNotExist);

    YamlFile findById(Long id);

    YamlFile findOrSave(YamlFile t);

    YamlFile findByReferenceEntity(YamlFile t);

    YamlFile findWithAssociatedLists(Long id);

    List<YamlFile> findAllOptimized();

    List<YamlFile> findAll();

    List<YamlFile> findByCriteria(YamlFileCriteria criteria);

    List<YamlFile> findPaginatedByCriteria(YamlFileCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(YamlFileCriteria criteria);

    List<YamlFile> delete(List<YamlFile> ts);

    boolean deleteById(Long id);

    List<List<YamlFile>> getToBeSavedAndToBeDeleted(List<YamlFile> oldList, List<YamlFile> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
