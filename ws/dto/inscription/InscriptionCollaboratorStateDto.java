package ma.zs.zyn.ws.dto.inscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionCollaboratorStateDto extends AuditBaseDto {

    private String code;
    private String libelle;
    private String style;


    public InscriptionCollaboratorStateDto() {
        super();
    }


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Log
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}
