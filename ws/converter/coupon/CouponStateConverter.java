package ma.zs.zyn.ws.converter.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.coupon.CouponState;
import ma.zs.zyn.ws.dto.coupon.CouponStateDto;

@Component
public class CouponStateConverter {


    public  CouponStateConverter() {
    }


    public CouponState toItem(CouponStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        CouponState item = new CouponState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public CouponStateDto toDto(CouponState item) {
        if (item == null) {
            return null;
        } else {
            CouponStateDto dto = new CouponStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<CouponState> toItem(List<CouponStateDto> dtos) {
        List<CouponState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CouponStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CouponStateDto> toDto(List<CouponState> items) {
        List<CouponStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CouponState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CouponStateDto dto, CouponState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CouponState> copy(List<CouponStateDto> dtos) {
        List<CouponState> result = new ArrayList<>();
        if (dtos != null) {
            for (CouponStateDto dto : dtos) {
                CouponState instance = new CouponState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
