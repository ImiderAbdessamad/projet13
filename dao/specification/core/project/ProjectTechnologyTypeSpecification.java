package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyTypeCriteria;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectTechnologyTypeSpecification extends  AbstractSpecification<ProjectTechnologyTypeCriteria, ProjectTechnologyType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ProjectTechnologyTypeSpecification(ProjectTechnologyTypeCriteria criteria) {
        super(criteria);
    }

    public ProjectTechnologyTypeSpecification(ProjectTechnologyTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
