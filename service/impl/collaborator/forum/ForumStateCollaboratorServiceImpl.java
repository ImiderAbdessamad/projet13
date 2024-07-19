package ma.zs.zyn.service.impl.collaborator.forum;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.dao.criteria.core.forum.ForumStateCriteria;
import ma.zs.zyn.dao.facade.core.forum.ForumStateDao;
import ma.zs.zyn.dao.specification.core.forum.ForumStateSpecification;
import ma.zs.zyn.service.facade.collaborator.forum.ForumStateCollaboratorService;
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
public class ForumStateCollaboratorServiceImpl implements ForumStateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumState update(ForumState t) {
        ForumState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ForumState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ForumState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ForumState findOrSave(ForumState t) {
        if (t != null) {
            ForumState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ForumState> findAll() {
        return dao.findAll();
    }

    public List<ForumState> findByCriteria(ForumStateCriteria criteria) {
        List<ForumState> content = null;
        if (criteria != null) {
            ForumStateSpecification mySpecification = constructSpecification(criteria);
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


    private ForumStateSpecification constructSpecification(ForumStateCriteria criteria) {
        ForumStateSpecification mySpecification =  (ForumStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ForumStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ForumState> findPaginatedByCriteria(ForumStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ForumStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ForumStateCriteria criteria) {
        ForumStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<ForumState> delete(List<ForumState> list) {
		List<ForumState> result = new ArrayList();
        if (list != null) {
            for (ForumState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumState create(ForumState t) {
        ForumState loaded = findByReferenceEntity(t);
        ForumState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ForumState findWithAssociatedLists(Long id){
        ForumState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ForumState> update(List<ForumState> ts, boolean createIfNotExist) {
        List<ForumState> result = new ArrayList<>();
        if (ts != null) {
            for (ForumState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ForumState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ForumState t, ForumState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ForumState findByReferenceEntity(ForumState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ForumState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ForumState>> getToBeSavedAndToBeDeleted(List<ForumState> oldList, List<ForumState> newList) {
        List<List<ForumState>> result = new ArrayList<>();
        List<ForumState> resultDelete = new ArrayList<>();
        List<ForumState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ForumState> oldList, List<ForumState> newList, List<ForumState> resultUpdateOrSave, List<ForumState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ForumState myOld = oldList.get(i);
                ForumState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ForumState myNew = newList.get(i);
                ForumState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ForumStateCollaboratorServiceImpl(ForumStateDao dao) {
        this.dao = dao;
    }

    private ForumStateDao dao;
}
