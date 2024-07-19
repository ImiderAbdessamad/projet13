package ma.zs.zyn.dao.criteria.core.blog;



import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class BlogCommentCriteria extends BaseCriteria {

    private LocalDateTime creationDate;
    private LocalDateTime creationDateFrom;
    private LocalDateTime creationDateTo;
    private String content;
    private String contentLike;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private BlogCriteria blog ;
    private List<BlogCriteria> blogs ;


    public BlogCommentCriteria(){}

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
    public BlogCriteria getBlog(){
        return this.blog;
    }

    public void setBlog(BlogCriteria blog){
        this.blog = blog;
    }
    public List<BlogCriteria> getBlogs(){
        return this.blogs;
    }

    public void setBlogs(List<BlogCriteria> blogs){
        this.blogs = blogs;
    }
}
