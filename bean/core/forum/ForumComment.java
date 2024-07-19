package ma.zs.zyn.bean.core.forum;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.collaborator.Collaborator;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "forum_comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="forum_comment_seq",sequenceName="forum_comment_seq",allocationSize=1, initialValue = 1)
public class ForumComment  extends BaseEntity     {




    private LocalDateTime creationDate ;

    @Column(length = 500)
    private String content;

    private Collaborator collaborator ;
    private Forum forum ;


    public ForumComment(){
        super();
    }

    public ForumComment(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="forum_comment_seq")
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
    @JoinColumn(name = "forum")
    public Forum getForum(){
        return this.forum;
    }
    public void setForum(Forum forum){
        this.forum = forum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumComment forumComment = (ForumComment) o;
        return id != null && id.equals(forumComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

