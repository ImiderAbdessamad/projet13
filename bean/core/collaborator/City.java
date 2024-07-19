package ma.zs.zyn.bean.core.collaborator;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "city")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="city_seq",sequenceName="city_seq",allocationSize=1, initialValue = 1)
public class City  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private Country country ;


    public City(){
        super();
    }

    public City(Long id){
        this.id = id;
    }

    public City(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public City(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="city_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    public Country getCountry(){
        return this.country;
    }
    public void setCountry(Country country){
        this.country = country;
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
        City city = (City) o;
        return id != null && id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

