package ma.zs.zyn.ws.dto.contactus;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactUsDto  extends AuditBaseDto {

    private String phone  ;
    private String email  ;
    private String object  ;
    private String message  ;
    private String description  ;

    private ContactUsCategoryDto contactUsCategory ;
    private ContactUsStateDto contactUsState ;



    public ContactUsDto(){
        super();
    }



    @Log
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    @Log
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Log
    public String getObject(){
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }

    @Log
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public ContactUsCategoryDto getContactUsCategory(){
        return this.contactUsCategory;
    }

    public void setContactUsCategory(ContactUsCategoryDto contactUsCategory){
        this.contactUsCategory = contactUsCategory;
    }
    public ContactUsStateDto getContactUsState(){
        return this.contactUsState;
    }

    public void setContactUsState(ContactUsStateDto contactUsState){
        this.contactUsState = contactUsState;
    }






}
