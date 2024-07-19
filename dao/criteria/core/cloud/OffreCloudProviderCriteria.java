package ma.zs.zyn.dao.criteria.core.cloud;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class OffreCloudProviderCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private String price;
    private String priceMin;
    private String priceMax;

    private CloudProviderCriteria cloudProvider ;
    private List<CloudProviderCriteria> cloudProviders ;


    public OffreCloudProviderCriteria(){}

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

    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      

    public CloudProviderCriteria getCloudProvider(){
        return this.cloudProvider;
    }

    public void setCloudProvider(CloudProviderCriteria cloudProvider){
        this.cloudProvider = cloudProvider;
    }
    public List<CloudProviderCriteria> getCloudProviders(){
        return this.cloudProviders;
    }

    public void setCloudProviders(List<CloudProviderCriteria> cloudProviders){
        this.cloudProviders = cloudProviders;
    }
}
