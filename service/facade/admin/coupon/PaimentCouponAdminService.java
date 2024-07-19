package ma.zs.zyn.service.facade.admin.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface PaimentCouponAdminService {



    List<PaimentCoupon> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);
    List<PaimentCoupon> findByPaimentCouponStateLibelle(String libelle);
    int deleteByPaimentCouponStateLibelle(String libelle);
    long countByPaimentCouponStateLibelle(String libelle);




	PaimentCoupon create(PaimentCoupon t);

    PaimentCoupon update(PaimentCoupon t);

    List<PaimentCoupon> update(List<PaimentCoupon> ts,boolean createIfNotExist);

    PaimentCoupon findById(Long id);

    PaimentCoupon findOrSave(PaimentCoupon t);

    PaimentCoupon findByReferenceEntity(PaimentCoupon t);

    PaimentCoupon findWithAssociatedLists(Long id);

    List<PaimentCoupon> findAllOptimized();

    List<PaimentCoupon> findAll();

    List<PaimentCoupon> findByCriteria(PaimentCouponCriteria criteria);

    List<PaimentCoupon> findPaginatedByCriteria(PaimentCouponCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCouponCriteria criteria);

    List<PaimentCoupon> delete(List<PaimentCoupon> ts);

    boolean deleteById(Long id);

    List<List<PaimentCoupon>> getToBeSavedAndToBeDeleted(List<PaimentCoupon> oldList, List<PaimentCoupon> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
