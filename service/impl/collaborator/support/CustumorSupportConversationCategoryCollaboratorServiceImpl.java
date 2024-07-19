package ma.zs.zyn.service.impl.collaborator.support;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCategoryCriteria;
import ma.zs.zyn.dao.facade.core.support.CustumorSupportConversationCategoryDao;
import ma.zs.zyn.dao.specification.core.support.CustumorSupportConversationCategorySpecification;
import ma.zs.zyn.service.facade.collaborator.support.CustumorSupportConversationCategoryCollaboratorService;
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
public class CustumorSupportConversationCategoryCollaboratorServiceImpl implements CustumorSupportConversationCategoryCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationCategory update(CustumorSupportConversationCategory t) {
        CustumorSupportConversationCategory loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CustumorSupportConversationCategory.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CustumorSupportConversationCategory findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CustumorSupportConversationCategory findOrSave(CustumorSupportConversationCategory t) {
        if (t != null) {
            CustumorSupportConversationCategory result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CustumorSupportConversationCategory> findAll() {
        return dao.findAll();
    }

    public List<CustumorSupportConversationCategory> findByCriteria(CustumorSupportConversationCategoryCriteria criteria) {
        List<CustumorSupportConversationCategory> content = null;
        if (criteria != null) {
            CustumorSupportConversationCategorySpecification mySpecification = constructSpecification(criteria);
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


    private CustumorSupportConversationCategorySpecification constructSpecification(CustumorSupportConversationCategoryCriteria criteria) {
        CustumorSupportConversationCategorySpecification mySpecification =  (CustumorSupportConversationCategorySpecification) RefelexivityUtil.constructObjectUsingOneParam(CustumorSupportConversationCategorySpecification.class, criteria);
        return mySpecification;
    }

    public List<CustumorSupportConversationCategory> findPaginatedByCriteria(CustumorSupportConversationCategoryCriteria criteria, int page, int pageSize, String order, String sortField) {
        CustumorSupportConversationCategorySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CustumorSupportConversationCategoryCriteria criteria) {
        CustumorSupportConversationCategorySpecification mySpecification = constructSpecification(criteria);
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
    public List<CustumorSupportConversationCategory> delete(List<CustumorSupportConversationCategory> list) {
		List<CustumorSupportConversationCategory> result = new ArrayList();
        if (list != null) {
            for (CustumorSupportConversationCategory t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationCategory create(CustumorSupportConversationCategory t) {
        CustumorSupportConversationCategory loaded = findByReferenceEntity(t);
        CustumorSupportConversationCategory saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CustumorSupportConversationCategory findWithAssociatedLists(Long id){
        CustumorSupportConversationCategory result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CustumorSupportConversationCategory> update(List<CustumorSupportConversationCategory> ts, boolean createIfNotExist) {
        List<CustumorSupportConversationCategory> result = new ArrayList<>();
        if (ts != null) {
            for (CustumorSupportConversationCategory t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CustumorSupportConversationCategory loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CustumorSupportConversationCategory t, CustumorSupportConversationCategory loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CustumorSupportConversationCategory findByReferenceEntity(CustumorSupportConversationCategory t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CustumorSupportConversationCategory> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CustumorSupportConversationCategory>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationCategory> oldList, List<CustumorSupportConversationCategory> newList) {
        List<List<CustumorSupportConversationCategory>> result = new ArrayList<>();
        List<CustumorSupportConversationCategory> resultDelete = new ArrayList<>();
        List<CustumorSupportConversationCategory> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CustumorSupportConversationCategory> oldList, List<CustumorSupportConversationCategory> newList, List<CustumorSupportConversationCategory> resultUpdateOrSave, List<CustumorSupportConversationCategory> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CustumorSupportConversationCategory myOld = oldList.get(i);
                CustumorSupportConversationCategory t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CustumorSupportConversationCategory myNew = newList.get(i);
                CustumorSupportConversationCategory t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CustumorSupportConversationCategoryCollaboratorServiceImpl(CustumorSupportConversationCategoryDao dao) {
        this.dao = dao;
    }

    private CustumorSupportConversationCategoryDao dao;
}
