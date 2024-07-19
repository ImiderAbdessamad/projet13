package ma.zs.zyn.dao.specification.core.support;

import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationStateCriteria;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CustumorSupportConversationStateSpecification extends  AbstractSpecification<CustumorSupportConversationStateCriteria, CustumorSupportConversationState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CustumorSupportConversationStateSpecification(CustumorSupportConversationStateCriteria criteria) {
        super(criteria);
    }

    public CustumorSupportConversationStateSpecification(CustumorSupportConversationStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
