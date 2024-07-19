package  ma.zs.zyn.ws.converter.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.forum.ForumConverter;
import ma.zs.zyn.bean.core.forum.Forum;

import ma.zs.zyn.bean.core.forum.Forum;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.forum.ForumComment;
import ma.zs.zyn.ws.dto.forum.ForumCommentDto;

@Component
public class ForumCommentConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private ForumConverter forumConverter ;
    private boolean collaborator;
    private boolean forum;

    public  ForumCommentConverter() {
        initObject(true);
    }


    public ForumComment toItem(ForumCommentDto dto) {
        if (dto == null) {
            return null;
        } else {
        ForumComment item = new ForumComment();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCreationDate()))
                item.setCreationDate(DateUtil.stringEnToDate(dto.getCreationDate()));
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(dto.getForum() != null && dto.getForum().getId() != null){
                item.setForum(new Forum());
                item.getForum().setId(dto.getForum().getId());
                item.getForum().setId(dto.getForum().getId());
            }




        return item;
        }
    }


    public ForumCommentDto toDto(ForumComment item) {
        if (item == null) {
            return null;
        } else {
            ForumCommentDto dto = new ForumCommentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getCreationDate()!=null)
                dto.setCreationDate(DateUtil.dateTimeToString(item.getCreationDate()));
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.forum && item.getForum()!=null) {
                dto.setForum(forumConverter.toDto(item.getForum())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.collaborator = value;
        this.forum = value;
    }
	
    public List<ForumComment> toItem(List<ForumCommentDto> dtos) {
        List<ForumComment> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ForumCommentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ForumCommentDto> toDto(List<ForumComment> items) {
        List<ForumCommentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ForumComment item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ForumCommentDto dto, ForumComment t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getForum() == null  && dto.getForum() != null){
            t.setForum(new Forum());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getForum() != null)
        forumConverter.copy(dto.getForum(), t.getForum());
    }

    public List<ForumComment> copy(List<ForumCommentDto> dtos) {
        List<ForumComment> result = new ArrayList<>();
        if (dtos != null) {
            for (ForumCommentDto dto : dtos) {
                ForumComment instance = new ForumComment();
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
    public ForumConverter getForumConverter(){
        return this.forumConverter;
    }
    public void setForumConverter(ForumConverter forumConverter ){
        this.forumConverter = forumConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isForum(){
        return this.forum;
    }
    public void  setForum(boolean forum){
        this.forum = forum;
    }
}
