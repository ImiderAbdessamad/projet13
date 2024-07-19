package ma.zs.zyn.ws.dto.support;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustumorSupportConversationMessageDto  extends AuditBaseDto {

    private String content  ;
    private Boolean collaborator  ;
    private String creationDate ;

    private CustumorSupportConversationDto custumorSupportConversation ;



    public CustumorSupportConversationMessageDto(){
        super();
    }



    @Log
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    @Log
    public Boolean getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Boolean collaborator){
        this.collaborator = collaborator;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }


    public CustumorSupportConversationDto getCustumorSupportConversation(){
        return this.custumorSupportConversation;
    }

    public void setCustumorSupportConversation(CustumorSupportConversationDto custumorSupportConversation){
        this.custumorSupportConversation = custumorSupportConversation;
    }






}
