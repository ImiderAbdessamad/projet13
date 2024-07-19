package ma.zs.zyn.bean.core.collaborator;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.security.bean.User;

import java.util.Objects;

@Entity
@Table(name = "collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="collaborator_seq",sequenceName="collaborator_seq",allocationSize=1, initialValue = 1)
public class Collaborator  extends User    {


    public Collaborator(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String postal;








    private Country country ;
    private City city ;


    public Collaborator(){
        super();
    }

    public Collaborator(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="collaborator_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    public Country getCountry(){
        return this.country;
    }
    public void setCountry(Country country){
        this.country = country;
    }
    public String getPostal(){
        return this.postal;
    }
    public void setPostal(String postal){
        this.postal = postal;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    public City getCity(){
        return this.city;
    }
    public void setCity(City city){
        this.city = city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator collaborator = (Collaborator) o;
        return id != null && id.equals(collaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

