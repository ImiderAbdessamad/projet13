package ma.zs.zyn.bean.core.coupon;








import com.fasterxml.jackson.annotation.JsonInclude;
//import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "coupon")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="coupon_seq",sequenceName="coupon_seq",allocationSize=1, initialValue = 1)
public class Coupon  extends BaseEntity {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private BigDecimal discountCollaborator = BigDecimal.ZERO;

    private BigDecimal percentInflucencer = BigDecimal.ZERO;

    private Integer nombreInscriptionMax = 0;

    private Integer nombreCollaboratorInscrit = 0;

    private Influenceur influenceur ;
    private CouponState couponState ;


    public Coupon(){
        super();
    }

    public Coupon(Long id){
        this.id = id;
    }

    public Coupon(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Coupon(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="coupon_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influenceur")
    public Influenceur getInfluenceur(){
        return this.influenceur;
    }
    public void setInfluenceur(Influenceur influenceur){
        this.influenceur = influenceur;
    }
    public BigDecimal getDiscountCollaborator(){
        return this.discountCollaborator;
    }
    public void setDiscountCollaborator(BigDecimal discountCollaborator){
        this.discountCollaborator = discountCollaborator;
    }
    public BigDecimal getPercentInflucencer(){
        return this.percentInflucencer;
    }
    public void setPercentInflucencer(BigDecimal percentInflucencer){
        this.percentInflucencer = percentInflucencer;
    }
    public Integer getNombreInscriptionMax(){
        return this.nombreInscriptionMax;
    }
    public void setNombreInscriptionMax(Integer nombreInscriptionMax){
        this.nombreInscriptionMax = nombreInscriptionMax;
    }
    public Integer getNombreCollaboratorInscrit(){
        return this.nombreCollaboratorInscrit;
    }
    public void setNombreCollaboratorInscrit(Integer nombreCollaboratorInscrit){
        this.nombreCollaboratorInscrit = nombreCollaboratorInscrit;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_state")
    public CouponState getCouponState(){
        return this.couponState;
    }
    public void setCouponState(CouponState couponState){
        this.couponState = couponState;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return id != null && id.equals(coupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

