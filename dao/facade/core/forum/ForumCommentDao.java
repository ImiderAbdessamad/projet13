package ma.zs.zyn.dao.facade.core.forum;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.forum.ForumComment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ForumCommentDao extends AbstractRepository<ForumComment,Long>  {

    List<ForumComment> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<ForumComment> findByForumId(Long id);
    int deleteByForumId(Long id);
    long countByForumId(Long id);


}
