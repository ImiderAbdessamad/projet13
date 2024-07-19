package ma.zs.zyn.ws.converter.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationStateDto;

@Component
public class CustumorSupportConversationStateConverter {



    public CustumorSupportConversationState toItem(CustumorSupportConversationStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        CustumorSupportConversationState item = new CustumorSupportConversationState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public CustumorSupportConversationStateDto toDto(CustumorSupportConversationState item) {
        if (item == null) {
            return null;
        } else {
            CustumorSupportConversationStateDto dto = new CustumorSupportConversationStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<CustumorSupportConversationState> toItem(List<CustumorSupportConversationStateDto> dtos) {
        List<CustumorSupportConversationState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CustumorSupportConversationStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CustumorSupportConversationStateDto> toDto(List<CustumorSupportConversationState> items) {
        List<CustumorSupportConversationStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CustumorSupportConversationState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CustumorSupportConversationStateDto dto, CustumorSupportConversationState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CustumorSupportConversationState> copy(List<CustumorSupportConversationStateDto> dtos) {
        List<CustumorSupportConversationState> result = new ArrayList<>();
        if (dtos != null) {
            for (CustumorSupportConversationStateDto dto : dtos) {
                CustumorSupportConversationState instance = new CustumorSupportConversationState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
