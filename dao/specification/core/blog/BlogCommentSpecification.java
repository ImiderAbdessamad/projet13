package ma.zs.zyn.dao.specification.core.blog;

import ma.zs.zyn.dao.criteria.core.blog.BlogCommentCriteria;
import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class BlogCommentSpecification extends  AbstractSpecification<BlogCommentCriteria, BlogComment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("creationDate", criteria.getCreationDate(), criteria.getCreationDateFrom(), criteria.getCreationDateTo());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("blog","id", criteria.getBlog()==null?null:criteria.getBlog().getId());
        addPredicateFk("blog","id", criteria.getBlogs());
    }

    public BlogCommentSpecification(BlogCommentCriteria criteria) {
        super(criteria);
    }

    public BlogCommentSpecification(BlogCommentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
