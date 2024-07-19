package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import java.util.List;


@Repository
public interface ProjectTechnologyTypeDao extends AbstractRepository<ProjectTechnologyType,Long>  {
    ProjectTechnologyType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectTechnologyType(item.id,item.libelle) FROM ProjectTechnologyType item")
    List<ProjectTechnologyType> findAllOptimized();

}
