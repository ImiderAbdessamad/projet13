package ma.zs.zyn.bean.core.contactus;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "contact_us_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "contact_us_category_seq", sequenceName = "contact_us_category_seq", allocationSize = 1, initialValue = 1)
public class ContactUsCategory extends BaseEntity {


    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;


    public ContactUsCategory() {
        super();
    }

    public ContactUsCategory(Long id) {
        this.id = id;
    }

    public ContactUsCategory(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public ContactUsCategory(String libelle) {
        this.libelle = libelle;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_us_category_seq")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactUsCategory contactUsCategory = (ContactUsCategory) o;
        return id != null && id.equals(contactUsCategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

