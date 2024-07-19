package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.dao.criteria.core.project.ProjectDetailCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectDetailDao;
import ma.zs.zyn.dao.specification.core.project.ProjectDetailSpecification;
import ma.zs.zyn.service.facade.admin.project.ProjectAdminService;
import ma.zs.zyn.service.facade.admin.project.ProjectDetailAdminService;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyAdminService;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyProfileAdminService;
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
import java.util.List;

import static ma.zs.zyn.zynerator.util.ListUtil.*;

@Service
public class ProjectDetailAdminServiceImpl implements ProjectDetailAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectDetail update(ProjectDetail t) {
        ProjectDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectDetail findOrSave(ProjectDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjectDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectDetail> findAll() {
        return dao.findAll();
    }

    public List<ProjectDetail> findByCriteria(ProjectDetailCriteria criteria) {
        List<ProjectDetail> content = null;
        if (criteria != null) {
            ProjectDetailSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectDetailSpecification constructSpecification(ProjectDetailCriteria criteria) {
        ProjectDetailSpecification mySpecification =  (ProjectDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectDetail> findPaginatedByCriteria(ProjectDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectDetailCriteria criteria) {
        ProjectDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjectDetail> findByProjectTechnologyId(Long id){
        return dao.findByProjectTechnologyId(id);
    }
    public int deleteByProjectTechnologyId(Long id){
        return dao.deleteByProjectTechnologyId(id);
    }
    public long countByProjectTechnologyCode(String code){
        return dao.countByProjectTechnologyCode(code);
    }
    public List<ProjectDetail> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectId(Long id){
        return dao.countByProjectId(id);
    }
    public List<ProjectDetail> findByProjectTechnologyProfileCode(String code){
        return dao.findByProjectTechnologyProfileCode(code);
    }
    public int deleteByProjectTechnologyProfileCode(String code){
        return dao.deleteByProjectTechnologyProfileCode(code);
    }
    public long countByProjectTechnologyProfileCode(String code){
        return dao.countByProjectTechnologyProfileCode(code);
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
    public List<ProjectDetail> delete(List<ProjectDetail> list) {
		List<ProjectDetail> result = new ArrayList();
        if (list != null) {
            for (ProjectDetail t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectDetail create(ProjectDetail t) {
        ProjectDetail loaded = findByReferenceEntity(t);
        ProjectDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectDetail findWithAssociatedLists(Long id){
        ProjectDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectDetail> update(List<ProjectDetail> ts, boolean createIfNotExist) {
        List<ProjectDetail> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectDetail t, ProjectDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectDetail findByReferenceEntity(ProjectDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjectDetail t){
        if( t != null) {
            t.setProjectTechnology(projectTechnologyService.findOrSave(t.getProjectTechnology()));
            t.setProject(projectService.findOrSave(t.getProject()));
            t.setProjectTechnologyProfile(projectTechnologyProfileService.findOrSave(t.getProjectTechnologyProfile()));
        }
    }



    public List<ProjectDetail> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectDetail>> getToBeSavedAndToBeDeleted(List<ProjectDetail> oldList, List<ProjectDetail> newList) {
        List<List<ProjectDetail>> result = new ArrayList<>();
        List<ProjectDetail> resultDelete = new ArrayList<>();
        List<ProjectDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectDetail> oldList, List<ProjectDetail> newList, List<ProjectDetail> resultUpdateOrSave, List<ProjectDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectDetail myOld = oldList.get(i);
                ProjectDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectDetail myNew = newList.get(i);
                ProjectDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectAdminService projectService ;
    @Autowired
    private ProjectTechnologyAdminService projectTechnologyService ;
    @Autowired
    private ProjectTechnologyProfileAdminService projectTechnologyProfileService ;

    public ProjectDetailAdminServiceImpl(ProjectDetailDao dao) {
        this.dao = dao;
    }

    private ProjectDetailDao dao;
}
