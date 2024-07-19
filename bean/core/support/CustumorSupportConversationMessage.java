package ma.zs.zyn.bean.core.support;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.zyn.zynerator.bean.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "custumor_support_conversation_message")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "custumor_support_conversation_message_seq", sequenceName = "custumor_support_conversation_message_seq", allocationSize = 1, initialValue = 1)
public class CustumorSupportConversationMessage extends BaseEntity {


    private String content;

    @Column(columnDefinition = "boolean default false")
    private Boolean collaborator = false;

    private LocalDateTime creationDate;

    private CustumorSupportConversation custumorSupportConversation;


    public CustumorSupportConversationMessage() {
        super();
    }

    public CustumorSupportConversationMessage(Long id) {
        this.id = id;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custumor_support_conversation_message_seq")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(columnDefinition = "TEXT")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCollaborator() {
        return this.collaborator;
    }

    public void setCollaborator(Boolean collaborator) {
        this.collaborator = collaborator;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custumor_support_conversation")
    public CustumorSupportConversation getCustumorSupportConversation() {
        return this.custumorSupportConversation;
    }

    public void setCustumorSupportConversation(CustumorSupportConversation custumorSupportConversation) {
        this.custumorSupportConversation = custumorSupportConversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustumorSupportConversationMessage custumorSupportConversationMessage = (CustumorSupportConversationMessage) o;
        return id != null && id.equals(custumorSupportConversationMessage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

