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
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import ma.zs.zyn.ws.dto.coupon.PaimentCouponStateDto;

@Component
public class PaimentCouponStateConverter {


    public  PaimentCouponStateConverter() {
    }


    public PaimentCouponState toItem(PaimentCouponStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentCouponState item = new PaimentCouponState();
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


    public PaimentCouponStateDto toDto(PaimentCouponState item) {
        if (item == null) {
            return null;
        } else {
            PaimentCouponStateDto dto = new PaimentCouponStateDto();
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


	
    public List<PaimentCouponState> toItem(List<PaimentCouponStateDto> dtos) {
        List<PaimentCouponState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentCouponStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentCouponStateDto> toDto(List<PaimentCouponState> items) {
        List<PaimentCouponStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentCouponState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentCouponStateDto dto, PaimentCouponState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PaimentCouponState> copy(List<PaimentCouponStateDto> dtos) {
        List<PaimentCouponState> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentCouponStateDto dto : dtos) {
                PaimentCouponState instance = new PaimentCouponState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
