package ma.zs.zyn.bean.core.inscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "inscription_collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "inscription_collaborator_seq", sequenceName = "inscription_collaborator_seq", allocationSize = 1, initialValue = 1)
public class InscriptionCollaborator extends BaseEntity {

    private Long id;


    @Column(length = 500)
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal consumedEntity = BigDecimal.ZERO;

    private BigDecimal consumedProjet = BigDecimal.ZERO;

    private BigDecimal consumedAttribut = BigDecimal.ZERO;

    private BigDecimal consumedTokenInput = BigDecimal.ZERO;

    private BigDecimal consumedTokenOutput = BigDecimal.ZERO;

    private Collaborator collaborator;
    private Packaging packaging;
    private InscriptionCollaboratorState inscriptionCollaboratorState;


    public InscriptionCollaborator() {
        super();
    }

    public InscriptionCollaborator(Long id) {
        this.id = id;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscription_collaborator_seq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getConsumedEntity() {
        return this.consumedEntity;
    }

    public void setConsumedEntity(BigDecimal consumedEntity) {
        this.consumedEntity = consumedEntity;
    }

    public BigDecimal getConsumedProjet() {
        return this.consumedProjet;
    }

    public void setConsumedProjet(BigDecimal consumedProjet) {
        this.consumedProjet = consumedProjet;
    }

    public BigDecimal getConsumedAttribut() {
        return this.consumedAttribut;
    }

    public void setConsumedAttribut(BigDecimal consumedAttribut) {
        this.consumedAttribut = consumedAttribut;
    }

    public BigDecimal getConsumedTokenInput() {
        return this.consumedTokenInput;
    }

    public void setConsumedTokenInput(BigDecimal consumedTokenInput) {
        this.consumedTokenInput = consumedTokenInput;
    }

    public BigDecimal getConsumedTokenOutput() {
        return this.consumedTokenOutput;
    }

    public void setConsumedTokenOutput(BigDecimal consumedTokenOutput) {
        this.consumedTokenOutput = consumedTokenOutput;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging")
    public Packaging getPackaging() {
        return this.packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_collaborator_state")
    public InscriptionCollaboratorState getInscriptionCollaboratorState() {
        return inscriptionCollaboratorState;
    }

    public void setInscriptionCollaboratorState(InscriptionCollaboratorState inscriptionCollaboratorState) {
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscriptionCollaborator inscriptionCollaborator = (InscriptionCollaborator) o;
        return id != null && id.equals(inscriptionCollaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

