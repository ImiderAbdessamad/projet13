package ma.zs.zyn.ws.dto.payement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.ws.dto.collaborator.CityDto;
import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.ws.dto.collaborator.CountryDto;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorDto;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaimentCollaboratorDto extends AuditBaseDto {

    private String cardHolder;
    private String cardNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private String cvc;
    private String postal;
    private String description;
    private BigDecimal amountToPaid;
    private String startDate;
    private String endDate;
    private BigDecimal consumedEntity;
    private BigDecimal consumedProjet;
    private BigDecimal consumedAttribut;
    private BigDecimal consumedTokenInput;
    private BigDecimal consumedTokenOutput;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal remaining;
    private String paiementDate;

    private CountryDto country;
    private CityDto city;
    private CollaboratorDto collaborator;
    private PackagingDto packaging;
    private PaimentCollaboratorStateDto paimentCollaboratorState;
    private PaimentCollaboratorTypeDto paimentCollaboratorType;
    private InscriptionCollaboratorDto inscriptionCollaborator;


    public PaimentCollaboratorDto() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Log
    public String getCardHolder() {
        return this.cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Log
    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Log
    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Log
    public String getCvc() {
        return this.cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Log
    public String getPostal() {
        return this.postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Log
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Log
    public BigDecimal getAmountToPaid() {
        return this.amountToPaid;
    }

    public void setAmountToPaid(BigDecimal amountToPaid) {
        this.amountToPaid = amountToPaid;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Log
    public BigDecimal getConsumedEntity() {
        return this.consumedEntity;
    }

    public void setConsumedEntity(BigDecimal consumedEntity) {
        this.consumedEntity = consumedEntity;
    }

    @Log
    public BigDecimal getConsumedProjet() {
        return this.consumedProjet;
    }

    public void setConsumedProjet(BigDecimal consumedProjet) {
        this.consumedProjet = consumedProjet;
    }

    @Log
    public BigDecimal getConsumedAttribut() {
        return this.consumedAttribut;
    }

    public void setConsumedAttribut(BigDecimal consumedAttribut) {
        this.consumedAttribut = consumedAttribut;
    }

    @Log
    public BigDecimal getConsumedTokenInput() {
        return this.consumedTokenInput;
    }

    public void setConsumedTokenInput(BigDecimal consumedTokenInput) {
        this.consumedTokenInput = consumedTokenInput;
    }

    @Log
    public BigDecimal getConsumedTokenOutput() {
        return this.consumedTokenOutput;
    }

    public void setConsumedTokenOutput(BigDecimal consumedTokenOutput) {
        this.consumedTokenOutput = consumedTokenOutput;
    }

    @Log
    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Log
    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Log
    public BigDecimal getRemaining() {
        return this.remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaiementDate() {
        return this.paiementDate;
    }

    public void setPaiementDate(String paiementDate) {
        this.paiementDate = paiementDate;
    }


    public CountryDto getCountry() {
        return this.country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public CityDto getCity() {
        return this.city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public CollaboratorDto getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator) {
        this.collaborator = collaborator;
    }

    public PackagingDto getPackaging() {
        return this.packaging;
    }

    public void setPackaging(PackagingDto packaging) {
        this.packaging = packaging;
    }

    public PaimentCollaboratorStateDto getPaimentCollaboratorState() {
        return this.paimentCollaboratorState;
    }

    public void setPaimentCollaboratorState(PaimentCollaboratorStateDto paimentCollaboratorState) {
        this.paimentCollaboratorState = paimentCollaboratorState;
    }

    public PaimentCollaboratorTypeDto getPaimentCollaboratorType() {
        return this.paimentCollaboratorType;
    }

    public void setPaimentCollaboratorType(PaimentCollaboratorTypeDto paimentCollaboratorType) {
        this.paimentCollaboratorType = paimentCollaboratorType;
    }

    public InscriptionCollaboratorDto getInscriptionCollaborator() {
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorDto inscriptionCollaborator) {
        this.inscriptionCollaborator = inscriptionCollaborator;
    }


}
