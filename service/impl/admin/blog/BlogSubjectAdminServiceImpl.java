package ma.zs.zyn.service.impl.admin.blog;


import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.dao.criteria.core.blog.BlogSubjectCriteria;
import ma.zs.zyn.dao.facade.core.blog.BlogSubjectDao;
import ma.zs.zyn.dao.specification.core.blog.BlogSubjectSpecification;
import ma.zs.zyn.service.facade.admin.blog.BlogSubjectAdminService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogSubjectCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;

@Service
public class BlogSubjectAdminServiceImpl implements BlogSubjectAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogSubject update(BlogSubject t) {
        BlogSubject loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{BlogSubject.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public BlogSubject findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public BlogSubject findOrSave(BlogSubject t) {
        if (t != null) {
            BlogSubject result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<BlogSubject> findAll() {
        return dao.findAll();
    }

    public List<BlogSubject> findByCriteria(BlogSubjectCriteria criteria) {
        List<BlogSubject> content = null;
        if (criteria != null) {
            BlogSubjectSpecification mySpecification = constructSpecification(criteria);
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


    private BlogSubjectSpecification constructSpecification(BlogSubjectCriteria criteria) {
        BlogSubjectSpecification mySpecification =  (BlogSubjectSpecification) RefelexivityUtil.constructObjectUsingOneParam(BlogSubjectSpecification.class, criteria);
        return mySpecification;
    }

    public List<BlogSubject> findPaginatedByCriteria(BlogSubjectCriteria criteria, int page, int pageSize, String order, String sortField) {
        BlogSubjectSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BlogSubjectCriteria criteria) {
        BlogSubjectSpecification mySpecification = constructSpecification(criteria);
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
    public List<BlogSubject> delete(List<BlogSubject> list) {
		List<BlogSubject> result = new ArrayList();
        if (list != null) {
            for (BlogSubject t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogSubject create(BlogSubject t) {
        BlogSubject loaded = findByReferenceEntity(t);
        BlogSubject saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public BlogSubject findWithAssociatedLists(Long id){
        BlogSubject result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BlogSubject> update(List<BlogSubject> ts, boolean createIfNotExist) {
        List<BlogSubject> result = new ArrayList<>();
        if (ts != null) {
            for (BlogSubject t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    BlogSubject loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, BlogSubject t, BlogSubject loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public BlogSubject findByReferenceEntity(BlogSubject t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<BlogSubject> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<BlogSubject>> getToBeSavedAndToBeDeleted(List<BlogSubject> oldList, List<BlogSubject> newList) {
        List<List<BlogSubject>> result = new ArrayList<>();
        List<BlogSubject> resultDelete = new ArrayList<>();
        List<BlogSubject> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<BlogSubject> oldList, List<BlogSubject> newList, List<BlogSubject> resultUpdateOrSave, List<BlogSubject> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                BlogSubject myOld = oldList.get(i);
                BlogSubject t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                BlogSubject myNew = newList.get(i);
                BlogSubject t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public BlogSubjectAdminServiceImpl(BlogSubjectDao dao) {
        this.dao = dao;
    }

    private BlogSubjectDao dao;
}
