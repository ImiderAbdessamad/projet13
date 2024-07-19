package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.dao.criteria.core.project.ProjectDetailCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectDetailSpecification extends  AbstractSpecification<ProjectDetailCriteria, ProjectDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicate("dbName", criteria.getDbName(),criteria.getDbNameLike());
        addPredicate("dbPassword", criteria.getDbPassword(),criteria.getDbPasswordLike());
        addPredicate("dbUserName", criteria.getDbUserName(),criteria.getDbUserNameLike());
        addPredicate("basePackage", criteria.getBasePackage(),criteria.getBasePackageLike());
        addPredicate("msName", criteria.getMsName(),criteria.getMsNameLike());
        addPredicate("port", criteria.getPort(),criteria.getPortLike());
        addPredicate("portDev", criteria.getPortDev(),criteria.getPortDevLike());
        addPredicate("portTest", criteria.getPortTest(),criteria.getPortTestLike());
        addPredicate("portProd", criteria.getPortProd(),criteria.getPortProdLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateFk("projectTechnology","id", criteria.getProjectTechnology()==null?null:criteria.getProjectTechnology().getId());
        addPredicateFk("projectTechnology","id", criteria.getProjectTechnologys());
        addPredicateFk("projectTechnology","code", criteria.getProjectTechnology()==null?null:criteria.getProjectTechnology().getCode());
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
        addPredicateFk("projectTechnologyProfile","id", criteria.getProjectTechnologyProfile()==null?null:criteria.getProjectTechnologyProfile().getId());
        addPredicateFk("projectTechnologyProfile","id", criteria.getProjectTechnologyProfiles());
        addPredicateFk("projectTechnologyProfile","code", criteria.getProjectTechnologyProfile()==null?null:criteria.getProjectTechnologyProfile().getCode());
    }

    public ProjectDetailSpecification(ProjectDetailCriteria criteria) {
        super(criteria);
    }

    public ProjectDetailSpecification(ProjectDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
