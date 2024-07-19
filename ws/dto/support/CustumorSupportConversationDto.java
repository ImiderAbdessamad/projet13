package ma.zs.zyn.ws.dto.support;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustumorSupportConversationDto  extends AuditBaseDto {

    private String object  ;
    private BigDecimal ratting  ;
    private String creationDate ;
    private String closingDate ;
    private String description  ;

    private CollaboratorDto collaborator ;
    private AgentDto agent ;
    private CustumorSupportConversationCategoryDto custumorSupportConversationCategory ;
    private CustumorSupportConversationStateDto custumorSupportConversationState ;

    private List<CustumorSupportConversationMessageDto> custumorSupportConversationMessages ;


    public CustumorSupportConversationDto(){
        super();
    }



    @Log
    public String getObject(){
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }

    @Log
    public BigDecimal getRatting(){
        return this.ratting;
    }
    public void setRatting(BigDecimal ratting){
        this.ratting = ratting;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getClosingDate(){
        return this.closingDate;
    }
    public void setClosingDate(String closingDate){
        this.closingDate = closingDate;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public AgentDto getAgent(){
        return this.agent;
    }

    public void setAgent(AgentDto agent){
        this.agent = agent;
    }
    public CustumorSupportConversationCategoryDto getCustumorSupportConversationCategory(){
        return this.custumorSupportConversationCategory;
    }

    public void setCustumorSupportConversationCategory(CustumorSupportConversationCategoryDto custumorSupportConversationCategory){
        this.custumorSupportConversationCategory = custumorSupportConversationCategory;
    }
    public CustumorSupportConversationStateDto getCustumorSupportConversationState(){
        return this.custumorSupportConversationState;
    }

    public void setCustumorSupportConversationState(CustumorSupportConversationStateDto custumorSupportConversationState){
        this.custumorSupportConversationState = custumorSupportConversationState;
    }



    public List<CustumorSupportConversationMessageDto> getCustumorSupportConversationMessages(){
        return this.custumorSupportConversationMessages;
    }

    public void setCustumorSupportConversationMessages(List<CustumorSupportConversationMessageDto> custumorSupportConversationMessages){
        this.custumorSupportConversationMessages = custumorSupportConversationMessages;
    }



}
