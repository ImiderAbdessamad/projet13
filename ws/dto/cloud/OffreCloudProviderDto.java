package ma.zs.zyn.ws.dto.cloud;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class OffreCloudProviderDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal price  ;

    private CloudProviderDto cloudProvider ;



    public OffreCloudProviderDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public CloudProviderDto getCloudProvider(){
        return this.cloudProvider;
    }

    public void setCloudProvider(CloudProviderDto cloudProvider){
        this.cloudProvider = cloudProvider;
    }






}
