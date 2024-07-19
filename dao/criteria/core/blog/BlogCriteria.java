package ma.zs.zyn.dao.criteria.core.blog;

import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class BlogCriteria extends  BaseCriteria  {

    private String content;
    private String contentLike;
    private LocalDateTime creationDate;
    private LocalDateTime creationDateFrom;
    private LocalDateTime creationDateTo;
    private LocalDateTime publicationDate;
    private LocalDateTime publicationDateFrom;
    private LocalDateTime publicationDateTo;
    private String title;
    private String titleLike;
    private String likes;
    private String likesMin;
    private String likesMax;
    private String comments;
    private String commentsMin;
    private String commentsMax;
    private String description;
    private String descriptionLike;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private BlogSubjectCriteria blogSubject ;
    private List<BlogSubjectCriteria> blogSubjects ;
    private BlogStateCriteria blogState ;
    private List<BlogStateCriteria> blogStates ;


    public BlogCriteria(){}

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
    public LocalDateTime getPublicationDate(){
        return this.publicationDate;
    }
    public void setPublicationDate(LocalDateTime publicationDate){
        this.publicationDate = publicationDate;
    }
    public LocalDateTime getPublicationDateFrom(){
        return this.publicationDateFrom;
    }
    public void setPublicationDateFrom(LocalDateTime publicationDateFrom){
        this.publicationDateFrom = publicationDateFrom;
    }
    public LocalDateTime getPublicationDateTo(){
        return this.publicationDateTo;
    }
    public void setPublicationDateTo(LocalDateTime publicationDateTo){
        this.publicationDateTo = publicationDateTo;
    }
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

    public String getLikes(){
        return this.likes;
    }
    public void setLikes(String likes){
        this.likes = likes;
    }   
    public String getLikesMin(){
        return this.likesMin;
    }
    public void setLikesMin(String likesMin){
        this.likesMin = likesMin;
    }
    public String getLikesMax(){
        return this.likesMax;
    }
    public void setLikesMax(String likesMax){
        this.likesMax = likesMax;
    }
      
    public String getComments(){
        return this.comments;
    }
    public void setComments(String comments){
        this.comments = comments;
    }   
    public String getCommentsMin(){
        return this.commentsMin;
    }
    public void setCommentsMin(String commentsMin){
        this.commentsMin = commentsMin;
    }
    public String getCommentsMax(){
        return this.commentsMax;
    }
    public void setCommentsMax(String commentsMax){
        this.commentsMax = commentsMax;
    }
      
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
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
    public BlogSubjectCriteria getBlogSubject(){
        return this.blogSubject;
    }

    public void setBlogSubject(BlogSubjectCriteria blogSubject){
        this.blogSubject = blogSubject;
    }
    public List<BlogSubjectCriteria> getBlogSubjects(){
        return this.blogSubjects;
    }

    public void setBlogSubjects(List<BlogSubjectCriteria> blogSubjects){
        this.blogSubjects = blogSubjects;
    }
    public BlogStateCriteria getBlogState(){
        return this.blogState;
    }

    public void setBlogState(BlogStateCriteria blogState){
        this.blogState = blogState;
    }
    public List<BlogStateCriteria> getBlogStates(){
        return this.blogStates;
    }

    public void setBlogStates(List<BlogStateCriteria> blogStates){
        this.blogStates = blogStates;
    }
}
