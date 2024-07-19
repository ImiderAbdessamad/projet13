package ma.zs.zyn.dao.criteria.core.project;


import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ConversationCriteria extends  BaseCriteria  {

    private String prompt;
    private String promptLike;
    private String response;
    private String responseLike;

    private ProjectCriteria project ;
    private List<ProjectCriteria> projects ;


    public ConversationCriteria(){}

    public String getPrompt(){
        return this.prompt;
    }
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    public String getPromptLike(){
        return this.promptLike;
    }
    public void setPromptLike(String promptLike){
        this.promptLike = promptLike;
    }

    public String getResponse(){
        return this.response;
    }
    public void setResponse(String response){
        this.response = response;
    }
    public String getResponseLike(){
        return this.responseLike;
    }
    public void setResponseLike(String responseLike){
        this.responseLike = responseLike;
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
