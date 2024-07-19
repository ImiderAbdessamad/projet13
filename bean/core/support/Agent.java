package ma.zs.zyn.bean.core.support;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.zyn.zynerator.security.bean.User;

@Entity
@Table(name = "agent")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="agent_seq",sequenceName="agent_seq",allocationSize=1, initialValue = 1)
public class Agent  extends User    {


    public Agent(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;










    public Agent(){
        super();
    }

    public Agent(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="agent_seq")
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
        Agent agent = (Agent) o;
        return id != null && id.equals(agent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

