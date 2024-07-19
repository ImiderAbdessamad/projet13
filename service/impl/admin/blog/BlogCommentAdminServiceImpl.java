package ma.zs.zyn.service.impl.admin.blog;


import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.dao.criteria.core.blog.BlogCommentCriteria;
import ma.zs.zyn.dao.facade.core.blog.BlogCommentDao;
import ma.zs.zyn.dao.specification.core.blog.BlogCommentSpecification;
import ma.zs.zyn.service.facade.admin.blog.BlogCommentAdminService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogCommentCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BlogCommentAdminServiceImpl implements BlogCommentAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogComment update(BlogComment t) {
        BlogComment loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{BlogComment.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public BlogComment findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public BlogComment findOrSave(BlogComment t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            BlogComment result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<BlogComment> findAll() {
        return dao.findAll();
    }

    public List<BlogComment> findByCriteria(BlogCommentCriteria criteria) {
        List<BlogComment> content = null;
        if (criteria != null) {
            BlogCommentSpecification mySpecification = constructSpecification(criteria);
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


    private BlogCommentSpecification constructSpecification(BlogCommentCriteria criteria) {
        BlogCommentSpecification mySpecification =  (BlogCommentSpecification) RefelexivityUtil.constructObjectUsingOneParam(BlogCommentSpecification.class, criteria);
        return mySpecification;
    }

    public List<BlogComment> findPaginatedByCriteria(BlogCommentCriteria criteria, int page, int pageSize, String order, String sortField) {
        BlogCommentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BlogCommentCriteria criteria) {
        BlogCommentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<BlogComment> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<BlogComment> findByBlogId(Long id){
        return dao.findByBlogId(id);
    }
    public int deleteByBlogId(Long id){
        return dao.deleteByBlogId(id);
    }
    public long countByBlogId(Long id){
        return dao.countByBlogId(id);
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
    public List<BlogComment> delete(List<BlogComment> list) {
		List<BlogComment> result = new ArrayList();
        if (list != null) {
            for (BlogComment t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BlogComment create(BlogComment t) {
        BlogComment loaded = findByReferenceEntity(t);
        BlogComment saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public BlogComment findWithAssociatedLists(Long id){
        BlogComment result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BlogComment> update(List<BlogComment> ts, boolean createIfNotExist) {
        List<BlogComment> result = new ArrayList<>();
        if (ts != null) {
            for (BlogComment t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    BlogComment loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, BlogComment t, BlogComment loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public BlogComment findByReferenceEntity(BlogComment t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(BlogComment t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setBlog(blogService.findOrSave(t.getBlog()));
        }
    }



    public List<BlogComment> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<BlogComment>> getToBeSavedAndToBeDeleted(List<BlogComment> oldList, List<BlogComment> newList) {
        List<List<BlogComment>> result = new ArrayList<>();
        List<BlogComment> resultDelete = new ArrayList<>();
        List<BlogComment> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<BlogComment> oldList, List<BlogComment> newList, List<BlogComment> resultUpdateOrSave, List<BlogComment> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                BlogComment myOld = oldList.get(i);
                BlogComment t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                BlogComment myNew = newList.get(i);
                BlogComment t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CollaboratorCollaboratorService collaboratorService ;
    @Autowired
    private BlogCollaboratorService blogService ;

    public BlogCommentAdminServiceImpl(BlogCommentDao dao) {
        this.dao = dao;
    }

    private BlogCommentDao dao;
}
