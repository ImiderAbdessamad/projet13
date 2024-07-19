package ma.zs.zyn.bean.core.coupon;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "paiment_coupon")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "paiment_coupon_seq", sequenceName = "paiment_coupon_seq", allocationSize = 1, initialValue = 1)
public class PaimentCoupon extends BaseEntity {


    @Column(length = 500)
    private String description;

    private BigDecimal total = BigDecimal.ZERO;

    private LocalDateTime paiementDate;

    private LocalDateTime paiementDateConfirmation;

    private Coupon coupon;
    private PaimentCouponState paimentCouponState;


    public PaimentCoupon() {
        super();
    }

    public PaimentCoupon(Long id) {
        this.id = id;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paiment_coupon_seq")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon")
    public Coupon getCoupon() {
        return this.coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getPaiementDate() {
        return this.paiementDate;
    }

    public void setPaiementDate(LocalDateTime paiementDate) {
        this.paiementDate = paiementDate;
    }

    public LocalDateTime getPaiementDateConfirmation() {
        return this.paiementDateConfirmation;
    }

    public void setPaiementDateConfirmation(LocalDateTime paiementDateConfirmation) {
        this.paiementDateConfirmation = paiementDateConfirmation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiment_coupon_state")
    public PaimentCouponState getPaimentCouponState() {
        return this.paimentCouponState;
    }

    public void setPaimentCouponState(PaimentCouponState paimentCouponState) {
        this.paimentCouponState = paimentCouponState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaimentCoupon paimentCoupon = (PaimentCoupon) o;
        return id != null && id.equals(paimentCoupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

