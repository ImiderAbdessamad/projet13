package ma.zs.zyn.bean.core.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_seq",sequenceName="project_seq",allocationSize=1, initialValue = 1)
public class Project extends BaseEntity     {




    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String titleChat;

    private LocalDateTime generatedDate ;

    private LocalDate chatDateStart ;

    @Column(columnDefinition = "boolean default false")
    private Boolean microService = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean microFront = false;

    private Collaborator collaborator ;
    private RemoteRepoInfo remoteRepoInfo ;

    private List<Conversation> conversations ;
    private List<ProjectDetail> projectDetails ;

    public Project(){
        super();
    }

    public Project(Long id){
        this.id = id;
    }

    public Project(Long id, String titleChat){
        this.id = id;
        this.titleChat = titleChat ;
    }
    public Project(String titleChat){
        this.titleChat = titleChat ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_seq")
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
    public String getTitleChat(){
        return this.titleChat;
    }
    public void setTitleChat(String titleChat){
        this.titleChat = titleChat;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
    }
    public LocalDateTime getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(LocalDateTime generatedDate){
        this.generatedDate = generatedDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remote_repo_info")
    public RemoteRepoInfo getRemoteRepoInfo(){
        return this.remoteRepoInfo;
    }
    public void setRemoteRepoInfo(RemoteRepoInfo remoteRepoInfo){
        this.remoteRepoInfo = remoteRepoInfo;
    }
    @OneToMany(mappedBy = "project")
    public List<Conversation> getConversations(){
        return this.conversations;
    }

    public void setConversations(List<Conversation> conversations){
        this.conversations = conversations;
    }
    public LocalDate getChatDateStart(){
        return this.chatDateStart;
    }
    public void setChatDateStart(LocalDate chatDateStart){
        this.chatDateStart = chatDateStart;
    }
    public Boolean  getMicroService(){
        return this.microService;
    }
    public void setMicroService(Boolean microService){
        this.microService = microService;
    }
    public Boolean  getMicroFront(){
        return this.microFront;
    }
    public void setMicroFront(Boolean microFront){
        this.microFront = microFront;
    }
    @OneToMany(mappedBy = "project")
    public List<ProjectDetail> getProjectDetails(){
        return this.projectDetails;
    }

    public void setProjectDetails(List<ProjectDetail> projectDetails){
        this.projectDetails = projectDetails;
    }

    @Transient
    public String getLabel() {
        label = titleChat;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id != null && id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

