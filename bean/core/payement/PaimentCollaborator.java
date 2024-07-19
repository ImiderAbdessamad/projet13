package ma.zs.zyn.bean.core.payement;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.collaborator.Country;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "paiment_collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "paiment_collaborator_seq", sequenceName = "paiment_collaborator_seq", allocationSize = 1, initialValue = 1)
public class PaimentCollaborator extends BaseEntity {

    private Long id;


    @Column(length = 500)
    private String cardHolder;


    @Column(length = 500)
    private String postal;

    @Column(length = 500)
    private String email;
    @Column(length = 500)
    private String firstName;
    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String description;

    private BigDecimal amountToPaid = BigDecimal.ZERO;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal remaining = BigDecimal.ZERO;

    private LocalDateTime paiementDate;

    private Country country;
    private City city;
    private Coupon coupon;
    private Collaborator collaborator;
    private Packaging packaging;
    private PaimentCollaboratorState paimentCollaboratorState;
    private PaimentCollaboratorType paimentCollaboratorType;
    private InscriptionCollaborator inscriptionCollaborator;


    public PaimentCollaborator() {
        super();
    }

    public PaimentCollaborator(Long id) {
        this.id = id;
    }

    public PaimentCollaborator(Long id, String cardHolder) {
        this.id = id;
        this.cardHolder = cardHolder;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paiment_collaborator_seq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCardHolder() {
        return this.cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon")
    public Coupon getCoupon() {
        return this.coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostal() {
        return this.postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmountToPaid() {
        return this.amountToPaid;
    }

    public void setAmountToPaid(BigDecimal amountToPaid) {
        this.amountToPaid = amountToPaid;
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

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getRemaining() {
        return this.remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public LocalDateTime getPaiementDate() {
        return this.paiementDate;
    }

    public void setPaiementDate(LocalDateTime paiementDate) {
        this.paiementDate = paiementDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @JoinColumn(name = "paiment_collaborator_state")
    public PaimentCollaboratorState getPaimentCollaboratorState() {
        return this.paimentCollaboratorState;
    }

    public void setPaimentCollaboratorState(PaimentCollaboratorState paimentCollaboratorState) {
        this.paimentCollaboratorState = paimentCollaboratorState;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiment_collaborator_type")
    public PaimentCollaboratorType getPaimentCollaboratorType() {
        return this.paimentCollaboratorType;
    }

    public void setPaimentCollaboratorType(PaimentCollaboratorType paimentCollaboratorType) {
        this.paimentCollaboratorType = paimentCollaboratorType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_collaborator")
    public InscriptionCollaborator getInscriptionCollaborator() {
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaborator inscriptionCollaborator) {
        this.inscriptionCollaborator = inscriptionCollaborator;
    }

    @Transient
    public String getLabel() {
        label = cardHolder;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaimentCollaborator paimentCollaborator = (PaimentCollaborator) o;
        return id != null && id.equals(paimentCollaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

