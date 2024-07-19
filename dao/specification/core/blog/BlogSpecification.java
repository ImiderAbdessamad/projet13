package ma.zs.zyn.dao.specification.core.blog;

import ma.zs.zyn.dao.criteria.core.blog.BlogCriteria;
import ma.zs.zyn.bean.core.blog.Blog;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class BlogSpecification extends  AbstractSpecification<BlogCriteria, Blog>  {

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
        addPredicateFk("blogSubject","id", criteria.getBlogSubject()==null?null:criteria.getBlogSubject().getId());
        addPredicateFk("blogSubject","id", criteria.getBlogSubjects());
        addPredicateFk("blogSubject","code", criteria.getBlogSubject()==null?null:criteria.getBlogSubject().getCode());
        addPredicateFk("blogState","id", criteria.getBlogState()==null?null:criteria.getBlogState().getId());
        addPredicateFk("blogState","id", criteria.getBlogStates());
        addPredicateFk("blogState","code", criteria.getBlogState()==null?null:criteria.getBlogState().getCode());
    }

    public BlogSpecification(BlogCriteria criteria) {
        super(criteria);
    }

    public BlogSpecification(BlogCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
