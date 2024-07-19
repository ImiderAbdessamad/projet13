package ma.zs.zyn.service.impl.collaborator.contactus;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCategoryCriteria;
import ma.zs.zyn.dao.facade.core.contactus.ContactUsCategoryDao;
import ma.zs.zyn.dao.specification.core.contactus.ContactUsCategorySpecification;
import ma.zs.zyn.service.facade.collaborator.contactus.ContactUsCategoryCollaboratorService;
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
public class ContactUsCategoryCollaboratorServiceImpl implements ContactUsCategoryCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUsCategory update(ContactUsCategory t) {
        ContactUsCategory loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ContactUsCategory.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ContactUsCategory findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ContactUsCategory findOrSave(ContactUsCategory t) {
        if (t != null) {
            ContactUsCategory result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ContactUsCategory> findAll() {
        return dao.findAll();
    }

    public List<ContactUsCategory> findByCriteria(ContactUsCategoryCriteria criteria) {
        List<ContactUsCategory> content = null;
        if (criteria != null) {
            ContactUsCategorySpecification mySpecification = constructSpecification(criteria);
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


    private ContactUsCategorySpecification constructSpecification(ContactUsCategoryCriteria criteria) {
        ContactUsCategorySpecification mySpecification =  (ContactUsCategorySpecification) RefelexivityUtil.constructObjectUsingOneParam(ContactUsCategorySpecification.class, criteria);
        return mySpecification;
    }

    public List<ContactUsCategory> findPaginatedByCriteria(ContactUsCategoryCriteria criteria, int page, int pageSize, String order, String sortField) {
        ContactUsCategorySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ContactUsCategoryCriteria criteria) {
        ContactUsCategorySpecification mySpecification = constructSpecification(criteria);
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
    public List<ContactUsCategory> delete(List<ContactUsCategory> list) {
		List<ContactUsCategory> result = new ArrayList();
        if (list != null) {
            for (ContactUsCategory t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUsCategory create(ContactUsCategory t) {
        ContactUsCategory loaded = findByReferenceEntity(t);
        ContactUsCategory saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ContactUsCategory findWithAssociatedLists(Long id){
        ContactUsCategory result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ContactUsCategory> update(List<ContactUsCategory> ts, boolean createIfNotExist) {
        List<ContactUsCategory> result = new ArrayList<>();
        if (ts != null) {
            for (ContactUsCategory t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ContactUsCategory loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ContactUsCategory t, ContactUsCategory loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ContactUsCategory findByReferenceEntity(ContactUsCategory t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ContactUsCategory> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ContactUsCategory>> getToBeSavedAndToBeDeleted(List<ContactUsCategory> oldList, List<ContactUsCategory> newList) {
        List<List<ContactUsCategory>> result = new ArrayList<>();
        List<ContactUsCategory> resultDelete = new ArrayList<>();
        List<ContactUsCategory> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ContactUsCategory> oldList, List<ContactUsCategory> newList, List<ContactUsCategory> resultUpdateOrSave, List<ContactUsCategory> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ContactUsCategory myOld = oldList.get(i);
                ContactUsCategory t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ContactUsCategory myNew = newList.get(i);
                ContactUsCategory t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ContactUsCategoryCollaboratorServiceImpl(ContactUsCategoryDao dao) {
        this.dao = dao;
    }

    private ContactUsCategoryDao dao;
}
