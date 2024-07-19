package  ma.zs.zyn.ws.converter.project;

import ma.zs.zyn.bean.core.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.ws.dto.project.YamlFileDto;

@Component
public class YamlFileConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    private boolean project;

    public  YamlFileConverter() {
        initObject(true);
    }


    public YamlFile toItem(YamlFileDto dto) {
        if (dto == null) {
            return null;
        } else {
        YamlFile item = new YamlFile();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(this.project && dto.getProject()!=null)
                item.setProject(projectConverter.toItem(dto.getProject())) ;




        return item;
        }
    }


    public YamlFileDto toDto(YamlFile item) {
        if (item == null) {
            return null;
        } else {
            YamlFileDto dto = new YamlFileDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(this.project && item.getProject()!=null) {
                dto.setProject(projectConverter.toDto(item.getProject())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.project = value;
    }
	
    public List<YamlFile> toItem(List<YamlFileDto> dtos) {
        List<YamlFile> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (YamlFileDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<YamlFileDto> toDto(List<YamlFile> items) {
        List<YamlFileDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (YamlFile item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(YamlFileDto dto, YamlFile t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProject() == null  && dto.getProject() != null){
            t.setProject(new Project());
        }
        if (dto.getProject() != null)
        projectConverter.copy(dto.getProject(), t.getProject());
    }

    public List<YamlFile> copy(List<YamlFileDto> dtos) {
        List<YamlFile> result = new ArrayList<>();
        if (dtos != null) {
            for (YamlFileDto dto : dtos) {
                YamlFile instance = new YamlFile();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectConverter getProjectConverter(){
        return this.projectConverter;
    }
    public void setProjectConverter(ProjectConverter projectConverter ){
        this.projectConverter = projectConverter;
    }
    public boolean  isProject(){
        return this.project;
    }
    public void  setProject(boolean project){
        this.project = project;
    }
}
