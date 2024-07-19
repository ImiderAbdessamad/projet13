package ma.zs.zyn.dao.specification.core.contactus;


import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCategoryCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;

public class ContactUsCategorySpecification extends AbstractSpecification<ContactUsCategoryCriteria, ContactUsCategory> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(), criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
    }

    public ContactUsCategorySpecification(ContactUsCategoryCriteria criteria) {
        super(criteria);
    }

    public ContactUsCategorySpecification(ContactUsCategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
