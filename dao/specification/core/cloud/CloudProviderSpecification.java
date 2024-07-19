package ma.zs.zyn.dao.specification.core.cloud;

import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.CloudProviderCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CloudProviderSpecification extends  AbstractSpecification<CloudProviderCriteria, CloudProvider>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CloudProviderSpecification(CloudProviderCriteria criteria) {
        super(criteria);
    }

    public CloudProviderSpecification(CloudProviderCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
