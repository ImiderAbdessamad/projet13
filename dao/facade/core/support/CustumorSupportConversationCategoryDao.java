package ma.zs.zyn.dao.facade.core.support;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustumorSupportConversationCategoryDao extends AbstractRepository<CustumorSupportConversationCategory,Long>  {
    CustumorSupportConversationCategory findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CustumorSupportConversationCategory(item.id,item.libelle) FROM CustumorSupportConversationCategory item")
    List<CustumorSupportConversationCategory> findAllOptimized();

}
