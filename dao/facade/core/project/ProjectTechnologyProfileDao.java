package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import java.util.List;


@Repository
public interface ProjectTechnologyProfileDao extends AbstractRepository<ProjectTechnologyProfile,Long>  {
    ProjectTechnologyProfile findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectTechnologyProfile(item.id,item.libelle) FROM ProjectTechnologyProfile item")
    List<ProjectTechnologyProfile> findAllOptimized();

}
