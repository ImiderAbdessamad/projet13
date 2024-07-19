package ma.zs.zyn.service.impl.admin.forum;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.dao.criteria.core.forum.ForumSubjectCriteria;
import ma.zs.zyn.dao.facade.core.forum.ForumSubjectDao;
import ma.zs.zyn.dao.specification.core.forum.ForumSubjectSpecification;
import ma.zs.zyn.service.facade.admin.forum.ForumSubjectAdminService;
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
public class ForumSubjectAdminServiceImpl implements ForumSubjectAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumSubject update(ForumSubject t) {
        ForumSubject loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ForumSubject.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ForumSubject findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ForumSubject findOrSave(ForumSubject t) {
        if (t != null) {
            ForumSubject result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ForumSubject> findAll() {
        return dao.findAll();
    }

    public List<ForumSubject> findByCriteria(ForumSubjectCriteria criteria) {
        List<ForumSubject> content = null;
        if (criteria != null) {
            ForumSubjectSpecification mySpecification = constructSpecification(criteria);
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


    private ForumSubjectSpecification constructSpecification(ForumSubjectCriteria criteria) {
        ForumSubjectSpecification mySpecification =  (ForumSubjectSpecification) RefelexivityUtil.constructObjectUsingOneParam(ForumSubjectSpecification.class, criteria);
        return mySpecification;
    }

    public List<ForumSubject> findPaginatedByCriteria(ForumSubjectCriteria criteria, int page, int pageSize, String order, String sortField) {
        ForumSubjectSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ForumSubjectCriteria criteria) {
        ForumSubjectSpecification mySpecification = constructSpecification(criteria);
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
    public List<ForumSubject> delete(List<ForumSubject> list) {
		List<ForumSubject> result = new ArrayList();
        if (list != null) {
            for (ForumSubject t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumSubject create(ForumSubject t) {
        ForumSubject loaded = findByReferenceEntity(t);
        ForumSubject saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ForumSubject findWithAssociatedLists(Long id){
        ForumSubject result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ForumSubject> update(List<ForumSubject> ts, boolean createIfNotExist) {
        List<ForumSubject> result = new ArrayList<>();
        if (ts != null) {
            for (ForumSubject t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ForumSubject loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ForumSubject t, ForumSubject loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ForumSubject findByReferenceEntity(ForumSubject t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ForumSubject> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ForumSubject>> getToBeSavedAndToBeDeleted(List<ForumSubject> oldList, List<ForumSubject> newList) {
        List<List<ForumSubject>> result = new ArrayList<>();
        List<ForumSubject> resultDelete = new ArrayList<>();
        List<ForumSubject> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ForumSubject> oldList, List<ForumSubject> newList, List<ForumSubject> resultUpdateOrSave, List<ForumSubject> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ForumSubject myOld = oldList.get(i);
                ForumSubject t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ForumSubject myNew = newList.get(i);
                ForumSubject t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ForumSubjectAdminServiceImpl(ForumSubjectDao dao) {
        this.dao = dao;
    }

    private ForumSubjectDao dao;
}
