package ma.zs.zyn.bean.core.blog;

import java.util.List;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "blog")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="blog_seq",sequenceName="blog_seq",allocationSize=1, initialValue = 1)
public class Blog  extends BaseEntity {




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
    private BlogSubject blogSubject ;
    private BlogState blogState ;

    private List<BlogComment> blogComments ;

    public Blog(){
        super();
    }

    public Blog(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="blog_seq")
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
    @JoinColumn(name = "blog_subject")
    public BlogSubject getBlogSubject(){
        return this.blogSubject;
    }
    public void setBlogSubject(BlogSubject blogSubject){
        this.blogSubject = blogSubject;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_state")
    public BlogState getBlogState(){
        return this.blogState;
    }
    public void setBlogState(BlogState blogState){
        this.blogState = blogState;
    }
    @OneToMany(mappedBy = "blog")
    public List<BlogComment> getBlogComments(){
        return this.blogComments;
    }

    public void setBlogComments(List<BlogComment> blogComments){
        this.blogComments = blogComments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id != null && id.equals(blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

