package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyCriteria;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectTechnologySpecification extends  AbstractSpecification<ProjectTechnologyCriteria, ProjectTechnology>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("defaultDbName", criteria.getDefaultDbName(),criteria.getDefaultDbNameLike());
        addPredicate("defaultUserName", criteria.getDefaultUserName(),criteria.getDefaultUserNameLike());
        addPredicate("defaultUserPassword", criteria.getDefaultUserPassword(),criteria.getDefaultUserPasswordLike());
        addPredicate("defaultPort", criteria.getDefaultPort(),criteria.getDefaultPortLike());
        addPredicate("defaultBasePackage", criteria.getDefaultBasePackage(),criteria.getDefaultBasePackageLike());
        addPredicateFk("projectTechnologyType","id", criteria.getProjectTechnologyType()==null?null:criteria.getProjectTechnologyType().getId());
        addPredicateFk("projectTechnologyType","id", criteria.getProjectTechnologyTypes());
        addPredicateFk("projectTechnologyType","code", criteria.getProjectTechnologyType()==null?null:criteria.getProjectTechnologyType().getCode());
    }

    public ProjectTechnologySpecification(ProjectTechnologyCriteria criteria) {
        super(criteria);
    }

    public ProjectTechnologySpecification(ProjectTechnologyCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
