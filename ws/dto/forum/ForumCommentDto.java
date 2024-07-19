package  ma.zs.zyn.ws.dto.forum;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumCommentDto  extends AuditBaseDto {

    private String creationDate ;
    private String content  ;

    private CollaboratorDto collaborator ;
    private ForumDto forum ;



    public ForumCommentDto(){
        super();
    }



    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    @Log
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }


    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public ForumDto getForum(){
        return this.forum;
    }

    public void setForum(ForumDto forum){
        this.forum = forum;
    }






}
