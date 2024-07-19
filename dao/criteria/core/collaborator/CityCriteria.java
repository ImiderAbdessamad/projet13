package ma.zs.zyn.dao.criteria.core.collaborator;


import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CityCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;

    private CountryCriteria country ;
    private List<CountryCriteria> countrys ;


    public CityCriteria(){}

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
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
}
