package ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.ws.dto.project.RemoteRepoTypeDto;

@Component
public class RemoteRepoTypeConverter {


    public  RemoteRepoTypeConverter() {
    }


    public RemoteRepoType toItem(RemoteRepoTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        RemoteRepoType item = new RemoteRepoType();
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


    public RemoteRepoTypeDto toDto(RemoteRepoType item) {
        if (item == null) {
            return null;
        } else {
            RemoteRepoTypeDto dto = new RemoteRepoTypeDto();
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


	
    public List<RemoteRepoType> toItem(List<RemoteRepoTypeDto> dtos) {
        List<RemoteRepoType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RemoteRepoTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RemoteRepoTypeDto> toDto(List<RemoteRepoType> items) {
        List<RemoteRepoTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RemoteRepoType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RemoteRepoTypeDto dto, RemoteRepoType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<RemoteRepoType> copy(List<RemoteRepoTypeDto> dtos) {
        List<RemoteRepoType> result = new ArrayList<>();
        if (dtos != null) {
            for (RemoteRepoTypeDto dto : dtos) {
                RemoteRepoType instance = new RemoteRepoType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
