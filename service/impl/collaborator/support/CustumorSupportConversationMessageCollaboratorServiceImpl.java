package ma.zs.zyn.service.impl.collaborator.support;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationMessageCriteria;
import ma.zs.zyn.dao.facade.core.support.CustumorSupportConversationMessageDao;
import ma.zs.zyn.dao.specification.core.support.CustumorSupportConversationMessageSpecification;
import ma.zs.zyn.service.facade.collaborator.support.CustumorSupportConversationMessageCollaboratorService;
import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.collaborator.support.CustumorSupportConversationCollaboratorService ;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation ;

import java.util.List;
@Service
public class CustumorSupportConversationMessageCollaboratorServiceImpl implements CustumorSupportConversationMessageCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationMessage update(CustumorSupportConversationMessage t) {
        CustumorSupportConversationMessage loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CustumorSupportConversationMessage.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CustumorSupportConversationMessage findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CustumorSupportConversationMessage findOrSave(CustumorSupportConversationMessage t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CustumorSupportConversationMessage result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CustumorSupportConversationMessage> findAll() {
        String currentUser = getCurrentUser();
        return dao.findByCustumorSupportConversationCollaboratorUsername(currentUser);
    }

    public List<CustumorSupportConversationMessage> findByCriteria(CustumorSupportConversationMessageCriteria criteria) {
        List<CustumorSupportConversationMessage> content = null;
        if (criteria != null) {
            CustumorSupportConversationMessageSpecification mySpecification = constructSpecification(criteria);
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


    private CustumorSupportConversationMessageSpecification constructSpecification(CustumorSupportConversationMessageCriteria criteria) {
        CustumorSupportConversationMessageSpecification mySpecification =  (CustumorSupportConversationMessageSpecification) RefelexivityUtil.constructObjectUsingOneParam(CustumorSupportConversationMessageSpecification.class, criteria);
        return mySpecification;
    }

    public List<CustumorSupportConversationMessage> findPaginatedByCriteria(CustumorSupportConversationMessageCriteria criteria, int page, int pageSize, String order, String sortField) {
        CustumorSupportConversationMessageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CustumorSupportConversationMessageCriteria criteria) {
        CustumorSupportConversationMessageSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CustumorSupportConversationMessage> findByCustumorSupportConversationId(Long id){
        return dao.findByCustumorSupportConversationId(id);
    }
    public int deleteByCustumorSupportConversationId(Long id){
        return dao.deleteByCustumorSupportConversationId(id);
    }
    public long countByCustumorSupportConversationId(Long id){
        return dao.countByCustumorSupportConversationId(id);
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
    public List<CustumorSupportConversationMessage> delete(List<CustumorSupportConversationMessage> list) {
		List<CustumorSupportConversationMessage> result = new ArrayList();
        if (list != null) {
            for (CustumorSupportConversationMessage t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversationMessage create(CustumorSupportConversationMessage t) {
        t.setCreationDate(LocalDateTime.now());
        t.setCollaborator(true);
        CustumorSupportConversationMessage saved = dao.save(t);
        CustumorSupportConversation conversation= saved.getCustumorSupportConversation();
            if (conversation == null) {
                conversation = new CustumorSupportConversation();
                conversation.setCreationDate(LocalDateTime.now());
                conversation = custumorSupportConversationService.create(conversation);
                String username = getCurrentUser();
                Collaborator collaborator = collaboratorService.findByUsername(username);
                conversation.setCollaborator(collaborator);
                saved.setCustumorSupportConversation(conversation);
                dao.save(saved);
            }
        return saved;
    }


    public String getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser != null && currentUser instanceof String) {
                return (String) currentUser;
            } else if (currentUser != null && currentUser instanceof User) {
                return ((User) currentUser).getUsername();
            } else return null;
        }

        return null;
    }
    public CustumorSupportConversationMessage findWithAssociatedLists(Long id){
        CustumorSupportConversationMessage result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CustumorSupportConversationMessage> update(List<CustumorSupportConversationMessage> ts, boolean createIfNotExist) {
        List<CustumorSupportConversationMessage> result = new ArrayList<>();
        if (ts != null) {
            for (CustumorSupportConversationMessage t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CustumorSupportConversationMessage loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CustumorSupportConversationMessage t, CustumorSupportConversationMessage loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CustumorSupportConversationMessage findByReferenceEntity(CustumorSupportConversationMessage t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CustumorSupportConversationMessage t){
        if( t != null) {
            t.setCustumorSupportConversation(custumorSupportConversationService.findOrSave(t.getCustumorSupportConversation()));
        }
    }



    public List<CustumorSupportConversationMessage> findAllOptimized() {
        String currentUser = getCurrentUser();
        return dao.findByCustumorSupportConversationCollaboratorUsername(currentUser);
    }

    @Override
    public List<List<CustumorSupportConversationMessage>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationMessage> oldList, List<CustumorSupportConversationMessage> newList) {
        List<List<CustumorSupportConversationMessage>> result = new ArrayList<>();
        List<CustumorSupportConversationMessage> resultDelete = new ArrayList<>();
        List<CustumorSupportConversationMessage> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CustumorSupportConversationMessage> oldList, List<CustumorSupportConversationMessage> newList, List<CustumorSupportConversationMessage> resultUpdateOrSave, List<CustumorSupportConversationMessage> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CustumorSupportConversationMessage myOld = oldList.get(i);
                CustumorSupportConversationMessage t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CustumorSupportConversationMessage myNew = newList.get(i);
                CustumorSupportConversationMessage t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CustumorSupportConversationCollaboratorService custumorSupportConversationService ;
    @Autowired
    private CollaboratorCollaboratorService collaboratorService ;

    public CustumorSupportConversationMessageCollaboratorServiceImpl(CustumorSupportConversationMessageDao dao) {
        this.dao = dao;
    }

    private CustumorSupportConversationMessageDao dao;
}
