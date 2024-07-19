package ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.InfluenceurCriteria;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InfluenceurSpecification extends  AbstractSpecification<InfluenceurCriteria, Influenceur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public InfluenceurSpecification(InfluenceurCriteria criteria) {
        super(criteria);
    }

    public InfluenceurSpecification(InfluenceurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
