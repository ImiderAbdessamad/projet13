package ma.zs.zyn.ws.converter.collaborator;

import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.bean.core.collaborator.Country;
import ma.zs.zyn.ws.dto.collaborator.CityDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConverter {

    @Autowired
    private CountryConverter countryConverter ;
    private boolean country;

    public  CityConverter() {
        initObject(true);
    }


    public City toItem(CityDto dto) {
        if (dto == null) {
            return null;
        } else {
        City item = new City();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.country && dto.getCountry()!=null)
                item.setCountry(countryConverter.toItem(dto.getCountry())) ;




        return item;
        }
    }


    public CityDto toDto(City item) {
        if (item == null) {
            return null;
        } else {
            CityDto dto = new CityDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.country && item.getCountry()!=null) {
                dto.setCountry(countryConverter.toDto(item.getCountry())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.country = value;
    }
	
    public List<City> toItem(List<CityDto> dtos) {
        List<City> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CityDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CityDto> toDto(List<City> items) {
        List<CityDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (City item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CityDto dto, City t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCountry() == null  && dto.getCountry() != null){
            t.setCountry(new Country());
        }
        if (dto.getCountry() != null)
        countryConverter.copy(dto.getCountry(), t.getCountry());
    }

    public List<City> copy(List<CityDto> dtos) {
        List<City> result = new ArrayList<>();
        if (dtos != null) {
            for (CityDto dto : dtos) {
                City instance = new City();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CountryConverter getCountryConverter(){
        return this.countryConverter;
    }
    public void setCountryConverter(CountryConverter countryConverter ){
        this.countryConverter = countryConverter;
    }
    public boolean  isCountry(){
        return this.country;
    }
    public void  setCountry(boolean country){
        this.country = country;
    }
}
