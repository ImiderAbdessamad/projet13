package ma.zs.zyn.dao.facade.core.blog;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.blog.Blog;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BlogDao extends AbstractRepository<Blog,Long>  {

    List<Blog> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Blog> findByBlogSubjectId(Long id);
    int deleteByBlogSubjectId(Long id);
    long countByBlogSubjectCode(String code);
    List<Blog> findByBlogStateCode(String code);
            public int deleteByBlogStateCode(String code);
    long countByBlogStateCode(String code);


}
