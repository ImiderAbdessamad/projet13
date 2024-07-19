package  ma.zs.zyn.ws.converter.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.ws.dto.forum.ForumSubjectDto;

@Component
public class ForumSubjectConverter {


    public  ForumSubjectConverter() {
    }


    public ForumSubject toItem(ForumSubjectDto dto) {
        if (dto == null) {
            return null;
        } else {
        ForumSubject item = new ForumSubject();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public ForumSubjectDto toDto(ForumSubject item) {
        if (item == null) {
            return null;
        } else {
            ForumSubjectDto dto = new ForumSubjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<ForumSubject> toItem(List<ForumSubjectDto> dtos) {
        List<ForumSubject> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ForumSubjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ForumSubjectDto> toDto(List<ForumSubject> items) {
        List<ForumSubjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ForumSubject item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ForumSubjectDto dto, ForumSubject t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ForumSubject> copy(List<ForumSubjectDto> dtos) {
        List<ForumSubject> result = new ArrayList<>();
        if (dtos != null) {
            for (ForumSubjectDto dto : dtos) {
                ForumSubject instance = new ForumSubject();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
