package ma.zs.zyn.dao.facade.core.cloud;

import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CloudProviderDao extends AbstractRepository<CloudProvider,Long>  {
    CloudProvider findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CloudProvider(item.id,item.libelle) FROM CloudProvider item")
    List<CloudProvider> findAllOptimized();

}
