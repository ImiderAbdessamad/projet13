package ma.zs.zyn.bean.core.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "remote_repo_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="remote_repo_info_seq",sequenceName="remote_repo_info_seq",allocationSize=1, initialValue = 1)
public class RemoteRepoInfo  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String username;

    @Column(length = 500)
    private String token;

    @Column(length = 500)
    private String name;

    private RemoteRepoType remoteRepoType ;
    private Collaborator collaborator ;


    public RemoteRepoInfo(){
        super();
    }

    public RemoteRepoInfo(Long id){
        this.id = id;
    }

    public RemoteRepoInfo(Long id,String title){
        this.id = id;
        this.title = title ;
    }
    public RemoteRepoInfo(String title){
        this.title = title ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="remote_repo_info_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remote_repo_type")
    public RemoteRepoType getRemoteRepoType(){
        return this.remoteRepoType;
    }
    public void setRemoteRepoType(RemoteRepoType remoteRepoType){
        this.remoteRepoType = remoteRepoType;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
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
        RemoteRepoInfo remoteRepoInfo = (RemoteRepoInfo) o;
        return id != null && id.equals(remoteRepoInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

