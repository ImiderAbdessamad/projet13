package ma.zs.zyn.dao.facade.core.coupon;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.CouponState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.coupon.CouponState;
import java.util.List;


@Repository
public interface CouponStateDao extends AbstractRepository<CouponState,Long>  {
    CouponState findByLibelle(String libelle);
    int deleteByLibelle(String libelle);

    CouponState findByCode(String code);


    @Query("SELECT NEW CouponState(item.id,item.libelle) FROM CouponState item")
    List<CouponState> findAllOptimized();

}
