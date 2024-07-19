package  ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.project.ProjectTechnologyConverter;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.ws.converter.project.RemoteRepoInfoConverter;
import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.ws.converter.project.ConversationConverter;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.ws.converter.project.ProjectTechnologyProfileConverter;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.ws.converter.project.ProjectDetailConverter;
import ma.zs.zyn.bean.core.project.ProjectDetail;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.ws.dto.project.ProjectDto;

@Component
public class ProjectConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private ProjectTechnologyConverter projectTechnologyConverter ;
    @Autowired
    private RemoteRepoInfoConverter remoteRepoInfoConverter ;
    @Autowired
    private ConversationConverter conversationConverter ;
    @Autowired
    private ProjectTechnologyProfileConverter projectTechnologyProfileConverter ;
    @Autowired
    private ProjectDetailConverter projectDetailConverter ;
    private boolean collaborator;
    private boolean remoteRepoInfo;
    private boolean conversations;
    private boolean projectDetails;

    public  ProjectConverter() {
        init(true);
    }


    public Project toItem(ProjectDto dto) {
        if (dto == null) {
            return null;
        } else {
            Project item = new Project();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getTitleChat()))
                item.setTitleChat(dto.getTitleChat());
            if(StringUtil.isNotEmpty(dto.getGeneratedDate()))
                item.setGeneratedDate(DateUtil.stringEnToDate(dto.getGeneratedDate()));
            if(dto.getChatDateStart() != null)
                item.setChatDateStart(dto.getChatDateStart());
            if(dto.getMicroService() != null)
                item.setMicroService(dto.getMicroService());
            if(dto.getMicroFront() != null)
                item.setMicroFront(dto.getMicroFront());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.remoteRepoInfo && dto.getRemoteRepoInfo()!=null)
                item.setRemoteRepoInfo(remoteRepoInfoConverter.toItem(dto.getRemoteRepoInfo())) ;


            if(this.conversations && ListUtil.isNotEmpty(dto.getConversations()))
                item.setConversations(conversationConverter.toItem(dto.getConversations()));
            if(this.projectDetails && ListUtil.isNotEmpty(dto.getProjectDetails()))
                item.setProjectDetails(projectDetailConverter.toItem(dto.getProjectDetails()));


            return item;
        }
    }


    public ProjectDto toDto(Project item) {
        if (item == null) {
            return null;
        } else {
            ProjectDto dto = new ProjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getTitleChat()))
                dto.setTitleChat(item.getTitleChat());
            if(item.getGeneratedDate()!=null)
                dto.setGeneratedDate(DateUtil.dateTimeToString(item.getGeneratedDate()));
            if(item.getChatDateStart()!=null)
                dto.setChatDateStart(item.getChatDateStart());
            dto.setMicroService(item.getMicroService());
            dto.setMicroFront(item.getMicroFront());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.remoteRepoInfo && item.getRemoteRepoInfo()!=null) {
                dto.setRemoteRepoInfo(remoteRepoInfoConverter.toDto(item.getRemoteRepoInfo())) ;

            }
            if(this.conversations && ListUtil.isNotEmpty(item.getConversations())){
                conversationConverter.init(true);
                conversationConverter.setProject(false);
                dto.setConversations(conversationConverter.toDto(item.getConversations()));
                conversationConverter.setProject(true);

            }
            if(this.projectDetails && ListUtil.isNotEmpty(item.getProjectDetails())){
                projectDetailConverter.init(true);
                projectDetailConverter.setProject(false);
                dto.setProjectDetails(projectDetailConverter.toDto(item.getProjectDetails()));
                projectDetailConverter.setProject(true);

            }


            return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.conversations = value;
        this.projectDetails = value;
    }
    public void initObject(boolean value) {
        this.collaborator = value;
        this.remoteRepoInfo = value;
    }

    public List<Project> toItem(List<ProjectDto> dtos) {
        List<Project> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectDto> toDto(List<Project> items) {
        List<ProjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Project item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectDto dto, Project t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getRemoteRepoInfo() == null  && dto.getRemoteRepoInfo() != null){
            t.setRemoteRepoInfo(new RemoteRepoInfo());
        }
        if (dto.getCollaborator() != null)
            collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getRemoteRepoInfo() != null)
            remoteRepoInfoConverter.copy(dto.getRemoteRepoInfo(), t.getRemoteRepoInfo());
        if (dto.getConversations() != null)
            t.setConversations(conversationConverter.copy(dto.getConversations()));
        if (dto.getProjectDetails() != null)
            t.setProjectDetails(projectDetailConverter.copy(dto.getProjectDetails()));
    }

    public List<Project> copy(List<ProjectDto> dtos) {
        List<Project> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectDto dto : dtos) {
                Project instance = new Project();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CollaboratorConverter getCollaboratorConverter(){
        return this.collaboratorConverter;
    }
    public void setCollaboratorConverter(CollaboratorConverter collaboratorConverter ){
        this.collaboratorConverter = collaboratorConverter;
    }
    public ProjectTechnologyConverter getProjectTechnologyConverter(){
        return this.projectTechnologyConverter;
    }
    public void setProjectTechnologyConverter(ProjectTechnologyConverter projectTechnologyConverter ){
        this.projectTechnologyConverter = projectTechnologyConverter;
    }
    public RemoteRepoInfoConverter getRemoteRepoInfoConverter(){
        return this.remoteRepoInfoConverter;
    }
    public void setRemoteRepoInfoConverter(RemoteRepoInfoConverter remoteRepoInfoConverter ){
        this.remoteRepoInfoConverter = remoteRepoInfoConverter;
    }
    public ConversationConverter getConversationConverter(){
        return this.conversationConverter;
    }
    public void setConversationConverter(ConversationConverter conversationConverter ){
        this.conversationConverter = conversationConverter;
    }
    public ProjectTechnologyProfileConverter getProjectTechnologyProfileConverter(){
        return this.projectTechnologyProfileConverter;
    }
    public void setProjectTechnologyProfileConverter(ProjectTechnologyProfileConverter projectTechnologyProfileConverter ){
        this.projectTechnologyProfileConverter = projectTechnologyProfileConverter;
    }
    public ProjectDetailConverter getProjectDetailConverter(){
        return this.projectDetailConverter;
    }
    public void setProjectDetailConverter(ProjectDetailConverter projectDetailConverter ){
        this.projectDetailConverter = projectDetailConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isRemoteRepoInfo(){
        return this.remoteRepoInfo;
    }
    public void  setRemoteRepoInfo(boolean remoteRepoInfo){
        this.remoteRepoInfo = remoteRepoInfo;
    }
    public boolean  isConversations(){
        return this.conversations ;
    }
    public void  setConversations(boolean conversations ){
        this.conversations  = conversations ;
    }
    public boolean  isProjectDetails(){
        return this.projectDetails ;
    }
    public void  setProjectDetails(boolean projectDetails ){
        this.projectDetails  = projectDetails ;
    }
}
