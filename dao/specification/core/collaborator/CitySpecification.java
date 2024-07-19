package ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.dao.criteria.core.collaborator.CityCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CitySpecification extends  AbstractSpecification<CityCriteria, City>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("country","id", criteria.getCountry()==null?null:criteria.getCountry().getId());
        addPredicateFk("country","id", criteria.getCountrys());
        addPredicateFk("country","code", criteria.getCountry()==null?null:criteria.getCountry().getCode());
    }

    public CitySpecification(CityCriteria criteria) {
        super(criteria);
    }

    public CitySpecification(CityCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
