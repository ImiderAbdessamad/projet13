package ma.zs.zyn.service.facade.admin.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CouponAdminService {



    List<Coupon> findByInfluenceurId(Long id);
    int deleteByInfluenceurId(Long id);
    long countByInfluenceurId(Long id);
    List<Coupon> findByCouponStateLibelle(String libelle);
    int deleteByCouponStateLibelle(String libelle);
    long countByCouponStateLibelle(String libelle);




	Coupon create(Coupon t);

    Coupon update(Coupon t);

    List<Coupon> update(List<Coupon> ts,boolean createIfNotExist);

    Coupon findById(Long id);

    Coupon findOrSave(Coupon t);

    Coupon findByReferenceEntity(Coupon t);

    Coupon findWithAssociatedLists(Long id);

    List<Coupon> findAllOptimized();

    List<Coupon> findAll();

    List<Coupon> findByCriteria(CouponCriteria criteria);

    List<Coupon> findPaginatedByCriteria(CouponCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CouponCriteria criteria);

    List<Coupon> delete(List<Coupon> ts);

    boolean deleteById(Long id);

    List<List<Coupon>> getToBeSavedAndToBeDeleted(List<Coupon> oldList, List<Coupon> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
