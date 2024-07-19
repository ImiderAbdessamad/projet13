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
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationCategoryDto;

@Component
public class CustumorSupportConversationCategoryConverter {



    public CustumorSupportConversationCategory toItem(CustumorSupportConversationCategoryDto dto) {
        if (dto == null) {
            return null;
        } else {
        CustumorSupportConversationCategory item = new CustumorSupportConversationCategory();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public CustumorSupportConversationCategoryDto toDto(CustumorSupportConversationCategory item) {
        if (item == null) {
            return null;
        } else {
            CustumorSupportConversationCategoryDto dto = new CustumorSupportConversationCategoryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<CustumorSupportConversationCategory> toItem(List<CustumorSupportConversationCategoryDto> dtos) {
        List<CustumorSupportConversationCategory> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CustumorSupportConversationCategoryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CustumorSupportConversationCategoryDto> toDto(List<CustumorSupportConversationCategory> items) {
        List<CustumorSupportConversationCategoryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CustumorSupportConversationCategory item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CustumorSupportConversationCategoryDto dto, CustumorSupportConversationCategory t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CustumorSupportConversationCategory> copy(List<CustumorSupportConversationCategoryDto> dtos) {
        List<CustumorSupportConversationCategory> result = new ArrayList<>();
        if (dtos != null) {
            for (CustumorSupportConversationCategoryDto dto : dtos) {
                CustumorSupportConversationCategory instance = new CustumorSupportConversationCategory();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
