package ma.zs.zyn.ws.converter.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.support.Agent;
import ma.zs.zyn.ws.dto.support.AgentDto;

@Component
public class AgentConverter {



    public Agent toItem(AgentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Agent item = new Agent();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public AgentDto toDto(Agent item) {
        if (item == null) {
            return null;
        } else {
            AgentDto dto = new AgentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());


        return dto;
        }
    }


	
    public List<Agent> toItem(List<AgentDto> dtos) {
        List<Agent> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AgentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AgentDto> toDto(List<Agent> items) {
        List<AgentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Agent item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AgentDto dto, Agent t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Agent> copy(List<AgentDto> dtos) {
        List<Agent> result = new ArrayList<>();
        if (dtos != null) {
            for (AgentDto dto : dtos) {
                Agent instance = new Agent();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
