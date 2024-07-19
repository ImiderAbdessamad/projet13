package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoTypeCriteria;
import ma.zs.zyn.dao.facade.core.project.RemoteRepoTypeDao;
import ma.zs.zyn.dao.specification.core.project.RemoteRepoTypeSpecification;
import ma.zs.zyn.service.facade.admin.project.RemoteRepoTypeAdminService;
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
public class RemoteRepoTypeAdminServiceImpl implements RemoteRepoTypeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RemoteRepoType update(RemoteRepoType t) {
        RemoteRepoType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RemoteRepoType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RemoteRepoType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RemoteRepoType findOrSave(RemoteRepoType t) {
        if (t != null) {
            RemoteRepoType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RemoteRepoType> findAll() {
        return dao.findAll();
    }

    public List<RemoteRepoType> findByCriteria(RemoteRepoTypeCriteria criteria) {
        List<RemoteRepoType> content = null;
        if (criteria != null) {
            RemoteRepoTypeSpecification mySpecification = constructSpecification(criteria);
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


    private RemoteRepoTypeSpecification constructSpecification(RemoteRepoTypeCriteria criteria) {
        RemoteRepoTypeSpecification mySpecification =  (RemoteRepoTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(RemoteRepoTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<RemoteRepoType> findPaginatedByCriteria(RemoteRepoTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        RemoteRepoTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RemoteRepoTypeCriteria criteria) {
        RemoteRepoTypeSpecification mySpecification = constructSpecification(criteria);
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
    public List<RemoteRepoType> delete(List<RemoteRepoType> list) {
		List<RemoteRepoType> result = new ArrayList();
        if (list != null) {
            for (RemoteRepoType t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RemoteRepoType create(RemoteRepoType t) {
        RemoteRepoType loaded = findByReferenceEntity(t);
        RemoteRepoType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RemoteRepoType findWithAssociatedLists(Long id){
        RemoteRepoType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RemoteRepoType> update(List<RemoteRepoType> ts, boolean createIfNotExist) {
        List<RemoteRepoType> result = new ArrayList<>();
        if (ts != null) {
            for (RemoteRepoType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RemoteRepoType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RemoteRepoType t, RemoteRepoType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RemoteRepoType findByReferenceEntity(RemoteRepoType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<RemoteRepoType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RemoteRepoType>> getToBeSavedAndToBeDeleted(List<RemoteRepoType> oldList, List<RemoteRepoType> newList) {
        List<List<RemoteRepoType>> result = new ArrayList<>();
        List<RemoteRepoType> resultDelete = new ArrayList<>();
        List<RemoteRepoType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RemoteRepoType> oldList, List<RemoteRepoType> newList, List<RemoteRepoType> resultUpdateOrSave, List<RemoteRepoType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RemoteRepoType myOld = oldList.get(i);
                RemoteRepoType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RemoteRepoType myNew = newList.get(i);
                RemoteRepoType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public RemoteRepoTypeAdminServiceImpl(RemoteRepoTypeDao dao) {
        this.dao = dao;
    }

    private RemoteRepoTypeDao dao;
}
