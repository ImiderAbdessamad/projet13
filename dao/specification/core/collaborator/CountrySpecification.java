package ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.dao.criteria.core.collaborator.CountryCriteria;
import ma.zs.zyn.bean.core.collaborator.Country;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CountrySpecification extends  AbstractSpecification<CountryCriteria, Country>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CountrySpecification(CountryCriteria criteria) {
        super(criteria);
    }

    public CountrySpecification(CountryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
