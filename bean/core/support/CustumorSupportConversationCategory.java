package ma.zs.zyn.bean.core.support;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "custumor_support_conversation_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="custumor_support_conversation_category_seq",sequenceName="custumor_support_conversation_category_seq",allocationSize=1, initialValue = 1)
public class CustumorSupportConversationCategory  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public CustumorSupportConversationCategory(){
        super();
    }

    public CustumorSupportConversationCategory(Long id){
        this.id = id;
    }

    public CustumorSupportConversationCategory(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CustumorSupportConversationCategory(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="custumor_support_conversation_category_seq")
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
        CustumorSupportConversationCategory custumorSupportConversationCategory = (CustumorSupportConversationCategory) o;
        return id != null && id.equals(custumorSupportConversationCategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

