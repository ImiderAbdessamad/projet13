package ma.zs.zyn.dao.facade.core.forum;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.forum.ForumState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.forum.ForumState;
import java.util.List;


@Repository
public interface ForumStateDao extends AbstractRepository<ForumState,Long>  {
    ForumState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ForumState(item.id,item.libelle) FROM ForumState item")
    List<ForumState> findAllOptimized();

}
