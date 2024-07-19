package ma.zs.zyn.service.impl.admin.blog;


import ma.zs.zyn.bean.core.blog.Blog;
import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.dao.criteria.core.blog.BlogCriteria;
import ma.zs.zyn.dao.facade.core.blog.BlogDao;
import ma.zs.zyn.dao.specification.core.blog.BlogSpecification;
import ma.zs.zyn.service.facade.admin.blog.BlogAdminService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogCommentCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogStateCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.blog.BlogSubjectCollaboratorService;
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

import static ma.zs.zyn.zynerator.util.ListUtil.*;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Blog update(Blog t) {
        Blog loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Blog.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Blog findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Blog findOrSave(Blog t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Blog result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Blog> findAll() {
        return dao.findAll();
    }

    public List<Blog> findByCriteria(BlogCriteria criteria) {
        List<Blog> content = null;
        if (criteria != null) {
            BlogSpecification mySpecification = constructSpecification(criteria);
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


    private BlogSpecification constructSpecification(BlogCriteria criteria) {
        BlogSpecification mySpecification =  (BlogSpecification) RefelexivityUtil.constructObjectUsingOneParam(BlogSpecification.class, criteria);
        return mySpecification;
    }

    public List<Blog> findPaginatedByCriteria(BlogCriteria criteria, int page, int pageSize, String order, String sortField) {
        BlogSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BlogCriteria criteria) {
        BlogSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Blog> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<Blog> findByBlogSubjectId(Long id){
        return dao.findByBlogSubjectId(id);
    }
    public int deleteByBlogSubjectId(Long id){
        return dao.deleteByBlogSubjectId(id);
    }
    public long countByBlogSubjectCode(String code){
        return dao.countByBlogSubjectCode(code);
    }
    public List<Blog> findByBlogStateCode(String code){
        return dao.findByBlogStateCode(code);
    }
    public int deleteByBlogStateCode(String code){
        return dao.deleteByBlogStateCode(code);
    }
    public long countByBlogStateCode(String code){
        return dao.countByBlogStateCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        blogCommentService.deleteByBlogId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Blog> delete(List<Blog> list) {
		List<Blog> result = new ArrayList();
        if (list != null) {
            for (Blog t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Blog create(Blog t) {
        Blog loaded = findByReferenceEntity(t);
        Blog saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getBlogComments() != null) {
                t.getBlogComments().forEach(element-> {
                    element.setBlog(saved);
                    blogCommentService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Blog findWithAssociatedLists(Long id){
        Blog result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setBlogComments(blogCommentService.findByBlogId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Blog> update(List<Blog> ts, boolean createIfNotExist) {
        List<Blog> result = new ArrayList<>();
        if (ts != null) {
            for (Blog t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Blog loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Blog t, Blog loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Blog blog){
    if(blog !=null && blog.getId() != null){
        List<List<BlogComment>> resultBlogComments= blogCommentService.getToBeSavedAndToBeDeleted(blogCommentService.findByBlogId(blog.getId()),blog.getBlogComments());
            blogCommentService.delete(resultBlogComments.get(1));
        emptyIfNull(resultBlogComments.get(0)).forEach(e -> e.setBlog(blog));
        blogCommentService.update(resultBlogComments.get(0),true);
        }
    }








    public Blog findByReferenceEntity(Blog t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Blog t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setBlogSubject(blogSubjectService.findOrSave(t.getBlogSubject()));
        }
    }



    public List<Blog> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Blog>> getToBeSavedAndToBeDeleted(List<Blog> oldList, List<Blog> newList) {
        List<List<Blog>> result = new ArrayList<>();
        List<Blog> resultDelete = new ArrayList<>();
        List<Blog> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Blog> oldList, List<Blog> newList, List<Blog> resultUpdateOrSave, List<Blog> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Blog myOld = oldList.get(i);
                Blog t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Blog myNew = newList.get(i);
                Blog t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private BlogSubjectCollaboratorService blogSubjectService ;
    @Autowired
    private BlogCommentCollaboratorService blogCommentService ;
    @Autowired
    private BlogStateCollaboratorService blogStateService ;

    public BlogAdminServiceImpl(BlogDao dao) {
        this.dao = dao;
    }

    private BlogDao dao;
}
