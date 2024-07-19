package ma.zs.zyn.dao.facade.core.forum;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.forum.Forum;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ForumDao extends AbstractRepository<Forum,Long>  {

    List<Forum> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Forum> findByForumSubjectId(Long id);
    int deleteByForumSubjectId(Long id);
    long countByForumSubjectCode(String code);
    List<Forum> findByForumStateCode(String code);
            public int deleteByForumStateCode(String code);
    long countByForumStateCode(String code);


}
