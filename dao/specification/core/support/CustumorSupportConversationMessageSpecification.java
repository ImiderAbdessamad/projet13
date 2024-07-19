package ma.zs.zyn.dao.specification.core.support;

import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationMessageCriteria;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CustumorSupportConversationMessageSpecification extends  AbstractSpecification<CustumorSupportConversationMessageCriteria, CustumorSupportConversationMessage>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("collaborator", criteria.getCollaborator());
        addPredicate("creationDate", criteria.getCreationDate(), criteria.getCreationDateFrom(), criteria.getCreationDateTo());
        addPredicateFk("custumorSupportConversation","id", criteria.getCustumorSupportConversation()==null?null:criteria.getCustumorSupportConversation().getId());
        addPredicateFk("custumorSupportConversation","id", criteria.getCustumorSupportConversations());
    }

    public CustumorSupportConversationMessageSpecification(CustumorSupportConversationMessageCriteria criteria) {
        super(criteria);
    }

    public CustumorSupportConversationMessageSpecification(CustumorSupportConversationMessageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
