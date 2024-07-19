package  ma.zs.zyn.dao.criteria.core.project;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class YamlFileCriteria extends  BaseCriteria  {

    private String title;
    private String titleLike;
    private String content;
    private String contentLike;

    private ProjectCriteria project ;
    private List<ProjectCriteria> projects ;


    public YamlFileCriteria(){}

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

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContentLike(){
        return this.contentLike;
    }
    public void setContentLike(String contentLike){
        this.contentLike = contentLike;
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
}
