package ma.zs.zyn.ws.converter.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.blog.BlogSubjectConverter;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.ws.converter.blog.BlogCommentConverter;
import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.ws.converter.blog.BlogStateConverter;
import ma.zs.zyn.bean.core.blog.BlogState;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.blog.Blog;
import ma.zs.zyn.ws.dto.blog.BlogDto;

@Component
public class BlogConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private BlogSubjectConverter blogSubjectConverter ;
    @Autowired
    private BlogCommentConverter blogCommentConverter ;
    @Autowired
    private BlogStateConverter blogStateConverter ;
    private boolean collaborator;
    private boolean blogSubject;
    private boolean blogState;
    private boolean blogComments;

    public  BlogConverter() {
        init(true);
    }


    public Blog toItem(BlogDto dto) {
        if (dto == null) {
            return null;
        } else {
        Blog item = new Blog();
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

            if(this.blogSubject && dto.getBlogSubject()!=null)
                item.setBlogSubject(blogSubjectConverter.toItem(dto.getBlogSubject())) ;

            if(this.blogState && dto.getBlogState()!=null)
                item.setBlogState(blogStateConverter.toItem(dto.getBlogState())) ;


            if(this.blogComments && ListUtil.isNotEmpty(dto.getBlogComments()))
                item.setBlogComments(blogCommentConverter.toItem(dto.getBlogComments()));


        return item;
        }
    }


    public BlogDto toDto(Blog item) {
        if (item == null) {
            return null;
        } else {
            BlogDto dto = new BlogDto();
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
            if(this.blogSubject && item.getBlogSubject()!=null) {
                dto.setBlogSubject(blogSubjectConverter.toDto(item.getBlogSubject())) ;

            }
            if(this.blogState && item.getBlogState()!=null) {
                dto.setBlogState(blogStateConverter.toDto(item.getBlogState())) ;

            }
        if(this.blogComments && ListUtil.isNotEmpty(item.getBlogComments())){
            blogCommentConverter.init(true);
            blogCommentConverter.setBlog(false);
            dto.setBlogComments(blogCommentConverter.toDto(item.getBlogComments()));
            blogCommentConverter.setBlog(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.blogComments = value;
    }
    public void initObject(boolean value) {
        this.collaborator = value;
        this.blogSubject = value;
        this.blogState = value;
    }
	
    public List<Blog> toItem(List<BlogDto> dtos) {
        List<Blog> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BlogDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BlogDto> toDto(List<Blog> items) {
        List<BlogDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Blog item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BlogDto dto, Blog t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getBlogSubject() == null  && dto.getBlogSubject() != null){
            t.setBlogSubject(new BlogSubject());
        }
        if(t.getBlogState() == null  && dto.getBlogState() != null){
            t.setBlogState(new BlogState());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getBlogSubject() != null)
        blogSubjectConverter.copy(dto.getBlogSubject(), t.getBlogSubject());
        if (dto.getBlogState() != null)
        blogStateConverter.copy(dto.getBlogState(), t.getBlogState());
        if (dto.getBlogComments() != null)
            t.setBlogComments(blogCommentConverter.copy(dto.getBlogComments()));
    }

    public List<Blog> copy(List<BlogDto> dtos) {
        List<Blog> result = new ArrayList<>();
        if (dtos != null) {
            for (BlogDto dto : dtos) {
                Blog instance = new Blog();
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
    public BlogSubjectConverter getBlogSubjectConverter(){
        return this.blogSubjectConverter;
    }
    public void setBlogSubjectConverter(BlogSubjectConverter blogSubjectConverter ){
        this.blogSubjectConverter = blogSubjectConverter;
    }
    public BlogCommentConverter getBlogCommentConverter(){
        return this.blogCommentConverter;
    }
    public void setBlogCommentConverter(BlogCommentConverter blogCommentConverter ){
        this.blogCommentConverter = blogCommentConverter;
    }
    public BlogStateConverter getBlogStateConverter(){
        return this.blogStateConverter;
    }
    public void setBlogStateConverter(BlogStateConverter blogStateConverter ){
        this.blogStateConverter = blogStateConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isBlogSubject(){
        return this.blogSubject;
    }
    public void  setBlogSubject(boolean blogSubject){
        this.blogSubject = blogSubject;
    }
    public boolean  isBlogState(){
        return this.blogState;
    }
    public void  setBlogState(boolean blogState){
        this.blogState = blogState;
    }
    public boolean  isBlogComments(){
        return this.blogComments ;
    }
    public void  setBlogComments(boolean blogComments ){
        this.blogComments  = blogComments ;
    }
}
