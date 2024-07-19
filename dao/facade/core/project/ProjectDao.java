package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.Project;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjectDao extends AbstractRepository<Project,Long>  {
    Project findByTitleChat(String titleChat);
    List<Project> findByCollaboratorId(Long id);
    List<Project> findByCollaboratorUsername(String username);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Project> findByRemoteRepoInfoId(Long id);
    int deleteByRemoteRepoInfoId(Long id);
    long countByRemoteRepoInfoId(Long id);

    @Query("SELECT NEW Project(item.id,item.titleChat) FROM Project item")
    List<Project> findAllOptimized();

}
