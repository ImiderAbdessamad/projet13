package ma.zs.zyn.dao.facade.core.coupon;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PaimentCouponDao extends AbstractRepository<PaimentCoupon,Long>  {

    List<PaimentCoupon> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);
    List<PaimentCoupon> findByPaimentCouponStateLibelle(String libelle);
            public int deleteByPaimentCouponStateLibelle(String libelle);
    long countByPaimentCouponStateLibelle(String libelle);


    PaimentCoupon findByCouponCodeAndPaimentCouponStateCode(String codeCoupon, String codePaiementState);

    List<PaimentCoupon> findByCouponInfluenceurId(Long id);
}
