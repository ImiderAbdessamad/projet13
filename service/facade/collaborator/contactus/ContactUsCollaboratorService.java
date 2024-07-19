package ma.zs.zyn.service.facade.collaborator.contactus;

import java.util.List;
import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ContactUsCollaboratorService {



    List<ContactUs> findByContactUsCategoryId(Long id);
    int deleteByContactUsCategoryId(Long id);
    long countByContactUsCategoryCode(String code);
    List<ContactUs> findByContactUsStateCode(String code);
    int deleteByContactUsStateCode(String code);
    long countByContactUsStateCode(String code);




	ContactUs create(ContactUs t);

    ContactUs update(ContactUs t);

    List<ContactUs> update(List<ContactUs> ts,boolean createIfNotExist);

    ContactUs findById(Long id);

    ContactUs findOrSave(ContactUs t);

    ContactUs findByReferenceEntity(ContactUs t);

    ContactUs findWithAssociatedLists(Long id);

    List<ContactUs> findAllOptimized();

    List<ContactUs> findAll();

    List<ContactUs> findByCriteria(ContactUsCriteria criteria);

    List<ContactUs> findPaginatedByCriteria(ContactUsCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ContactUsCriteria criteria);

    List<ContactUs> delete(List<ContactUs> ts);

    boolean deleteById(Long id);

    List<List<ContactUs>> getToBeSavedAndToBeDeleted(List<ContactUs> oldList, List<ContactUs> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
