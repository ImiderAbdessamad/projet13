package ma.zs.zyn.dao.specification.core.support;

import ma.zs.zyn.dao.criteria.core.support.AgentCriteria;
import ma.zs.zyn.bean.core.support.Agent;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class AgentSpecification extends  AbstractSpecification<AgentCriteria, Agent>  {

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

    public AgentSpecification(AgentCriteria criteria) {
        super(criteria);
    }

    public AgentSpecification(AgentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
