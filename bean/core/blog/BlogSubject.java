package ma.zs.zyn.bean.core.blog;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "blog_subject")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="blog_subject_seq",sequenceName="blog_subject_seq",allocationSize=1, initialValue = 1)
public class BlogSubject  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public BlogSubject(){
        super();
    }

    public BlogSubject(Long id){
        this.id = id;
    }

    public BlogSubject(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public BlogSubject(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="blog_subject_seq")
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
        BlogSubject blogSubject = (BlogSubject) o;
        return id != null && id.equals(blogSubject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

