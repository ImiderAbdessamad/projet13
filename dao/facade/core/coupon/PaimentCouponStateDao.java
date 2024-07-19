package ma.zs.zyn.dao.facade.core.coupon;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import java.util.List;


@Repository
public interface PaimentCouponStateDao extends AbstractRepository<PaimentCouponState,Long>  {
    PaimentCouponState findByLibelle(String libelle);
    int deleteByLibelle(String libelle);

    PaimentCouponState findByCode(String code);


    @Query("SELECT NEW PaimentCouponState(item.id,item.libelle) FROM PaimentCouponState item")
    List<PaimentCouponState> findAllOptimized();

}
