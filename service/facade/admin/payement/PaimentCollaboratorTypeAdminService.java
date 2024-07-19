package ma.zs.zyn.service.facade.admin.payement;

import java.util.List;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorTypeCriteria;


public interface PaimentCollaboratorTypeAdminService {







	PaimentCollaboratorType create(PaimentCollaboratorType t);

    PaimentCollaboratorType update(PaimentCollaboratorType t);

    List<PaimentCollaboratorType> update(List<PaimentCollaboratorType> ts,boolean createIfNotExist);

    PaimentCollaboratorType findById(Long id);

    PaimentCollaboratorType findOrSave(PaimentCollaboratorType t);

    PaimentCollaboratorType findByReferenceEntity(PaimentCollaboratorType t);

    PaimentCollaboratorType findWithAssociatedLists(Long id);

    List<PaimentCollaboratorType> findAllOptimized();

    List<PaimentCollaboratorType> findAll();

    List<PaimentCollaboratorType> findByCriteria(PaimentCollaboratorTypeCriteria criteria);

    List<PaimentCollaboratorType> findPaginatedByCriteria(PaimentCollaboratorTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCollaboratorTypeCriteria criteria);

    List<PaimentCollaboratorType> delete(List<PaimentCollaboratorType> ts);

    boolean deleteById(Long id);

    List<List<PaimentCollaboratorType>> getToBeSavedAndToBeDeleted(List<PaimentCollaboratorType> oldList, List<PaimentCollaboratorType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
