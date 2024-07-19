package ma.zs.zyn.dao.facade.core.collaborator;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.Country;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.collaborator.Country;
import java.util.List;


@Repository
public interface CountryDao extends AbstractRepository<Country,Long>  {
    Country findByCode(String code);
    Country findByLibelle(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Country(item.id,item.libelle) FROM Country item")
    List<Country> findAllOptimized();

}
