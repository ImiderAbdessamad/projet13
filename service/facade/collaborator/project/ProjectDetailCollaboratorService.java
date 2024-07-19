package ma.zs.zyn.service.facade.collaborator.project;

import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.dao.criteria.core.project.ProjectDetailCriteria;

import java.util.List;



public interface ProjectDetailCollaboratorService {



    List<ProjectDetail> findByProjectTechnologyId(Long id);
    int deleteByProjectTechnologyId(Long id);
    long countByProjectTechnologyCode(String code);
    List<ProjectDetail> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);
    List<ProjectDetail> findByProjectTechnologyProfileCode(String code);
    int deleteByProjectTechnologyProfileCode(String code);
    long countByProjectTechnologyProfileCode(String code);




	ProjectDetail create(ProjectDetail t);

    ProjectDetail update(ProjectDetail t);

    List<ProjectDetail> update(List<ProjectDetail> ts,boolean createIfNotExist);

    ProjectDetail findById(Long id);

    ProjectDetail findOrSave(ProjectDetail t);

    ProjectDetail findByReferenceEntity(ProjectDetail t);

    ProjectDetail findWithAssociatedLists(Long id);

    List<ProjectDetail> findAllOptimized();

    List<ProjectDetail> findAll();

    List<ProjectDetail> findByCriteria(ProjectDetailCriteria criteria);

    List<ProjectDetail> findPaginatedByCriteria(ProjectDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectDetailCriteria criteria);

    List<ProjectDetail> delete(List<ProjectDetail> ts);

    boolean deleteById(Long id);

    List<List<ProjectDetail>> getToBeSavedAndToBeDeleted(List<ProjectDetail> oldList, List<ProjectDetail> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
