package  ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.project.ProjectTechnologyTypeConverter;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyDto;

@Component
public class ProjectTechnologyConverter {

    @Autowired
    private ProjectTechnologyTypeConverter projectTechnologyTypeConverter ;
    private boolean projectTechnologyType;

    public  ProjectTechnologyConverter() {
        initObject(true);
    }


    public ProjectTechnology toItem(ProjectTechnologyDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectTechnology item = new ProjectTechnology();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDefaultDbName()))
                item.setDefaultDbName(dto.getDefaultDbName());
            if(StringUtil.isNotEmpty(dto.getDefaultUserName()))
                item.setDefaultUserName(dto.getDefaultUserName());
            if(StringUtil.isNotEmpty(dto.getDefaultUserPassword()))
                item.setDefaultUserPassword(dto.getDefaultUserPassword());
            if(StringUtil.isNotEmpty(dto.getDefaultPort()))
                item.setDefaultPort(dto.getDefaultPort());
            if(StringUtil.isNotEmpty(dto.getDefaultBasePackage()))
                item.setDefaultBasePackage(dto.getDefaultBasePackage());
            if(this.projectTechnologyType && dto.getProjectTechnologyType()!=null)
                item.setProjectTechnologyType(projectTechnologyTypeConverter.toItem(dto.getProjectTechnologyType())) ;




        return item;
        }
    }


    public ProjectTechnologyDto toDto(ProjectTechnology item) {
        if (item == null) {
            return null;
        } else {
            ProjectTechnologyDto dto = new ProjectTechnologyDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDefaultDbName()))
                dto.setDefaultDbName(item.getDefaultDbName());
            if(StringUtil.isNotEmpty(item.getDefaultUserName()))
                dto.setDefaultUserName(item.getDefaultUserName());
            if(StringUtil.isNotEmpty(item.getDefaultUserPassword()))
                dto.setDefaultUserPassword(item.getDefaultUserPassword());
            if(StringUtil.isNotEmpty(item.getDefaultPort()))
                dto.setDefaultPort(item.getDefaultPort());
            if(StringUtil.isNotEmpty(item.getDefaultBasePackage()))
                dto.setDefaultBasePackage(item.getDefaultBasePackage());
            if(this.projectTechnologyType && item.getProjectTechnologyType()!=null) {
                dto.setProjectTechnologyType(projectTechnologyTypeConverter.toDto(item.getProjectTechnologyType())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.projectTechnologyType = value;
    }
	
    public List<ProjectTechnology> toItem(List<ProjectTechnologyDto> dtos) {
        List<ProjectTechnology> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTechnologyDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTechnologyDto> toDto(List<ProjectTechnology> items) {
        List<ProjectTechnologyDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectTechnology item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTechnologyDto dto, ProjectTechnology t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProjectTechnologyType() == null  && dto.getProjectTechnologyType() != null){
            t.setProjectTechnologyType(new ProjectTechnologyType());
        }
        if (dto.getProjectTechnologyType() != null)
        projectTechnologyTypeConverter.copy(dto.getProjectTechnologyType(), t.getProjectTechnologyType());
    }

    public List<ProjectTechnology> copy(List<ProjectTechnologyDto> dtos) {
        List<ProjectTechnology> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTechnologyDto dto : dtos) {
                ProjectTechnology instance = new ProjectTechnology();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectTechnologyTypeConverter getProjectTechnologyTypeConverter(){
        return this.projectTechnologyTypeConverter;
    }
    public void setProjectTechnologyTypeConverter(ProjectTechnologyTypeConverter projectTechnologyTypeConverter ){
        this.projectTechnologyTypeConverter = projectTechnologyTypeConverter;
    }
    public boolean  isProjectTechnologyType(){
        return this.projectTechnologyType;
    }
    public void  setProjectTechnologyType(boolean projectTechnologyType){
        this.projectTechnologyType = projectTechnologyType;
    }
}
