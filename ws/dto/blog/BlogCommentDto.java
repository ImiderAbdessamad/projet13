package ma.zs.zyn.ws.dto.blog;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogCommentDto  extends AuditBaseDto {

    private String creationDate ;
    private String content  ;

    private CollaboratorDto collaborator ;
    private BlogDto blog ;



    public BlogCommentDto(){
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
    public BlogDto getBlog(){
        return this.blog;
    }

    public void setBlog(BlogDto blog){
        this.blog = blog;
    }






}
