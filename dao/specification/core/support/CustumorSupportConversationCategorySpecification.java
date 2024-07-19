package ma.zs.zyn.dao.specification.core.support;

import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCategoryCriteria;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CustumorSupportConversationCategorySpecification extends  AbstractSpecification<CustumorSupportConversationCategoryCriteria, CustumorSupportConversationCategory>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CustumorSupportConversationCategorySpecification(CustumorSupportConversationCategoryCriteria criteria) {
        super(criteria);
    }

    public CustumorSupportConversationCategorySpecification(CustumorSupportConversationCategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
