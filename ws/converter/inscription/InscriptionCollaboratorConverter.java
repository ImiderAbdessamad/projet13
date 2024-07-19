package ma.zs.zyn.ws.converter.inscription;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InscriptionCollaboratorConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private PackagingConverter packagingConverter ;
    @Autowired
    private InscriptionCollaboratorStateConverter paimentCollaboratorStateConverter;
    private boolean collaborator;
    private boolean packaging;
    private boolean inscreptionCollaboratorState;
    
    public  InscriptionCollaboratorConverter() {
        initObject(true);
    }


    public InscriptionCollaborator toItem(InscriptionCollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionCollaborator item = new InscriptionCollaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getConsumedEntity()))
                item.setConsumedEntity(dto.getConsumedEntity());
            if(StringUtil.isNotEmpty(dto.getConsumedProjet()))
                item.setConsumedProjet(dto.getConsumedProjet());
            if(StringUtil.isNotEmpty(dto.getConsumedAttribut()))
                item.setConsumedAttribut(dto.getConsumedAttribut());
            if(StringUtil.isNotEmpty(dto.getConsumedTokenInput()))
                item.setConsumedTokenInput(dto.getConsumedTokenInput());
            if(StringUtil.isNotEmpty(dto.getConsumedTokenOutput()))
                item.setConsumedTokenOutput(dto.getConsumedTokenOutput());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.packaging && dto.getPackaging()!=null)
                item.setPackaging(packagingConverter.toItem(dto.getPackaging())) ;
            if (this.inscreptionCollaboratorState && dto.getInscriptionCollaboratorState() != null)
                item.setInscriptionCollaboratorState(paimentCollaboratorStateConverter.toItem(dto.getInscriptionCollaboratorState()));




            return item;
        }
    }


    public InscriptionCollaboratorDto toDto(InscriptionCollaborator item) {
        if (item == null) {
            return null;
        } else {
            InscriptionCollaboratorDto dto = new InscriptionCollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(StringUtil.isNotEmpty(item.getConsumedEntity()))
                dto.setConsumedEntity(item.getConsumedEntity());
            if(StringUtil.isNotEmpty(item.getConsumedProjet()))
                dto.setConsumedProjet(item.getConsumedProjet());
            if(StringUtil.isNotEmpty(item.getConsumedAttribut()))
                dto.setConsumedAttribut(item.getConsumedAttribut());
            if(StringUtil.isNotEmpty(item.getConsumedTokenInput()))
                dto.setConsumedTokenInput(item.getConsumedTokenInput());
            if(StringUtil.isNotEmpty(item.getConsumedTokenOutput()))
                dto.setConsumedTokenOutput(item.getConsumedTokenOutput());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.packaging && item.getPackaging()!=null) {
                dto.setPackaging(packagingConverter.toDto(item.getPackaging())) ;

            }
            if (this.inscreptionCollaboratorState && item.getInscriptionCollaboratorState() != null) {
                dto.setInscriptionCollaboratorState(paimentCollaboratorStateConverter.toDto(item.getInscriptionCollaboratorState()));

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.collaborator = value;
        this.packaging = value;
        this.inscreptionCollaboratorState = value;
    }
	
    public List<InscriptionCollaborator> toItem(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionCollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionCollaboratorDto> toDto(List<InscriptionCollaborator> items) {
        List<InscriptionCollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionCollaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionCollaboratorDto dto, InscriptionCollaborator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getPackaging() == null  && dto.getPackaging() != null){
            t.setPackaging(new Packaging());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getPackaging() != null)
        packagingConverter.copy(dto.getPackaging(), t.getPackaging());
    }

    public List<InscriptionCollaborator> copy(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionCollaboratorDto dto : dtos) {
                InscriptionCollaborator instance = new InscriptionCollaborator();
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
    public PackagingConverter getPackagingConverter(){
        return this.packagingConverter;
    }
    public void setPackagingConverter(PackagingConverter packagingConverter ){
        this.packagingConverter = packagingConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isPackaging(){
        return this.packaging;
    }
    public void  setPackaging(boolean packaging){
        this.packaging = packaging;
    }
}
