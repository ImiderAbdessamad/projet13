package  ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.ws.dto.project.ConversationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto  extends AuditBaseDto {

    private String title  ;
    private String titleChat  ;
    private String generatedDate ;
    private LocalDate chatDateStart ;
    private Boolean microService  ;
    private Boolean microFront  ;

    private CollaboratorDto collaborator ;
    private RemoteRepoInfoDto remoteRepoInfo ;

    private List<ConversationDto> conversations ;
    private List<ProjectDetailDto> projectDetails ;


    public ProjectDto(){
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
    public String getTitleChat(){
        return this.titleChat;
    }
    public void setTitleChat(String titleChat){
        this.titleChat = titleChat;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(String generatedDate){
        this.generatedDate = generatedDate;
    }

    @Log

    public LocalDate getChatDateStart(){
        return this.chatDateStart;
    }
    public void setChatDateStart(LocalDate chatDateStart){
        this.chatDateStart = chatDateStart;
    }

    @Log
    public Boolean getMicroService(){
        return this.microService;
    }
    public void setMicroService(Boolean microService){
        this.microService = microService;
    }

    @Log
    public Boolean getMicroFront(){
        return this.microFront;
    }
    public void setMicroFront(Boolean microFront){
        this.microFront = microFront;
    }


    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public RemoteRepoInfoDto getRemoteRepoInfo(){
        return this.remoteRepoInfo;
    }

    public void setRemoteRepoInfo(RemoteRepoInfoDto remoteRepoInfo){
        this.remoteRepoInfo = remoteRepoInfo;
    }



    public List<ConversationDto> getConversations(){
        return this.conversations;
    }

    public void setConversations(List<ConversationDto> conversations){
        this.conversations = conversations;
    }
    public List<ProjectDetailDto> getProjectDetails(){
        return this.projectDetails;
    }

    public void setProjectDetails(List<ProjectDetailDto> projectDetails){
        this.projectDetails = projectDetails;
    }



}
