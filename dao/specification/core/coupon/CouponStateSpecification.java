package ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.CouponStateCriteria;
import ma.zs.zyn.bean.core.coupon.CouponState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CouponStateSpecification extends  AbstractSpecification<CouponStateCriteria, CouponState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public CouponStateSpecification(CouponStateCriteria criteria) {
        super(criteria);
    }

    public CouponStateSpecification(CouponStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
