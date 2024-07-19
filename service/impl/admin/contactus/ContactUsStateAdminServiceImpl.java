package ma.zs.zyn.service.impl.admin.contactus;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsStateCriteria;
import ma.zs.zyn.dao.facade.core.contactus.ContactUsStateDao;
import ma.zs.zyn.dao.specification.core.contactus.ContactUsStateSpecification;
import ma.zs.zyn.service.facade.admin.contactus.ContactUsStateAdminService;
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
public class ContactUsStateAdminServiceImpl implements ContactUsStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUsState update(ContactUsState t) {
        ContactUsState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ContactUsState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ContactUsState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ContactUsState findOrSave(ContactUsState t) {
        if (t != null) {
            ContactUsState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ContactUsState> findAll() {
        return dao.findAll();
    }

    public List<ContactUsState> findByCriteria(ContactUsStateCriteria criteria) {
        List<ContactUsState> content = null;
        if (criteria != null) {
            ContactUsStateSpecification mySpecification = constructSpecification(criteria);
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


    private ContactUsStateSpecification constructSpecification(ContactUsStateCriteria criteria) {
        ContactUsStateSpecification mySpecification =  (ContactUsStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ContactUsStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ContactUsState> findPaginatedByCriteria(ContactUsStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ContactUsStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ContactUsStateCriteria criteria) {
        ContactUsStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<ContactUsState> delete(List<ContactUsState> list) {
		List<ContactUsState> result = new ArrayList();
        if (list != null) {
            for (ContactUsState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ContactUsState create(ContactUsState t) {
        ContactUsState loaded = findByReferenceEntity(t);
        ContactUsState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ContactUsState findWithAssociatedLists(Long id){
        ContactUsState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ContactUsState> update(List<ContactUsState> ts, boolean createIfNotExist) {
        List<ContactUsState> result = new ArrayList<>();
        if (ts != null) {
            for (ContactUsState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ContactUsState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ContactUsState t, ContactUsState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ContactUsState findByReferenceEntity(ContactUsState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ContactUsState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ContactUsState>> getToBeSavedAndToBeDeleted(List<ContactUsState> oldList, List<ContactUsState> newList) {
        List<List<ContactUsState>> result = new ArrayList<>();
        List<ContactUsState> resultDelete = new ArrayList<>();
        List<ContactUsState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ContactUsState> oldList, List<ContactUsState> newList, List<ContactUsState> resultUpdateOrSave, List<ContactUsState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ContactUsState myOld = oldList.get(i);
                ContactUsState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ContactUsState myNew = newList.get(i);
                ContactUsState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ContactUsStateAdminServiceImpl(ContactUsStateDao dao) {
        this.dao = dao;
    }

    private ContactUsStateDao dao;
}
