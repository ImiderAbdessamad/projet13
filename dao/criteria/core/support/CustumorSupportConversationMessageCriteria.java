package ma.zs.zyn.dao.criteria.core.support;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CustumorSupportConversationMessageCriteria extends  BaseCriteria  {

    private String content;
    private String contentLike;
    private Boolean collaborator;
    private LocalDateTime creationDate;
    private LocalDateTime creationDateFrom;
    private LocalDateTime creationDateTo;

    private CustumorSupportConversationCriteria custumorSupportConversation ;
    private List<CustumorSupportConversationCriteria> custumorSupportConversations ;


    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContentLike(){
        return this.contentLike;
    }
    public void setContentLike(String contentLike){
        this.contentLike = contentLike;
    }

    public Boolean getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Boolean collaborator){
        this.collaborator = collaborator;
    }
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public LocalDateTime getCreationDateFrom(){
        return this.creationDateFrom;
    }
    public void setCreationDateFrom(LocalDateTime creationDateFrom){
        this.creationDateFrom = creationDateFrom;
    }
    public LocalDateTime getCreationDateTo(){
        return this.creationDateTo;
    }
    public void setCreationDateTo(LocalDateTime creationDateTo){
        this.creationDateTo = creationDateTo;
    }

    public CustumorSupportConversationCriteria getCustumorSupportConversation(){
        return this.custumorSupportConversation;
    }

    public void setCustumorSupportConversation(CustumorSupportConversationCriteria custumorSupportConversation){
        this.custumorSupportConversation = custumorSupportConversation;
    }
    public List<CustumorSupportConversationCriteria> getCustumorSupportConversations(){
        return this.custumorSupportConversations;
    }

    public void setCustumorSupportConversations(List<CustumorSupportConversationCriteria> custumorSupportConversations){
        this.custumorSupportConversations = custumorSupportConversations;
    }
}
