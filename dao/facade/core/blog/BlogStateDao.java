package ma.zs.zyn.dao.facade.core.blog;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.blog.BlogState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.blog.BlogState;
import java.util.List;


@Repository
public interface BlogStateDao extends AbstractRepository<BlogState,Long>  {
    BlogState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW BlogState(item.id,item.libelle) FROM BlogState item")
    List<BlogState> findAllOptimized();

}
