package ma.zs.zyn.ws.dto.coupon;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private BigDecimal discountCollaborator  ;
    private BigDecimal percentInflucencer  ;
    private Integer nombreInscriptionMax  = 0 ;
    private Integer nombreCollaboratorInscrit  = 0 ;

    private InfluenceurDto influenceur ;
    private CouponStateDto couponState ;



    public CouponDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Log
    public BigDecimal getDiscountCollaborator(){
        return this.discountCollaborator;
    }
    public void setDiscountCollaborator(BigDecimal discountCollaborator){
        this.discountCollaborator = discountCollaborator;
    }

    @Log
    public BigDecimal getPercentInflucencer(){
        return this.percentInflucencer;
    }
    public void setPercentInflucencer(BigDecimal percentInflucencer){
        this.percentInflucencer = percentInflucencer;
    }

    @Log
    public Integer getNombreInscriptionMax(){
        return this.nombreInscriptionMax;
    }
    public void setNombreInscriptionMax(Integer nombreInscriptionMax){
        this.nombreInscriptionMax = nombreInscriptionMax;
    }

    @Log
    public Integer getNombreCollaboratorInscrit(){
        return this.nombreCollaboratorInscrit;
    }
    public void setNombreCollaboratorInscrit(Integer nombreCollaboratorInscrit){
        this.nombreCollaboratorInscrit = nombreCollaboratorInscrit;
    }


    public InfluenceurDto getInfluenceur(){
        return this.influenceur;
    }

    public void setInfluenceur(InfluenceurDto influenceur){
        this.influenceur = influenceur;
    }
    public CouponStateDto getCouponState(){
        return this.couponState;
    }

    public void setCouponState(CouponStateDto couponState){
        this.couponState = couponState;
    }






}
