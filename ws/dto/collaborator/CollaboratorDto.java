package ma.zs.zyn.ws.dto.collaborator;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zs.zyn.zynerator.security.bean.Role;
import ma.zs.zyn.zynerator.security.ws.dto.UserDto;

import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollaboratorDto extends UserDto {

    private String description;
    private String postal;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean passwordChanged;
    private String username;
    private String password;

    private CountryDto country;
    private CityDto city;

    private Collection<Role> roles;

    public CollaboratorDto() {
        super();
    }


    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    @Log
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Log
    public String getPostal() {
        return this.postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Log
    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Log
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Log
    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Log
    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Log
    public Boolean getPasswordChanged() {
        return this.passwordChanged;
    }

    public void setPasswordChanged(Boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    @Log
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Log
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public CityDto getCity() {
        return this.city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }


    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
