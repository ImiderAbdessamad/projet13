package ma.zs.zyn.bean.core.coupon;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paiment_coupon_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="paiment_coupon_state_seq",sequenceName="paiment_coupon_state_seq",allocationSize=1, initialValue = 1)
public class PaimentCouponState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public PaimentCouponState(){
        super();
    }

    public PaimentCouponState(Long id){
        this.id = id;
    }

    public PaimentCouponState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public PaimentCouponState(String libelle){
        this.libelle = libelle ;
    }
    public PaimentCouponState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="paiment_coupon_state_seq")
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
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
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
        PaimentCouponState paimentCouponState = (PaimentCouponState) o;
        return id != null && id.equals(paimentCouponState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

