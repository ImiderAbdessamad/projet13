package ma.zs.zyn.service.impl.collaborator.forum;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.forum.ForumComment;
import ma.zs.zyn.dao.criteria.core.forum.ForumCommentCriteria;
import ma.zs.zyn.dao.facade.core.forum.ForumCommentDao;
import ma.zs.zyn.dao.specification.core.forum.ForumCommentSpecification;
import ma.zs.zyn.service.facade.collaborator.forum.ForumCommentCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService ;
import ma.zs.zyn.bean.core.collaborator.Collaborator ;
import ma.zs.zyn.service.facade.collaborator.forum.ForumCollaboratorService ;
import ma.zs.zyn.bean.core.forum.Forum ;

import java.util.List;
@Service
public class ForumCommentCollaboratorServiceImpl implements ForumCommentCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumComment update(ForumComment t) {
        ForumComment loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ForumComment.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ForumComment findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ForumComment findOrSave(ForumComment t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ForumComment result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ForumComment> findAll() {
        return dao.findAll();
    }

    public List<ForumComment> findByCriteria(ForumCommentCriteria criteria) {
        List<ForumComment> content = null;
        if (criteria != null) {
            ForumCommentSpecification mySpecification = constructSpecification(criteria);
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


    private ForumCommentSpecification constructSpecification(ForumCommentCriteria criteria) {
        ForumCommentSpecification mySpecification =  (ForumCommentSpecification) RefelexivityUtil.constructObjectUsingOneParam(ForumCommentSpecification.class, criteria);
        return mySpecification;
    }

    public List<ForumComment> findPaginatedByCriteria(ForumCommentCriteria criteria, int page, int pageSize, String order, String sortField) {
        ForumCommentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ForumCommentCriteria criteria) {
        ForumCommentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ForumComment> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<ForumComment> findByForumId(Long id){
        return dao.findByForumId(id);
    }
    public int deleteByForumId(Long id){
        return dao.deleteByForumId(id);
    }
    public long countByForumId(Long id){
        return dao.countByForumId(id);
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
    public List<ForumComment> delete(List<ForumComment> list) {
		List<ForumComment> result = new ArrayList();
        if (list != null) {
            for (ForumComment t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ForumComment create(ForumComment t) {
        ForumComment loaded = findByReferenceEntity(t);
        ForumComment saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ForumComment findWithAssociatedLists(Long id){
        ForumComment result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ForumComment> update(List<ForumComment> ts, boolean createIfNotExist) {
        List<ForumComment> result = new ArrayList<>();
        if (ts != null) {
            for (ForumComment t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ForumComment loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ForumComment t, ForumComment loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ForumComment findByReferenceEntity(ForumComment t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ForumComment t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setForum(forumService.findOrSave(t.getForum()));
        }
    }



    public List<ForumComment> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ForumComment>> getToBeSavedAndToBeDeleted(List<ForumComment> oldList, List<ForumComment> newList) {
        List<List<ForumComment>> result = new ArrayList<>();
        List<ForumComment> resultDelete = new ArrayList<>();
        List<ForumComment> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ForumComment> oldList, List<ForumComment> newList, List<ForumComment> resultUpdateOrSave, List<ForumComment> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ForumComment myOld = oldList.get(i);
                ForumComment t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ForumComment myNew = newList.get(i);
                ForumComment t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ForumCollaboratorService forumService ;

    public ForumCommentCollaboratorServiceImpl(ForumCommentDao dao) {
        this.dao = dao;
    }

    private ForumCommentDao dao;
}
