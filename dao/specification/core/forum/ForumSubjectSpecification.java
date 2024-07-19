package  ma.zs.zyn.dao.specification.core.forum;

import ma.zs.zyn.dao.criteria.core.forum.ForumSubjectCriteria;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ForumSubjectSpecification extends  AbstractSpecification<ForumSubjectCriteria, ForumSubject>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public ForumSubjectSpecification(ForumSubjectCriteria criteria) {
        super(criteria);
    }

    public ForumSubjectSpecification(ForumSubjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
