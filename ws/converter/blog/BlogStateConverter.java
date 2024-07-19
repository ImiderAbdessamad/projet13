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
import ma.zs.zyn.bean.core.blog.BlogState;
import ma.zs.zyn.ws.dto.blog.BlogStateDto;

@Component
public class BlogStateConverter {


    public  BlogStateConverter() {
    }


    public BlogState toItem(BlogStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        BlogState item = new BlogState();
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


    public BlogStateDto toDto(BlogState item) {
        if (item == null) {
            return null;
        } else {
            BlogStateDto dto = new BlogStateDto();
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


	
    public List<BlogState> toItem(List<BlogStateDto> dtos) {
        List<BlogState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BlogStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BlogStateDto> toDto(List<BlogState> items) {
        List<BlogStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (BlogState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BlogStateDto dto, BlogState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<BlogState> copy(List<BlogStateDto> dtos) {
        List<BlogState> result = new ArrayList<>();
        if (dtos != null) {
            for (BlogStateDto dto : dtos) {
                BlogState instance = new BlogState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
