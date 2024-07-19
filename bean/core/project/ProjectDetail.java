package ma.zs.zyn.bean.core.project;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "project_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_detail_seq",sequenceName="project_detail_seq",allocationSize=1, initialValue = 1)
public class ProjectDetail  extends BaseEntity     {




    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String dbName;

    @Column(length = 500)
    private String dbPassword;

    @Column(length = 500)
    private String dbUserName;

    @Column(length = 500)
    private String basePackage;

    @Column(length = 500)
    private String msName;

    @Column(length = 500)
    private String port;

    @Column(length = 500)
    private String portDev;

    @Column(length = 500)
    private String portTest;

    @Column(length = 500)
    private String portProd;

    @Column(columnDefinition = "boolean default false")
    private Boolean enabled = false;

    private ProjectTechnology projectTechnology ;
    private Project project ;
    private ProjectTechnologyProfile projectTechnologyProfile ;



    public ProjectDetail(){
        super();
    }

    public ProjectDetail(Long id){
        this.id = id;
    }

    public ProjectDetail(Long id,String title){
        this.id = id;
        this.title = title ;
    }
    public ProjectDetail(String title){
        this.title = title ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_detail_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_technology")
    public ProjectTechnology getProjectTechnology(){
        return this.projectTechnology;
    }
    public void setProjectTechnology(ProjectTechnology projectTechnology){
        this.projectTechnology = projectTechnology;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_technology_profile")
    public ProjectTechnologyProfile getProjectTechnologyProfile(){
        return this.projectTechnologyProfile;
    }
    public void setProjectTechnologyProfile(ProjectTechnologyProfile projectTechnologyProfile){
        this.projectTechnologyProfile = projectTechnologyProfile;
    }


    public String getDbName(){
        return this.dbName;
    }
    public void setDbName(String dbName){
        this.dbName = dbName;
    }
    public String getDbPassword(){
        return this.dbPassword;
    }
    public void setDbPassword(String dbPassword){
        this.dbPassword = dbPassword;
    }
    public String getDbUserName(){
        return this.dbUserName;
    }
    public void setDbUserName(String dbUserName){
        this.dbUserName = dbUserName;
    }
    public String getBasePackage(){
        return this.basePackage;
    }
    public void setBasePackage(String basePackage){
        this.basePackage = basePackage;
    }
    public String getMsName(){
        return this.msName;
    }
    public void setMsName(String msName){
        this.msName = msName;
    }
    public String getPort(){
        return this.port;
    }
    public void setPort(String port){
        this.port = port;
    }
    public String getPortDev(){
        return this.portDev;
    }
    public void setPortDev(String portDev){
        this.portDev = portDev;
    }
    public String getPortTest(){
        return this.portTest;
    }
    public void setPortTest(String portTest){
        this.portTest = portTest;
    }
    public String getPortProd(){
        return this.portProd;
    }
    public void setPortProd(String portProd){
        this.portProd = portProd;
    }
    public boolean  getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    @Transient
    public String getLabel() {
        label = title;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDetail projectDetail = (ProjectDetail) o;
        return id != null && id.equals(projectDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

