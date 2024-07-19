package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyProfileCriteria;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectTechnologyProfileSpecification extends  AbstractSpecification<ProjectTechnologyProfileCriteria, ProjectTechnologyProfile>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ProjectTechnologyProfileSpecification(ProjectTechnologyProfileCriteria criteria) {
        super(criteria);
    }

    public ProjectTechnologyProfileSpecification(ProjectTechnologyProfileCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
