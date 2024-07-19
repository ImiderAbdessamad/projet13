package ma.zs.zyn.dao.specification.core.contactus;


import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;

public class ContactUsSpecification extends AbstractSpecification<ContactUsCriteria, ContactUs> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("phone", criteria.getPhone(),criteria.getPhoneLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("object", criteria.getObject(),criteria.getObjectLike());
        addPredicate("message", criteria.getMessage(),criteria.getMessageLike());
        addPredicateFk("contactUsCategory","id", criteria.getContactUsCategory()==null?null:criteria.getContactUsCategory().getId());
        addPredicateFk("contactUsCategory","id", criteria.getContactUsCategorys());
        addPredicateFk("contactUsCategory","code", criteria.getContactUsCategory()==null?null:criteria.getContactUsCategory().getCode());
        addPredicateFk("contactUsState","id", criteria.getContactUsState()==null?null:criteria.getContactUsState().getId());
        addPredicateFk("contactUsState","id", criteria.getContactUsStates());
        addPredicateFk("contactUsState","code", criteria.getContactUsState()==null?null:criteria.getContactUsState().getCode());
    }

    public ContactUsSpecification(ContactUsCriteria criteria) {
        super(criteria);
    }

    public ContactUsSpecification(ContactUsCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
