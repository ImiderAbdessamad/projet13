package ma.zs.zyn.bean.core.support;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.collaborator.Collaborator;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "custumor_support_conversation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="custumor_support_conversation_seq",sequenceName="custumor_support_conversation_seq",allocationSize=1, initialValue = 1)
public class CustumorSupportConversation  extends BaseEntity     {




    @Column(length = 500)
    private String object;

    private BigDecimal ratting = BigDecimal.ZERO;

    private LocalDateTime creationDate ;

    private LocalDateTime closingDate ;

    @Column(length = 500)
    private String description;

    private Collaborator collaborator ;
    private Agent agent ;
    private CustumorSupportConversationCategory custumorSupportConversationCategory ;
    private CustumorSupportConversationState custumorSupportConversationState ;

    private List<CustumorSupportConversationMessage> custumorSupportConversationMessages ;

    public CustumorSupportConversation(){
        super();
    }

    public CustumorSupportConversation(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="custumor_support_conversation_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent")
    public Agent getAgent(){
        return this.agent;
    }
    public void setAgent(Agent agent){
        this.agent = agent;
    }
    public String getObject(){
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }
    public BigDecimal getRatting(){
        return this.ratting;
    }
    public void setRatting(BigDecimal ratting){
        this.ratting = ratting;
    }
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public LocalDateTime getClosingDate(){
        return this.closingDate;
    }
    public void setClosingDate(LocalDateTime closingDate){
        this.closingDate = closingDate;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custumor_support_conversation_category")
    public CustumorSupportConversationCategory getCustumorSupportConversationCategory(){
        return this.custumorSupportConversationCategory;
    }
    public void setCustumorSupportConversationCategory(CustumorSupportConversationCategory custumorSupportConversationCategory){
        this.custumorSupportConversationCategory = custumorSupportConversationCategory;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custumor_support_conversation_state")
    public CustumorSupportConversationState getCustumorSupportConversationState(){
        return this.custumorSupportConversationState;
    }
    public void setCustumorSupportConversationState(CustumorSupportConversationState custumorSupportConversationState){
        this.custumorSupportConversationState = custumorSupportConversationState;
    }
    @OneToMany(mappedBy = "custumorSupportConversation")
    public List<CustumorSupportConversationMessage> getCustumorSupportConversationMessages(){
        return this.custumorSupportConversationMessages;
    }

    public void setCustumorSupportConversationMessages(List<CustumorSupportConversationMessage> custumorSupportConversationMessages){
        this.custumorSupportConversationMessages = custumorSupportConversationMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustumorSupportConversation custumorSupportConversation = (CustumorSupportConversation) o;
        return id != null && id.equals(custumorSupportConversation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

