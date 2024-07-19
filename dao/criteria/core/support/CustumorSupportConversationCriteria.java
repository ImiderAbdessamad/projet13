package ma.zs.zyn.dao.criteria.core.support;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CustumorSupportConversationCriteria extends  BaseCriteria  {

    private String object;
    private String objectLike;
    private String ratting;
    private String rattingMin;
    private String rattingMax;
    private LocalDateTime creationDate;
    private LocalDateTime creationDateFrom;
    private LocalDateTime creationDateTo;
    private LocalDateTime closingDate;
    private LocalDateTime closingDateFrom;
    private LocalDateTime closingDateTo;
    private String description;
    private String descriptionLike;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private AgentCriteria agent ;
    private List<AgentCriteria> agents ;
    private CustumorSupportConversationCategoryCriteria custumorSupportConversationCategory ;
    private List<CustumorSupportConversationCategoryCriteria> custumorSupportConversationCategorys ;
    private CustumorSupportConversationStateCriteria custumorSupportConversationState ;
    private List<CustumorSupportConversationStateCriteria> custumorSupportConversationStates ;


    public String getObject(){
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObjectLike(){
        return this.objectLike;
    }
    public void setObjectLike(String objectLike){
        this.objectLike = objectLike;
    }

    public String getRatting(){
        return this.ratting;
    }
    public void setRatting(String ratting){
        this.ratting = ratting;
    }   
    public String getRattingMin(){
        return this.rattingMin;
    }
    public void setRattingMin(String rattingMin){
        this.rattingMin = rattingMin;
    }
    public String getRattingMax(){
        return this.rattingMax;
    }
    public void setRattingMax(String rattingMax){
        this.rattingMax = rattingMax;
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
    public LocalDateTime getClosingDate(){
        return this.closingDate;
    }
    public void setClosingDate(LocalDateTime closingDate){
        this.closingDate = closingDate;
    }
    public LocalDateTime getClosingDateFrom(){
        return this.closingDateFrom;
    }
    public void setClosingDateFrom(LocalDateTime closingDateFrom){
        this.closingDateFrom = closingDateFrom;
    }
    public LocalDateTime getClosingDateTo(){
        return this.closingDateTo;
    }
    public void setClosingDateTo(LocalDateTime closingDateTo){
        this.closingDateTo = closingDateTo;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public CollaboratorCriteria getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorCriteria collaborator){
        this.collaborator = collaborator;
    }
    public List<CollaboratorCriteria> getCollaborators(){
        return this.collaborators;
    }

    public void setCollaborators(List<CollaboratorCriteria> collaborators){
        this.collaborators = collaborators;
    }
    public AgentCriteria getAgent(){
        return this.agent;
    }

    public void setAgent(AgentCriteria agent){
        this.agent = agent;
    }
    public List<AgentCriteria> getAgents(){
        return this.agents;
    }

    public void setAgents(List<AgentCriteria> agents){
        this.agents = agents;
    }
    public CustumorSupportConversationCategoryCriteria getCustumorSupportConversationCategory(){
        return this.custumorSupportConversationCategory;
    }

    public void setCustumorSupportConversationCategory(CustumorSupportConversationCategoryCriteria custumorSupportConversationCategory){
        this.custumorSupportConversationCategory = custumorSupportConversationCategory;
    }
    public List<CustumorSupportConversationCategoryCriteria> getCustumorSupportConversationCategorys(){
        return this.custumorSupportConversationCategorys;
    }

    public void setCustumorSupportConversationCategorys(List<CustumorSupportConversationCategoryCriteria> custumorSupportConversationCategorys){
        this.custumorSupportConversationCategorys = custumorSupportConversationCategorys;
    }
    public CustumorSupportConversationStateCriteria getCustumorSupportConversationState(){
        return this.custumorSupportConversationState;
    }

    public void setCustumorSupportConversationState(CustumorSupportConversationStateCriteria custumorSupportConversationState){
        this.custumorSupportConversationState = custumorSupportConversationState;
    }
    public List<CustumorSupportConversationStateCriteria> getCustumorSupportConversationStates(){
        return this.custumorSupportConversationStates;
    }

    public void setCustumorSupportConversationStates(List<CustumorSupportConversationStateCriteria> custumorSupportConversationStates){
        this.custumorSupportConversationStates = custumorSupportConversationStates;
    }
}
