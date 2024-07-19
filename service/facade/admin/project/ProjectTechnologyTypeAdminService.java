package ma.zs.zyn.service.facade.admin.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyTypeCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ProjectTechnologyTypeAdminService {







	ProjectTechnologyType create(ProjectTechnologyType t);

    ProjectTechnologyType update(ProjectTechnologyType t);

    List<ProjectTechnologyType> update(List<ProjectTechnologyType> ts,boolean createIfNotExist);

    ProjectTechnologyType findById(Long id);

    ProjectTechnologyType findOrSave(ProjectTechnologyType t);

    ProjectTechnologyType findByReferenceEntity(ProjectTechnologyType t);

    ProjectTechnologyType findWithAssociatedLists(Long id);

    List<ProjectTechnologyType> findAllOptimized();

    List<ProjectTechnologyType> findAll();

    List<ProjectTechnologyType> findByCriteria(ProjectTechnologyTypeCriteria criteria);

    List<ProjectTechnologyType> findPaginatedByCriteria(ProjectTechnologyTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTechnologyTypeCriteria criteria);

    List<ProjectTechnologyType> delete(List<ProjectTechnologyType> ts);

    boolean deleteById(Long id);

    List<List<ProjectTechnologyType>> getToBeSavedAndToBeDeleted(List<ProjectTechnologyType> oldList, List<ProjectTechnologyType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
