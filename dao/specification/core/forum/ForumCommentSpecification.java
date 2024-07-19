package  ma.zs.zyn.dao.specification.core.forum;

import ma.zs.zyn.dao.criteria.core.forum.ForumCommentCriteria;
import ma.zs.zyn.bean.core.forum.ForumComment;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ForumCommentSpecification extends  AbstractSpecification<ForumCommentCriteria, ForumComment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("creationDate", criteria.getCreationDate(), criteria.getCreationDateFrom(), criteria.getCreationDateTo());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("forum","id", criteria.getForum()==null?null:criteria.getForum().getId());
        addPredicateFk("forum","id", criteria.getForums());
    }

    public ForumCommentSpecification(ForumCommentCriteria criteria) {
        super(criteria);
    }

    public ForumCommentSpecification(ForumCommentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
