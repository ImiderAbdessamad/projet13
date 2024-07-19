package ma.zs.zyn.service.facade.admin.project;

import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoInfoCriteria;

import java.util.List;


public interface RemoteRepoInfoAdminService {



    List<RemoteRepoInfo> findByRemoteRepoTypeCode(String code);
    int deleteByRemoteRepoTypeCode(String code);
    long countByRemoteRepoTypeCode(String code);
    List<RemoteRepoInfo> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);




	RemoteRepoInfo create(RemoteRepoInfo t);

    RemoteRepoInfo update(RemoteRepoInfo t);

    List<RemoteRepoInfo> update(List<RemoteRepoInfo> ts,boolean createIfNotExist);

    RemoteRepoInfo findById(Long id);

    RemoteRepoInfo findOrSave(RemoteRepoInfo t);

    RemoteRepoInfo findByReferenceEntity(RemoteRepoInfo t);

    RemoteRepoInfo findWithAssociatedLists(Long id);

    List<RemoteRepoInfo> findAllOptimized();

    List<RemoteRepoInfo> findAll();

    List<RemoteRepoInfo> findByCriteria(RemoteRepoInfoCriteria criteria);

    List<RemoteRepoInfo> findPaginatedByCriteria(RemoteRepoInfoCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RemoteRepoInfoCriteria criteria);

    List<RemoteRepoInfo> delete(List<RemoteRepoInfo> ts);

    boolean deleteById(Long id);

    List<List<RemoteRepoInfo>> getToBeSavedAndToBeDeleted(List<RemoteRepoInfo> oldList, List<RemoteRepoInfo> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
