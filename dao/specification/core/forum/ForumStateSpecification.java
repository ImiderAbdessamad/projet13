package ma.zs.zyn.dao.specification.core.forum;

import ma.zs.zyn.dao.criteria.core.forum.ForumStateCriteria;
import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ForumStateSpecification extends  AbstractSpecification<ForumStateCriteria, ForumState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ForumStateSpecification(ForumStateCriteria criteria) {
        super(criteria);
    }

    public ForumStateSpecification(ForumStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
