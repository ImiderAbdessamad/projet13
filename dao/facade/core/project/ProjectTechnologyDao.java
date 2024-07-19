package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.ProjectTechnology;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectTechnologyDao extends AbstractRepository<ProjectTechnology,Long>  {
    ProjectTechnology findByCode(String code);
    int deleteByCode(String code);

    List<ProjectTechnology> findByProjectTechnologyTypeCode(String libelle);
            public int deleteByProjectTechnologyTypeCode(String code);
    long countByProjectTechnologyTypeCode(String code);

    @Query("SELECT NEW ProjectTechnology(item.id,item.libelle) FROM ProjectTechnology item")
    List<ProjectTechnology> findAllOptimized();

}
