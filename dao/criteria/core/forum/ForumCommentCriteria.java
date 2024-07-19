package  ma.zs.zyn.dao.criteria.core.forum;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ForumCommentCriteria extends  BaseCriteria  {

    private LocalDateTime creationDate;
    private LocalDateTime creationDateFrom;
    private LocalDateTime creationDateTo;
    private String content;
    private String contentLike;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private ForumCriteria forum ;
    private List<ForumCriteria> forums ;


    public ForumCommentCriteria(){}

    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public LocalDateTime getCreationDateFrom(){
        return this.creationDateFrom;
    }
    public void setCreationDateFrom(LocalDateTime creationDateFrom){
        this.creationDateFrom = creationDateFrom;
    }
    public LocalDateTime getCreationDateTo(){
        return this.creationDateTo;
    }
    public void setCreationDateTo(LocalDateTime creationDateTo){
        this.creationDateTo = creationDateTo;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContentLike(){
        return this.contentLike;
    }
    public void setContentLike(String contentLike){
        this.contentLike = contentLike;
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
    public ForumCriteria getForum(){
        return this.forum;
    }

    public void setForum(ForumCriteria forum){
        this.forum = forum;
    }
    public List<ForumCriteria> getForums(){
        return this.forums;
    }

    public void setForums(List<ForumCriteria> forums){
        this.forums = forums;
    }
}
