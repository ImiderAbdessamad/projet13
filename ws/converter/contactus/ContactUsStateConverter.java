package ma.zs.zyn.ws.converter.contactus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import ma.zs.zyn.ws.dto.contactus.ContactUsStateDto;

@Component
public class ContactUsStateConverter {


    public  ContactUsStateConverter() {
    }


    public ContactUsState toItem(ContactUsStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ContactUsState item = new ContactUsState();
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


    public ContactUsStateDto toDto(ContactUsState item) {
        if (item == null) {
            return null;
        } else {
            ContactUsStateDto dto = new ContactUsStateDto();
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


	
    public List<ContactUsState> toItem(List<ContactUsStateDto> dtos) {
        List<ContactUsState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ContactUsStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ContactUsStateDto> toDto(List<ContactUsState> items) {
        List<ContactUsStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ContactUsState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ContactUsStateDto dto, ContactUsState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ContactUsState> copy(List<ContactUsStateDto> dtos) {
        List<ContactUsState> result = new ArrayList<>();
        if (dtos != null) {
            for (ContactUsStateDto dto : dtos) {
                ContactUsState instance = new ContactUsState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
