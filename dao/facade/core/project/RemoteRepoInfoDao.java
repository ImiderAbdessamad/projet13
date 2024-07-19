package ma.zs.zyn.dao.facade.core.project;

import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RemoteRepoInfoDao extends AbstractRepository<RemoteRepoInfo,Long>  {

    List<RemoteRepoInfo> findByRemoteRepoTypeCode(String code);
            public int deleteByRemoteRepoTypeCode(String code);
    long countByRemoteRepoTypeCode(String code);
    List<RemoteRepoInfo> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);

    @Query("SELECT NEW RemoteRepoInfo(item.id,item.title) FROM RemoteRepoInfo item")
    List<RemoteRepoInfo> findAllOptimized();

}
