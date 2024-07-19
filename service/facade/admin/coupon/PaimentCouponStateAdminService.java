package ma.zs.zyn.service.facade.admin.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface PaimentCouponStateAdminService {







	PaimentCouponState create(PaimentCouponState t);

    PaimentCouponState update(PaimentCouponState t);

    List<PaimentCouponState> update(List<PaimentCouponState> ts,boolean createIfNotExist);

    PaimentCouponState findById(Long id);

    PaimentCouponState findOrSave(PaimentCouponState t);

    PaimentCouponState findByReferenceEntity(PaimentCouponState t);

    PaimentCouponState findWithAssociatedLists(Long id);

    List<PaimentCouponState> findAllOptimized();

    List<PaimentCouponState> findAll();

    List<PaimentCouponState> findByCriteria(PaimentCouponStateCriteria criteria);

    List<PaimentCouponState> findPaginatedByCriteria(PaimentCouponStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCouponStateCriteria criteria);

    List<PaimentCouponState> delete(List<PaimentCouponState> ts);

    boolean deleteById(Long id);

    List<List<PaimentCouponState>> getToBeSavedAndToBeDeleted(List<PaimentCouponState> oldList, List<PaimentCouponState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
