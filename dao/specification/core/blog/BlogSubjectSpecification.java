package ma.zs.zyn.dao.specification.core.blog;

import ma.zs.zyn.dao.criteria.core.blog.BlogSubjectCriteria;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class BlogSubjectSpecification extends  AbstractSpecification<BlogSubjectCriteria, BlogSubject>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public BlogSubjectSpecification(BlogSubjectCriteria criteria) {
        super(criteria);
    }

    public BlogSubjectSpecification(BlogSubjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
