package ma.zs.zyn.dao.facade.core.project;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.Conversation;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ConversationDao extends AbstractRepository<Conversation,Long>  {

    List<Conversation> findByProjectId(Long id);
    List<Conversation> findByProjectTitle(String title);
    List<Conversation> findByProjectTitleChat(String title);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);


}
