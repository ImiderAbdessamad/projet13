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
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyProfileDto;

@Component
public class ProjectTechnologyProfileConverter {


    public  ProjectTechnologyProfileConverter() {
    }


    public ProjectTechnologyProfile toItem(ProjectTechnologyProfileDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectTechnologyProfile item = new ProjectTechnologyProfile();
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


    public ProjectTechnologyProfileDto toDto(ProjectTechnologyProfile item) {
        if (item == null) {
            return null;
        } else {
            ProjectTechnologyProfileDto dto = new ProjectTechnologyProfileDto();
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


	
    public List<ProjectTechnologyProfile> toItem(List<ProjectTechnologyProfileDto> dtos) {
        List<ProjectTechnologyProfile> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTechnologyProfileDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTechnologyProfileDto> toDto(List<ProjectTechnologyProfile> items) {
        List<ProjectTechnologyProfileDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectTechnologyProfile item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTechnologyProfileDto dto, ProjectTechnologyProfile t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectTechnologyProfile> copy(List<ProjectTechnologyProfileDto> dtos) {
        List<ProjectTechnologyProfile> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTechnologyProfileDto dto : dtos) {
                ProjectTechnologyProfile instance = new ProjectTechnologyProfile();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
