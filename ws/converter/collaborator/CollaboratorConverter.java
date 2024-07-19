package ma.zs.zyn.ws.converter.collaborator;

import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.collaborator.Country;
import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;
import ma.zs.zyn.zynerator.security.ws.converter.UserConverter;
import ma.zs.zyn.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CollaboratorConverter {

    @Autowired
    private CountryConverter countryConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CityConverter cityConverter;
    private boolean country;
    private boolean city;

    public CollaboratorConverter() {
        initObject(true);
    }


    public Collaborator toItem(CollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
            Collaborator item = new Collaborator();
            BeanUtils.copyProperties(userConverter.toItem(dto), item);
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if (StringUtil.isNotEmpty(dto.getPostal()))
                item.setPostal(dto.getPostal());
            if (StringUtil.isNotEmpty(dto.getCredentialsNonExpired()))
              item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            if (StringUtil.isNotEmpty(dto.getEnabled()))
              item.setEnabled(dto.getEnabled());
            if (StringUtil.isNotEmpty(dto.getAccountNonExpired()))
              item.setAccountNonExpired(dto.getAccountNonExpired());
            if (StringUtil.isNotEmpty(dto.getAccountNonLocked()))
              item.setAccountNonLocked(dto.getAccountNonLocked());
            if (StringUtil.isNotEmpty(dto.getPasswordChanged()))
              item.setPasswordChanged(dto.getPasswordChanged());
            if (StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if (StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if (this.country && dto.getCountry() != null)
                item.setCountry(countryConverter.toItem(dto.getCountry()));

            if (this.city && dto.getCity() != null)
                item.setCity(cityConverter.toItem(dto.getCity()));


            //item.setRoles(dto.getRoles());

            return item;
        }
    }


    public CollaboratorDto toDto(Collaborator item) {
        if (item == null) {
            return null;
        } else {
            CollaboratorDto dto = new CollaboratorDto();
            BeanUtils.copyProperties(userConverter.toDto(item), dto);
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if (StringUtil.isNotEmpty(item.getPostal()))
                dto.setPostal(item.getPostal());
            if (StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if (StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if (StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if (StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if (StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if (StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if (this.country && item.getCountry() != null) {
                dto.setCountry(countryConverter.toDto(item.getCountry()));

            }
            if (this.city && item.getCity() != null) {
                dto.setCity(cityConverter.toDto(item.getCity()));

            }


            return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.country = value;
        this.city = value;
    }

    public List<Collaborator> toItem(List<CollaboratorDto> dtos) {
        List<Collaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CollaboratorDto> toDto(List<Collaborator> items) {
        List<CollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Collaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CollaboratorDto dto, Collaborator t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (t.getCountry() == null && dto.getCountry() != null) {
            t.setCountry(new Country());
        }
        if (t.getCity() == null && dto.getCity() != null) {
            t.setCity(new City());
        }
        if (dto.getCountry() != null)
            countryConverter.copy(dto.getCountry(), t.getCountry());
        if (dto.getCity() != null)
            cityConverter.copy(dto.getCity(), t.getCity());
    }

    public List<Collaborator> copy(List<CollaboratorDto> dtos) {
        List<Collaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (CollaboratorDto dto : dtos) {
                Collaborator instance = new Collaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CountryConverter getCountryConverter() {
        return this.countryConverter;
    }

    public void setCountryConverter(CountryConverter countryConverter) {
        this.countryConverter = countryConverter;
    }

    public CityConverter getCityConverter() {
        return this.cityConverter;
    }

    public void setCityConverter(CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    public boolean isCountry() {
        return this.country;
    }

    public void setCountry(boolean country) {
        this.country = country;
    }

    public boolean isCity() {
        return this.city;
    }

    public void setCity(boolean city) {
        this.city = city;
    }
}
