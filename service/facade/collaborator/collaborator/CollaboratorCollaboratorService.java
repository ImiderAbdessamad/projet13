package ma.zs.zyn.service.facade.collaborator.collaborator;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;

import java.util.List;


public interface CollaboratorCollaboratorService {


    Collaborator findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Collaborator> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);
    List<Collaborator> findByCityId(Long id);
    int deleteByCityId(Long id);
    long countByCityCode(String code);

    Collaborator getCurrentUser();

    Collaborator create(Collaborator t);

    Collaborator update(Collaborator t);

    List<Collaborator> update(List<Collaborator> ts,boolean createIfNotExist);

    Collaborator findById(Long id);

    Collaborator findOrSave(Collaborator t);

    Collaborator findByReferenceEntity(Collaborator t);

    Collaborator findWithAssociatedLists(Long id);

    List<Collaborator> findAllOptimized();

    List<Collaborator> findAll();

    List<Collaborator> findByCriteria(CollaboratorCriteria criteria);

    List<Collaborator> findPaginatedByCriteria(CollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CollaboratorCriteria criteria);

    List<Collaborator> delete(List<Collaborator> ts);

    boolean deleteById(Long id);

    List<List<Collaborator>> getToBeSavedAndToBeDeleted(List<Collaborator> oldList, List<Collaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
