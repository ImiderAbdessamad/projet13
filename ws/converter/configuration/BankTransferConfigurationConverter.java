package ma.zs.zyn.ws.converter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.ws.dto.configuration.BankTransferConfigurationDto;

@Component
public class BankTransferConfigurationConverter {


    public  BankTransferConfigurationConverter() {
    }


    public BankTransferConfiguration toItem(BankTransferConfigurationDto dto) {
        if (dto == null) {
            return null;
        } else {
        BankTransferConfiguration item = new BankTransferConfiguration();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getIban()))
                item.setIban(dto.getIban());
            if(StringUtil.isNotEmpty(dto.getSwift()))
                item.setSwift(dto.getSwift());
            if(StringUtil.isNotEmpty(dto.getAccountHolderName()))
                item.setAccountHolderName(dto.getAccountHolderName());
            if(StringUtil.isNotEmpty(dto.getBankName()))
                item.setBankName(dto.getBankName());
            if(StringUtil.isNotEmpty(dto.getBankAddress()))
                item.setBankAddress(dto.getBankAddress());



        return item;
        }
    }


    public BankTransferConfigurationDto toDto(BankTransferConfiguration item) {
        if (item == null) {
            return null;
        } else {
            BankTransferConfigurationDto dto = new BankTransferConfigurationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getIban()))
                dto.setIban(item.getIban());
            if(StringUtil.isNotEmpty(item.getSwift()))
                dto.setSwift(item.getSwift());
            if(StringUtil.isNotEmpty(item.getAccountHolderName()))
                dto.setAccountHolderName(item.getAccountHolderName());
            if(StringUtil.isNotEmpty(item.getBankName()))
                dto.setBankName(item.getBankName());
            if(StringUtil.isNotEmpty(item.getBankAddress()))
                dto.setBankAddress(item.getBankAddress());


        return dto;
        }
    }


	
    public List<BankTransferConfiguration> toItem(List<BankTransferConfigurationDto> dtos) {
        List<BankTransferConfiguration> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BankTransferConfigurationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BankTransferConfigurationDto> toDto(List<BankTransferConfiguration> items) {
        List<BankTransferConfigurationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (BankTransferConfiguration item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BankTransferConfigurationDto dto, BankTransferConfiguration t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<BankTransferConfiguration> copy(List<BankTransferConfigurationDto> dtos) {
        List<BankTransferConfiguration> result = new ArrayList<>();
        if (dtos != null) {
            for (BankTransferConfigurationDto dto : dtos) {
                BankTransferConfiguration instance = new BankTransferConfiguration();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
