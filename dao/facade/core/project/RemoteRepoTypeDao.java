package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import java.util.List;


@Repository
public interface RemoteRepoTypeDao extends AbstractRepository<RemoteRepoType,Long>  {
    RemoteRepoType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW RemoteRepoType(item.id,item.libelle) FROM RemoteRepoType item")
    List<RemoteRepoType> findAllOptimized();

}
