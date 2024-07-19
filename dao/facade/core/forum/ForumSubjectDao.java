package ma.zs.zyn.dao.facade.core.forum;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import java.util.List;


@Repository
public interface ForumSubjectDao extends AbstractRepository<ForumSubject,Long>  {
    ForumSubject findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ForumSubject(item.id,item.libelle) FROM ForumSubject item")
    List<ForumSubject> findAllOptimized();

}
