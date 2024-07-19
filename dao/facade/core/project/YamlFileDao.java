package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.YamlFile;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface YamlFileDao extends AbstractRepository<YamlFile,Long>  {

    List<YamlFile> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);

    @Query("SELECT NEW YamlFile(item.id,item.title) FROM YamlFile item")
    List<YamlFile> findAllOptimized();
    List<YamlFile> findByProjectCollaboratorUsername(String username);


}
