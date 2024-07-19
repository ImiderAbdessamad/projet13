package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.YamlFileCriteria;
import ma.zs.zyn.dao.facade.core.project.YamlFileDao;
import ma.zs.zyn.dao.specification.core.project.YamlFileSpecification;
import ma.zs.zyn.service.facade.admin.project.YamlFileAdminService;
import ma.zs.zyn.zynerator.security.service.facade.UserService;

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

import ma.zs.zyn.service.facade.admin.project.ProjectAdminService ;

@Service
public class YamlFileAdminServiceImpl implements YamlFileAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public YamlFile update(YamlFile t) {
        YamlFile loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{YamlFile.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public YamlFile findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public YamlFile findOrSave(YamlFile t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            YamlFile result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<YamlFile> findAll() {
        return dao.findAll();
    }

    public List<YamlFile> findByCriteria(YamlFileCriteria criteria) {
        List<YamlFile> content = null;
        if (criteria != null) {
            YamlFileSpecification mySpecification = constructSpecification(criteria);
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


    private YamlFileSpecification constructSpecification(YamlFileCriteria criteria) {
        YamlFileSpecification mySpecification =  (YamlFileSpecification) RefelexivityUtil.constructObjectUsingOneParam(YamlFileSpecification.class, criteria);
        return mySpecification;
    }

    public List<YamlFile> findPaginatedByCriteria(YamlFileCriteria criteria, int page, int pageSize, String order, String sortField) {
        YamlFileSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(YamlFileCriteria criteria) {
        YamlFileSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<YamlFile> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectId(Long id){
        return dao.countByProjectId(id);
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
    public List<YamlFile> delete(List<YamlFile> list) {
		List<YamlFile> result = new ArrayList();
        if (list != null) {
            for (YamlFile t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public YamlFile create(YamlFile t) {
        YamlFile loaded = findByReferenceEntity(t);
        YamlFile saved;
        if (loaded == null) {
            if (t.getProject().getCollaborator() == null || t.getProject().getCollaborator().getUsername() == null)
                return null;

            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public YamlFile findWithAssociatedLists(Long id){
        YamlFile result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<YamlFile> update(List<YamlFile> ts, boolean createIfNotExist) {
        List<YamlFile> result = new ArrayList<>();
        if (ts != null) {
            for (YamlFile t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    YamlFile loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, YamlFile t, YamlFile loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public YamlFile findByReferenceEntity(YamlFile t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(YamlFile t){
        if( t != null) {
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<YamlFile> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<YamlFile>> getToBeSavedAndToBeDeleted(List<YamlFile> oldList, List<YamlFile> newList) {
        List<List<YamlFile>> result = new ArrayList<>();
        List<YamlFile> resultDelete = new ArrayList<>();
        List<YamlFile> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<YamlFile> oldList, List<YamlFile> newList, List<YamlFile> resultUpdateOrSave, List<YamlFile> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                YamlFile myOld = oldList.get(i);
                YamlFile t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                YamlFile myNew = newList.get(i);
                YamlFile t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private UserService userService;

    public YamlFileAdminServiceImpl(YamlFileDao dao) {
        this.dao = dao;
    }

    private YamlFileDao dao;
}
