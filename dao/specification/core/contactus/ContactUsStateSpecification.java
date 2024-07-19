package ma.zs.zyn.dao.specification.core.contactus;

import ma.zs.zyn.dao.criteria.core.contactus.ContactUsStateCriteria;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ContactUsStateSpecification extends  AbstractSpecification<ContactUsStateCriteria, ContactUsState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ContactUsStateSpecification(ContactUsStateCriteria criteria) {
        super(criteria);
    }

    public ContactUsStateSpecification(ContactUsStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
