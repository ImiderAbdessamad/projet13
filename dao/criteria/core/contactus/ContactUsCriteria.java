package ma.zs.zyn.dao.criteria.core.contactus;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ContactUsCriteria extends  BaseCriteria  {

    private String phone;
    private String phoneLike;
    private String email;
    private String emailLike;
    private String object;
    private String objectLike;
    private String message;
    private String messageLike;
    private String description;
    private String descriptionLike;

    private ContactUsCategoryCriteria contactUsCategory ;
    private List<ContactUsCategoryCriteria> contactUsCategorys ;
    private ContactUsStateCriteria contactUsState ;
    private List<ContactUsStateCriteria> contactUsStates ;


    public ContactUsCriteria(){}

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhoneLike(){
        return this.phoneLike;
    }
    public void setPhoneLike(String phoneLike){
        this.phoneLike = phoneLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getObject(){
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObjectLike(){
        return this.objectLike;
    }
    public void setObjectLike(String objectLike){
        this.objectLike = objectLike;
    }

    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessageLike(){
        return this.messageLike;
    }
    public void setMessageLike(String messageLike){
        this.messageLike = messageLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public ContactUsCategoryCriteria getContactUsCategory(){
        return this.contactUsCategory;
    }

    public void setContactUsCategory(ContactUsCategoryCriteria contactUsCategory){
        this.contactUsCategory = contactUsCategory;
    }
    public List<ContactUsCategoryCriteria> getContactUsCategorys(){
        return this.contactUsCategorys;
    }

    public void setContactUsCategorys(List<ContactUsCategoryCriteria> contactUsCategorys){
        this.contactUsCategorys = contactUsCategorys;
    }
    public ContactUsStateCriteria getContactUsState(){
        return this.contactUsState;
    }

    public void setContactUsState(ContactUsStateCriteria contactUsState){
        this.contactUsState = contactUsState;
    }
    public List<ContactUsStateCriteria> getContactUsStates(){
        return this.contactUsStates;
    }

    public void setContactUsStates(List<ContactUsStateCriteria> contactUsStates){
        this.contactUsStates = contactUsStates;
    }
}
