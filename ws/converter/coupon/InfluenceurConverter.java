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
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.ws.dto.coupon.InfluenceurDto;

@Component
public class InfluenceurConverter {


    public  InfluenceurConverter() {
    }


    public Influenceur toItem(InfluenceurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Influenceur item = new Influenceur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public InfluenceurDto toDto(Influenceur item) {
        if (item == null) {
            return null;
        } else {
            InfluenceurDto dto = new InfluenceurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());


        return dto;
        }
    }


	
    public List<Influenceur> toItem(List<InfluenceurDto> dtos) {
        List<Influenceur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InfluenceurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InfluenceurDto> toDto(List<Influenceur> items) {
        List<InfluenceurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Influenceur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InfluenceurDto dto, Influenceur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Influenceur> copy(List<InfluenceurDto> dtos) {
        List<Influenceur> result = new ArrayList<>();
        if (dtos != null) {
            for (InfluenceurDto dto : dtos) {
                Influenceur instance = new Influenceur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
