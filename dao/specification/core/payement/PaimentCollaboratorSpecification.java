package ma.zs.zyn.dao.specification.core.payement;

import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCollaboratorSpecification extends  AbstractSpecification<PaimentCollaboratorCriteria, PaimentCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("cardHolder", criteria.getCardHolder(),criteria.getCardHolderLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("cardNumber", criteria.getCardNumber(),criteria.getCardNumberLike());
        addPredicate("expirationDate", criteria.getExpirationDate(),criteria.getExpirationDateLike());
        addPredicate("cvc", criteria.getCvc(),criteria.getCvcLike());
        addPredicate("postal", criteria.getPostal(),criteria.getPostalLike());
        addPredicateBigDecimal("amountToPaid", criteria.getAmountToPaid(), criteria.getAmountToPaidMin(), criteria.getAmountToPaidMax());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateBigDecimal("consumedEntity", criteria.getConsumedEntity(), criteria.getConsumedEntityMin(), criteria.getConsumedEntityMax());
        addPredicateBigDecimal("consumedProjet", criteria.getConsumedProjet(), criteria.getConsumedProjetMin(), criteria.getConsumedProjetMax());
        addPredicateBigDecimal("consumedAttribut", criteria.getConsumedAttribut(), criteria.getConsumedAttributMin(), criteria.getConsumedAttributMax());
        addPredicateBigDecimal("consumedTokenInput", criteria.getConsumedTokenInput(), criteria.getConsumedTokenInputMin(), criteria.getConsumedTokenInputMax());
        addPredicateBigDecimal("consumedTokenOutput", criteria.getConsumedTokenOutput(), criteria.getConsumedTokenOutputMin(), criteria.getConsumedTokenOutputMax());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("discount", criteria.getDiscount(), criteria.getDiscountMin(), criteria.getDiscountMax());
        addPredicateBigDecimal("remaining", criteria.getRemaining(), criteria.getRemainingMin(), criteria.getRemainingMax());
        addPredicate("paiementDate", criteria.getPaiementDate(), criteria.getPaiementDateFrom(), criteria.getPaiementDateTo());
        addPredicateFk("country","id", criteria.getCountry()==null?null:criteria.getCountry().getId());
        addPredicateFk("country","id", criteria.getCountrys());
        addPredicateFk("country","code", criteria.getCountry()==null?null:criteria.getCountry().getCode());
        addPredicateFk("city","id", criteria.getCity()==null?null:criteria.getCity().getId());
        addPredicateFk("city","id", criteria.getCitys());
        addPredicateFk("city","code", criteria.getCity()==null?null:criteria.getCity().getCode());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("packaging","id", criteria.getPackaging()==null?null:criteria.getPackaging().getId());
        addPredicateFk("packaging","id", criteria.getPackagings());
        addPredicateFk("packaging","code", criteria.getPackaging()==null?null:criteria.getPackaging().getCode());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getId());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorStates());
        addPredicateFk("paimentCollaboratorState","code", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getCode());
        addPredicateFk("paimentCollaboratorType","id", criteria.getPaimentCollaboratorType()==null?null:criteria.getPaimentCollaboratorType().getId());
        addPredicateFk("paimentCollaboratorType","id", criteria.getPaimentCollaboratorTypes());
        addPredicateFk("paimentCollaboratorType","code", criteria.getPaimentCollaboratorType()==null?null:criteria.getPaimentCollaboratorType().getCode());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborator()==null?null:criteria.getInscriptionCollaborator().getId());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborators());
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria) {
        super(criteria);
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
