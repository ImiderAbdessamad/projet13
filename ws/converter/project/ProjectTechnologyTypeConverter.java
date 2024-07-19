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
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyTypeDto;

@Component
public class ProjectTechnologyTypeConverter {


    public  ProjectTechnologyTypeConverter() {
    }


    public ProjectTechnologyType toItem(ProjectTechnologyTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectTechnologyType item = new ProjectTechnologyType();
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


    public ProjectTechnologyTypeDto toDto(ProjectTechnologyType item) {
        if (item == null) {
            return null;
        } else {
            ProjectTechnologyTypeDto dto = new ProjectTechnologyTypeDto();
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


	
    public List<ProjectTechnologyType> toItem(List<ProjectTechnologyTypeDto> dtos) {
        List<ProjectTechnologyType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTechnologyTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTechnologyTypeDto> toDto(List<ProjectTechnologyType> items) {
        List<ProjectTechnologyTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectTechnologyType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTechnologyTypeDto dto, ProjectTechnologyType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectTechnologyType> copy(List<ProjectTechnologyTypeDto> dtos) {
        List<ProjectTechnologyType> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTechnologyTypeDto dto : dtos) {
                ProjectTechnologyType instance = new ProjectTechnologyType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
