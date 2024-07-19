package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectSpecification extends  AbstractSpecification<ProjectCriteria, Project>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicate("titleChat", criteria.getTitleChat(),criteria.getTitleChatLike());
        addPredicate("generatedDate", criteria.getGeneratedDate(), criteria.getGeneratedDateFrom(), criteria.getGeneratedDateTo());
        addPredicate("chatDateStart", criteria.getChatDateStart(), criteria.getChatDateStartFrom(), criteria.getChatDateStartTo());
        addPredicateBool("microService", criteria.getMicroService());
        addPredicateBool("microFront", criteria.getMicroFront());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("remoteRepoInfo","id", criteria.getRemoteRepoInfo()==null?null:criteria.getRemoteRepoInfo().getId());
        addPredicateFk("remoteRepoInfo","id", criteria.getRemoteRepoInfos());
    }

    public ProjectSpecification(ProjectCriteria criteria) {
        super(criteria);
    }

    public ProjectSpecification(ProjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
