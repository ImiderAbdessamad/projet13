package ma.zs.zyn.ws.converter.project;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.ws.dto.project.RemoteRepoInfoDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoteRepoInfoConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private RemoteRepoTypeConverter remoteRepoTypeConverter ;
    private boolean remoteRepoType;
    private boolean collaborator;

    public  RemoteRepoInfoConverter() {
        initObject(true);
    }


    public RemoteRepoInfo toItem(RemoteRepoInfoDto dto) {
        if (dto == null) {
            return null;
        } else {
        RemoteRepoInfo item = new RemoteRepoInfo();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getToken()))
                item.setToken(dto.getToken());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(this.remoteRepoType && dto.getRemoteRepoType()!=null)
                item.setRemoteRepoType(remoteRepoTypeConverter.toItem(dto.getRemoteRepoType())) ;

            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;




        return item;
        }
    }


    public RemoteRepoInfoDto toDto(RemoteRepoInfo item) {
        if (item == null) {
            return null;
        } else {
            RemoteRepoInfoDto dto = new RemoteRepoInfoDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getToken()))
                dto.setToken(item.getToken());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(this.remoteRepoType && item.getRemoteRepoType()!=null) {
                dto.setRemoteRepoType(remoteRepoTypeConverter.toDto(item.getRemoteRepoType())) ;

            }
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.remoteRepoType = value;
        this.collaborator = value;
    }
	
    public List<RemoteRepoInfo> toItem(List<RemoteRepoInfoDto> dtos) {
        List<RemoteRepoInfo> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RemoteRepoInfoDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RemoteRepoInfoDto> toDto(List<RemoteRepoInfo> items) {
        List<RemoteRepoInfoDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RemoteRepoInfo item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RemoteRepoInfoDto dto, RemoteRepoInfo t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getRemoteRepoType() == null  && dto.getRemoteRepoType() != null){
            t.setRemoteRepoType(new RemoteRepoType());
        }
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if (dto.getRemoteRepoType() != null)
        remoteRepoTypeConverter.copy(dto.getRemoteRepoType(), t.getRemoteRepoType());
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
    }

    public List<RemoteRepoInfo> copy(List<RemoteRepoInfoDto> dtos) {
        List<RemoteRepoInfo> result = new ArrayList<>();
        if (dtos != null) {
            for (RemoteRepoInfoDto dto : dtos) {
                RemoteRepoInfo instance = new RemoteRepoInfo();
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
    public RemoteRepoTypeConverter getRemoteRepoTypeConverter(){
        return this.remoteRepoTypeConverter;
    }
    public void setRemoteRepoTypeConverter(RemoteRepoTypeConverter remoteRepoTypeConverter ){
        this.remoteRepoTypeConverter = remoteRepoTypeConverter;
    }
    public boolean  isRemoteRepoType(){
        return this.remoteRepoType;
    }
    public void  setRemoteRepoType(boolean remoteRepoType){
        this.remoteRepoType = remoteRepoType;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
}
