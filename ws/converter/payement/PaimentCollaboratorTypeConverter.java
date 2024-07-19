package ma.zs.zyn.ws.converter.payement;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import ma.zs.zyn.ws.dto.payement.PaimentCollaboratorTypeDto;

@Component
public class PaimentCollaboratorTypeConverter {


    public  PaimentCollaboratorTypeConverter() {
    }


    public PaimentCollaboratorType toItem(PaimentCollaboratorTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentCollaboratorType item = new PaimentCollaboratorType();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PaimentCollaboratorTypeDto toDto(PaimentCollaboratorType item) {
        if (item == null) {
            return null;
        } else {
            PaimentCollaboratorTypeDto dto = new PaimentCollaboratorTypeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<PaimentCollaboratorType> toItem(List<PaimentCollaboratorTypeDto> dtos) {
        List<PaimentCollaboratorType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentCollaboratorTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentCollaboratorTypeDto> toDto(List<PaimentCollaboratorType> items) {
        List<PaimentCollaboratorTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentCollaboratorType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentCollaboratorTypeDto dto, PaimentCollaboratorType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PaimentCollaboratorType> copy(List<PaimentCollaboratorTypeDto> dtos) {
        List<PaimentCollaboratorType> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentCollaboratorTypeDto dto : dtos) {
                PaimentCollaboratorType instance = new PaimentCollaboratorType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
