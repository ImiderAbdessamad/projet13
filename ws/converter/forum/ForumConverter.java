package ma.zs.zyn.ws.converter.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.forum.ForumSubjectConverter;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.ws.converter.forum.ForumStateConverter;
import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.ws.converter.forum.ForumCommentConverter;
import ma.zs.zyn.bean.core.forum.ForumComment;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.forum.Forum;
import ma.zs.zyn.ws.dto.forum.ForumDto;

@Component
public class ForumConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private ForumSubjectConverter forumSubjectConverter ;
    @Autowired
    private ForumStateConverter forumStateConverter ;
    @Autowired
    private ForumCommentConverter forumCommentConverter ;
    private boolean collaborator;
    private boolean forumSubject;
    private boolean forumState;
    private boolean forumComments;

    public  ForumConverter() {
        init(true);
    }


    public Forum toItem(ForumDto dto) {
        if (dto == null) {
            return null;
        } else {
        Forum item = new Forum();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(StringUtil.isNotEmpty(dto.getCreationDate()))
                item.setCreationDate(DateUtil.stringEnToDate(dto.getCreationDate()));
            if(StringUtil.isNotEmpty(dto.getPublicationDate()))
                item.setPublicationDate(DateUtil.stringEnToDate(dto.getPublicationDate()));
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getLikes()))
                item.setLikes(dto.getLikes());
            if(StringUtil.isNotEmpty(dto.getComments()))
                item.setComments(dto.getComments());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.forumSubject && dto.getForumSubject()!=null)
                item.setForumSubject(forumSubjectConverter.toItem(dto.getForumSubject())) ;

            if(this.forumState && dto.getForumState()!=null)
                item.setForumState(forumStateConverter.toItem(dto.getForumState())) ;


            if(this.forumComments && ListUtil.isNotEmpty(dto.getForumComments()))
                item.setForumComments(forumCommentConverter.toItem(dto.getForumComments()));


        return item;
        }
    }


    public ForumDto toDto(Forum item) {
        if (item == null) {
            return null;
        } else {
            ForumDto dto = new ForumDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(item.getCreationDate()!=null)
                dto.setCreationDate(DateUtil.dateTimeToString(item.getCreationDate()));
            if(item.getPublicationDate()!=null)
                dto.setPublicationDate(DateUtil.dateTimeToString(item.getPublicationDate()));
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getLikes()))
                dto.setLikes(item.getLikes());
            if(StringUtil.isNotEmpty(item.getComments()))
                dto.setComments(item.getComments());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.forumSubject && item.getForumSubject()!=null) {
                dto.setForumSubject(forumSubjectConverter.toDto(item.getForumSubject())) ;

            }
            if(this.forumState && item.getForumState()!=null) {
                dto.setForumState(forumStateConverter.toDto(item.getForumState())) ;

            }
        if(this.forumComments && ListUtil.isNotEmpty(item.getForumComments())){
            forumCommentConverter.init(true);
            forumCommentConverter.setForum(false);
            dto.setForumComments(forumCommentConverter.toDto(item.getForumComments()));
            forumCommentConverter.setForum(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.forumComments = value;
    }
    public void initObject(boolean value) {
        this.collaborator = value;
        this.forumSubject = value;
        this.forumState = value;
    }
	
    public List<Forum> toItem(List<ForumDto> dtos) {
        List<Forum> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ForumDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ForumDto> toDto(List<Forum> items) {
        List<ForumDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Forum item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ForumDto dto, Forum t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getForumSubject() == null  && dto.getForumSubject() != null){
            t.setForumSubject(new ForumSubject());
        }
        if(t.getForumState() == null  && dto.getForumState() != null){
            t.setForumState(new ForumState());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getForumSubject() != null)
        forumSubjectConverter.copy(dto.getForumSubject(), t.getForumSubject());
        if (dto.getForumState() != null)
        forumStateConverter.copy(dto.getForumState(), t.getForumState());
        if (dto.getForumComments() != null)
            t.setForumComments(forumCommentConverter.copy(dto.getForumComments()));
    }

    public List<Forum> copy(List<ForumDto> dtos) {
        List<Forum> result = new ArrayList<>();
        if (dtos != null) {
            for (ForumDto dto : dtos) {
                Forum instance = new Forum();
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
    public ForumSubjectConverter getForumSubjectConverter(){
        return this.forumSubjectConverter;
    }
    public void setForumSubjectConverter(ForumSubjectConverter forumSubjectConverter ){
        this.forumSubjectConverter = forumSubjectConverter;
    }
    public ForumStateConverter getForumStateConverter(){
        return this.forumStateConverter;
    }
    public void setForumStateConverter(ForumStateConverter forumStateConverter ){
        this.forumStateConverter = forumStateConverter;
    }
    public ForumCommentConverter getForumCommentConverter(){
        return this.forumCommentConverter;
    }
    public void setForumCommentConverter(ForumCommentConverter forumCommentConverter ){
        this.forumCommentConverter = forumCommentConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isForumSubject(){
        return this.forumSubject;
    }
    public void  setForumSubject(boolean forumSubject){
        this.forumSubject = forumSubject;
    }
    public boolean  isForumState(){
        return this.forumState;
    }
    public void  setForumState(boolean forumState){
        this.forumState = forumState;
    }
    public boolean  isForumComments(){
        return this.forumComments ;
    }
    public void  setForumComments(boolean forumComments ){
        this.forumComments  = forumComments ;
    }
}
