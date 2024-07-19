package  ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.project.ProjectConverter;
import ma.zs.zyn.bean.core.project.Project;

import ma.zs.zyn.bean.core.project.Project;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.ws.dto.project.ConversationDto;

@Component
public class ConversationConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    private boolean project;

    public  ConversationConverter() {
        initObject(true);
    }


    public Conversation toItem(ConversationDto dto) {
        if (dto == null) {
            return null;
        } else {
            Conversation item = new Conversation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPrompt()))
                item.setPrompt(dto.getPrompt());
            if(StringUtil.isNotEmpty(dto.getResponse()))
                item.setResponse(dto.getResponse());
            if(this.project && dto.getProject() != null ){
                item.setProject(projectConverter.toItem(dto.getProject()));
//                item.getProject().setId(dto.getProject().getId());
//                item.getProject().setTitleChat(dto.getProject().getTitleChat());
            }




            return item;
        }
    }


    public ConversationDto toDto(Conversation item) {
        if (item == null) {
            return null;
        } else {
            ConversationDto dto = new ConversationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPrompt()))
                dto.setPrompt(item.getPrompt());
            if(StringUtil.isNotEmpty(item.getResponse()))
                dto.setResponse(item.getResponse());
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

    public List<Conversation> toItem(List<ConversationDto> dtos) {
        List<Conversation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ConversationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ConversationDto> toDto(List<Conversation> items) {
        List<ConversationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Conversation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ConversationDto dto, Conversation t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProject() == null  && dto.getProject() != null){
            t.setProject(new Project());
        }
        if (dto.getProject() != null)
            projectConverter.copy(dto.getProject(), t.getProject());
    }

    public List<Conversation> copy(List<ConversationDto> dtos) {
        List<Conversation> result = new ArrayList<>();
        if (dtos != null) {
            for (ConversationDto dto : dtos) {
                Conversation instance = new Conversation();
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
