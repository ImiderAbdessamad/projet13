package ma.zs.zyn.service.impl.admin.forum;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.forum.Forum;
import ma.zs.zyn.dao.criteria.core.forum.ForumCriteria;
import ma.zs.zyn.dao.facade.core.forum.ForumDao;
import ma.zs.zyn.dao.specification.core.forum.ForumSpecification;
import ma.zs.zyn.service.facade.admin.forum.ForumAdminService;
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

import ma.zs.zyn.service.facade.admin.collaborator.CollaboratorAdminService ;
import ma.zs.zyn.bean.core.collaborator.Collaborator ;
import ma.zs.zyn.service.facade.admin.forum.ForumSubjectAdminService ;
import ma.zs.zyn.bean.core.forum.ForumSubject ;
import ma.zs.zyn.service.facade.admin.forum.ForumStateAdminService ;
import ma.zs.zyn.bean.core.forum.ForumState ;
import ma.zs.zyn.service.facade.admin.forum.ForumCommentAdminService ;
import ma.zs.zyn.bean.core.forum.ForumComment ;

import java.util.List;
@Service
public class ForumAdminServiceImpl implements ForumAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Forum update(Forum t) {
        Forum loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Forum.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Forum findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Forum findOrSave(Forum t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Forum result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Forum> findAll() {
        return dao.findAll();
    }

    public List<Forum> findByCriteria(ForumCriteria criteria) {
        List<Forum> content = null;
        if (criteria != null) {
            ForumSpecification mySpecification = constructSpecification(criteria);
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


    private ForumSpecification constructSpecification(ForumCriteria criteria) {
        ForumSpecification mySpecification =  (ForumSpecification) RefelexivityUtil.constructObjectUsingOneParam(ForumSpecification.class, criteria);
        return mySpecification;
    }

    public List<Forum> findPaginatedByCriteria(ForumCriteria criteria, int page, int pageSize, String order, String sortField) {
        ForumSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ForumCriteria criteria) {
        ForumSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Forum> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<Forum> findByForumSubjectId(Long id){
        return dao.findByForumSubjectId(id);
    }
    public int deleteByForumSubjectId(Long id){
        return dao.deleteByForumSubjectId(id);
    }
    public long countByForumSubjectCode(String code){
        return dao.countByForumSubjectCode(code);
    }
    public List<Forum> findByForumStateCode(String code){
        return dao.findByForumStateCode(code);
    }
    public int deleteByForumStateCode(String code){
        return dao.deleteByForumStateCode(code);
    }
    public long countByForumStateCode(String code){
        return dao.countByForumStateCode(code);
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
        forumCommentService.deleteByForumId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Forum> delete(List<Forum> list) {
		List<Forum> result = new ArrayList();
        if (list != null) {
            for (Forum t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Forum create(Forum t) {
        Forum loaded = findByReferenceEntity(t);
        Forum saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getForumComments() != null) {
                t.getForumComments().forEach(element-> {
                    element.setForum(saved);
                    forumCommentService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Forum findWithAssociatedLists(Long id){
        Forum result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setForumComments(forumCommentService.findByForumId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Forum> update(List<Forum> ts, boolean createIfNotExist) {
        List<Forum> result = new ArrayList<>();
        if (ts != null) {
            for (Forum t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Forum loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Forum t, Forum loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Forum forum){
    if(forum !=null && forum.getId() != null){
        List<List<ForumComment>> resultForumComments= forumCommentService.getToBeSavedAndToBeDeleted(forumCommentService.findByForumId(forum.getId()),forum.getForumComments());
            forumCommentService.delete(resultForumComments.get(1));
        emptyIfNull(resultForumComments.get(0)).forEach(e -> e.setForum(forum));
        forumCommentService.update(resultForumComments.get(0),true);
        }
    }








    public Forum findByReferenceEntity(Forum t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Forum t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setForumSubject(forumSubjectService.findOrSave(t.getForumSubject()));
            t.setForumState(forumStateService.findOrSave(t.getForumState()));
        }
    }



    public List<Forum> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Forum>> getToBeSavedAndToBeDeleted(List<Forum> oldList, List<Forum> newList) {
        List<List<Forum>> result = new ArrayList<>();
        List<Forum> resultDelete = new ArrayList<>();
        List<Forum> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Forum> oldList, List<Forum> newList, List<Forum> resultUpdateOrSave, List<Forum> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Forum myOld = oldList.get(i);
                Forum t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Forum myNew = newList.get(i);
                Forum t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CollaboratorAdminService collaboratorService ;
    @Autowired
    private ForumSubjectAdminService forumSubjectService ;
    @Autowired
    private ForumStateAdminService forumStateService ;
    @Autowired
    private ForumCommentAdminService forumCommentService ;

    public ForumAdminServiceImpl(ForumDao dao) {
        this.dao = dao;
    }

    private ForumDao dao;
}
