package ma.zs.zyn.dao.specification.core.payement;

import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorTypeCriteria;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCollaboratorTypeSpecification extends  AbstractSpecification<PaimentCollaboratorTypeCriteria, PaimentCollaboratorType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PaimentCollaboratorTypeSpecification(PaimentCollaboratorTypeCriteria criteria) {
        super(criteria);
    }

    public PaimentCollaboratorTypeSpecification(PaimentCollaboratorTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
