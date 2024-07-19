package ma.zs.zyn.service.facade.admin.payement;

import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PaimentCollaboratorAdminService {



    List<PaimentCollaborator> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);
    List<PaimentCollaborator> findByCityId(Long id);
    int deleteByCityId(Long id);
    long countByCityCode(String code);
    List<PaimentCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<PaimentCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
    int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);
    List<PaimentCollaborator> findByPaimentCollaboratorTypeCode(String code);
    int deleteByPaimentCollaboratorTypeCode(String code);
    long countByPaimentCollaboratorTypeCode(String code);
    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);




	PaimentCollaborator create(PaimentCollaborator t);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    PaimentCollaborator changeState(PaimentCollaborator t);

    PaimentCollaborator update(PaimentCollaborator t);

    List<PaimentCollaborator> update(List<PaimentCollaborator> ts,boolean createIfNotExist);

    PaimentCollaborator findById(Long id);

    PaimentCollaborator findOrSave(PaimentCollaborator t);

    PaimentCollaborator findByReferenceEntity(PaimentCollaborator t);

    PaimentCollaborator findWithAssociatedLists(Long id);

    List<PaimentCollaborator> findAllOptimized();

    List<PaimentCollaborator> findAll();

    List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> delete(List<PaimentCollaborator> ts);

    boolean deleteById(Long id);

    List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
