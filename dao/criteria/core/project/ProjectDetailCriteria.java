package ma.zs.zyn.dao.criteria.core.project;


import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProjectDetailCriteria extends  BaseCriteria  {

    private String title;
    private String titleLike;
    private String dbName;
    private String dbNameLike;
    private String dbPassword;
    private String dbPasswordLike;
    private String dbUserName;
    private String dbUserNameLike;
    private String basePackage;
    private String basePackageLike;
    private String msName;
    private String msNameLike;
    private String port;
    private String portLike;
    private String portDev;
    private String portDevLike;
    private String portTest;
    private String portTestLike;
    private String portProd;
    private String portProdLike;
    private Boolean enabled;

    private ProjectTechnologyCriteria projectTechnology ;
    private List<ProjectTechnologyCriteria> projectTechnologys ;
    private ProjectCriteria project ;
    private List<ProjectCriteria> projects ;
    private ProjectTechnologyProfileCriteria projectTechnologyProfile ;
    private List<ProjectTechnologyProfileCriteria> projectTechnologyProfiles ;


    public ProjectDetailCriteria(){}

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitleLike(){
        return this.titleLike;
    }
    public void setTitleLike(String titleLike){
        this.titleLike = titleLike;
    }

    public String getDbName(){
        return this.dbName;
    }
    public void setDbName(String dbName){
        this.dbName = dbName;
    }
    public String getDbNameLike(){
        return this.dbNameLike;
    }
    public void setDbNameLike(String dbNameLike){
        this.dbNameLike = dbNameLike;
    }

    public String getDbPassword(){
        return this.dbPassword;
    }
    public void setDbPassword(String dbPassword){
        this.dbPassword = dbPassword;
    }
    public String getDbPasswordLike(){
        return this.dbPasswordLike;
    }
    public void setDbPasswordLike(String dbPasswordLike){
        this.dbPasswordLike = dbPasswordLike;
    }

    public String getDbUserName(){
        return this.dbUserName;
    }
    public void setDbUserName(String dbUserName){
        this.dbUserName = dbUserName;
    }
    public String getDbUserNameLike(){
        return this.dbUserNameLike;
    }
    public void setDbUserNameLike(String dbUserNameLike){
        this.dbUserNameLike = dbUserNameLike;
    }

    public String getBasePackage(){
        return this.basePackage;
    }
    public void setBasePackage(String basePackage){
        this.basePackage = basePackage;
    }
    public String getBasePackageLike(){
        return this.basePackageLike;
    }
    public void setBasePackageLike(String basePackageLike){
        this.basePackageLike = basePackageLike;
    }

    public String getMsName(){
        return this.msName;
    }
    public void setMsName(String msName){
        this.msName = msName;
    }
    public String getMsNameLike(){
        return this.msNameLike;
    }
    public void setMsNameLike(String msNameLike){
        this.msNameLike = msNameLike;
    }

    public String getPort(){
        return this.port;
    }
    public void setPort(String port){
        this.port = port;
    }
    public String getPortLike(){
        return this.portLike;
    }
    public void setPortLike(String portLike){
        this.portLike = portLike;
    }

    public String getPortDev(){
        return this.portDev;
    }
    public void setPortDev(String portDev){
        this.portDev = portDev;
    }
    public String getPortDevLike(){
        return this.portDevLike;
    }
    public void setPortDevLike(String portDevLike){
        this.portDevLike = portDevLike;
    }

    public String getPortTest(){
        return this.portTest;
    }
    public void setPortTest(String portTest){
        this.portTest = portTest;
    }
    public String getPortTestLike(){
        return this.portTestLike;
    }
    public void setPortTestLike(String portTestLike){
        this.portTestLike = portTestLike;
    }

    public String getPortProd(){
        return this.portProd;
    }
    public void setPortProd(String portProd){
        this.portProd = portProd;
    }
    public String getPortProdLike(){
        return this.portProdLike;
    }
    public void setPortProdLike(String portProdLike){
        this.portProdLike = portProdLike;
    }

    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    public ProjectTechnologyCriteria getProjectTechnology(){
        return this.projectTechnology;
    }

    public void setProjectTechnology(ProjectTechnologyCriteria projectTechnology){
        this.projectTechnology = projectTechnology;
    }
    public List<ProjectTechnologyCriteria> getProjectTechnologys(){
        return this.projectTechnologys;
    }

    public void setProjectTechnologys(List<ProjectTechnologyCriteria> projectTechnologys){
        this.projectTechnologys = projectTechnologys;
    }
    public ProjectCriteria getProject(){
        return this.project;
    }

    public void setProject(ProjectCriteria project){
        this.project = project;
    }
    public List<ProjectCriteria> getProjects(){
        return this.projects;
    }

    public void setProjects(List<ProjectCriteria> projects){
        this.projects = projects;
    }
    public ProjectTechnologyProfileCriteria getProjectTechnologyProfile(){
        return this.projectTechnologyProfile;
    }

    public void setProjectTechnologyProfile(ProjectTechnologyProfileCriteria projectTechnologyProfile){
        this.projectTechnologyProfile = projectTechnologyProfile;
    }
    public List<ProjectTechnologyProfileCriteria> getProjectTechnologyProfiles(){
        return this.projectTechnologyProfiles;
    }

    public void setProjectTechnologyProfiles(List<ProjectTechnologyProfileCriteria> projectTechnologyProfiles){
        this.projectTechnologyProfiles = projectTechnologyProfiles;
    }
}
