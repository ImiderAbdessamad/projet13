package ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoInfoCriteria;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class RemoteRepoInfoSpecification extends  AbstractSpecification<RemoteRepoInfoCriteria, RemoteRepoInfo>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("token", criteria.getToken(),criteria.getTokenLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateFk("remoteRepoType","id", criteria.getRemoteRepoType()==null?null:criteria.getRemoteRepoType().getId());
        addPredicateFk("remoteRepoType","id", criteria.getRemoteRepoTypes());
        addPredicateFk("remoteRepoType","code", criteria.getRemoteRepoType()==null?null:criteria.getRemoteRepoType().getCode());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
    }

    public RemoteRepoInfoSpecification(RemoteRepoInfoCriteria criteria) {
        super(criteria);
    }

    public RemoteRepoInfoSpecification(RemoteRepoInfoCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
