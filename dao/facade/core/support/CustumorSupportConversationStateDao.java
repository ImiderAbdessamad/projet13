package ma.zs.zyn.dao.facade.core.support;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import java.util.List;


@Repository
public interface CustumorSupportConversationStateDao extends AbstractRepository<CustumorSupportConversationState,Long>  {
    CustumorSupportConversationState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CustumorSupportConversationState(item.id,item.libelle) FROM CustumorSupportConversationState item")
    List<CustumorSupportConversationState> findAllOptimized();

}
