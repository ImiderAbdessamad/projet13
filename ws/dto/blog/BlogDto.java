package ma.zs.zyn.ws.dto.blog;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogDto  extends AuditBaseDto {

    private String content  ;
    private String creationDate ;
    private String publicationDate ;
    private String title  ;
    private BigDecimal likes  ;
    private BigDecimal comments  ;
    private String description  ;

    private CollaboratorDto collaborator ;
    private BlogSubjectDto blogSubject ;
    private BlogStateDto blogState ;

    private List<BlogCommentDto> blogComments ;


    public BlogDto(){
        super();
    }



    @Log
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPublicationDate(){
        return this.publicationDate;
    }
    public void setPublicationDate(String publicationDate){
        this.publicationDate = publicationDate;
    }

    @Log
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Log
    public BigDecimal getLikes(){
        return this.likes;
    }
    public void setLikes(BigDecimal likes){
        this.likes = likes;
    }

    @Log
    public BigDecimal getComments(){
        return this.comments;
    }
    public void setComments(BigDecimal comments){
        this.comments = comments;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public BlogSubjectDto getBlogSubject(){
        return this.blogSubject;
    }

    public void setBlogSubject(BlogSubjectDto blogSubject){
        this.blogSubject = blogSubject;
    }
    public BlogStateDto getBlogState(){
        return this.blogState;
    }

    public void setBlogState(BlogStateDto blogState){
        this.blogState = blogState;
    }



    public List<BlogCommentDto> getBlogComments(){
        return this.blogComments;
    }

    public void setBlogComments(List<BlogCommentDto> blogComments){
        this.blogComments = blogComments;
    }



}
