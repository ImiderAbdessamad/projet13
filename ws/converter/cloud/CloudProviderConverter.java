package ma.zs.zyn.ws.converter.cloud;

import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.ws.dto.cloud.CloudProviderDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CloudProviderConverter {


    public  CloudProviderConverter() {
    }


    public CloudProvider toItem(CloudProviderDto dto) {
        if (dto == null) {
            return null;
        } else {
        CloudProvider item = new CloudProvider();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public CloudProviderDto toDto(CloudProvider item) {
        if (item == null) {
            return null;
        } else {
            CloudProviderDto dto = new CloudProviderDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<CloudProvider> toItem(List<CloudProviderDto> dtos) {
        List<CloudProvider> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CloudProviderDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CloudProviderDto> toDto(List<CloudProvider> items) {
        List<CloudProviderDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CloudProvider item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CloudProviderDto dto, CloudProvider t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CloudProvider> copy(List<CloudProviderDto> dtos) {
        List<CloudProvider> result = new ArrayList<>();
        if (dtos != null) {
            for (CloudProviderDto dto : dtos) {
                CloudProvider instance = new CloudProvider();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
