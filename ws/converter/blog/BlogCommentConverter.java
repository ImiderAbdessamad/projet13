package ma.zs.zyn.ws.converter.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.blog.BlogConverter;
import ma.zs.zyn.bean.core.blog.Blog;

import ma.zs.zyn.bean.core.blog.Blog;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.ws.dto.blog.BlogCommentDto;

@Component
public class BlogCommentConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private BlogConverter blogConverter ;
    private boolean collaborator;
    private boolean blog;

    public  BlogCommentConverter() {
        initObject(true);
    }


    public BlogComment toItem(BlogCommentDto dto) {
        if (dto == null) {
            return null;
        } else {
        BlogComment item = new BlogComment();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCreationDate()))
                item.setCreationDate(DateUtil.stringEnToDate(dto.getCreationDate()));
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(dto.getBlog() != null && dto.getBlog().getId() != null){
                item.setBlog(new Blog());
                item.getBlog().setId(dto.getBlog().getId());
                item.getBlog().setId(dto.getBlog().getId());
            }




        return item;
        }
    }


    public BlogCommentDto toDto(BlogComment item) {
        if (item == null) {
            return null;
        } else {
            BlogCommentDto dto = new BlogCommentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getCreationDate()!=null)
                dto.setCreationDate(DateUtil.dateTimeToString(item.getCreationDate()));
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.blog && item.getBlog()!=null) {
                dto.setBlog(blogConverter.toDto(item.getBlog())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.collaborator = value;
        this.blog = value;
    }
	
    public List<BlogComment> toItem(List<BlogCommentDto> dtos) {
        List<BlogComment> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BlogCommentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BlogCommentDto> toDto(List<BlogComment> items) {
        List<BlogCommentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (BlogComment item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BlogCommentDto dto, BlogComment t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getBlog() == null  && dto.getBlog() != null){
            t.setBlog(new Blog());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getBlog() != null)
        blogConverter.copy(dto.getBlog(), t.getBlog());
    }

    public List<BlogComment> copy(List<BlogCommentDto> dtos) {
        List<BlogComment> result = new ArrayList<>();
        if (dtos != null) {
            for (BlogCommentDto dto : dtos) {
                BlogComment instance = new BlogComment();
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
    public BlogConverter getBlogConverter(){
        return this.blogConverter;
    }
    public void setBlogConverter(BlogConverter blogConverter ){
        this.blogConverter = blogConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isBlog(){
        return this.blog;
    }
    public void  setBlog(boolean blog){
        this.blog = blog;
    }
}
