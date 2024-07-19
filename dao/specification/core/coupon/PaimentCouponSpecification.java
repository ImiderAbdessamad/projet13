package ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponCriteria;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCouponSpecification extends  AbstractSpecification<PaimentCouponCriteria, PaimentCoupon>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicate("paiementDate", criteria.getPaiementDate(), criteria.getPaiementDateFrom(), criteria.getPaiementDateTo());
        addPredicate("paiementDateConfirmation", criteria.getPaiementDateConfirmation(), criteria.getPaiementDateConfirmationFrom(), criteria.getPaiementDateConfirmationTo());
        addPredicateFk("coupon","id", criteria.getCoupon()==null?null:criteria.getCoupon().getId());
        addPredicateFk("coupon","id", criteria.getCoupons());
        addPredicateFk("coupon","code", criteria.getCoupon()==null?null:criteria.getCoupon().getCode());
        addPredicateFk("paimentCouponState","id", criteria.getPaimentCouponState()==null?null:criteria.getPaimentCouponState().getId());
        addPredicateFk("paimentCouponState","id", criteria.getPaimentCouponStates());
        addPredicateFk("paimentCouponState","libelle", criteria.getPaimentCouponState()==null?null:criteria.getPaimentCouponState().getLibelle());
    }

    public PaimentCouponSpecification(PaimentCouponCriteria criteria) {
        super(criteria);
    }

    public PaimentCouponSpecification(PaimentCouponCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
