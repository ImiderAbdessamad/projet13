package ma.zs.zyn.bean.core.project;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_technology")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_technology_seq",sequenceName="project_technology_seq",allocationSize=1, initialValue = 1)
public class ProjectTechnology  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String defaultDbName;

    @Column(length = 500)
    private String defaultUserName;

    @Column(length = 500)
    private String defaultUserPassword;

    @Column(length = 500)
    private String defaultPort;

    @Column(length = 500)
    private String defaultBasePackage;

    private ProjectTechnologyType projectTechnologyType ;


    public ProjectTechnology(){
        super();
    }

    public ProjectTechnology(Long id){
        this.id = id;
    }

    public ProjectTechnology(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ProjectTechnology(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_technology_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getDefaultDbName(){
        return this.defaultDbName;
    }
    public void setDefaultDbName(String defaultDbName){
        this.defaultDbName = defaultDbName;
    }
    public String getDefaultUserName(){
        return this.defaultUserName;
    }
    public void setDefaultUserName(String defaultUserName){
        this.defaultUserName = defaultUserName;
    }
    public String getDefaultUserPassword(){
        return this.defaultUserPassword;
    }
    public void setDefaultUserPassword(String defaultUserPassword){
        this.defaultUserPassword = defaultUserPassword;
    }
    public String getDefaultPort(){
        return this.defaultPort;
    }
    public void setDefaultPort(String defaultPort){
        this.defaultPort = defaultPort;
    }
    public String getDefaultBasePackage(){
        return this.defaultBasePackage;
    }
    public void setDefaultBasePackage(String defaultBasePackage){
        this.defaultBasePackage = defaultBasePackage;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_technology_type")
    public ProjectTechnologyType getProjectTechnologyType(){
        return this.projectTechnologyType;
    }
    public void setProjectTechnologyType(ProjectTechnologyType projectTechnologyType){
        this.projectTechnologyType = projectTechnologyType;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTechnology projectTechnology = (ProjectTechnology) o;
        return id != null && id.equals(projectTechnology.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

