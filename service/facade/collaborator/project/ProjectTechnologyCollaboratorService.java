package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ProjectTechnologyCollaboratorService {



    List<ProjectTechnology> findByProjectTechnologyTypeCode(String code);
    int deleteByProjectTechnologyTypeCode(String code);
    long countByProjectTechnologyTypeCode(String code);




	ProjectTechnology create(ProjectTechnology t);

    List<ProjectTechnology> findByProjectTechnologyTypeCodeDb();

    List<ProjectTechnology> findByProjectTechnologyTypeCodeBack();

    List<ProjectTechnology> findByProjectTechnologyTypeCodeFront();

    ProjectTechnology update(ProjectTechnology t);

    List<ProjectTechnology> update(List<ProjectTechnology> ts,boolean createIfNotExist);

    ProjectTechnology findById(Long id);

    ProjectTechnology findOrSave(ProjectTechnology t);

    ProjectTechnology findByReferenceEntity(ProjectTechnology t);

    ProjectTechnology findWithAssociatedLists(Long id);

    List<ProjectTechnology> findAllOptimized();

    List<ProjectTechnology> findAll();

    List<ProjectTechnology> findByCriteria(ProjectTechnologyCriteria criteria);

    List<ProjectTechnology> findPaginatedByCriteria(ProjectTechnologyCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTechnologyCriteria criteria);

    List<ProjectTechnology> delete(List<ProjectTechnology> ts);

    boolean deleteById(Long id);

    List<List<ProjectTechnology>> getToBeSavedAndToBeDeleted(List<ProjectTechnology> oldList, List<ProjectTechnology> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
