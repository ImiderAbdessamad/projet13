package ma.zs.zyn.service.impl.collaborator.contactus;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCriteria;
import ma.zs.zyn.dao.facade.core.contactus.ContactUsDao;
import ma.zs.zyn.dao.specification.core.contactus.ContactUsSpecification;
import ma.zs.zyn.service.facade.collaborator.contactus.ContactUsCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.contactus.ContactUsCategoryCollaboratorService ;
import ma.zs.zyn.bean.core.contactus.ContactUsCategory ;
import ma.zs.zyn.service.facade.collaborator.contactus.ContactUsStateCollaboratorService ;
import ma.zs.zyn.bean.core.contactus.ContactUsState ;

import java.util.List;
@Service
public class ContactUsCollaboratorServiceImpl implements ContactUsCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUs update(ContactUs t) {
        ContactUs loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ContactUs.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ContactUs findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ContactUs findOrSave(ContactUs t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ContactUs result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ContactUs> findAll() {
        return dao.findAll();
    }

    public List<ContactUs> findByCriteria(ContactUsCriteria criteria) {
        List<ContactUs> content = null;
        if (criteria != null) {
            ContactUsSpecification mySpecification = constructSpecification(criteria);
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


    private ContactUsSpecification constructSpecification(ContactUsCriteria criteria) {
        ContactUsSpecification mySpecification =  (ContactUsSpecification) RefelexivityUtil.constructObjectUsingOneParam(ContactUsSpecification.class, criteria);
        return mySpecification;
    }

    public List<ContactUs> findPaginatedByCriteria(ContactUsCriteria criteria, int page, int pageSize, String order, String sortField) {
        ContactUsSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ContactUsCriteria criteria) {
        ContactUsSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ContactUs> findByContactUsCategoryId(Long id){
        return dao.findByContactUsCategoryId(id);
    }
    public int deleteByContactUsCategoryId(Long id){
        return dao.deleteByContactUsCategoryId(id);
    }
    public long countByContactUsCategoryCode(String code){
        return dao.countByContactUsCategoryCode(code);
    }
    public List<ContactUs> findByContactUsStateCode(String code){
        return dao.findByContactUsStateCode(code);
    }
    public int deleteByContactUsStateCode(String code){
        return dao.deleteByContactUsStateCode(code);
    }
    public long countByContactUsStateCode(String code){
        return dao.countByContactUsStateCode(code);
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
    public List<ContactUs> delete(List<ContactUs> list) {
		List<ContactUs> result = new ArrayList();
        if (list != null) {
            for (ContactUs t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUs create(ContactUs t) {
        ContactUs loaded = findByReferenceEntity(t);
        if (loaded != null) {
            return loaded;
        }
        t.setContactUsCategory(null);
        return dao.save(t);
    }

    public ContactUs findWithAssociatedLists(Long id){
        ContactUs result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ContactUs> update(List<ContactUs> ts, boolean createIfNotExist) {
        List<ContactUs> result = new ArrayList<>();
        if (ts != null) {
            for (ContactUs t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ContactUs loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ContactUs t, ContactUs loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ContactUs findByReferenceEntity(ContactUs t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ContactUs t){
        if( t != null) {
        }
    }



    public List<ContactUs> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ContactUs>> getToBeSavedAndToBeDeleted(List<ContactUs> oldList, List<ContactUs> newList) {
        List<List<ContactUs>> result = new ArrayList<>();
        List<ContactUs> resultDelete = new ArrayList<>();
        List<ContactUs> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ContactUs> oldList, List<ContactUs> newList, List<ContactUs> resultUpdateOrSave, List<ContactUs> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ContactUs myOld = oldList.get(i);
                ContactUs t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ContactUs myNew = newList.get(i);
                ContactUs t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ContactUsCategoryCollaboratorService contactUsCategoryService ;
    @Autowired
    private ContactUsStateCollaboratorService contactUsStateService ;

    public ContactUsCollaboratorServiceImpl(ContactUsDao dao) {
        this.dao = dao;
    }

    private ContactUsDao dao;
}
