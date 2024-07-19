package ma.zs.zyn.ws.dto.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDetailDto  extends AuditBaseDto {

    private String title  ;
    private String dbName  ;
    private String dbPassword  ;
    private String dbUserName  ;
    private String basePackage  ;
    private String msName  ;
    private String port  ;
    private String portDev  ;
    private String portTest  ;
    private String portProd  ;
    private Boolean enabled  ;

    private ProjectTechnologyDto projectTechnology ;
    private ProjectDto project ;
    private ProjectTechnologyProfileDto projectTechnologyProfile ;

    public ProjectDetailDto(){
        super();
    }



    @Log
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Log
    public String getDbName(){
        return this.dbName;
    }
    public void setDbName(String dbName){
        this.dbName = dbName;
    }

    @Log
    public String getDbPassword(){
        return this.dbPassword;
    }
    public void setDbPassword(String dbPassword){
        this.dbPassword = dbPassword;
    }

    @Log
    public String getDbUserName(){
        return this.dbUserName;
    }
    public void setDbUserName(String dbUserName){
        this.dbUserName = dbUserName;
    }

    @Log
    public String getBasePackage(){
        return this.basePackage;
    }
    public void setBasePackage(String basePackage){
        this.basePackage = basePackage;
    }

    @Log
    public String getMsName(){
        return this.msName;
    }
    public void setMsName(String msName){
        this.msName = msName;
    }

    @Log
    public String getPort(){
        return this.port;
    }
    public void setPort(String port){
        this.port = port;
    }

    @Log
    public String getPortDev(){
        return this.portDev;
    }
    public void setPortDev(String portDev){
        this.portDev = portDev;
    }

    @Log
    public String getPortTest(){
        return this.portTest;
    }
    public void setPortTest(String portTest){
        this.portTest = portTest;
    }

    @Log
    public String getPortProd(){
        return this.portProd;
    }
    public void setPortProd(String portProd){
        this.portProd = portProd;
    }

    @Log
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }


    public ProjectTechnologyDto getProjectTechnology(){
        return this.projectTechnology;
    }

    public void setProjectTechnology(ProjectTechnologyDto projectTechnology){
        this.projectTechnology = projectTechnology;
    }
    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }
    public ProjectTechnologyProfileDto getProjectTechnologyProfile(){
        return this.projectTechnologyProfile;
    }

    public void setProjectTechnologyProfile(ProjectTechnologyProfileDto projectTechnologyProfile){
        this.projectTechnologyProfile = projectTechnologyProfile;
    }


}
