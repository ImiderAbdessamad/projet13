package ma.zs.zyn.service.impl.collaborator.project;


import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.dao.criteria.core.project.ConversationCriteria;
import ma.zs.zyn.dao.facade.core.project.ConversationDao;
import ma.zs.zyn.dao.specification.core.project.ConversationSpecification;
import ma.zs.zyn.service.facade.collaborator.project.ProjectCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.project.ConversationCollaboratorService;
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
public class ConversationCollaboratorServiceImpl implements ConversationCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Conversation update(Conversation t) {
        Conversation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Conversation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Conversation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Conversation findOrSave(Conversation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Conversation result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Conversation> findAll() {
        return dao.findAll();
    }

    public List<Conversation> findByCriteria(ConversationCriteria criteria) {
        List<Conversation> content = null;
        if (criteria != null) {
            ConversationSpecification mySpecification = constructSpecification(criteria);
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


    private ConversationSpecification constructSpecification(ConversationCriteria criteria) {
        ConversationSpecification mySpecification =  (ConversationSpecification) RefelexivityUtil.constructObjectUsingOneParam(ConversationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Conversation> findPaginatedByCriteria(ConversationCriteria criteria, int page, int pageSize, String order, String sortField) {
        ConversationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ConversationCriteria criteria) {
        ConversationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Override
    public List<Conversation> findByProjectTitle(String title) {
        return dao.findByProjectTitle(title);
    }

    @Override
    public List<Conversation> findByProjectTitleChat(String title) {
        return dao.findByProjectTitleChat(title);
    }

    public List<Conversation> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectId(Long id){
        return dao.countByProjectId(id);
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
    public List<Conversation> delete(List<Conversation> list) {
		List<Conversation> result = new ArrayList();
        if (list != null) {
            for (Conversation t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Conversation create(Conversation t) {
        Conversation loaded = findByReferenceEntity(t);
        Conversation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Conversation findWithAssociatedLists(Long id){
        Conversation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Conversation> update(List<Conversation> ts, boolean createIfNotExist) {
        List<Conversation> result = new ArrayList<>();
        if (ts != null) {
            for (Conversation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Conversation loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Conversation t, Conversation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Conversation findByReferenceEntity(Conversation t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Conversation t){
        if( t != null) {
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<Conversation> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Conversation>> getToBeSavedAndToBeDeleted(List<Conversation> oldList, List<Conversation> newList) {
        List<List<Conversation>> result = new ArrayList<>();
        List<Conversation> resultDelete = new ArrayList<>();
        List<Conversation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Conversation> oldList, List<Conversation> newList, List<Conversation> resultUpdateOrSave, List<Conversation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Conversation myOld = oldList.get(i);
                Conversation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Conversation myNew = newList.get(i);
                Conversation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectCollaboratorService projectService ;

    public ConversationCollaboratorServiceImpl(ConversationDao dao) {
        this.dao = dao;
    }

    private ConversationDao dao;
}
