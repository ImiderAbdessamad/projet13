package ma.zs.zyn.service.impl.collaborator.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyProfileCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectTechnologyProfileDao;
import ma.zs.zyn.dao.specification.core.project.ProjectTechnologyProfileSpecification;
import ma.zs.zyn.service.facade.collaborator.project.ProjectTechnologyProfileCollaboratorService;
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
public class ProjectTechnologyProfileCollaboratorServiceImpl implements ProjectTechnologyProfileCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnologyProfile update(ProjectTechnologyProfile t) {
        ProjectTechnologyProfile loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTechnologyProfile.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTechnologyProfile findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTechnologyProfile findOrSave(ProjectTechnologyProfile t) {
        if (t != null) {
            ProjectTechnologyProfile result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectTechnologyProfile> findAll() {
        return dao.findAll();
    }

    public List<ProjectTechnologyProfile> findByCriteria(ProjectTechnologyProfileCriteria criteria) {
        List<ProjectTechnologyProfile> content = null;
        if (criteria != null) {
            ProjectTechnologyProfileSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTechnologyProfileSpecification constructSpecification(ProjectTechnologyProfileCriteria criteria) {
        ProjectTechnologyProfileSpecification mySpecification =  (ProjectTechnologyProfileSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTechnologyProfileSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTechnologyProfile> findPaginatedByCriteria(ProjectTechnologyProfileCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTechnologyProfileSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTechnologyProfileCriteria criteria) {
        ProjectTechnologyProfileSpecification mySpecification = constructSpecification(criteria);
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
    public List<ProjectTechnologyProfile> delete(List<ProjectTechnologyProfile> list) {
		List<ProjectTechnologyProfile> result = new ArrayList();
        if (list != null) {
            for (ProjectTechnologyProfile t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTechnologyProfile create(ProjectTechnologyProfile t) {
        ProjectTechnologyProfile loaded = findByReferenceEntity(t);
        ProjectTechnologyProfile saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectTechnologyProfile findWithAssociatedLists(Long id){
        ProjectTechnologyProfile result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTechnologyProfile> update(List<ProjectTechnologyProfile> ts, boolean createIfNotExist) {
        List<ProjectTechnologyProfile> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTechnologyProfile t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTechnologyProfile loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectTechnologyProfile t, ProjectTechnologyProfile loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectTechnologyProfile findByReferenceEntity(ProjectTechnologyProfile t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectTechnologyProfile> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectTechnologyProfile>> getToBeSavedAndToBeDeleted(List<ProjectTechnologyProfile> oldList, List<ProjectTechnologyProfile> newList) {
        List<List<ProjectTechnologyProfile>> result = new ArrayList<>();
        List<ProjectTechnologyProfile> resultDelete = new ArrayList<>();
        List<ProjectTechnologyProfile> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectTechnologyProfile> oldList, List<ProjectTechnologyProfile> newList, List<ProjectTechnologyProfile> resultUpdateOrSave, List<ProjectTechnologyProfile> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectTechnologyProfile myOld = oldList.get(i);
                ProjectTechnologyProfile t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectTechnologyProfile myNew = newList.get(i);
                ProjectTechnologyProfile t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public ProjectTechnologyProfile findByCode(ProjectTechnologyProfile projectTechnologyProfile) {
        return dao.findByCode(projectTechnologyProfile.getCode());
    }


    public ProjectTechnologyProfileCollaboratorServiceImpl(ProjectTechnologyProfileDao dao) {
        this.dao = dao;
    }

    private ProjectTechnologyProfileDao dao;
}
