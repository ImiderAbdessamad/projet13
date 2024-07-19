package ma.zs.zyn.ws.converter.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.coupon.CouponConverter;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.ws.converter.coupon.PaimentCouponStateConverter;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import ma.zs.zyn.ws.dto.coupon.PaimentCouponDto;

@Component
public class PaimentCouponConverter {

    @Autowired
    private CouponConverter couponConverter ;
    @Autowired
    private PaimentCouponStateConverter paimentCouponStateConverter ;
    private boolean coupon;
    private boolean paimentCouponState;

    public  PaimentCouponConverter() {
        initObject(true);
    }


    public PaimentCoupon toItem(PaimentCouponDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentCoupon item = new PaimentCoupon();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getPaiementDate()))
                item.setPaiementDate(DateUtil.stringEnToDate(dto.getPaiementDate()));
            if(StringUtil.isNotEmpty(dto.getPaiementDateConfirmation()))
                item.setPaiementDateConfirmation(DateUtil.stringEnToDate(dto.getPaiementDateConfirmation()));
            if(this.coupon && dto.getCoupon()!=null)
                item.setCoupon(couponConverter.toItem(dto.getCoupon())) ;

            if(this.paimentCouponState && dto.getPaimentCouponState()!=null)
                item.setPaimentCouponState(paimentCouponStateConverter.toItem(dto.getPaimentCouponState())) ;




        return item;
        }
    }


    public PaimentCouponDto toDto(PaimentCoupon item) {
        if (item == null) {
            return null;
        } else {
            PaimentCouponDto dto = new PaimentCouponDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(item.getPaiementDate()!=null)
                dto.setPaiementDate(DateUtil.dateTimeToString(item.getPaiementDate()));
            if(item.getPaiementDateConfirmation()!=null)
                dto.setPaiementDateConfirmation(DateUtil.dateTimeToString(item.getPaiementDateConfirmation()));
            if(this.coupon && item.getCoupon()!=null) {
                dto.setCoupon(couponConverter.toDto(item.getCoupon())) ;

            }
            if(this.paimentCouponState && item.getPaimentCouponState()!=null) {
                dto.setPaimentCouponState(paimentCouponStateConverter.toDto(item.getPaimentCouponState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.coupon = value;
        this.paimentCouponState = value;
    }
	
    public List<PaimentCoupon> toItem(List<PaimentCouponDto> dtos) {
        List<PaimentCoupon> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentCouponDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentCouponDto> toDto(List<PaimentCoupon> items) {
        List<PaimentCouponDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentCoupon item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentCouponDto dto, PaimentCoupon t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCoupon() == null  && dto.getCoupon() != null){
            t.setCoupon(new Coupon());
        }
        if(t.getPaimentCouponState() == null  && dto.getPaimentCouponState() != null){
            t.setPaimentCouponState(new PaimentCouponState());
        }
        if (dto.getCoupon() != null)
        couponConverter.copy(dto.getCoupon(), t.getCoupon());
        if (dto.getPaimentCouponState() != null)
        paimentCouponStateConverter.copy(dto.getPaimentCouponState(), t.getPaimentCouponState());
    }

    public List<PaimentCoupon> copy(List<PaimentCouponDto> dtos) {
        List<PaimentCoupon> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentCouponDto dto : dtos) {
                PaimentCoupon instance = new PaimentCoupon();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CouponConverter getCouponConverter(){
        return this.couponConverter;
    }
    public void setCouponConverter(CouponConverter couponConverter ){
        this.couponConverter = couponConverter;
    }
    public PaimentCouponStateConverter getPaimentCouponStateConverter(){
        return this.paimentCouponStateConverter;
    }
    public void setPaimentCouponStateConverter(PaimentCouponStateConverter paimentCouponStateConverter ){
        this.paimentCouponStateConverter = paimentCouponStateConverter;
    }
    public boolean  isCoupon(){
        return this.coupon;
    }
    public void  setCoupon(boolean coupon){
        this.coupon = coupon;
    }
    public boolean  isPaimentCouponState(){
        return this.paimentCouponState;
    }
    public void  setPaimentCouponState(boolean paimentCouponState){
        this.paimentCouponState = paimentCouponState;
    }
}
