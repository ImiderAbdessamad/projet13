package ma.zs.zyn.bean.core.forum;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.collaborator.Collaborator;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "forum")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="forum_seq",sequenceName="forum_seq",allocationSize=1, initialValue = 1)
public class Forum  extends BaseEntity     {




    @Column(length = 500)
    private String content;

    private LocalDateTime creationDate ;

    private LocalDateTime publicationDate ;

    @Column(length = 500)
    private String title;

    private BigDecimal likes = BigDecimal.ZERO;

    private BigDecimal comments = BigDecimal.ZERO;

    @Column(length = 500)
    private String description;

    private Collaborator collaborator ;
    private ForumSubject forumSubject ;
    private ForumState forumState ;

    private List<ForumComment> forumComments ;

    public Forum(){
        super();
    }

    public Forum(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="forum_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
    }
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public LocalDateTime getPublicationDate(){
        return this.publicationDate;
    }
    public void setPublicationDate(LocalDateTime publicationDate){
        this.publicationDate = publicationDate;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public BigDecimal getLikes(){
        return this.likes;
    }
    public void setLikes(BigDecimal likes){
        this.likes = likes;
    }
    public BigDecimal getComments(){
        return this.comments;
    }
    public void setComments(BigDecimal comments){
        this.comments = comments;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_subject")
    public ForumSubject getForumSubject(){
        return this.forumSubject;
    }
    public void setForumSubject(ForumSubject forumSubject){
        this.forumSubject = forumSubject;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_state")
    public ForumState getForumState(){
        return this.forumState;
    }
    public void setForumState(ForumState forumState){
        this.forumState = forumState;
    }
    @OneToMany(mappedBy = "forum")
    public List<ForumComment> getForumComments(){
        return this.forumComments;
    }

    public void setForumComments(List<ForumComment> forumComments){
        this.forumComments = forumComments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forum forum = (Forum) o;
        return id != null && id.equals(forum.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

