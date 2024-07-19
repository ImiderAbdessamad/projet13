package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.RemoteRepoTypeCriteria;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class RemoteRepoTypeSpecification extends  AbstractSpecification<RemoteRepoTypeCriteria, RemoteRepoType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public RemoteRepoTypeSpecification(RemoteRepoTypeCriteria criteria) {
        super(criteria);
    }

    public RemoteRepoTypeSpecification(RemoteRepoTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
