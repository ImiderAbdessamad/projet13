package ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponStateCriteria;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCouponStateSpecification extends  AbstractSpecification<PaimentCouponStateCriteria, PaimentCouponState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public PaimentCouponStateSpecification(PaimentCouponStateCriteria criteria) {
        super(criteria);
    }

    public PaimentCouponStateSpecification(PaimentCouponStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
