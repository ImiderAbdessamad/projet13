package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.dao.criteria.core.project.ConversationCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ConversationSpecification extends  AbstractSpecification<ConversationCriteria, Conversation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
    }

    public ConversationSpecification(ConversationCriteria criteria) {
        super(criteria);
    }

    public ConversationSpecification(ConversationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
