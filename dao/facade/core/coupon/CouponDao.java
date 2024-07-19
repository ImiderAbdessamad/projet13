package ma.zs.zyn.dao.facade.core.coupon;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.Coupon;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.coupon.Coupon;
import java.util.List;


@Repository
public interface CouponDao extends AbstractRepository<Coupon,Long>  {
    Coupon findByCode(String code);
    int deleteByCode(String code);

    List<Coupon> findByInfluenceurId(Long id);
    int deleteByInfluenceurId(Long id);
    long countByInfluenceurId(Long id);
    List<Coupon> findByCouponStateLibelle(String libelle);
            public int deleteByCouponStateLibelle(String libelle);
    long countByCouponStateLibelle(String libelle);

    @Query("SELECT NEW Coupon(item.id,item.libelle) FROM Coupon item")
    List<Coupon> findAllOptimized();

}
