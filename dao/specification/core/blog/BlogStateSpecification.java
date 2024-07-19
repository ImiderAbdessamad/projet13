package ma.zs.zyn.dao.specification.core.blog;

import ma.zs.zyn.dao.criteria.core.blog.BlogStateCriteria;
import ma.zs.zyn.bean.core.blog.BlogState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class BlogStateSpecification extends  AbstractSpecification<BlogStateCriteria, BlogState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public BlogStateSpecification(BlogStateCriteria criteria) {
        super(criteria);
    }

    public BlogStateSpecification(BlogStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
