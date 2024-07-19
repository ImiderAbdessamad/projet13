package ma.zs.zyn.service.impl.collaborator.blog;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.blog.BlogState;
import ma.zs.zyn.dao.criteria.core.blog.BlogStateCriteria;
import ma.zs.zyn.dao.facade.core.blog.BlogStateDao;
import ma.zs.zyn.dao.specification.core.blog.BlogStateSpecification;
import ma.zs.zyn.service.facade.collaborator.blog.BlogStateCollaboratorService;
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
public class BlogStateCollaboratorServiceImpl implements BlogStateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogState update(BlogState t) {
        BlogState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{BlogState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public BlogState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public BlogState findOrSave(BlogState t) {
        if (t != null) {
            BlogState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<BlogState> findAll() {
        return dao.findAll();
    }

    public List<BlogState> findByCriteria(BlogStateCriteria criteria) {
        List<BlogState> content = null;
        if (criteria != null) {
            BlogStateSpecification mySpecification = constructSpecification(criteria);
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


    private BlogStateSpecification constructSpecification(BlogStateCriteria criteria) {
        BlogStateSpecification mySpecification =  (BlogStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(BlogStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<BlogState> findPaginatedByCriteria(BlogStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        BlogStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BlogStateCriteria criteria) {
        BlogStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<BlogState> delete(List<BlogState> list) {
		List<BlogState> result = new ArrayList();
        if (list != null) {
            for (BlogState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogState create(BlogState t) {
        BlogState loaded = findByReferenceEntity(t);
        BlogState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public BlogState findWithAssociatedLists(Long id){
        BlogState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BlogState> update(List<BlogState> ts, boolean createIfNotExist) {
        List<BlogState> result = new ArrayList<>();
        if (ts != null) {
            for (BlogState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    BlogState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, BlogState t, BlogState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public BlogState findByReferenceEntity(BlogState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<BlogState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<BlogState>> getToBeSavedAndToBeDeleted(List<BlogState> oldList, List<BlogState> newList) {
        List<List<BlogState>> result = new ArrayList<>();
        List<BlogState> resultDelete = new ArrayList<>();
        List<BlogState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<BlogState> oldList, List<BlogState> newList, List<BlogState> resultUpdateOrSave, List<BlogState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                BlogState myOld = oldList.get(i);
                BlogState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                BlogState myNew = newList.get(i);
                BlogState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public BlogStateCollaboratorServiceImpl(BlogStateDao dao) {
        this.dao = dao;
    }

    private BlogStateDao dao;
}
