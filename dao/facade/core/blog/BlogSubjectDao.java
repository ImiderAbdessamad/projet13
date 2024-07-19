package ma.zs.zyn.dao.facade.core.blog;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import java.util.List;


@Repository
public interface BlogSubjectDao extends AbstractRepository<BlogSubject,Long>  {
    BlogSubject findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW BlogSubject(item.id,item.libelle) FROM BlogSubject item")
    List<BlogSubject> findAllOptimized();

}
