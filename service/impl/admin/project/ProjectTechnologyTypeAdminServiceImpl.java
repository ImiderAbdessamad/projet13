package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyTypeCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectTechnologyTypeDao;
import ma.zs.zyn.dao.specification.core.project.ProjectTechnologyTypeSpecification;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyTypeAdminService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
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


import java.util.List;
@Service
public class ProjectTechnologyTypeAdminServiceImpl implements ProjectTechnologyTypeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnologyType update(ProjectTechnologyType t) {
        ProjectTechnologyType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTechnologyType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTechnologyType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTechnologyType findOrSave(ProjectTechnologyType t) {
        if (t != null) {
            ProjectTechnologyType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectTechnologyType> findAll() {
        return dao.findAll();
    }

    public List<ProjectTechnologyType> findByCriteria(ProjectTechnologyTypeCriteria criteria) {
        List<ProjectTechnologyType> content = null;
        if (criteria != null) {
            ProjectTechnologyTypeSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTechnologyTypeSpecification constructSpecification(ProjectTechnologyTypeCriteria criteria) {
        ProjectTechnologyTypeSpecification mySpecification =  (ProjectTechnologyTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTechnologyTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTechnologyType> findPaginatedByCriteria(ProjectTechnologyTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTechnologyTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTechnologyTypeCriteria criteria) {
        ProjectTechnologyTypeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<ProjectTechnologyType> delete(List<ProjectTechnologyType> list) {
		List<ProjectTechnologyType> result = new ArrayList();
        if (list != null) {
            for (ProjectTechnologyType t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnologyType create(ProjectTechnologyType t) {
        ProjectTechnologyType loaded = findByReferenceEntity(t);
        ProjectTechnologyType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectTechnologyType findWithAssociatedLists(Long id){
        ProjectTechnologyType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTechnologyType> update(List<ProjectTechnologyType> ts, boolean createIfNotExist) {
        List<ProjectTechnologyType> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTechnologyType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTechnologyType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectTechnologyType t, ProjectTechnologyType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectTechnologyType findByReferenceEntity(ProjectTechnologyType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectTechnologyType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectTechnologyType>> getToBeSavedAndToBeDeleted(List<ProjectTechnologyType> oldList, List<ProjectTechnologyType> newList) {
        List<List<ProjectTechnologyType>> result = new ArrayList<>();
        List<ProjectTechnologyType> resultDelete = new ArrayList<>();
        List<ProjectTechnologyType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectTechnologyType> oldList, List<ProjectTechnologyType> newList, List<ProjectTechnologyType> resultUpdateOrSave, List<ProjectTechnologyType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectTechnologyType myOld = oldList.get(i);
                ProjectTechnologyType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectTechnologyType myNew = newList.get(i);
                ProjectTechnologyType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProjectTechnologyTypeAdminServiceImpl(ProjectTechnologyTypeDao dao) {
        this.dao = dao;
    }

    private ProjectTechnologyTypeDao dao;
}
