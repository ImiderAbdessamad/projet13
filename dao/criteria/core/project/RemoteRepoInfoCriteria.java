package ma.zs.zyn.dao.criteria.core.project;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class RemoteRepoInfoCriteria extends  BaseCriteria  {

    private String title;
    private String titleLike;
    private String username;
    private String usernameLike;
    private String token;
    private String tokenLike;
    private String name;
    private String nameLike;

    private RemoteRepoTypeCriteria remoteRepoType ;
    private List<RemoteRepoTypeCriteria> remoteRepoTypes ;
    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;


    public RemoteRepoInfoCriteria(){}

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitleLike(){
        return this.titleLike;
    }
    public void setTitleLike(String titleLike){
        this.titleLike = titleLike;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getTokenLike(){
        return this.tokenLike;
    }
    public void setTokenLike(String tokenLike){
        this.tokenLike = tokenLike;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }


    public RemoteRepoTypeCriteria getRemoteRepoType(){
        return this.remoteRepoType;
    }

    public void setRemoteRepoType(RemoteRepoTypeCriteria remoteRepoType){
        this.remoteRepoType = remoteRepoType;
    }
    public List<RemoteRepoTypeCriteria> getRemoteRepoTypes(){
        return this.remoteRepoTypes;
    }

    public void setRemoteRepoTypes(List<RemoteRepoTypeCriteria> remoteRepoTypes){
        this.remoteRepoTypes = remoteRepoTypes;
    }
    public CollaboratorCriteria getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorCriteria collaborator){
        this.collaborator = collaborator;
    }
    public List<CollaboratorCriteria> getCollaborators(){
        return this.collaborators;
    }

    public void setCollaborators(List<CollaboratorCriteria> collaborators){
        this.collaborators = collaborators;
    }
}
