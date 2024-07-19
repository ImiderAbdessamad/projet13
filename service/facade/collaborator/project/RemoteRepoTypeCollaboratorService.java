package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoTypeCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface RemoteRepoTypeCollaboratorService {







	RemoteRepoType create(RemoteRepoType t);

    RemoteRepoType update(RemoteRepoType t);

    List<RemoteRepoType> update(List<RemoteRepoType> ts,boolean createIfNotExist);

    RemoteRepoType findById(Long id);

    RemoteRepoType findOrSave(RemoteRepoType t);

    RemoteRepoType findByReferenceEntity(RemoteRepoType t);

    RemoteRepoType findWithAssociatedLists(Long id);

    List<RemoteRepoType> findAllOptimized();

    List<RemoteRepoType> findAll();

    List<RemoteRepoType> findByCriteria(RemoteRepoTypeCriteria criteria);

    List<RemoteRepoType> findPaginatedByCriteria(RemoteRepoTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RemoteRepoTypeCriteria criteria);

    List<RemoteRepoType> delete(List<RemoteRepoType> ts);

    boolean deleteById(Long id);

    List<List<RemoteRepoType>> getToBeSavedAndToBeDeleted(List<RemoteRepoType> oldList, List<RemoteRepoType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
