package  ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.zyn.ws.dto.project.ProjectDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationDto  extends AuditBaseDto {

    private String prompt  ;
    private String response  ;

    private ProjectDto project ;



    public ConversationDto(){
        super();
    }



    @Log
    public String getPrompt(){
        return this.prompt;
    }
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }

    @Log
    public String getResponse(){
        return this.response;
    }
    public void setResponse(String response){
        this.response = response;
    }


    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }






}
