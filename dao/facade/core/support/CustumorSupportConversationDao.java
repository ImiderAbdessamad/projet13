package ma.zs.zyn.dao.facade.core.support;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustumorSupportConversationDao extends AbstractRepository<CustumorSupportConversation,Long>  {

    List<CustumorSupportConversation> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<CustumorSupportConversation> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentId(Long id);
    List<CustumorSupportConversation> findByCustumorSupportConversationCategoryId(Long id);
    int deleteByCustumorSupportConversationCategoryId(Long id);
    long countByCustumorSupportConversationCategoryCode(String code);
    List<CustumorSupportConversation> findByCustumorSupportConversationStateCode(String code);
            public int deleteByCustumorSupportConversationStateCode(String code);
    long countByCustumorSupportConversationStateCode(String code);


    List<CustumorSupportConversation> findByCollaboratorUsername(String currentUser);
}
