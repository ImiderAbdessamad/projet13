package ma.zs.zyn.service.facade.admin.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyProfileCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ProjectTechnologyProfileAdminService {







	ProjectTechnologyProfile create(ProjectTechnologyProfile t);

    ProjectTechnologyProfile update(ProjectTechnologyProfile t);

    List<ProjectTechnologyProfile> update(List<ProjectTechnologyProfile> ts,boolean createIfNotExist);

    ProjectTechnologyProfile findById(Long id);

    ProjectTechnologyProfile findOrSave(ProjectTechnologyProfile t);

    ProjectTechnologyProfile findByReferenceEntity(ProjectTechnologyProfile t);

    ProjectTechnologyProfile findWithAssociatedLists(Long id);

    List<ProjectTechnologyProfile> findAllOptimized();

    List<ProjectTechnologyProfile> findAll();

    List<ProjectTechnologyProfile> findByCriteria(ProjectTechnologyProfileCriteria criteria);

    List<ProjectTechnologyProfile> findPaginatedByCriteria(ProjectTechnologyProfileCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTechnologyProfileCriteria criteria);

    List<ProjectTechnologyProfile> delete(List<ProjectTechnologyProfile> ts);

    boolean deleteById(Long id);

    List<List<ProjectTechnologyProfile>> getToBeSavedAndToBeDeleted(List<ProjectTechnologyProfile> oldList, List<ProjectTechnologyProfile> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
