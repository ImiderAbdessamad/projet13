package ma.zs.zyn.ws.converter.project;

import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.ws.dto.project.ProjectDetailDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectDetailConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    @Autowired
    private ProjectTechnologyConverter projectTechnologyConverter ;
    @Autowired
    private ProjectTechnologyProfileConverter projectTechnologyProfileConverter ;

    private boolean projectTechnology;
    private boolean project;
    private boolean projectTechnologyProfile;


    public  ProjectDetailConverter() {
        initObject(true);
    }


    public ProjectDetail toItem(ProjectDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectDetail item = new ProjectDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getDbName()))
                item.setDbName(dto.getDbName());
            if(StringUtil.isNotEmpty(dto.getDbPassword()))
                item.setDbPassword(dto.getDbPassword());
            if(StringUtil.isNotEmpty(dto.getDbUserName()))
                item.setDbUserName(dto.getDbUserName());
            if(StringUtil.isNotEmpty(dto.getBasePackage()))
                item.setBasePackage(dto.getBasePackage());
            if(StringUtil.isNotEmpty(dto.getMsName()))
                item.setMsName(dto.getMsName());
            if(StringUtil.isNotEmpty(dto.getPort()))
                item.setPort(dto.getPort());
            if(StringUtil.isNotEmpty(dto.getPortDev()))
                item.setPortDev(dto.getPortDev());
            if(StringUtil.isNotEmpty(dto.getPortTest()))
                item.setPortTest(dto.getPortTest());
            if(StringUtil.isNotEmpty(dto.getPortProd()))
                item.setPortProd(dto.getPortProd());
            if(dto.getEnabled() != null)
                item.setEnabled(dto.getEnabled());
            if(this.projectTechnology && dto.getProjectTechnology()!=null)
                item.setProjectTechnology(projectTechnologyConverter.toItem(dto.getProjectTechnology())) ;

            if(dto.getProject() != null && dto.getProject().getId() != null){
                item.setProject(new Project());
                item.getProject().setId(dto.getProject().getId());
                item.getProject().setTitleChat(dto.getProject().getTitleChat());
            }

            if(this.projectTechnologyProfile && dto.getProjectTechnologyProfile()!=null)
                item.setProjectTechnologyProfile(projectTechnologyProfileConverter.toItem(dto.getProjectTechnologyProfile())) ;





        return item;
        }
    }


    public ProjectDetailDto toDto(ProjectDetail item) {
        if (item == null) {
            return null;
        } else {
            ProjectDetailDto dto = new ProjectDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getDbName()))
                dto.setDbName(item.getDbName());
            if(StringUtil.isNotEmpty(item.getDbPassword()))
                dto.setDbPassword(item.getDbPassword());
            if(StringUtil.isNotEmpty(item.getDbUserName()))
                dto.setDbUserName(item.getDbUserName());
            if(StringUtil.isNotEmpty(item.getBasePackage()))
                dto.setBasePackage(item.getBasePackage());
            if(StringUtil.isNotEmpty(item.getMsName()))
                dto.setMsName(item.getMsName());
            if(StringUtil.isNotEmpty(item.getPort()))
                dto.setPort(item.getPort());
            if(StringUtil.isNotEmpty(item.getPortDev()))
                dto.setPortDev(item.getPortDev());
            if(StringUtil.isNotEmpty(item.getPortTest()))
                dto.setPortTest(item.getPortTest());
            if(StringUtil.isNotEmpty(item.getPortProd()))
                dto.setPortProd(item.getPortProd());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(this.projectTechnology && item.getProjectTechnology()!=null) {
                dto.setProjectTechnology(projectTechnologyConverter.toDto(item.getProjectTechnology())) ;

            }
            if(this.project && item.getProject()!=null) {
                dto.setProject(projectConverter.toDto(item.getProject())) ;

            }
            if(this.projectTechnologyProfile && item.getProjectTechnologyProfile()!=null) {
                dto.setProjectTechnologyProfile(projectTechnologyProfileConverter.toDto(item.getProjectTechnologyProfile())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.projectTechnology = value;
        this.project = value;
        this.projectTechnologyProfile = value;
    }
	
    public List<ProjectDetail> toItem(List<ProjectDetailDto> dtos) {
        List<ProjectDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectDetailDto> toDto(List<ProjectDetail> items) {
        List<ProjectDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectDetailDto dto, ProjectDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProjectTechnology() == null  && dto.getProjectTechnology() != null){
            t.setProjectTechnology(new ProjectTechnology());
        }
        if(t.getProject() == null  && dto.getProject() != null){
            t.setProject(new Project());
        }
        if(t.getProjectTechnologyProfile() == null  && dto.getProjectTechnologyProfile() != null){
            t.setProjectTechnologyProfile(new ProjectTechnologyProfile());
        }
        if (dto.getProjectTechnology() != null)
          projectTechnologyConverter.copy(dto.getProjectTechnology(), t.getProjectTechnology());
        if (dto.getProject() != null)
           projectConverter.copy(dto.getProject(), t.getProject());
        if (dto.getProjectTechnologyProfile() != null)
          projectTechnologyProfileConverter.copy(dto.getProjectTechnologyProfile(), t.getProjectTechnologyProfile());

    }

    public List<ProjectDetail> copy(List<ProjectDetailDto> dtos) {
        List<ProjectDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectDetailDto dto : dtos) {
                ProjectDetail instance = new ProjectDetail();
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
    public ProjectTechnologyConverter getProjectTechnologyConverter(){
        return this.projectTechnologyConverter;
    }
    public void setProjectTechnologyConverter(ProjectTechnologyConverter projectTechnologyConverter ){
        this.projectTechnologyConverter = projectTechnologyConverter;
    }
    public ProjectTechnologyProfileConverter getProjectTechnologyProfileConverter(){
        return this.projectTechnologyProfileConverter;
    }
    public void setProjectTechnologyProfileConverter(ProjectTechnologyProfileConverter projectTechnologyProfileConverter ){
        this.projectTechnologyProfileConverter = projectTechnologyProfileConverter;
    }
    public boolean  isProjectTechnology(){
        return this.projectTechnology;
    }
    public void  setProjectTechnology(boolean projectTechnology){
        this.projectTechnology = projectTechnology;
    }
    public boolean  isProject(){
        return this.project;
    }
    public void  setProject(boolean project){
        this.project = project;
    }
    public boolean  isProjectTechnologyProfile(){
        return this.projectTechnologyProfile;
    }
    public void  setProjectTechnologyProfile(boolean projectTechnologyProfile){
        this.projectTechnologyProfile = projectTechnologyProfile;
    }


}
