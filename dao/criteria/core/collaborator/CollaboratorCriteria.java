package ma.zs.zyn.dao.criteria.core.collaborator;


import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import ma.zs.zyn.zynerator.security.dao.criteria.core.UserCriteria;

import java.util.List;

public class CollaboratorCriteria extends UserCriteria {

    private String description;
    private String descriptionLike;
    private String postal;
    private String postalLike;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean passwordChanged;
    private String username;
    private String usernameLike;
    private String password;
    private String passwordLike;

    private CountryCriteria country ;
    private List<CountryCriteria> countrys ;
    private CityCriteria city ;
    private List<CityCriteria> citys ;


    public CollaboratorCriteria(String username){
        this.username = username;
    }

    public CollaboratorCriteria(){}

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getPostal(){
        return this.postal;
    }
    public void setPostal(String postal){
        this.postal = postal;
    }
    public String getPostalLike(){
        return this.postalLike;
    }
    public void setPostalLike(String postalLike){
        this.postalLike = postalLike;
    }

    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }


    public CountryCriteria getCountry(){
        return this.country;
    }

    public void setCountry(CountryCriteria country){
        this.country = country;
    }
    public List<CountryCriteria> getCountrys(){
        return this.countrys;
    }

    public void setCountrys(List<CountryCriteria> countrys){
        this.countrys = countrys;
    }
    public CityCriteria getCity(){
        return this.city;
    }

    public void setCity(CityCriteria city){
        this.city = city;
    }
    public List<CityCriteria> getCitys(){
        return this.citys;
    }

    public void setCitys(List<CityCriteria> citys){
        this.citys = citys;
    }
}
