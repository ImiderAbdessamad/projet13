package ma.zs.zyn.service.facade.collaborator.payement;

import java.util.List;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorState;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorStateCriteria;


public interface PaimentCollaboratorStateCollaboratorService {







	PaimentCollaboratorState create(PaimentCollaboratorState t);

    PaimentCollaboratorState update(PaimentCollaboratorState t);

    List<PaimentCollaboratorState> update(List<PaimentCollaboratorState> ts,boolean createIfNotExist);

    PaimentCollaboratorState findById(Long id);
    PaimentCollaboratorState findByCode(String code);

    PaimentCollaboratorState findOrSave(PaimentCollaboratorState t);

    PaimentCollaboratorState findByReferenceEntity(PaimentCollaboratorState t);

    PaimentCollaboratorState findWithAssociatedLists(Long id);

    List<PaimentCollaboratorState> findAllOptimized();

    List<PaimentCollaboratorState> findAll();

    List<PaimentCollaboratorState> findByCriteria(PaimentCollaboratorStateCriteria criteria);

    List<PaimentCollaboratorState> findPaginatedByCriteria(PaimentCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCollaboratorStateCriteria criteria);

    List<PaimentCollaboratorState> delete(List<PaimentCollaboratorState> ts);

    boolean deleteById(Long id);

    List<List<PaimentCollaboratorState>> getToBeSavedAndToBeDeleted(List<PaimentCollaboratorState> oldList, List<PaimentCollaboratorState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
