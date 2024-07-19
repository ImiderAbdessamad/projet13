package ma.zs.zyn.ws.converter.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.support.AgentConverter;
import ma.zs.zyn.bean.core.support.Agent;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationCategoryConverter;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationStateConverter;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationMessageConverter;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationDto;

@Component
public class CustumorSupportConversationConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private AgentConverter agentConverter ;
    @Autowired
    private CustumorSupportConversationCategoryConverter custumorSupportConversationCategoryConverter ;
    @Autowired
    private CustumorSupportConversationStateConverter custumorSupportConversationStateConverter ;
    @Autowired
    private CustumorSupportConversationMessageConverter custumorSupportConversationMessageConverter ;
    private boolean collaborator;
    private boolean agent;
    private boolean custumorSupportConversationCategory;
    private boolean custumorSupportConversationState;
    private boolean custumorSupportConversationMessages;

    public  CustumorSupportConversationConverter() {
        init(true);
    }

    public CustumorSupportConversation toItem(CustumorSupportConversationDto dto) {
        if (dto == null) {
            return null;
        } else {
        CustumorSupportConversation item = new CustumorSupportConversation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getObject()))
                item.setObject(dto.getObject());
            if(StringUtil.isNotEmpty(dto.getRatting()))
                item.setRatting(dto.getRatting());
            if(StringUtil.isNotEmpty(dto.getCreationDate()))
                item.setCreationDate(DateUtil.stringEnToDate(dto.getCreationDate()));
            if(StringUtil.isNotEmpty(dto.getClosingDate()))
                item.setClosingDate(DateUtil.stringEnToDate(dto.getClosingDate()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.agent && dto.getAgent()!=null)
                item.setAgent(agentConverter.toItem(dto.getAgent())) ;

            if(this.custumorSupportConversationCategory && dto.getCustumorSupportConversationCategory()!=null)
                item.setCustumorSupportConversationCategory(custumorSupportConversationCategoryConverter.toItem(dto.getCustumorSupportConversationCategory())) ;

            if(this.custumorSupportConversationState && dto.getCustumorSupportConversationState()!=null)
                item.setCustumorSupportConversationState(custumorSupportConversationStateConverter.toItem(dto.getCustumorSupportConversationState())) ;


            if(this.custumorSupportConversationMessages && ListUtil.isNotEmpty(dto.getCustumorSupportConversationMessages()))
                item.setCustumorSupportConversationMessages(custumorSupportConversationMessageConverter.toItem(dto.getCustumorSupportConversationMessages()));


        return item;
        }
    }


    public CustumorSupportConversationDto toDto(CustumorSupportConversation item) {
        if (item == null) {
            return null;
        } else {
            CustumorSupportConversationDto dto = new CustumorSupportConversationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getObject()))
                dto.setObject(item.getObject());
            if(StringUtil.isNotEmpty(item.getRatting()))
                dto.setRatting(item.getRatting());
            if(item.getCreationDate()!=null)
                dto.setCreationDate(DateUtil.dateTimeToString(item.getCreationDate()));
            if(item.getClosingDate()!=null)
                dto.setClosingDate(DateUtil.dateTimeToString(item.getClosingDate()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.agent && item.getAgent()!=null) {
                dto.setAgent(agentConverter.toDto(item.getAgent())) ;

            }
            if(this.custumorSupportConversationCategory && item.getCustumorSupportConversationCategory()!=null) {
                dto.setCustumorSupportConversationCategory(custumorSupportConversationCategoryConverter.toDto(item.getCustumorSupportConversationCategory())) ;

            }
            if(this.custumorSupportConversationState && item.getCustumorSupportConversationState()!=null) {
                dto.setCustumorSupportConversationState(custumorSupportConversationStateConverter.toDto(item.getCustumorSupportConversationState())) ;

            }
        if(this.custumorSupportConversationMessages && ListUtil.isNotEmpty(item.getCustumorSupportConversationMessages())){
            custumorSupportConversationMessageConverter.init(true);
            custumorSupportConversationMessageConverter.setCustumorSupportConversation(false);
            dto.setCustumorSupportConversationMessages(custumorSupportConversationMessageConverter.toDto(item.getCustumorSupportConversationMessages()));
            custumorSupportConversationMessageConverter.setCustumorSupportConversation(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.custumorSupportConversationMessages = value;
    }
    public void initObject(boolean value) {
        this.collaborator = value;
        this.agent = value;
        this.custumorSupportConversationCategory = value;
        this.custumorSupportConversationState = value;
    }
	
    public List<CustumorSupportConversation> toItem(List<CustumorSupportConversationDto> dtos) {
        List<CustumorSupportConversation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CustumorSupportConversationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CustumorSupportConversationDto> toDto(List<CustumorSupportConversation> items) {
        List<CustumorSupportConversationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CustumorSupportConversation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CustumorSupportConversationDto dto, CustumorSupportConversation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getAgent() == null  && dto.getAgent() != null){
            t.setAgent(new Agent());
        }
        if(t.getCustumorSupportConversationCategory() == null  && dto.getCustumorSupportConversationCategory() != null){
            t.setCustumorSupportConversationCategory(new CustumorSupportConversationCategory());
        }
        if(t.getCustumorSupportConversationState() == null  && dto.getCustumorSupportConversationState() != null){
            t.setCustumorSupportConversationState(new CustumorSupportConversationState());
        }
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getAgent() != null)
        agentConverter.copy(dto.getAgent(), t.getAgent());
        if (dto.getCustumorSupportConversationCategory() != null)
        custumorSupportConversationCategoryConverter.copy(dto.getCustumorSupportConversationCategory(), t.getCustumorSupportConversationCategory());
        if (dto.getCustumorSupportConversationState() != null)
        custumorSupportConversationStateConverter.copy(dto.getCustumorSupportConversationState(), t.getCustumorSupportConversationState());
        if (dto.getCustumorSupportConversationMessages() != null)
            t.setCustumorSupportConversationMessages(custumorSupportConversationMessageConverter.copy(dto.getCustumorSupportConversationMessages()));
    }

    public List<CustumorSupportConversation> copy(List<CustumorSupportConversationDto> dtos) {
        List<CustumorSupportConversation> result = new ArrayList<>();
        if (dtos != null) {
            for (CustumorSupportConversationDto dto : dtos) {
                CustumorSupportConversation instance = new CustumorSupportConversation();
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
    public AgentConverter getAgentConverter(){
        return this.agentConverter;
    }
    public void setAgentConverter(AgentConverter agentConverter ){
        this.agentConverter = agentConverter;
    }
    public CustumorSupportConversationCategoryConverter getCustumorSupportConversationCategoryConverter(){
        return this.custumorSupportConversationCategoryConverter;
    }
    public void setCustumorSupportConversationCategoryConverter(CustumorSupportConversationCategoryConverter custumorSupportConversationCategoryConverter ){
        this.custumorSupportConversationCategoryConverter = custumorSupportConversationCategoryConverter;
    }
    public CustumorSupportConversationStateConverter getCustumorSupportConversationStateConverter(){
        return this.custumorSupportConversationStateConverter;
    }
    public void setCustumorSupportConversationStateConverter(CustumorSupportConversationStateConverter custumorSupportConversationStateConverter ){
        this.custumorSupportConversationStateConverter = custumorSupportConversationStateConverter;
    }
    public CustumorSupportConversationMessageConverter getCustumorSupportConversationMessageConverter(){
        return this.custumorSupportConversationMessageConverter;
    }
    public void setCustumorSupportConversationMessageConverter(CustumorSupportConversationMessageConverter custumorSupportConversationMessageConverter ){
        this.custumorSupportConversationMessageConverter = custumorSupportConversationMessageConverter;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isAgent(){
        return this.agent;
    }
    public void  setAgent(boolean agent){
        this.agent = agent;
    }
    public boolean  isCustumorSupportConversationCategory(){
        return this.custumorSupportConversationCategory;
    }
    public void  setCustumorSupportConversationCategory(boolean custumorSupportConversationCategory){
        this.custumorSupportConversationCategory = custumorSupportConversationCategory;
    }
    public boolean  isCustumorSupportConversationState(){
        return this.custumorSupportConversationState;
    }
    public void  setCustumorSupportConversationState(boolean custumorSupportConversationState){
        this.custumorSupportConversationState = custumorSupportConversationState;
    }
    public boolean  isCustumorSupportConversationMessages(){
        return this.custumorSupportConversationMessages ;
    }
    public void  setCustumorSupportConversationMessages(boolean custumorSupportConversationMessages ){
        this.custumorSupportConversationMessages  = custumorSupportConversationMessages ;
    }
}
