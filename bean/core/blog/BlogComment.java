package ma.zs.zyn.bean.core.blog;


import java.time.LocalDateTime;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.zynerator.bean.BaseEntity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "blog_comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="blog_comment_seq",sequenceName="blog_comment_seq",allocationSize=1, initialValue = 1)
public class BlogComment  extends BaseEntity     {




    private LocalDateTime creationDate ;

    @Column(length = 500)
    private String content;

    private Collaborator collaborator ;
    private Blog blog ;


    public BlogComment(){
        super();
    }

    public BlogComment(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="blog_comment_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog")
    public Blog getBlog(){
        return this.blog;
    }
    public void setBlog(Blog blog){
        this.blog = blog;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogComment blogComment = (BlogComment) o;
        return id != null && id.equals(blogComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

