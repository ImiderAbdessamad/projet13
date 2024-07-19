package ma.zs.zyn.service.impl.collaborator.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponStateCriteria;
import ma.zs.zyn.dao.facade.core.coupon.PaimentCouponStateDao;
import ma.zs.zyn.dao.specification.core.coupon.PaimentCouponStateSpecification;
import ma.zs.zyn.service.facade.collaborator.coupon.PaimentCouponStateCollaboratorService;
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
public class PaimentCouponStateCollaboratorServiceImpl implements PaimentCouponStateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCouponState update(PaimentCouponState t) {
        PaimentCouponState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCouponState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCouponState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCouponState findOrSave(PaimentCouponState t) {
        if (t != null) {
            PaimentCouponState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCouponState> findAll() {
        return dao.findAll();
    }

    public List<PaimentCouponState> findByCriteria(PaimentCouponStateCriteria criteria) {
        List<PaimentCouponState> content = null;
        if (criteria != null) {
            PaimentCouponStateSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCouponStateSpecification constructSpecification(PaimentCouponStateCriteria criteria) {
        PaimentCouponStateSpecification mySpecification =  (PaimentCouponStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCouponStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCouponState> findPaginatedByCriteria(PaimentCouponStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCouponStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCouponStateCriteria criteria) {
        PaimentCouponStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<PaimentCouponState> delete(List<PaimentCouponState> list) {
		List<PaimentCouponState> result = new ArrayList();
        if (list != null) {
            for (PaimentCouponState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCouponState create(PaimentCouponState t) {
        PaimentCouponState loaded = findByReferenceEntity(t);
        PaimentCouponState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaimentCouponState findWithAssociatedLists(Long id){
        PaimentCouponState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCouponState> update(List<PaimentCouponState> ts, boolean createIfNotExist) {
        List<PaimentCouponState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCouponState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCouponState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCouponState t, PaimentCouponState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaimentCouponState findByReferenceEntity(PaimentCouponState t){
        return t==null? null : dao.findByLibelle(t.getLibelle());
    }



    public List<PaimentCouponState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCouponState>> getToBeSavedAndToBeDeleted(List<PaimentCouponState> oldList, List<PaimentCouponState> newList) {
        List<List<PaimentCouponState>> result = new ArrayList<>();
        List<PaimentCouponState> resultDelete = new ArrayList<>();
        List<PaimentCouponState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaimentCouponState> oldList, List<PaimentCouponState> newList, List<PaimentCouponState> resultUpdateOrSave, List<PaimentCouponState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaimentCouponState myOld = oldList.get(i);
                PaimentCouponState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaimentCouponState myNew = newList.get(i);
                PaimentCouponState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public PaimentCouponStateCollaboratorServiceImpl(PaimentCouponStateDao dao) {
        this.dao = dao;
    }

    private PaimentCouponStateDao dao;
}
