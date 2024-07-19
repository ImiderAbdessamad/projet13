package ma.zs.zyn.service.impl.admin.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.CouponState;
import ma.zs.zyn.dao.criteria.core.coupon.CouponStateCriteria;
import ma.zs.zyn.dao.facade.core.coupon.CouponStateDao;
import ma.zs.zyn.dao.specification.core.coupon.CouponStateSpecification;
import ma.zs.zyn.service.facade.admin.coupon.CouponStateAdminService;
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
public class CouponStateAdminServiceImpl implements CouponStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CouponState update(CouponState t) {
        CouponState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CouponState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CouponState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CouponState findOrSave(CouponState t) {
        if (t != null) {
            CouponState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CouponState> findAll() {
        return dao.findAll();
    }

    public List<CouponState> findByCriteria(CouponStateCriteria criteria) {
        List<CouponState> content = null;
        if (criteria != null) {
            CouponStateSpecification mySpecification = constructSpecification(criteria);
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


    private CouponStateSpecification constructSpecification(CouponStateCriteria criteria) {
        CouponStateSpecification mySpecification =  (CouponStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(CouponStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<CouponState> findPaginatedByCriteria(CouponStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        CouponStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CouponStateCriteria criteria) {
        CouponStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<CouponState> delete(List<CouponState> list) {
		List<CouponState> result = new ArrayList();
        if (list != null) {
            for (CouponState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CouponState create(CouponState t) {
        CouponState loaded = findByReferenceEntity(t);
        CouponState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CouponState findWithAssociatedLists(Long id){
        CouponState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CouponState> update(List<CouponState> ts, boolean createIfNotExist) {
        List<CouponState> result = new ArrayList<>();
        if (ts != null) {
            for (CouponState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CouponState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CouponState t, CouponState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CouponState findByReferenceEntity(CouponState t){
        return t==null? null : dao.findByLibelle(t.getLibelle());
    }



    public List<CouponState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CouponState>> getToBeSavedAndToBeDeleted(List<CouponState> oldList, List<CouponState> newList) {
        List<List<CouponState>> result = new ArrayList<>();
        List<CouponState> resultDelete = new ArrayList<>();
        List<CouponState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CouponState> oldList, List<CouponState> newList, List<CouponState> resultUpdateOrSave, List<CouponState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CouponState myOld = oldList.get(i);
                CouponState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CouponState myNew = newList.get(i);
                CouponState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CouponStateAdminServiceImpl(CouponStateDao dao) {
        this.dao = dao;
    }

    private CouponStateDao dao;
}
