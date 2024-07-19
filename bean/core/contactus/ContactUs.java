package ma.zs.zyn.bean.core.contactus;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "contact_us")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "contact_us_seq", sequenceName = "contact_us_seq", allocationSize = 1, initialValue = 1)
public class ContactUs extends BaseEntity {


    @Column(length = 500)
    private String phone;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String object;


    private String message;

    @Column(length = 500)
    private String description;

    private ContactUsCategory contactUsCategory;
    private ContactUsState contactUsState;


    public ContactUs() {
        super();
    }

    public ContactUs(Long id) {
        this.id = id;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_us_seq")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }
    @Column(columnDefinition = "TEXT")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_us_category")
    public ContactUsCategory getContactUsCategory() {
        return this.contactUsCategory;
    }

    public void setContactUsCategory(ContactUsCategory contactUsCategory) {
        this.contactUsCategory = contactUsCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_us_state")
    public ContactUsState getContactUsState() {
        return this.contactUsState;
    }

    public void setContactUsState(ContactUsState contactUsState) {
        this.contactUsState = contactUsState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactUs contactUs = (ContactUs) o;
        return id != null && id.equals(contactUs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

