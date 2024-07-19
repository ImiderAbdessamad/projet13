package ma.zs.zyn.service.impl.agent.support;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationStateCriteria;
import ma.zs.zyn.dao.facade.core.support.CustumorSupportConversationStateDao;
import ma.zs.zyn.dao.specification.core.support.CustumorSupportConversationStateSpecification;
import ma.zs.zyn.service.facade.agent.support.CustumorSupportConversationStateAgentService;
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
public class CustumorSupportConversationStateAgentServiceImpl implements CustumorSupportConversationStateAgentService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationState update(CustumorSupportConversationState t) {
        CustumorSupportConversationState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CustumorSupportConversationState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CustumorSupportConversationState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CustumorSupportConversationState findOrSave(CustumorSupportConversationState t) {
        if (t != null) {
            CustumorSupportConversationState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CustumorSupportConversationState> findAll() {
        return dao.findAll();
    }

    public List<CustumorSupportConversationState> findByCriteria(CustumorSupportConversationStateCriteria criteria) {
        List<CustumorSupportConversationState> content = null;
        if (criteria != null) {
            CustumorSupportConversationStateSpecification mySpecification = constructSpecification(criteria);
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


    private CustumorSupportConversationStateSpecification constructSpecification(CustumorSupportConversationStateCriteria criteria) {
        CustumorSupportConversationStateSpecification mySpecification =  (CustumorSupportConversationStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(CustumorSupportConversationStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<CustumorSupportConversationState> findPaginatedByCriteria(CustumorSupportConversationStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        CustumorSupportConversationStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CustumorSupportConversationStateCriteria criteria) {
        CustumorSupportConversationStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<CustumorSupportConversationState> delete(List<CustumorSupportConversationState> list) {
		List<CustumorSupportConversationState> result = new ArrayList();
        if (list != null) {
            for (CustumorSupportConversationState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationState create(CustumorSupportConversationState t) {
        CustumorSupportConversationState loaded = findByReferenceEntity(t);
        CustumorSupportConversationState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CustumorSupportConversationState findWithAssociatedLists(Long id){
        CustumorSupportConversationState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CustumorSupportConversationState> update(List<CustumorSupportConversationState> ts, boolean createIfNotExist) {
        List<CustumorSupportConversationState> result = new ArrayList<>();
        if (ts != null) {
            for (CustumorSupportConversationState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CustumorSupportConversationState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CustumorSupportConversationState t, CustumorSupportConversationState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CustumorSupportConversationState findByReferenceEntity(CustumorSupportConversationState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CustumorSupportConversationState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CustumorSupportConversationState>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationState> oldList, List<CustumorSupportConversationState> newList) {
        List<List<CustumorSupportConversationState>> result = new ArrayList<>();
        List<CustumorSupportConversationState> resultDelete = new ArrayList<>();
        List<CustumorSupportConversationState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CustumorSupportConversationState> oldList, List<CustumorSupportConversationState> newList, List<CustumorSupportConversationState> resultUpdateOrSave, List<CustumorSupportConversationState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CustumorSupportConversationState myOld = oldList.get(i);
                CustumorSupportConversationState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CustumorSupportConversationState myNew = newList.get(i);
                CustumorSupportConversationState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CustumorSupportConversationStateAgentServiceImpl(CustumorSupportConversationStateDao dao) {
        this.dao = dao;
    }

    private CustumorSupportConversationStateDao dao;
}
