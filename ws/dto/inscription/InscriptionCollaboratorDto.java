package ma.zs.zyn.ws.dto.inscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionCollaboratorDto  extends AuditBaseDto {

    private String description  ;
    private String startDate ;
    private String endDate ;
    private BigDecimal consumedEntity  ;
    private BigDecimal consumedProjet  ;
    private BigDecimal consumedAttribut  ;
    private BigDecimal consumedTokenInput  ;
    private BigDecimal consumedTokenOutput  ;

    private CollaboratorDto collaborator ;
    private PackagingDto packaging ;
    private InscriptionCollaboratorStateDto inscriptionCollaboratorState;


    public InscriptionCollaboratorDto(){
        super();
    }



    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    @Log
    public BigDecimal getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(BigDecimal consumedEntity){
        this.consumedEntity = consumedEntity;
    }

    @Log
    public BigDecimal getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(BigDecimal consumedProjet){
        this.consumedProjet = consumedProjet;
    }

    @Log
    public BigDecimal getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(BigDecimal consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }

    @Log
    public BigDecimal getConsumedTokenInput(){
        return this.consumedTokenInput;
    }
    public void setConsumedTokenInput(BigDecimal consumedTokenInput){
        this.consumedTokenInput = consumedTokenInput;
    }

    @Log
    public BigDecimal getConsumedTokenOutput(){
        return this.consumedTokenOutput;
    }
    public void setConsumedTokenOutput(BigDecimal consumedTokenOutput){
        this.consumedTokenOutput = consumedTokenOutput;
    }


    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public PackagingDto getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingDto packaging){
        this.packaging = packaging;
    }

    public InscriptionCollaboratorStateDto getInscriptionCollaboratorState() {
        return this.inscriptionCollaboratorState;
    }

    public void setInscriptionCollaboratorState(InscriptionCollaboratorStateDto inscriptionCollaboratorState) {
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }




}
