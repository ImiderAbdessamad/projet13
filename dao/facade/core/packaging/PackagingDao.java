package ma.zs.zyn.dao.facade.core.packaging;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.packaging.Packaging;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.packaging.Packaging;
import java.util.List;


@Repository
public interface PackagingDao extends AbstractRepository<Packaging,Long>  {
    Packaging findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Packaging(item.id,item.code) FROM Packaging item")
    List<Packaging> findAllOptimized();

}
