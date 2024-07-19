package ma.zs.zyn.bean.core.project;





import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conversation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="conversation_seq",sequenceName="conversation_seq",allocationSize=1, initialValue = 1)
public class Conversation  extends BaseEntity     {





    private String prompt;

    private String response;

    private Project project ;


    public Conversation(){
        super();
    }

    public Conversation(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="conversation_seq")
    @Override
    public Long getId(){
        return this.id;
    }
    @Override
    public void setId(Long id){
        this.id = id;
    }
    @Column(columnDefinition = "TEXT")
    public String getPrompt(){
        return this.prompt;
    }
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    @Column(columnDefinition = "TEXT")
    public String getResponse(){
        return this.response;
    }
    public void setResponse(String response){
        this.response = response;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation conversation = (Conversation) o;
        return id != null && id.equals(conversation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

