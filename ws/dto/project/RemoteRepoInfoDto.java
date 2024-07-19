package ma.zs.zyn.ws.dto.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemoteRepoInfoDto  extends AuditBaseDto {

    private String title  ;
    private String username  ;
    private String token  ;
    private String name  ;

    private RemoteRepoTypeDto remoteRepoType ;
    private CollaboratorDto collaborator ;



    public RemoteRepoInfoDto(){
        super();
    }



    @Log
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Log
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    @Log
    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token = token;
    }

    @Log
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public RemoteRepoTypeDto getRemoteRepoType(){
        return this.remoteRepoType;
    }

    public void setRemoteRepoType(RemoteRepoTypeDto remoteRepoType){
        this.remoteRepoType = remoteRepoType;
    }
    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }






}
