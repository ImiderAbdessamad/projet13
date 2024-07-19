package ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CouponSpecification extends  AbstractSpecification<CouponCriteria, Coupon>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBigDecimal("discountCollaborator", criteria.getDiscountCollaborator(), criteria.getDiscountCollaboratorMin(), criteria.getDiscountCollaboratorMax());
        addPredicateBigDecimal("percentInflucencer", criteria.getPercentInflucencer(), criteria.getPercentInflucencerMin(), criteria.getPercentInflucencerMax());
        addPredicateInt("nombreInscriptionMax", criteria.getNombreInscriptionMax(), criteria.getNombreInscriptionMaxMin(), criteria.getNombreInscriptionMaxMax());
        addPredicateInt("nombreCollaboratorInscrit", criteria.getNombreCollaboratorInscrit(), criteria.getNombreCollaboratorInscritMin(), criteria.getNombreCollaboratorInscritMax());
        addPredicateFk("influenceur","id", criteria.getInfluenceur()==null?null:criteria.getInfluenceur().getId());
        addPredicateFk("influenceur","id", criteria.getInfluenceurs());
        addPredicateFk("couponState","id", criteria.getCouponState()==null?null:criteria.getCouponState().getId());
        addPredicateFk("couponState","id", criteria.getCouponStates());
        addPredicateFk("couponState","libelle", criteria.getCouponState()==null?null:criteria.getCouponState().getLibelle());
    }

    public CouponSpecification(CouponCriteria criteria) {
        super(criteria);
    }

    public CouponSpecification(CouponCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
