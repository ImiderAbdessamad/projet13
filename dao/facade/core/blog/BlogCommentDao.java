package ma.zs.zyn.dao.facade.core.blog;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.blog.BlogComment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BlogCommentDao extends AbstractRepository<BlogComment,Long>  {

    List<BlogComment> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<BlogComment> findByBlogId(Long id);
    int deleteByBlogId(Long id);
    long countByBlogId(Long id);


}
