package ma.zs.zyn.service.impl.collaborator.project;

import org.springframework.stereotype.Service;

@Service
public class ChatGeminiMessageBuilder {
    public String generateYamlDescription(String projectDescription, String projectName, String historyPrompt, String historyResponse) {
        StringBuilder yamlBuilder = new StringBuilder();
        yamlBuilder.append("I will give you a java project description input , and you will return to me a yaml file in this structure : \n" +
                "ClassName_MS(ms1)_ROLES(value)_SUB-MODULE(default)_MENU(default): \n" +
                "    id: Long ID \n" +
                "    Attribute1: type \n" +
                "    Attribute2: type REQ \n" +
                "    Attribute3: type \n" +
                "    Etc... \n\n" +
                "some informations/rules : \n" +
                "- the names that are attached to the class name are called class decorators there are some who got values inside parenthesis such as sub-module or menu and there are some who got no values such as arch \n" +
                "- the class names that are attached to State  are called class decorators there are some who got values inside parenthesis such as sub-module or menu and there are some who got no values such as arch \n" +
                "- there are also attribute decorators such as REF_REQ, ID, they depend on the semantic of the attribute \n" +
                "- class and attribute decorators are in uppercase letters \n" +
                "- class name start by a capital letter \n" +
                "- attribute name is in lowercase letters \n" +
                "- available class decorators: ARCH, MS(value),IGNORE-FRONT,  SUB_MODULE(value), MENU(value), ROLE(value) default value is admin \n" +
                "-the class decorator IGNORE-FRONT is used when we  don't want to create a view part in frontend it's usuallay used for association classes , \n" +
                "- add roles to the class decorator ROLE(value) if it was added \n" +
                "- add role admin by default to the class decorator ROLE(value) \n" +
                "-the class decorator MS(value) is for defining micro service  for each class and if it doesn't specified the default value is ms1 and if it specified you shoud write it the form ms1 or ms2 etc   \n" +
                "- if there is no value for SUB_MODULE you can analyse the class and give an appropriate one \n" +
                "- if there is no value for MENU you can analyse the class and give an appropriate one , and MENU reference to MENU that will be displayed in front end for user in graphique interface it's like nav bar to access to different intface for this classes for example Client Management , Project Management , and  in each MENU we regroup just classes that are similaire   \n" +
                "- arch should not be attached to the class name by default like this Class1_SUB-MODULE(default)_MENU(default)_others: \n" +
                "- arch stands for archivable \n" +
                "- for STATE if the user give specific value you don't need to suggest value , and if it's not specified you give values in english \n" +
                "- available attribute decorators: ID,REQ,REF,REF_REQ,LARGE,LABEL,LABEL_REQ,LIST \n" +
                "- LARGE for String attribute to mark that this attribute hold a big String like description\n" +
                "- ID for attribute id, REQUIRED if an attribute should be given, REFERENCE if an attribute represents a reference such as product reference, REF_REQ is a required reference, LABEL for attribute that represents labels such as product label, LABEL_REQ is a required LABEL, LIST for attributes that represents a list or an array \n" +
                "- by default when you generate the file allocate each attribute its corresponding decorator based on its semantic if no decorator corresponds to it then dont \n" +
                "- each class should have an id attribute of type Long and class decorator ID, like this id: Long ID \n" +
                "- if you generate a class apart from those demanded by the user they should follow the same rules \n" +
                "- if there is an attribute of a class type you should add the according class description alongside the others \n" +
                "- the name of an attribute should start always with lower case \n" +
                "- if a class is an attribute in another class you should just write its name without class decorators \n" +
                "- the attribute decorator List can only be used on attributes with non simple type such as Integer, BigDecimal etc \n" +
                "- instead of int use Integer, and instead of float and double use BigDecimal \n" +
                "- always use english in your generation of entities names and attributes \n" +
                "- class Role and class User are already defined so you don't generate it \n" +
                "- if a entity is an actor in the project add  to it the  decorator Actor \n" +
                "- if a entity is marked as actor don't add next attributes they are already defined :\n" +
                "   String password;\n" +
                "   String username;\n" +
                "   String firstName;\n" +
                "   String lastName;\n" +
                "   String phone;\n" +
                "   String code;" +
                "- always divide entities in different Menus based in your vision if the Menu of each entity is not specified" +
                "- respect the line breaks given in the previous example \n" +
                "- always show the full yaml \n\n" +
                "in the next prompt i will give you an example \n" +
                "example : \n" +
                "```yaml"
                +
                "Project_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(project)_MENU(Project Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  name: String\n" +
                "  generatedDate: LocalDateTime\n" +
                "  yaml: String LARGE\n" +
                "  projectState: ProjectState\n" +
                "  inscriptionMembre: InscriptionMembre\n" +
                "  domainTemplate: DomainTemplate\n" +
                "  projectTemplates: ProjectTemplate List\n" +
                "\n" +
                "\n" +
                "ProjectTemplate_IGNORE-FRONT_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(template)_MENU(Project Management):\n" +
                "  id: Long ID\n" +
                "  template: Template\n" +
                "  project: Project\n" +
                "\n" +
                "ProjectState_STATE(Pending=warning,Validated=success,Blocked=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(project)_MENU(Project Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "Template_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "  description: String LARGE\n" +
                "  addingDate: LocalDateTime\n" +
                "  lastUpdateDate: LocalDateTime\n" +
                "  categoryTemplate: CategoryTemplate #private or public\n" +
                "  typeTemplate: TypeTemplate #back front or deploy\n" +
                "  levelTemplate: LevelTemplate  #junior or senior\n" +
                "  templateTags: String\n" +
                "  domainTemplate: DomainTemplate\n" +
                "  price: BigDecimal\n" +
                "  member: Member\n" +
                "  technology: Technology\n" +
                "\n" +
                "\n" +
                "\n" +
                "DomainTemplate_MS(ms1)_ROLES(admin)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "CategoryTemplate_STATE(Private=info,Public=success)_MS(ms1)_ROLES(admin)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "LevelTemplate_MS(ms1)_STATE(Senior=success,Junior=danger)_ROLES(admin)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "TypeTemplate_MS(ms1)_STATE(Front=success,Back=primary)_ROLES(admin)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "Technology_MS(ms1)_ROLES(admin)_SUB-MODULE(template)_MENU(Template Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "  logo: String\n" +
                "  typeTemplate: TypeTemplate #back front or deploy\n" +
                "\n" +
                "\n" +
                "Packaging_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(packaging)_MENU(Packaging Management):\n" +
                "  id: Long ID\n" +
                "  name: String\n" +
                "  code: String REF_REQ\n" +
                "  description: String LARGE\n" +
                "  dateStart: LocalDateTime\n" +
                "  dateEnd: LocalDateTime\n" +
                "  price: BigDecimal\n" +
                "  maxEntity: BigDecimal\n" +
                "  maxProjet: BigDecimal\n" +
                "  maxAttribut: BigDecimal\n" +
                "  maxIndicator: BigDecimal\n" +
                "  categoryPackaging: CategoryPackaging\n" +
                "\n" +
                "\n" +
                "CategoryPackaging_STATE(silver=warning,Free=success,Gold=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(packaging)_MENU(Packaging Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Influencer_ACTOR_MS(ms1)_ROLES(admin,influencer)_SUB-MODULE(influencer)_MENU(Influencer Management):\n" +
                "  id: Long ID\n" +
                "  nickName: String REF_REQ\n" +
                "  rib: String\n" +
                "\n" +
                "\n" +
                "Coupon_MS(ms1)_ROLES(admin,influencer)_SUB-MODULE(influencer)_MENU(Influencer Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  dateStart: LocalDateTime\n" +
                "  dateEnd: LocalDateTime\n" +
                "  libelle: String LABEL_REQ\n" +
                "  couponDetails: CouponDetail List\n" +
                "  influencer: Influencer\n" +
                "\n" +
                "CouponDetail_IGNORE-FRONT_MS(ms1)_ROLES(admin,influencer)_SUB-MODULE(influencer)_MENU(Influencer Management):\n" +
                "  id: Long ID\n" +
                "  packaging: Packaging\n" +
                "  discount: BigDecimal\n" +
                "  amountGivenInfluencer: BigDecimal\n" +
                "  usingNumber: BigDecimal\n" +
                "  maxUsingNumber: BigDecimal\n" +
                "  coupon: Coupon\n" +
                "\n" +
                "PaimentInfluencer_MS(ms1)_ROLES(admin,influencer)_SUB-MODULE(influencer)_MENU(Influencer Management):\n" +
                "  id: Long ID\n" +
                "  libelle: String LABEL_REQ\n" +
                "  description: String LARGE\n" +
                "  code: String REF_REQ\n" +
                "  influencer: Influencer\n" +
                "  coupon: Coupon\n" +
                "  total: BigDecimal\n" +
                "  nbrUtilisation: BigDecimal\n" +
                "  datePaiement: LocalDateTime\n" +
                "  paimentInfluencerState: PaimentInfluencerState\n" +
                "\n" +
                "PaimentInfluencerState_STATE(Pending=warning,Validated=success,Blocked=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(influencer)_MENU(Influencer Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Collaborator_ACTOR_MS(ms1)_ROLES(admin,collaborator)_SUB-MODULE(collaborator)_MENU(Collaborator Management):\n" +
                "  id: Long ID\n" +
                "  description: String LABEL_REQ\n" +
                "  rib: String\n" +
                "\n" +
                "\n" +
                "InscriptionCollaborator_MS(ms1)_ROLES(admin,collaborator)_SUB-MODULE(collaborator)_MENU(Collaborator Management):\n" +
                "  id: Long ID\n" +
                "  reference: String REF\n" +
                "  startDate: LocalDateTime\n" +
                "  endDate: LocalDateTime\n" +
                "  renewDate: LocalDateTime\n" +
                "  packaging: Packaging\n" +
                "  consumedEntity: BigDecimal\n" +
                "  consumedProjet: BigDecimal\n" +
                "  consumedAttribut: BigDecimal\n" +
                "  consumedIndicator: BigDecimal\n" +
                "  collaborator: Collaborator\n" +
                "  inscriptionCollaboratorState: InscriptionCollaboratorState\n" +
                "  inscriptionCollaboratorType: InscriptionCollaboratorType\n" +
                "  inscriptionMembres: InscriptionMembre List\n" +
                "\n" +
                "\n" +
                "InscriptionCollaboratorState_STATE(Pending=warning,Validated=success,Blocked=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(collaborator)_MENU(Collaborator Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "InscriptionCollaboratorType_MS(ms1)_ROLES(admin)_SUB-MODULE(collaborator)_MENU(Collaborator Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "PaimentCollaborator_MS(ms1)_ROLES(admin,collaborator)_SUB-MODULE(collaborator)_MENU(Collaborator Management):\n" +
                "  id: Long ID\n" +
                "  libelle: String LABEL_REQ\n" +
                "  description: String LARGE\n" +
                "  code: String REF_REQ\n" +
                "  couponDetail: CouponDetail\n" +
                "  amountToPaid: BigDecimal\n" +
                "  total: BigDecimal\n" +
                "  discount: BigDecimal\n" +
                "  remaining: BigDecimal\n" +
                "  paiementDate: LocalDateTime\n" +
                "  inscriptionCollaborator: InscriptionCollaborator\n" +
                "  paimentCollaboratorState: PaimentCollaboratorState\n" +
                "\n" +
                "\n" +
                "PaimentCollaboratorState_STATE(Pending=warning,Validated=success,Blocked=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(collaborator)_MENU(Project Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Member_ACTOR_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(member)_MENU(Member Management):\n" +
                "  id: Long ID\n" +
                "  description: String\n" +
                "  collaborator: Collaborator\n" +
                "\n" +
                "InscriptionMembre_MS(ms1)_ROLES(admin,collaborator,member)_SUB-MODULE(member)_MENU(Member Management):\n" +
                "  id: Long ID\n" +
                "  inscriptionDate: LocalDateTime\n" +
                "  member: Member\n" +
                "  inscriptionMembreState: InscriptionMembreState\n" +
                "  inscriptionCollaborator: InscriptionCollaborator\n" +
                "  consumedEntity: BigDecimal\n" +
                "  consumedProjet: BigDecimal\n" +
                "  consumedAttribut: BigDecimal\n" +
                "  consumedIndicator: BigDecimal\n" +
                "  affectedEntity: BigDecimal\n" +
                "  affectedProjet: BigDecimal\n" +
                "  affectedAttribut: BigDecimal\n" +
                "  affectedIndicator: BigDecimal\n" +
                "\n" +
                "\n" +
                "InscriptionMembreState_STATE(Pending=warning,Validated=success,Blocked=danger)_MS(ms1)_ROLES(admin)_SUB-MODULE(member)_MENU(Member Management):\n" +
                "  id: Long ID\n" +
                "  code: String REF_REQ\n" +
                "  libelle: String LABEL_REQ\n" +
                "\n" +
                "\n" +
                "``w` \n" +
                "respect this structure , and it's just an example don't repeat its value in my new yaml");
        if (historyPrompt != null && historyResponse != null) {
            yamlBuilder.append("Based on our last conversation, here is the improved response:\n");
            yamlBuilder.append("Last prompt: ").append(historyPrompt).append("\n");
            yamlBuilder.append("Last response: ").append(historyResponse).append("\n\n");
            yamlBuilder.append("now modify the next  yaml file " + historyResponse + "  for the project description and please respect just like this syntaxe and don't add anything to this syntaxe and if i give you attributes for each class don't add any attribute from you, and that's what  i want you to do as changes in the file yaml given  : \n" + projectDescription + "\n  , and don't forget give all the yaml file  \n" +
                    "- if i asked you something else under this context like what is something or define something or anything else that has no relation to yaml of an project of developement  answer with you can't help me \n");
            return yamlBuilder.toString();
        }
        yamlBuilder.append(
                "-don't add something that i give you in the example before to my yaml file like roles or something else!\n " +
                        "- be more precise when you generate for me the yaml file , and don't make mistakes !\n " +
                        "-if i asked you to correct a yaml file the file i will give should respect all rules that i specified before , like decorator MS , and ROLES() and SUB-MODULE() and MENU should be present obligatory\n" +
                        "- if i asked you something else under this context like ' what is java ?' or 'what is something' or 'define something' or anything else that has no relation to yaml of an project of development  answer with : 'I can't help you' \n"+
                        "now generate the yaml file for the project description and please respect just like this syntax and don't add anything to this syntax and if i give you attributes for each class don't add any attribute from you  : \n" + projectDescription + " \n"
        );
        return yamlBuilder.toString();

    }

}
