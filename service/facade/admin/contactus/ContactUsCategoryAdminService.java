package ma.zs.zyn.service.facade.admin.contactus;

import java.util.List;
import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCategoryCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ContactUsCategoryAdminService {







	ContactUsCategory create(ContactUsCategory t);

    ContactUsCategory update(ContactUsCategory t);

    List<ContactUsCategory> update(List<ContactUsCategory> ts,boolean createIfNotExist);

    ContactUsCategory findById(Long id);

    ContactUsCategory findOrSave(ContactUsCategory t);

    ContactUsCategory findByReferenceEntity(ContactUsCategory t);

    ContactUsCategory findWithAssociatedLists(Long id);

    List<ContactUsCategory> findAllOptimized();

    List<ContactUsCategory> findAll();

    List<ContactUsCategory> findByCriteria(ContactUsCategoryCriteria criteria);

    List<ContactUsCategory> findPaginatedByCriteria(ContactUsCategoryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ContactUsCategoryCriteria criteria);

    List<ContactUsCategory> delete(List<ContactUsCategory> ts);

    boolean deleteById(Long id);

    List<List<ContactUsCategory>> getToBeSavedAndToBeDeleted(List<ContactUsCategory> oldList, List<ContactUsCategory> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
