package ma.zs.zyn.dao.criteria.core.coupon;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PaimentCouponCriteria extends  BaseCriteria  {

    private String description;
    private String descriptionLike;
    private String total;
    private String totalMin;
    private String totalMax;
    private LocalDateTime paiementDate;
    private LocalDateTime paiementDateFrom;
    private LocalDateTime paiementDateTo;
    private LocalDateTime paiementDateConfirmation;
    private LocalDateTime paiementDateConfirmationFrom;
    private LocalDateTime paiementDateConfirmationTo;

    private CouponCriteria coupon ;
    private List<CouponCriteria> coupons ;
    private PaimentCouponStateCriteria paimentCouponState ;
    private List<PaimentCouponStateCriteria> paimentCouponStates ;


    public PaimentCouponCriteria(){}

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getTotal(){
        return this.total;
    }
    public void setTotal(String total){
        this.total = total;
    }   
    public String getTotalMin(){
        return this.totalMin;
    }
    public void setTotalMin(String totalMin){
        this.totalMin = totalMin;
    }
    public String getTotalMax(){
        return this.totalMax;
    }
    public void setTotalMax(String totalMax){
        this.totalMax = totalMax;
    }
      
    public LocalDateTime getPaiementDate(){
        return this.paiementDate;
    }
    public void setPaiementDate(LocalDateTime paiementDate){
        this.paiementDate = paiementDate;
    }
    public LocalDateTime getPaiementDateFrom(){
        return this.paiementDateFrom;
    }
    public void setPaiementDateFrom(LocalDateTime paiementDateFrom){
        this.paiementDateFrom = paiementDateFrom;
    }
    public LocalDateTime getPaiementDateTo(){
        return this.paiementDateTo;
    }
    public void setPaiementDateTo(LocalDateTime paiementDateTo){
        this.paiementDateTo = paiementDateTo;
    }
    public LocalDateTime getPaiementDateConfirmation(){
        return this.paiementDateConfirmation;
    }
    public void setPaiementDateConfirmation(LocalDateTime paiementDateConfirmation){
        this.paiementDateConfirmation = paiementDateConfirmation;
    }
    public LocalDateTime getPaiementDateConfirmationFrom(){
        return this.paiementDateConfirmationFrom;
    }
    public void setPaiementDateConfirmationFrom(LocalDateTime paiementDateConfirmationFrom){
        this.paiementDateConfirmationFrom = paiementDateConfirmationFrom;
    }
    public LocalDateTime getPaiementDateConfirmationTo(){
        return this.paiementDateConfirmationTo;
    }
    public void setPaiementDateConfirmationTo(LocalDateTime paiementDateConfirmationTo){
        this.paiementDateConfirmationTo = paiementDateConfirmationTo;
    }

    public CouponCriteria getCoupon(){
        return this.coupon;
    }

    public void setCoupon(CouponCriteria coupon){
        this.coupon = coupon;
    }
    public List<CouponCriteria> getCoupons(){
        return this.coupons;
    }

    public void setCoupons(List<CouponCriteria> coupons){
        this.coupons = coupons;
    }
    public PaimentCouponStateCriteria getPaimentCouponState(){
        return this.paimentCouponState;
    }

    public void setPaimentCouponState(PaimentCouponStateCriteria paimentCouponState){
        this.paimentCouponState = paimentCouponState;
    }
    public List<PaimentCouponStateCriteria> getPaimentCouponStates(){
        return this.paimentCouponStates;
    }

    public void setPaimentCouponStates(List<PaimentCouponStateCriteria> paimentCouponStates){
        this.paimentCouponStates = paimentCouponStates;
    }
}
