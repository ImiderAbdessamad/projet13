package ma.zs.zyn.service.facade.collaborator.inscription;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorCriteria;

import java.math.BigDecimal;
import java.util.List;


public interface InscriptionCollaboratorCollaboratorService {

    InscriptionCollaborator initInscriptionAsFree(Collaborator collaborator);

    boolean checkTokenOutputCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging);

    boolean checkTokenInputCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging);

    boolean incrementConsumedTokenInput(InscriptionCollaborator inscriptionCollaborator, int countTokenInput);

    boolean incrementConsumedTokenOutput(InscriptionCollaborator inscriptionCollaborator, int countTokenOutput);

    InscriptionCollaborator findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);




	InscriptionCollaborator create(InscriptionCollaborator t);

    int resetInscription(Collaborator collaborator);

    boolean checkProjectCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging);

    boolean checkEntityCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging);

    boolean checkAttributCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging);

    void incrementConsumed(InscriptionCollaborator inscriptionCollaborator, BigDecimal incrementEntity, BigDecimal incrementAttribut, BigDecimal incrementProject);

    InscriptionCollaborator findByConnectedCollaboratorId();

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

    boolean isFreeInscription(Packaging packaging);
}
