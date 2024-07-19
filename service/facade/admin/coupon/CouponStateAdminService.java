package ma.zs.zyn.service.facade.admin.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.CouponState;
import ma.zs.zyn.dao.criteria.core.coupon.CouponStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CouponStateAdminService {







	CouponState create(CouponState t);

    CouponState update(CouponState t);

    List<CouponState> update(List<CouponState> ts,boolean createIfNotExist);

    CouponState findById(Long id);

    CouponState findOrSave(CouponState t);

    CouponState findByReferenceEntity(CouponState t);

    CouponState findWithAssociatedLists(Long id);

    List<CouponState> findAllOptimized();

    List<CouponState> findAll();

    List<CouponState> findByCriteria(CouponStateCriteria criteria);

    List<CouponState> findPaginatedByCriteria(CouponStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CouponStateCriteria criteria);

    List<CouponState> delete(List<CouponState> ts);

    boolean deleteById(Long id);

    List<List<CouponState>> getToBeSavedAndToBeDeleted(List<CouponState> oldList, List<CouponState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
