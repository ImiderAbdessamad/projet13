package ma.zs.zyn.ws.converter.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.support.CustumorSupportConversationConverter;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;

import ma.zs.zyn.bean.core.support.CustumorSupportConversation;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationMessageDto;

@Component
public class CustumorSupportConversationMessageConverter {

    @Autowired
    private CustumorSupportConversationConverter custumorSupportConversationConverter ;
    private boolean custumorSupportConversation;

    public  CustumorSupportConversationMessageConverter() {
        initObject(true);
    }

    public CustumorSupportConversationMessage toItem(CustumorSupportConversationMessageDto dto) {
        if (dto == null) {
            return null;
        } else {
        CustumorSupportConversationMessage item = new CustumorSupportConversationMessage();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(dto.getCollaborator() != null)
                item.setCollaborator(dto.getCollaborator());
            if(StringUtil.isNotEmpty(dto.getCreationDate()))
                item.setCreationDate(DateUtil.stringEnToDate(dto.getCreationDate()));
            if(dto.getCustumorSupportConversation() != null && dto.getCustumorSupportConversation().getId() != null){
                item.setCustumorSupportConversation(new CustumorSupportConversation());
                item.getCustumorSupportConversation().setId(dto.getCustumorSupportConversation().getId());
                item.getCustumorSupportConversation().setId(dto.getCustumorSupportConversation().getId());
            }




        return item;
        }
    }


    public CustumorSupportConversationMessageDto toDto(CustumorSupportConversationMessage item) {
        if (item == null) {
            return null;
        } else {
            CustumorSupportConversationMessageDto dto = new CustumorSupportConversationMessageDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
                dto.setCollaborator(item.getCollaborator());
            if(item.getCreationDate()!=null)
                dto.setCreationDate(DateUtil.dateTimeToString(item.getCreationDate()));
            if(this.custumorSupportConversation && item.getCustumorSupportConversation()!=null) {
                dto.setCustumorSupportConversation(custumorSupportConversationConverter.toDto(item.getCustumorSupportConversation())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.custumorSupportConversation = value;
    }
	
    public List<CustumorSupportConversationMessage> toItem(List<CustumorSupportConversationMessageDto> dtos) {
        List<CustumorSupportConversationMessage> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CustumorSupportConversationMessageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CustumorSupportConversationMessageDto> toDto(List<CustumorSupportConversationMessage> items) {
        List<CustumorSupportConversationMessageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CustumorSupportConversationMessage item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CustumorSupportConversationMessageDto dto, CustumorSupportConversationMessage t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCustumorSupportConversation() == null  && dto.getCustumorSupportConversation() != null){
            t.setCustumorSupportConversation(new CustumorSupportConversation());
        }
        if (dto.getCustumorSupportConversation() != null)
        custumorSupportConversationConverter.copy(dto.getCustumorSupportConversation(), t.getCustumorSupportConversation());
    }

    public List<CustumorSupportConversationMessage> copy(List<CustumorSupportConversationMessageDto> dtos) {
        List<CustumorSupportConversationMessage> result = new ArrayList<>();
        if (dtos != null) {
            for (CustumorSupportConversationMessageDto dto : dtos) {
                CustumorSupportConversationMessage instance = new CustumorSupportConversationMessage();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CustumorSupportConversationConverter getCustumorSupportConversationConverter(){
        return this.custumorSupportConversationConverter;
    }
    public void setCustumorSupportConversationConverter(CustumorSupportConversationConverter custumorSupportConversationConverter ){
        this.custumorSupportConversationConverter = custumorSupportConversationConverter;
    }
    public boolean  isCustumorSupportConversation(){
        return this.custumorSupportConversation;
    }
    public void  setCustumorSupportConversation(boolean custumorSupportConversation){
        this.custumorSupportConversation = custumorSupportConversation;
    }
}
