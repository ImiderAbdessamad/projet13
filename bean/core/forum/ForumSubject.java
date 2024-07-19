package ma.zs.zyn.bean.core.forum;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "forum_subject")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="forum_subject_seq",sequenceName="forum_subject_seq",allocationSize=1, initialValue = 1)
public class ForumSubject  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public ForumSubject(){
        super();
    }

    public ForumSubject(Long id){
        this.id = id;
    }

    public ForumSubject(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ForumSubject(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="forum_subject_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumSubject forumSubject = (ForumSubject) o;
        return id != null && id.equals(forumSubject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

