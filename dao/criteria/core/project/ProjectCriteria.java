package ma.zs.zyn.dao.criteria.core.project;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ProjectCriteria extends  BaseCriteria  {

    private String title;
    private String titleLike;
    private String titleChat;
    private String titleChatLike;
    private LocalDateTime generatedDate;
    private LocalDateTime generatedDateFrom;
    private LocalDateTime generatedDateTo;
    private LocalDateTime chatDateStart;
    private LocalDateTime chatDateStartFrom;
    private LocalDateTime chatDateStartTo;
    private Boolean microService;
    private Boolean microFront;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private RemoteRepoInfoCriteria remoteRepoInfo ;
    private List<RemoteRepoInfoCriteria> remoteRepoInfos ;


    public ProjectCriteria(){}

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

    public String getTitleChat(){
        return this.titleChat;
    }
    public void setTitleChat(String titleChat){
        this.titleChat = titleChat;
    }
    public String getTitleChatLike(){
        return this.titleChatLike;
    }
    public void setTitleChatLike(String titleChatLike){
        this.titleChatLike = titleChatLike;
    }

    public LocalDateTime getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(LocalDateTime generatedDate){
        this.generatedDate = generatedDate;
    }
    public LocalDateTime getGeneratedDateFrom(){
        return this.generatedDateFrom;
    }
    public void setGeneratedDateFrom(LocalDateTime generatedDateFrom){
        this.generatedDateFrom = generatedDateFrom;
    }
    public LocalDateTime getGeneratedDateTo(){
        return this.generatedDateTo;
    }
    public void setGeneratedDateTo(LocalDateTime generatedDateTo){
        this.generatedDateTo = generatedDateTo;
    }
    public LocalDateTime getChatDateStart(){
        return this.chatDateStart;
    }
    public void setChatDateStart(LocalDateTime chatDateStart){
        this.chatDateStart = chatDateStart;
    }
    public LocalDateTime getChatDateStartFrom(){
        return this.chatDateStartFrom;
    }
    public void setChatDateStartFrom(LocalDateTime chatDateStartFrom){
        this.chatDateStartFrom = chatDateStartFrom;
    }
    public LocalDateTime getChatDateStartTo(){
        return this.chatDateStartTo;
    }
    public void setChatDateStartTo(LocalDateTime chatDateStartTo){
        this.chatDateStartTo = chatDateStartTo;
    }
    public Boolean getMicroService(){
        return this.microService;
    }
    public void setMicroService(Boolean microService){
        this.microService = microService;
    }
    public Boolean getMicroFront(){
        return this.microFront;
    }
    public void setMicroFront(Boolean microFront){
        this.microFront = microFront;
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
    public RemoteRepoInfoCriteria getRemoteRepoInfo(){
        return this.remoteRepoInfo;
    }

    public void setRemoteRepoInfo(RemoteRepoInfoCriteria remoteRepoInfo){
        this.remoteRepoInfo = remoteRepoInfo;
    }
    public List<RemoteRepoInfoCriteria> getRemoteRepoInfos(){
        return this.remoteRepoInfos;
    }

    public void setRemoteRepoInfos(List<RemoteRepoInfoCriteria> remoteRepoInfos){
        this.remoteRepoInfos = remoteRepoInfos;
    }
}
