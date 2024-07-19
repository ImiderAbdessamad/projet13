package ma.zs.zyn.ws.converter.cloud;

import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.ws.dto.cloud.OffreCloudProviderDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OffreCloudProviderConverter {

    @Autowired
    private CloudProviderConverter cloudProviderConverter ;
    private boolean cloudProvider;

    public  OffreCloudProviderConverter() {
        initObject(true);
    }


    public OffreCloudProvider toItem(OffreCloudProviderDto dto) {
        if (dto == null) {
            return null;
        } else {
        OffreCloudProvider item = new OffreCloudProvider();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(this.cloudProvider && dto.getCloudProvider()!=null)
                item.setCloudProvider(cloudProviderConverter.toItem(dto.getCloudProvider())) ;




        return item;
        }
    }


    public OffreCloudProviderDto toDto(OffreCloudProvider item) {
        if (item == null) {
            return null;
        } else {
            OffreCloudProviderDto dto = new OffreCloudProviderDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(this.cloudProvider && item.getCloudProvider()!=null) {
                dto.setCloudProvider(cloudProviderConverter.toDto(item.getCloudProvider())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.cloudProvider = value;
    }
	
    public List<OffreCloudProvider> toItem(List<OffreCloudProviderDto> dtos) {
        List<OffreCloudProvider> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OffreCloudProviderDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OffreCloudProviderDto> toDto(List<OffreCloudProvider> items) {
        List<OffreCloudProviderDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (OffreCloudProvider item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OffreCloudProviderDto dto, OffreCloudProvider t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCloudProvider() == null  && dto.getCloudProvider() != null){
            t.setCloudProvider(new CloudProvider());
        }
        if (dto.getCloudProvider() != null)
        cloudProviderConverter.copy(dto.getCloudProvider(), t.getCloudProvider());
    }

    public List<OffreCloudProvider> copy(List<OffreCloudProviderDto> dtos) {
        List<OffreCloudProvider> result = new ArrayList<>();
        if (dtos != null) {
            for (OffreCloudProviderDto dto : dtos) {
                OffreCloudProvider instance = new OffreCloudProvider();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CloudProviderConverter getCloudProviderConverter(){
        return this.cloudProviderConverter;
    }
    public void setCloudProviderConverter(CloudProviderConverter cloudProviderConverter ){
        this.cloudProviderConverter = cloudProviderConverter;
    }
    public boolean  isCloudProvider(){
        return this.cloudProvider;
    }
    public void  setCloudProvider(boolean cloudProvider){
        this.cloudProvider = cloudProvider;
    }
}
