package ma.zs.zyn.dao.facade.core.coupon;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InfluenceurDao extends AbstractRepository<Influenceur,Long>  {

    Influenceur findByUsername(String username);


}
