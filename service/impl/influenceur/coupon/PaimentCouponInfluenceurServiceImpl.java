package ma.zs.zyn.service.impl.influenceur.coupon;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import ma.zs.zyn.service.facade.influenceur.coupon.*;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponCriteria;
import ma.zs.zyn.dao.facade.core.coupon.PaimentCouponDao;
import ma.zs.zyn.dao.specification.core.coupon.PaimentCouponSpecification;

import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.bean.core.coupon.Coupon;

@Service
public class PaimentCouponInfluenceurServiceImpl implements PaimentCouponInfluenceurService {

    public static final String COUPON_STATE_BLOCKED = "BLOCKED";
    public static final String PAYMENT_COUPON_STATE_PENDING = "PENDING";
    public static final String PAYMENT_COUPON_STATE_ACTIVATED = "ACTIVATED";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int saveOrUpdatePaiment(PaimentCollaborator paimentCollaborator) {
        Coupon coupon = new Coupon();//paimentCollaborator.getCoupon();
        coupon.setCode("YOYO");
        if (coupon == null || coupon.getCode() == null) {
            return 0;
        }
        String codeCoupon = coupon.getCode();
        Coupon loadedCoupon = couponService.findByCode(codeCoupon);
        if (loadedCoupon == null) {
            return -1;
        } else if (loadedCoupon.getCouponState() != null && COUPON_STATE_BLOCKED.equals(loadedCoupon.getCouponState().getCode())) {
            return -2;
        } else if (loadedCoupon.getNombreInscriptionMax() <= loadedCoupon.getNombreCollaboratorInscrit() + 1) {
            return -3;
        } else {
            BigDecimal total = paimentCollaborator.getTotal();
            BigDecimal montantInfluenceur = loadedCoupon.getPercentInflucencer().multiply(total).divide(BigDecimal.valueOf(100));
            BigDecimal discountCollaborator = loadedCoupon.getDiscountCollaborator().multiply(total).divide(BigDecimal.valueOf(100));
            paimentCollaborator.setDiscount(discountCollaborator);
            paimentCollaborator.setRemaining(total.subtract(discountCollaborator));
            PaimentCoupon paimentCoupon = findAvailable(loadedCoupon);
            if (paimentCoupon == null) {
                paimentCoupon = new PaimentCoupon();
                paimentCoupon.setCoupon(loadedCoupon);
                paimentCoupon.setPaimentCouponState(paimentCouponStateService.findByCode(PAYMENT_COUPON_STATE_ACTIVATED));
                paimentCoupon.setTotal(montantInfluenceur);
                paimentCoupon.setPaiementDate(null);
            } else {
                paimentCoupon.setTotal(paimentCoupon.getTotal().add(montantInfluenceur));
            }
            if (loadedCoupon.getNombreCollaboratorInscrit() == null) {
                loadedCoupon.setNombreCollaboratorInscrit(0);
            }
            loadedCoupon.setNombreCollaboratorInscrit(loadedCoupon.getNombreCollaboratorInscrit() + 1);
            if (loadedCoupon.getNombreInscriptionMax() <= loadedCoupon.getNombreCollaboratorInscrit()) {
                loadedCoupon.setCouponState(couponStateService.findByCode(COUPON_STATE_BLOCKED));
            }
            dao.save(paimentCoupon);
            return 1;
        }
    }

    private PaimentCoupon findAvailable(Coupon coupon) {
        return dao.findByCouponCodeAndPaimentCouponStateCode(coupon.getCode(), PAYMENT_COUPON_STATE_ACTIVATED);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCoupon update(PaimentCoupon t) {
        PaimentCoupon loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCoupon.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCoupon findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCoupon findOrSave(PaimentCoupon t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaimentCoupon result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCoupon> findAll() {
        Influenceur currentUser = getCurrentUser();
        if (currentUser != null) {
            return dao.findByCouponInfluenceurId(currentUser.getId());
        }
        return new ArrayList<>();
    }


    private Influenceur getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof Influenceur) {
            return (Influenceur) principal;
        } else if (principal != null && principal instanceof String) {
            return findByUsername(principal.toString());
        } else {
            return null;
        }
    }

    public Influenceur findByUsername(String username) {
        return influenceurService.findByUsername(username);
    }

    public List<PaimentCoupon> findByCriteria(PaimentCouponCriteria criteria) {
        List<PaimentCoupon> content = null;
        if (criteria != null) {
            PaimentCouponSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCouponSpecification constructSpecification(PaimentCouponCriteria criteria) {
        PaimentCouponSpecification mySpecification = (PaimentCouponSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCouponSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCoupon> findPaginatedByCriteria(PaimentCouponCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCouponSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCouponCriteria criteria) {
        PaimentCouponSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PaimentCoupon> findByCouponId(Long id) {
        return dao.findByCouponId(id);
    }

    public int deleteByCouponId(Long id) {
        return dao.deleteByCouponId(id);
    }

    public long countByCouponCode(String code) {
        return dao.countByCouponCode(code);
    }

    public List<PaimentCoupon> findByPaimentCouponStateLibelle(String libelle) {
        return dao.findByPaimentCouponStateLibelle(libelle);
    }

    public int deleteByPaimentCouponStateLibelle(String libelle) {
        return dao.deleteByPaimentCouponStateLibelle(libelle);
    }

    public long countByPaimentCouponStateLibelle(String libelle) {
        return dao.countByPaimentCouponStateLibelle(libelle);
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
    public List<PaimentCoupon> delete(List<PaimentCoupon> list) {
        List<PaimentCoupon> result = new ArrayList();
        if (list != null) {
            for (PaimentCoupon t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCoupon create(PaimentCoupon t) {
        PaimentCoupon loaded = findByReferenceEntity(t);
        PaimentCoupon saved;
        if (loaded == null) {
            saved = dao.save(t);
        } else {
            saved = null;
        }
        return saved;
    }

    public PaimentCoupon findWithAssociatedLists(Long id) {
        PaimentCoupon result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCoupon> update(List<PaimentCoupon> ts, boolean createIfNotExist) {
        List<PaimentCoupon> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCoupon t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCoupon loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCoupon t, PaimentCoupon loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public PaimentCoupon findByReferenceEntity(PaimentCoupon t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }

    public void findOrSaveAssociatedObject(PaimentCoupon t) {
        if (t != null) {
            t.setCoupon(couponService.findOrSave(t.getCoupon()));
        }
    }


    public List<PaimentCoupon> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<PaimentCoupon>> getToBeSavedAndToBeDeleted(List<PaimentCoupon> oldList, List<PaimentCoupon> newList) {
        List<List<PaimentCoupon>> result = new ArrayList<>();
        List<PaimentCoupon> resultDelete = new ArrayList<>();
        List<PaimentCoupon> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaimentCoupon> oldList, List<PaimentCoupon> newList, List<PaimentCoupon> resultUpdateOrSave, List<PaimentCoupon> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            PaimentCoupon myOld = oldList.get(i);
            PaimentCoupon t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            PaimentCoupon myNew = newList.get(i);
            PaimentCoupon t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CouponInfluenceurService couponService;
    @Autowired
    private PaimentCouponStateInfluenceurService paimentCouponStateService;
    @Autowired
    private CouponStateInfluenceurService couponStateService;
    @Autowired
    private InfluenceurInfluenceurService influenceurService;

    public PaimentCouponInfluenceurServiceImpl(PaimentCouponDao dao) {
        this.dao = dao;
    }

    private PaimentCouponDao dao;
}
