package  ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class YamlFileDto  extends AuditBaseDto {

    private String title  ;
    private String content  ;

    private ProjectDto project ;



    public YamlFileDto(){
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
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }


    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }






}
