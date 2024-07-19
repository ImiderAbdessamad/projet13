package ma.zs.zyn.dao.criteria.core.payement;


import ma.zs.zyn.dao.criteria.core.collaborator.CityCriteria;
import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.dao.criteria.core.collaborator.CountryCriteria;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.time.LocalDateTime;
import java.util.List;

public class PaimentCollaboratorCriteria extends BaseCriteria {

    private String cardHolder;
    private String cardHolderLike;
    private String cardNumber;
    private String cardNumberLike;
    private String expirationDate;
    private String expirationDateLike;
    private String cvc;
    private String cvcLike;
    private String postal;
    private String postalLike;
    private String email;
    private String emailLike;
    private String lastName;
    private String lastNameLike;
    private String firstName;
    private String firstNameLike;
    private String description;
    private String descriptionLike;
    private String amountToPaid;
    private String amountToPaidMin;
    private String amountToPaidMax;
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
    private String total;
    private String totalMin;
    private String totalMax;
    private String discount;
    private String discountMin;
    private String discountMax;
    private String remaining;
    private String remainingMin;
    private String remainingMax;
    private LocalDateTime paiementDate;
    private LocalDateTime paiementDateFrom;
    private LocalDateTime paiementDateTo;

    private CountryCriteria country;
    private List<CountryCriteria> countrys;
    private CityCriteria city;
    private List<CityCriteria> citys;
    private CollaboratorCriteria collaborator;
    private List<CollaboratorCriteria> collaborators;
    private PackagingCriteria packaging;
    private List<PackagingCriteria> packagings;
    private PaimentCollaboratorStateCriteria paimentCollaboratorState;
    private List<PaimentCollaboratorStateCriteria> paimentCollaboratorStates;
    private PaimentCollaboratorTypeCriteria paimentCollaboratorType;
    private List<PaimentCollaboratorTypeCriteria> paimentCollaboratorTypes;
    private InscriptionCollaboratorCriteria inscriptionCollaborator;
    private List<InscriptionCollaboratorCriteria> inscriptionCollaborators;


    public PaimentCollaboratorCriteria() {
    }

    public String getCardHolder() {
        return this.cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardHolderLike() {
        return this.cardHolderLike;
    }

    public void setCardHolderLike(String cardHolderLike) {
        this.cardHolderLike = cardHolderLike;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumberLike() {
        return this.cardNumberLike;
    }

    public void setCardNumberLike(String cardNumberLike) {
        this.cardNumberLike = cardNumberLike;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailLike() {
        return emailLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDateLike() {
        return this.expirationDateLike;
    }

    public void setExpirationDateLike(String expirationDateLike) {
        this.expirationDateLike = expirationDateLike;
    }

    public String getCvc() {
        return this.cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCvcLike() {
        return this.cvcLike;
    }

    public void setCvcLike(String cvcLike) {
        this.cvcLike = cvcLike;
    }

    public String getPostal() {
        return this.postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPostalLike() {
        return this.postalLike;
    }

    public void setPostalLike(String postalLike) {
        this.postalLike = postalLike;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLike() {
        return this.descriptionLike;
    }

    public void setDescriptionLike(String descriptionLike) {
        this.descriptionLike = descriptionLike;
    }

    public String getAmountToPaid() {
        return this.amountToPaid;
    }

    public void setAmountToPaid(String amountToPaid) {
        this.amountToPaid = amountToPaid;
    }

    public String getAmountToPaidMin() {
        return this.amountToPaidMin;
    }

    public void setAmountToPaidMin(String amountToPaidMin) {
        this.amountToPaidMin = amountToPaidMin;
    }

    public String getAmountToPaidMax() {
        return this.amountToPaidMax;
    }

    public void setAmountToPaidMax(String amountToPaidMax) {
        this.amountToPaidMax = amountToPaidMax;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDateFrom() {
        return this.startDateFrom;
    }

    public void setStartDateFrom(LocalDateTime startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public LocalDateTime getStartDateTo() {
        return this.startDateTo;
    }

    public void setStartDateTo(LocalDateTime startDateTo) {
        this.startDateTo = startDateTo;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDateFrom() {
        return this.endDateFrom;
    }

    public void setEndDateFrom(LocalDateTime endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public LocalDateTime getEndDateTo() {
        return this.endDateTo;
    }

    public void setEndDateTo(LocalDateTime endDateTo) {
        this.endDateTo = endDateTo;
    }

    public String getConsumedEntity() {
        return this.consumedEntity;
    }

    public void setConsumedEntity(String consumedEntity) {
        this.consumedEntity = consumedEntity;
    }

    public String getConsumedEntityMin() {
        return this.consumedEntityMin;
    }

    public void setConsumedEntityMin(String consumedEntityMin) {
        this.consumedEntityMin = consumedEntityMin;
    }

    public String getConsumedEntityMax() {
        return this.consumedEntityMax;
    }

    public void setConsumedEntityMax(String consumedEntityMax) {
        this.consumedEntityMax = consumedEntityMax;
    }

    public String getConsumedProjet() {
        return this.consumedProjet;
    }

    public void setConsumedProjet(String consumedProjet) {
        this.consumedProjet = consumedProjet;
    }

    public String getConsumedProjetMin() {
        return this.consumedProjetMin;
    }

    public void setConsumedProjetMin(String consumedProjetMin) {
        this.consumedProjetMin = consumedProjetMin;
    }

    public String getConsumedProjetMax() {
        return this.consumedProjetMax;
    }

    public void setConsumedProjetMax(String consumedProjetMax) {
        this.consumedProjetMax = consumedProjetMax;
    }

    public String getConsumedAttribut() {
        return this.consumedAttribut;
    }

    public void setConsumedAttribut(String consumedAttribut) {
        this.consumedAttribut = consumedAttribut;
    }

    public String getConsumedAttributMin() {
        return this.consumedAttributMin;
    }

    public void setConsumedAttributMin(String consumedAttributMin) {
        this.consumedAttributMin = consumedAttributMin;
    }

    public String getConsumedAttributMax() {
        return this.consumedAttributMax;
    }

    public void setConsumedAttributMax(String consumedAttributMax) {
        this.consumedAttributMax = consumedAttributMax;
    }

    public String getConsumedTokenInput() {
        return this.consumedTokenInput;
    }

    public void setConsumedTokenInput(String consumedTokenInput) {
        this.consumedTokenInput = consumedTokenInput;
    }

    public String getConsumedTokenInputMin() {
        return this.consumedTokenInputMin;
    }

    public void setConsumedTokenInputMin(String consumedTokenInputMin) {
        this.consumedTokenInputMin = consumedTokenInputMin;
    }

    public String getConsumedTokenInputMax() {
        return this.consumedTokenInputMax;
    }

    public void setConsumedTokenInputMax(String consumedTokenInputMax) {
        this.consumedTokenInputMax = consumedTokenInputMax;
    }

    public String getConsumedTokenOutput() {
        return this.consumedTokenOutput;
    }

    public void setConsumedTokenOutput(String consumedTokenOutput) {
        this.consumedTokenOutput = consumedTokenOutput;
    }

    public String getConsumedTokenOutputMin() {
        return this.consumedTokenOutputMin;
    }

    public void setConsumedTokenOutputMin(String consumedTokenOutputMin) {
        this.consumedTokenOutputMin = consumedTokenOutputMin;
    }

    public String getConsumedTokenOutputMax() {
        return this.consumedTokenOutputMax;
    }

    public void setConsumedTokenOutputMax(String consumedTokenOutputMax) {
        this.consumedTokenOutputMax = consumedTokenOutputMax;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalMin() {
        return this.totalMin;
    }

    public void setTotalMin(String totalMin) {
        this.totalMin = totalMin;
    }

    public String getTotalMax() {
        return this.totalMax;
    }

    public void setTotalMax(String totalMax) {
        this.totalMax = totalMax;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountMin() {
        return this.discountMin;
    }

    public void setDiscountMin(String discountMin) {
        this.discountMin = discountMin;
    }

    public String getDiscountMax() {
        return this.discountMax;
    }

    public void setDiscountMax(String discountMax) {
        this.discountMax = discountMax;
    }

    public String getRemaining() {
        return this.remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getRemainingMin() {
        return this.remainingMin;
    }

    public void setRemainingMin(String remainingMin) {
        this.remainingMin = remainingMin;
    }

    public String getRemainingMax() {
        return this.remainingMax;
    }

    public void setRemainingMax(String remainingMax) {
        this.remainingMax = remainingMax;
    }

    public LocalDateTime getPaiementDate() {
        return this.paiementDate;
    }

    public void setPaiementDate(LocalDateTime paiementDate) {
        this.paiementDate = paiementDate;
    }

    public LocalDateTime getPaiementDateFrom() {
        return this.paiementDateFrom;
    }

    public void setPaiementDateFrom(LocalDateTime paiementDateFrom) {
        this.paiementDateFrom = paiementDateFrom;
    }

    public LocalDateTime getPaiementDateTo() {
        return this.paiementDateTo;
    }

    public void setPaiementDateTo(LocalDateTime paiementDateTo) {
        this.paiementDateTo = paiementDateTo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameLike() {
        return firstNameLike;
    }

    public void setFirstNameLike(String firstNameLike) {
        this.firstNameLike = firstNameLike;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameLike() {
        return lastNameLike;
    }

    public void setLastNameLike(String lastNameLike) {
        this.lastNameLike = lastNameLike;
    }

    public CountryCriteria getCountry() {
        return this.country;
    }

    public void setCountry(CountryCriteria country) {
        this.country = country;
    }

    public List<CountryCriteria> getCountrys() {
        return this.countrys;
    }

    public void setCountrys(List<CountryCriteria> countrys) {
        this.countrys = countrys;
    }

    public CityCriteria getCity() {
        return this.city;
    }

    public void setCity(CityCriteria city) {
        this.city = city;
    }

    public List<CityCriteria> getCitys() {
        return this.citys;
    }

    public void setCitys(List<CityCriteria> citys) {
        this.citys = citys;
    }

    public CollaboratorCriteria getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorCriteria collaborator) {
        this.collaborator = collaborator;
    }

    public List<CollaboratorCriteria> getCollaborators() {
        return this.collaborators;
    }

    public void setCollaborators(List<CollaboratorCriteria> collaborators) {
        this.collaborators = collaborators;
    }

    public PackagingCriteria getPackaging() {
        return this.packaging;
    }

    public void setPackaging(PackagingCriteria packaging) {
        this.packaging = packaging;
    }

    public List<PackagingCriteria> getPackagings() {
        return this.packagings;
    }

    public void setPackagings(List<PackagingCriteria> packagings) {
        this.packagings = packagings;
    }

    public PaimentCollaboratorStateCriteria getPaimentCollaboratorState() {
        return this.paimentCollaboratorState;
    }

    public void setPaimentCollaboratorState(PaimentCollaboratorStateCriteria paimentCollaboratorState) {
        this.paimentCollaboratorState = paimentCollaboratorState;
    }

    public List<PaimentCollaboratorStateCriteria> getPaimentCollaboratorStates() {
        return this.paimentCollaboratorStates;
    }

    public void setPaimentCollaboratorStates(List<PaimentCollaboratorStateCriteria> paimentCollaboratorStates) {
        this.paimentCollaboratorStates = paimentCollaboratorStates;
    }

    public PaimentCollaboratorTypeCriteria getPaimentCollaboratorType() {
        return this.paimentCollaboratorType;
    }

    public void setPaimentCollaboratorType(PaimentCollaboratorTypeCriteria paimentCollaboratorType) {
        this.paimentCollaboratorType = paimentCollaboratorType;
    }

    public List<PaimentCollaboratorTypeCriteria> getPaimentCollaboratorTypes() {
        return this.paimentCollaboratorTypes;
    }

    public void setPaimentCollaboratorTypes(List<PaimentCollaboratorTypeCriteria> paimentCollaboratorTypes) {
        this.paimentCollaboratorTypes = paimentCollaboratorTypes;
    }

    public InscriptionCollaboratorCriteria getInscriptionCollaborator() {
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorCriteria inscriptionCollaborator) {
        this.inscriptionCollaborator = inscriptionCollaborator;
    }

    public List<InscriptionCollaboratorCriteria> getInscriptionCollaborators() {
        return this.inscriptionCollaborators;
    }

    public void setInscriptionCollaborators(List<InscriptionCollaboratorCriteria> inscriptionCollaborators) {
        this.inscriptionCollaborators = inscriptionCollaborators;
    }
}
