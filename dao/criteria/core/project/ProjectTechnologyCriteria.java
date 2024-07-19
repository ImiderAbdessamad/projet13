package  ma.zs.zyn.dao.criteria.core.project;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProjectTechnologyCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String defaultDbName;
    private String defaultDbNameLike;
    private String defaultUserName;
    private String defaultUserNameLike;
    private String defaultUserPassword;
    private String defaultUserPasswordLike;
    private String defaultPort;
    private String defaultPortLike;
    private String defaultBasePackage;
    private String defaultBasePackageLike;

    private ProjectTechnologyTypeCriteria projectTechnologyType ;
    private List<ProjectTechnologyTypeCriteria> projectTechnologyTypes ;


    public ProjectTechnologyCriteria(){}

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

    public String getDefaultDbName(){
        return this.defaultDbName;
    }
    public void setDefaultDbName(String defaultDbName){
        this.defaultDbName = defaultDbName;
    }
    public String getDefaultDbNameLike(){
        return this.defaultDbNameLike;
    }
    public void setDefaultDbNameLike(String defaultDbNameLike){
        this.defaultDbNameLike = defaultDbNameLike;
    }

    public String getDefaultUserName(){
        return this.defaultUserName;
    }
    public void setDefaultUserName(String defaultUserName){
        this.defaultUserName = defaultUserName;
    }
    public String getDefaultUserNameLike(){
        return this.defaultUserNameLike;
    }
    public void setDefaultUserNameLike(String defaultUserNameLike){
        this.defaultUserNameLike = defaultUserNameLike;
    }

    public String getDefaultUserPassword(){
        return this.defaultUserPassword;
    }
    public void setDefaultUserPassword(String defaultUserPassword){
        this.defaultUserPassword = defaultUserPassword;
    }
    public String getDefaultUserPasswordLike(){
        return this.defaultUserPasswordLike;
    }
    public void setDefaultUserPasswordLike(String defaultUserPasswordLike){
        this.defaultUserPasswordLike = defaultUserPasswordLike;
    }

    public String getDefaultPort(){
        return this.defaultPort;
    }
    public void setDefaultPort(String defaultPort){
        this.defaultPort = defaultPort;
    }
    public String getDefaultPortLike(){
        return this.defaultPortLike;
    }
    public void setDefaultPortLike(String defaultPortLike){
        this.defaultPortLike = defaultPortLike;
    }

    public String getDefaultBasePackage(){
        return this.defaultBasePackage;
    }
    public void setDefaultBasePackage(String defaultBasePackage){
        this.defaultBasePackage = defaultBasePackage;
    }
    public String getDefaultBasePackageLike(){
        return this.defaultBasePackageLike;
    }
    public void setDefaultBasePackageLike(String defaultBasePackageLike){
        this.defaultBasePackageLike = defaultBasePackageLike;
    }


    public ProjectTechnologyTypeCriteria getProjectTechnologyType(){
        return this.projectTechnologyType;
    }

    public void setProjectTechnologyType(ProjectTechnologyTypeCriteria projectTechnologyType){
        this.projectTechnologyType = projectTechnologyType;
    }
    public List<ProjectTechnologyTypeCriteria> getProjectTechnologyTypes(){
        return this.projectTechnologyTypes;
    }

    public void setProjectTechnologyTypes(List<ProjectTechnologyTypeCriteria> projectTechnologyTypes){
        this.projectTechnologyTypes = projectTechnologyTypes;
    }
}
