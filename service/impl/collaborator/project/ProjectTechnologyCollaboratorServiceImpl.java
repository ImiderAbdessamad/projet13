package ma.zs.zyn.service.impl.collaborator.project;


import ma.zs.zyn.service.util.ProjectTechnologyTypeConstant;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectTechnologyDao;
import ma.zs.zyn.dao.specification.core.project.ProjectTechnologySpecification;
import ma.zs.zyn.service.facade.collaborator.project.ProjectTechnologyCollaboratorService;

import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.collaborator.project.ProjectTechnologyTypeCollaboratorService ;

@Service
public class ProjectTechnologyCollaboratorServiceImpl implements ProjectTechnologyCollaboratorService {


    @Override
    public List<ProjectTechnology> findByProjectTechnologyTypeCodeDb() {
        return dao.findByProjectTechnologyTypeCode(ProjectTechnologyTypeConstant.BD);
    }
    @Override
    public List<ProjectTechnology> findByProjectTechnologyTypeCodeBack() {
        return dao.findByProjectTechnologyTypeCode(ProjectTechnologyTypeConstant.BACK);
    }

    @Override
    public List<ProjectTechnology> findByProjectTechnologyTypeCodeFront() {
        return dao.findByProjectTechnologyTypeCode(ProjectTechnologyTypeConstant.FRONT);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnology update(ProjectTechnology t) {
        ProjectTechnology loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTechnology.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTechnology findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTechnology findOrSave(ProjectTechnology t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjectTechnology result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectTechnology> findAll() {
        return dao.findAll();
    }

    public List<ProjectTechnology> findByCriteria(ProjectTechnologyCriteria criteria) {
        List<ProjectTechnology> content = null;
        if (criteria != null) {
            ProjectTechnologySpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTechnologySpecification constructSpecification(ProjectTechnologyCriteria criteria) {
        ProjectTechnologySpecification mySpecification =  (ProjectTechnologySpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTechnologySpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTechnology> findPaginatedByCriteria(ProjectTechnologyCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTechnologySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTechnologyCriteria criteria) {
        ProjectTechnologySpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjectTechnology> findByProjectTechnologyTypeCode(String code){
        return dao.findByProjectTechnologyTypeCode(code);
    }
    public int deleteByProjectTechnologyTypeCode(String code){
        return dao.deleteByProjectTechnologyTypeCode(code);
    }
    public long countByProjectTechnologyTypeCode(String code){
        return dao.countByProjectTechnologyTypeCode(code);
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
    public List<ProjectTechnology> delete(List<ProjectTechnology> list) {
		List<ProjectTechnology> result = new ArrayList();
        if (list != null) {
            for (ProjectTechnology t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnology create(ProjectTechnology t) {
        ProjectTechnology loaded = findByReferenceEntity(t);
        ProjectTechnology saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectTechnology findWithAssociatedLists(Long id){
        ProjectTechnology result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTechnology> update(List<ProjectTechnology> ts, boolean createIfNotExist) {
        List<ProjectTechnology> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTechnology t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTechnology loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectTechnology t, ProjectTechnology loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectTechnology findByReferenceEntity(ProjectTechnology t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ProjectTechnology t){
        if( t != null) {
            t.setProjectTechnologyType(projectTechnologyTypeService.findOrSave(t.getProjectTechnologyType()));
        }
    }



    public List<ProjectTechnology> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectTechnology>> getToBeSavedAndToBeDeleted(List<ProjectTechnology> oldList, List<ProjectTechnology> newList) {
        List<List<ProjectTechnology>> result = new ArrayList<>();
        List<ProjectTechnology> resultDelete = new ArrayList<>();
        List<ProjectTechnology> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectTechnology> oldList, List<ProjectTechnology> newList, List<ProjectTechnology> resultUpdateOrSave, List<ProjectTechnology> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectTechnology myOld = oldList.get(i);
                ProjectTechnology t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectTechnology myNew = newList.get(i);
                ProjectTechnology t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectTechnologyTypeCollaboratorService projectTechnologyTypeService ;

    public ProjectTechnologyCollaboratorServiceImpl(ProjectTechnologyDao dao) {
        this.dao = dao;
    }

    private ProjectTechnologyDao dao;
}
