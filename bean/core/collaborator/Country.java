package ma.zs.zyn.bean.core.collaborator;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "country")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="country_seq",sequenceName="country_seq",allocationSize=1, initialValue = 1)
public class Country  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public Country(){
        super();
    }

    public Country(Long id){
        this.id = id;
    }

    public Country(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Country(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="country_seq")
    public Long getId(){
        return this.id;
    }
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
        Country country = (Country) o;
        return id != null && id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

