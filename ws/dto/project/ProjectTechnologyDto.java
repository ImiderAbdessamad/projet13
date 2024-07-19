package  ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectTechnologyDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String defaultDbName  ;
    private String defaultUserName  ;
    private String defaultUserPassword  ;
    private String defaultPort  ;
    private String defaultBasePackage  ;

    private ProjectTechnologyTypeDto projectTechnologyType ;



    public ProjectTechnologyDto(){
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
    public String getDefaultDbName(){
        return this.defaultDbName;
    }
    public void setDefaultDbName(String defaultDbName){
        this.defaultDbName = defaultDbName;
    }

    @Log
    public String getDefaultUserName(){
        return this.defaultUserName;
    }
    public void setDefaultUserName(String defaultUserName){
        this.defaultUserName = defaultUserName;
    }

    @Log
    public String getDefaultUserPassword(){
        return this.defaultUserPassword;
    }
    public void setDefaultUserPassword(String defaultUserPassword){
        this.defaultUserPassword = defaultUserPassword;
    }

    @Log
    public String getDefaultPort(){
        return this.defaultPort;
    }
    public void setDefaultPort(String defaultPort){
        this.defaultPort = defaultPort;
    }

    @Log
    public String getDefaultBasePackage(){
        return this.defaultBasePackage;
    }
    public void setDefaultBasePackage(String defaultBasePackage){
        this.defaultBasePackage = defaultBasePackage;
    }


    public ProjectTechnologyTypeDto getProjectTechnologyType(){
        return this.projectTechnologyType;
    }

    public void setProjectTechnologyType(ProjectTechnologyTypeDto projectTechnologyType){
        this.projectTechnologyType = projectTechnologyType;
    }






}
