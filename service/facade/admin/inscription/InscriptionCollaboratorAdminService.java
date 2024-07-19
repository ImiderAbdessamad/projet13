package ma.zs.zyn.service.facade.admin.inscription;

import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorCriteria;

import java.util.List;


public interface InscriptionCollaboratorAdminService {



    InscriptionCollaborator findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);




	InscriptionCollaborator create(InscriptionCollaborator t);

    InscriptionCollaborator update(InscriptionCollaborator t);

    List<InscriptionCollaborator> update(List<InscriptionCollaborator> ts,boolean createIfNotExist);

    InscriptionCollaborator findById(Long id);

    InscriptionCollaborator findOrSave(InscriptionCollaborator t);

    InscriptionCollaborator findByReferenceEntity(InscriptionCollaborator t);

    InscriptionCollaborator findWithAssociatedLists(Long id);

    List<InscriptionCollaborator> findAllOptimized();

    List<InscriptionCollaborator> findAll();

    List<InscriptionCollaborator> findByCriteria(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> findPaginatedByCriteria(InscriptionCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> delete(List<InscriptionCollaborator> ts);

    boolean deleteById(Long id);

    List<List<InscriptionCollaborator>> getToBeSavedAndToBeDeleted(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
