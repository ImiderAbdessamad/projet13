package ma.zs.zyn.bean.core.coupon;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.zyn.zynerator.security.bean.User;

@Entity
@Table(name = "influenceur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="influenceur_seq",sequenceName="influenceur_seq",allocationSize=1, initialValue = 1)
public class Influenceur  extends User    {


    public Influenceur(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;










    public Influenceur(){
        super();
    }

    public Influenceur(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="influenceur_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influenceur influenceur = (Influenceur) o;
        return id != null && id.equals(influenceur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

