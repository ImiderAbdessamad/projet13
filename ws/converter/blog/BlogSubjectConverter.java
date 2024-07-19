package ma.zs.zyn.ws.converter.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.ws.dto.blog.BlogSubjectDto;

@Component
public class BlogSubjectConverter {


    public  BlogSubjectConverter() {
    }


    public BlogSubject toItem(BlogSubjectDto dto) {
        if (dto == null) {
            return null;
        } else {
        BlogSubject item = new BlogSubject();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public BlogSubjectDto toDto(BlogSubject item) {
        if (item == null) {
            return null;
        } else {
            BlogSubjectDto dto = new BlogSubjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<BlogSubject> toItem(List<BlogSubjectDto> dtos) {
        List<BlogSubject> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BlogSubjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BlogSubjectDto> toDto(List<BlogSubject> items) {
        List<BlogSubjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (BlogSubject item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BlogSubjectDto dto, BlogSubject t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<BlogSubject> copy(List<BlogSubjectDto> dtos) {
        List<BlogSubject> result = new ArrayList<>();
        if (dtos != null) {
            for (BlogSubjectDto dto : dtos) {
                BlogSubject instance = new BlogSubject();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
