package ma.zs.zyn.bean.core.blog;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "blog_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="blog_state_seq",sequenceName="blog_state_seq",allocationSize=1, initialValue = 1)
public class BlogState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public BlogState(){
        super();
    }

    public BlogState(Long id){
        this.id = id;
    }

    public BlogState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public BlogState(String libelle){
        this.libelle = libelle ;
    }
    public BlogState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="blog_state_seq")
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
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
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
        BlogState blogState = (BlogState) o;
        return id != null && id.equals(blogState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

