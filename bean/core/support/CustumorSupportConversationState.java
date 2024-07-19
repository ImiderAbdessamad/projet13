package ma.zs.zyn.bean.core.support;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "custumor_support_conversation_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="custumor_support_conversation_state_seq",sequenceName="custumor_support_conversation_state_seq",allocationSize=1, initialValue = 1)
public class CustumorSupportConversationState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public CustumorSupportConversationState(){
        super();
    }

    public CustumorSupportConversationState(Long id){
        this.id = id;
    }

    public CustumorSupportConversationState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CustumorSupportConversationState(String libelle){
        this.libelle = libelle ;
    }
    public CustumorSupportConversationState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="custumor_support_conversation_state_seq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustumorSupportConversationState custumorSupportConversationState = (CustumorSupportConversationState) o;
        return id != null && id.equals(custumorSupportConversationState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

