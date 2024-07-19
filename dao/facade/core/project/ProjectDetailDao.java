package ma.zs.zyn.dao.facade.core.project;

import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectDetailDao extends AbstractRepository<ProjectDetail,Long>  {

    List<ProjectDetail> findByProjectTechnologyId(Long id);
    int deleteByProjectTechnologyId(Long id);
    long countByProjectTechnologyCode(String code);
    List<ProjectDetail> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);
    List<ProjectDetail> findByProjectTechnologyProfileCode(String code);
    public int deleteByProjectTechnologyProfileCode(String code);
    long countByProjectTechnologyProfileCode(String code);

    @Query("SELECT NEW ProjectDetail(item.id,item.title) FROM ProjectDetail item")
    List<ProjectDetail> findAllOptimized();

}
