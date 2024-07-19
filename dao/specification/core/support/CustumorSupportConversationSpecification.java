package ma.zs.zyn.dao.specification.core.support;

import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCriteria;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CustumorSupportConversationSpecification extends  AbstractSpecification<CustumorSupportConversationCriteria, CustumorSupportConversation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("object", criteria.getObject(),criteria.getObjectLike());
        addPredicateBigDecimal("ratting", criteria.getRatting(), criteria.getRattingMin(), criteria.getRattingMax());
        addPredicate("creationDate", criteria.getCreationDate(), criteria.getCreationDateFrom(), criteria.getCreationDateTo());
        addPredicate("closingDate", criteria.getClosingDate(), criteria.getClosingDateFrom(), criteria.getClosingDateTo());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("agent","id", criteria.getAgent()==null?null:criteria.getAgent().getId());
        addPredicateFk("agent","id", criteria.getAgents());
        addPredicateFk("custumorSupportConversationCategory","id", criteria.getCustumorSupportConversationCategory()==null?null:criteria.getCustumorSupportConversationCategory().getId());
        addPredicateFk("custumorSupportConversationCategory","id", criteria.getCustumorSupportConversationCategorys());
        addPredicateFk("custumorSupportConversationCategory","code", criteria.getCustumorSupportConversationCategory()==null?null:criteria.getCustumorSupportConversationCategory().getCode());
        addPredicateFk("custumorSupportConversationState","id", criteria.getCustumorSupportConversationState()==null?null:criteria.getCustumorSupportConversationState().getId());
        addPredicateFk("custumorSupportConversationState","id", criteria.getCustumorSupportConversationStates());
        addPredicateFk("custumorSupportConversationState","code", criteria.getCustumorSupportConversationState()==null?null:criteria.getCustumorSupportConversationState().getCode());
    }

    public CustumorSupportConversationSpecification(CustumorSupportConversationCriteria criteria) {
        super(criteria);
    }

    public CustumorSupportConversationSpecification(CustumorSupportConversationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
