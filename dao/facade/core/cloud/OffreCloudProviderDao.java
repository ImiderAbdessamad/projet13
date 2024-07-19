package ma.zs.zyn.dao.facade.core.cloud;

import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OffreCloudProviderDao extends AbstractRepository<OffreCloudProvider,Long>  {
    OffreCloudProvider findByCode(String code);
    int deleteByCode(String code);

    List<OffreCloudProvider> findByCloudProviderId(Long id);
    int deleteByCloudProviderId(Long id);
    long countByCloudProviderCode(String code);

    @Query("SELECT NEW OffreCloudProvider(item.id,item.libelle) FROM OffreCloudProvider item")
    List<OffreCloudProvider> findAllOptimized();

}
