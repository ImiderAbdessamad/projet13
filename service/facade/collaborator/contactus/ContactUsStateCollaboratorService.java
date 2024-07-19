package ma.zs.zyn.service.facade.collaborator.contactus;

import java.util.List;
import ma.zs.zyn.bean.core.contactus.ContactUsState;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ContactUsStateCollaboratorService {







	ContactUsState create(ContactUsState t);

    ContactUsState update(ContactUsState t);

    List<ContactUsState> update(List<ContactUsState> ts,boolean createIfNotExist);

    ContactUsState findById(Long id);

    ContactUsState findOrSave(ContactUsState t);

    ContactUsState findByReferenceEntity(ContactUsState t);

    ContactUsState findWithAssociatedLists(Long id);

    List<ContactUsState> findAllOptimized();

    List<ContactUsState> findAll();

    List<ContactUsState> findByCriteria(ContactUsStateCriteria criteria);

    List<ContactUsState> findPaginatedByCriteria(ContactUsStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ContactUsStateCriteria criteria);

    List<ContactUsState> delete(List<ContactUsState> ts);

    boolean deleteById(Long id);

    List<List<ContactUsState>> getToBeSavedAndToBeDeleted(List<ContactUsState> oldList, List<ContactUsState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
