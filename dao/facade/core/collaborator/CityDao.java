package ma.zs.zyn.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityDao extends AbstractRepository<City,Long>  {
    City findByCode(String code);
    int deleteByCode(String code);

    List<City> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);

    @Query("SELECT NEW City(item.id,item.libelle) FROM City item")
    List<City> findAllOptimized();

}
