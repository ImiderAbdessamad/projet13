package ma.zs.zyn.dao.criteria.core.payement;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.time.LocalDateTime;
import java.util.List;

public class InscriptionCollaboratorCriteria extends  BaseCriteria  {

    private String description;
    private String descriptionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private String consumedEntity;
    private String consumedEntityMin;
    private String consumedEntityMax;
    private String consumedProjet;
    private String consumedProjetMin;
    private String consumedProjetMax;
    private String consumedAttribut;
    private String consumedAttributMin;
    private String consumedAttributMax;
    private String consumedTokenInput;
    private String consumedTokenInputMin;
    private String consumedTokenInputMax;
    private String consumedTokenOutput;
    private String consumedTokenOutputMin;
    private String consumedTokenOutputMax;

    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private PackagingCriteria packaging ;
    private List<PackagingCriteria> packagings ;


    public InscriptionCollaboratorCriteria(){}

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

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }
    public String getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(String consumedEntity){
        this.consumedEntity = consumedEntity;
    }   
    public String getConsumedEntityMin(){
        return this.consumedEntityMin;
    }
    public void setConsumedEntityMin(String consumedEntityMin){
        this.consumedEntityMin = consumedEntityMin;
    }
    public String getConsumedEntityMax(){
        return this.consumedEntityMax;
    }
    public void setConsumedEntityMax(String consumedEntityMax){
        this.consumedEntityMax = consumedEntityMax;
    }
      
    public String getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(String consumedProjet){
        this.consumedProjet = consumedProjet;
    }   
    public String getConsumedProjetMin(){
        return this.consumedProjetMin;
    }
    public void setConsumedProjetMin(String consumedProjetMin){
        this.consumedProjetMin = consumedProjetMin;
    }
    public String getConsumedProjetMax(){
        return this.consumedProjetMax;
    }
    public void setConsumedProjetMax(String consumedProjetMax){
        this.consumedProjetMax = consumedProjetMax;
    }
      
    public String getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(String consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }   
    public String getConsumedAttributMin(){
        return this.consumedAttributMin;
    }
    public void setConsumedAttributMin(String consumedAttributMin){
        this.consumedAttributMin = consumedAttributMin;
    }
    public String getConsumedAttributMax(){
        return this.consumedAttributMax;
    }
    public void setConsumedAttributMax(String consumedAttributMax){
        this.consumedAttributMax = consumedAttributMax;
    }
      
    public String getConsumedTokenInput(){
        return this.consumedTokenInput;
    }
    public void setConsumedTokenInput(String consumedTokenInput){
        this.consumedTokenInput = consumedTokenInput;
    }   
    public String getConsumedTokenInputMin(){
        return this.consumedTokenInputMin;
    }
    public void setConsumedTokenInputMin(String consumedTokenInputMin){
        this.consumedTokenInputMin = consumedTokenInputMin;
    }
    public String getConsumedTokenInputMax(){
        return this.consumedTokenInputMax;
    }
    public void setConsumedTokenInputMax(String consumedTokenInputMax){
        this.consumedTokenInputMax = consumedTokenInputMax;
    }
      
    public String getConsumedTokenOutput(){
        return this.consumedTokenOutput;
    }
    public void setConsumedTokenOutput(String consumedTokenOutput){
        this.consumedTokenOutput = consumedTokenOutput;
    }   
    public String getConsumedTokenOutputMin(){
        return this.consumedTokenOutputMin;
    }
    public void setConsumedTokenOutputMin(String consumedTokenOutputMin){
        this.consumedTokenOutputMin = consumedTokenOutputMin;
    }
    public String getConsumedTokenOutputMax(){
        return this.consumedTokenOutputMax;
    }
    public void setConsumedTokenOutputMax(String consumedTokenOutputMax){
        this.consumedTokenOutputMax = consumedTokenOutputMax;
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
    public PackagingCriteria getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingCriteria packaging){
        this.packaging = packaging;
    }
    public List<PackagingCriteria> getPackagings(){
        return this.packagings;
    }

    public void setPackagings(List<PackagingCriteria> packagings){
        this.packagings = packagings;
    }
}
