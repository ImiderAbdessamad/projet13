package ma.zs.zyn.bean.core.contactus;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_us_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="contact_us_state_seq",sequenceName="contact_us_state_seq",allocationSize=1, initialValue = 1)
public class ContactUsState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public ContactUsState(){
        super();
    }

    public ContactUsState(Long id){
        this.id = id;
    }

    public ContactUsState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ContactUsState(String libelle){
        this.libelle = libelle ;
    }
    public ContactUsState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="contact_us_state_seq")
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
        ContactUsState contactUsState = (ContactUsState) o;
        return id != null && id.equals(contactUsState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

