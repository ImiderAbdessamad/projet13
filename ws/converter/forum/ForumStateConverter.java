package ma.zs.zyn.ws.converter.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.ws.dto.forum.ForumStateDto;

@Component
public class ForumStateConverter {


    public  ForumStateConverter() {
    }


    public ForumState toItem(ForumStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ForumState item = new ForumState();
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


    public ForumStateDto toDto(ForumState item) {
        if (item == null) {
            return null;
        } else {
            ForumStateDto dto = new ForumStateDto();
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


	
    public List<ForumState> toItem(List<ForumStateDto> dtos) {
        List<ForumState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ForumStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ForumStateDto> toDto(List<ForumState> items) {
        List<ForumStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ForumState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ForumStateDto dto, ForumState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ForumState> copy(List<ForumStateDto> dtos) {
        List<ForumState> result = new ArrayList<>();
        if (dtos != null) {
            for (ForumStateDto dto : dtos) {
                ForumState instance = new ForumState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
