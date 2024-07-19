package  ma.zs.zyn.dao.specification.core.forum;

import ma.zs.zyn.dao.criteria.core.forum.ForumCriteria;
import ma.zs.zyn.bean.core.forum.Forum;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ForumSpecification extends  AbstractSpecification<ForumCriteria, Forum>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("creationDate", criteria.getCreationDate(), criteria.getCreationDateFrom(), criteria.getCreationDateTo());
        addPredicate("publicationDate", criteria.getPublicationDate(), criteria.getPublicationDateFrom(), criteria.getPublicationDateTo());
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicateBigDecimal("likes", criteria.getLikes(), criteria.getLikesMin(), criteria.getLikesMax());
        addPredicateBigDecimal("comments", criteria.getComments(), criteria.getCommentsMin(), criteria.getCommentsMax());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("forumSubject","id", criteria.getForumSubject()==null?null:criteria.getForumSubject().getId());
        addPredicateFk("forumSubject","id", criteria.getForumSubjects());
        addPredicateFk("forumSubject","code", criteria.getForumSubject()==null?null:criteria.getForumSubject().getCode());
        addPredicateFk("forumState","id", criteria.getForumState()==null?null:criteria.getForumState().getId());
        addPredicateFk("forumState","id", criteria.getForumStates());
        addPredicateFk("forumState","code", criteria.getForumState()==null?null:criteria.getForumState().getCode());
    }

    public ForumSpecification(ForumCriteria criteria) {
        super(criteria);
    }

    public ForumSpecification(ForumCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
