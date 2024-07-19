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
import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.ws.dto.contactus.ContactUsCategoryDto;

@Component
public class ContactUsCategoryConverter {


    public  ContactUsCategoryConverter() {
    }


    public ContactUsCategory toItem(ContactUsCategoryDto dto) {
        if (dto == null) {
            return null;
        } else {
        ContactUsCategory item = new ContactUsCategory();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public ContactUsCategoryDto toDto(ContactUsCategory item) {
        if (item == null) {
            return null;
        } else {
            ContactUsCategoryDto dto = new ContactUsCategoryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<ContactUsCategory> toItem(List<ContactUsCategoryDto> dtos) {
        List<ContactUsCategory> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ContactUsCategoryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ContactUsCategoryDto> toDto(List<ContactUsCategory> items) {
        List<ContactUsCategoryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ContactUsCategory item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ContactUsCategoryDto dto, ContactUsCategory t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ContactUsCategory> copy(List<ContactUsCategoryDto> dtos) {
        List<ContactUsCategory> result = new ArrayList<>();
        if (dtos != null) {
            for (ContactUsCategoryDto dto : dtos) {
                ContactUsCategory instance = new ContactUsCategory();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
