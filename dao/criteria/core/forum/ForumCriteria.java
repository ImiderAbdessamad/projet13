package  ma.zs.zyn.dao.criteria.core.forum;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ForumCriteria extends  BaseCriteria  {

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
    private ForumSubjectCriteria forumSubject ;
    private List<ForumSubjectCriteria> forumSubjects ;
    private ForumStateCriteria forumState ;
    private List<ForumStateCriteria> forumStates ;


    public ForumCriteria(){}

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
    public ForumSubjectCriteria getForumSubject(){
        return this.forumSubject;
    }

    public void setForumSubject(ForumSubjectCriteria forumSubject){
        this.forumSubject = forumSubject;
    }
    public List<ForumSubjectCriteria> getForumSubjects(){
        return this.forumSubjects;
    }

    public void setForumSubjects(List<ForumSubjectCriteria> forumSubjects){
        this.forumSubjects = forumSubjects;
    }
    public ForumStateCriteria getForumState(){
        return this.forumState;
    }

    public void setForumState(ForumStateCriteria forumState){
        this.forumState = forumState;
    }
    public List<ForumStateCriteria> getForumStates(){
        return this.forumStates;
    }

    public void setForumStates(List<ForumStateCriteria> forumStates){
        this.forumStates = forumStates;
    }
}
