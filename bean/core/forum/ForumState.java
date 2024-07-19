package ma.zs.zyn.bean.core.forum;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "forum_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="forum_state_seq",sequenceName="forum_state_seq",allocationSize=1, initialValue = 1)
public class ForumState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public ForumState(){
        super();
    }

    public ForumState(Long id){
        this.id = id;
    }

    public ForumState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ForumState(String libelle){
        this.libelle = libelle ;
    }
    public ForumState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="forum_state_seq")
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
        ForumState forumState = (ForumState) o;
        return id != null && id.equals(forumState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

