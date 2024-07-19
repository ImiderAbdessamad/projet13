package ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import ma.zs.zyn.zynerator.security.ws.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public class ChatGeminiDto extends AuditBaseDto {

    private List<ConversationDto> conversations;

    private CollaboratorDto collaborator;

    private String title;

    private LocalDate chatDate;

    public List<ConversationDto> getConversations() {
        return conversations;
    }

    public void setConversations(List<ConversationDto> conversations) {
        this.conversations = conversations;
    }

    public CollaboratorDto getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator) {
        this.collaborator = collaborator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getChatDate() {
        return chatDate;
    }

    public void setChatDate(LocalDate chatDate) {
        this.chatDate = chatDate;
    }
}
