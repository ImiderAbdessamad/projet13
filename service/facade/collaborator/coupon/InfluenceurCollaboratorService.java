package ma.zs.zyn.service.facade.collaborator.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.dao.criteria.core.coupon.InfluenceurCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface InfluenceurCollaboratorService {


    Influenceur findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Influenceur create(Influenceur t);

    Influenceur update(Influenceur t);

    List<Influenceur> update(List<Influenceur> ts,boolean createIfNotExist);

    Influenceur findById(Long id);

    Influenceur findOrSave(Influenceur t);

    Influenceur findByReferenceEntity(Influenceur t);

    Influenceur findWithAssociatedLists(Long id);

    List<Influenceur> findAllOptimized();

    List<Influenceur> findAll();

    List<Influenceur> findByCriteria(InfluenceurCriteria criteria);

    List<Influenceur> findPaginatedByCriteria(InfluenceurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InfluenceurCriteria criteria);

    List<Influenceur> delete(List<Influenceur> ts);

    boolean deleteById(Long id);

    List<List<Influenceur>> getToBeSavedAndToBeDeleted(List<Influenceur> oldList, List<Influenceur> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
