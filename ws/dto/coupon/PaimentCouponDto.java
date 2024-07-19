package ma.zs.zyn.ws.dto.coupon;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaimentCouponDto  extends AuditBaseDto {

    private String description  ;
    private BigDecimal total  ;
    private String paiementDate ;
    private String paiementDateConfirmation ;

    private CouponDto coupon ;
    private PaimentCouponStateDto paimentCouponState ;



    public PaimentCouponDto(){
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
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaiementDate(){
        return this.paiementDate;
    }
    public void setPaiementDate(String paiementDate){
        this.paiementDate = paiementDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaiementDateConfirmation(){
        return this.paiementDateConfirmation;
    }
    public void setPaiementDateConfirmation(String paiementDateConfirmation){
        this.paiementDateConfirmation = paiementDateConfirmation;
    }


    public CouponDto getCoupon(){
        return this.coupon;
    }

    public void setCoupon(CouponDto coupon){
        this.coupon = coupon;
    }
    public PaimentCouponStateDto getPaimentCouponState(){
        return this.paimentCouponState;
    }

    public void setPaimentCouponState(PaimentCouponStateDto paimentCouponState){
        this.paimentCouponState = paimentCouponState;
    }






}
