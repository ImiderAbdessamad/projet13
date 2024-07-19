package ma.zs.zyn.ws.dto.collaborator;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;

    private CountryDto country ;



    public CityDto(){
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


    public CountryDto getCountry(){
        return this.country;
    }

    public void setCountry(CountryDto country){
        this.country = country;
    }






}
