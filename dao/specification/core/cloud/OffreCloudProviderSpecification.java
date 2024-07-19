package ma.zs.zyn.dao.specification.core.cloud;

import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.OffreCloudProviderCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class OffreCloudProviderSpecification extends  AbstractSpecification<OffreCloudProviderCriteria, OffreCloudProvider>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateFk("cloudProvider","id", criteria.getCloudProvider()==null?null:criteria.getCloudProvider().getId());
        addPredicateFk("cloudProvider","id", criteria.getCloudProviders());
        addPredicateFk("cloudProvider","code", criteria.getCloudProvider()==null?null:criteria.getCloudProvider().getCode());
    }

    public OffreCloudProviderSpecification(OffreCloudProviderCriteria criteria) {
        super(criteria);
    }

    public OffreCloudProviderSpecification(OffreCloudProviderCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
