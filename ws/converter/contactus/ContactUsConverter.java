package ma.zs.zyn.ws.converter.contactus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.contactus.ContactUsCategoryConverter;
import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.ws.converter.contactus.ContactUsStateConverter;
import ma.zs.zyn.bean.core.contactus.ContactUsState;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.ws.dto.contactus.ContactUsDto;

@Component
public class ContactUsConverter {

    @Autowired
    private ContactUsCategoryConverter contactUsCategoryConverter ;
    @Autowired
    private ContactUsStateConverter contactUsStateConverter ;
    private boolean contactUsCategory;
    private boolean contactUsState;

    public  ContactUsConverter() {
        initObject(true);
    }


    public ContactUs toItem(ContactUsDto dto) {
        if (dto == null) {
            return null;
        } else {
        ContactUs item = new ContactUs();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPhone()))
                item.setPhone(dto.getPhone());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getObject()))
                item.setObject(dto.getObject());
            if(StringUtil.isNotEmpty(dto.getMessage()))
                item.setMessage(dto.getMessage());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.contactUsCategory && dto.getContactUsCategory()!=null)
                item.setContactUsCategory(contactUsCategoryConverter.toItem(dto.getContactUsCategory())) ;

            if(this.contactUsState && dto.getContactUsState()!=null)
                item.setContactUsState(contactUsStateConverter.toItem(dto.getContactUsState())) ;




        return item;
        }
    }


    public ContactUsDto toDto(ContactUs item) {
        if (item == null) {
            return null;
        } else {
            ContactUsDto dto = new ContactUsDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPhone()))
                dto.setPhone(item.getPhone());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getObject()))
                dto.setObject(item.getObject());
            if(StringUtil.isNotEmpty(item.getMessage()))
                dto.setMessage(item.getMessage());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.contactUsCategory && item.getContactUsCategory()!=null) {
                dto.setContactUsCategory(contactUsCategoryConverter.toDto(item.getContactUsCategory())) ;

            }
            if(this.contactUsState && item.getContactUsState()!=null) {
                dto.setContactUsState(contactUsStateConverter.toDto(item.getContactUsState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.contactUsCategory = value;
        this.contactUsState = value;
    }
	
    public List<ContactUs> toItem(List<ContactUsDto> dtos) {
        List<ContactUs> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ContactUsDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ContactUsDto> toDto(List<ContactUs> items) {
        List<ContactUsDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ContactUs item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ContactUsDto dto, ContactUs t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getContactUsCategory() == null  && dto.getContactUsCategory() != null){
            t.setContactUsCategory(new ContactUsCategory());
        }
        if(t.getContactUsState() == null  && dto.getContactUsState() != null){
            t.setContactUsState(new ContactUsState());
        }
        if (dto.getContactUsCategory() != null)
        contactUsCategoryConverter.copy(dto.getContactUsCategory(), t.getContactUsCategory());
        if (dto.getContactUsState() != null)
        contactUsStateConverter.copy(dto.getContactUsState(), t.getContactUsState());
    }

    public List<ContactUs> copy(List<ContactUsDto> dtos) {
        List<ContactUs> result = new ArrayList<>();
        if (dtos != null) {
            for (ContactUsDto dto : dtos) {
                ContactUs instance = new ContactUs();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ContactUsCategoryConverter getContactUsCategoryConverter(){
        return this.contactUsCategoryConverter;
    }
    public void setContactUsCategoryConverter(ContactUsCategoryConverter contactUsCategoryConverter ){
        this.contactUsCategoryConverter = contactUsCategoryConverter;
    }
    public ContactUsStateConverter getContactUsStateConverter(){
        return this.contactUsStateConverter;
    }
    public void setContactUsStateConverter(ContactUsStateConverter contactUsStateConverter ){
        this.contactUsStateConverter = contactUsStateConverter;
    }
    public boolean  isContactUsCategory(){
        return this.contactUsCategory;
    }
    public void  setContactUsCategory(boolean contactUsCategory){
        this.contactUsCategory = contactUsCategory;
    }
    public boolean  isContactUsState(){
        return this.contactUsState;
    }
    public void  setContactUsState(boolean contactUsState){
        this.contactUsState = contactUsState;
    }
}
