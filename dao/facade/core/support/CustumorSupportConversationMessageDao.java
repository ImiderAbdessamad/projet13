package ma.zs.zyn.dao.facade.core.support;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustumorSupportConversationMessageDao extends AbstractRepository<CustumorSupportConversationMessage,Long>  {

    List<CustumorSupportConversationMessage> findByCustumorSupportConversationId(Long id);
    int deleteByCustumorSupportConversationId(Long id);
    long countByCustumorSupportConversationId(Long id);
    List<CustumorSupportConversationMessage> findByCustumorSupportConversationCollaboratorUsername(String currentUser);
}
