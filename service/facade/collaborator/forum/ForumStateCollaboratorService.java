package ma.zs.zyn.service.facade.collaborator.forum;

import java.util.List;
import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.dao.criteria.core.forum.ForumStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ForumStateCollaboratorService {







	ForumState create(ForumState t);

    ForumState update(ForumState t);

    List<ForumState> update(List<ForumState> ts,boolean createIfNotExist);

    ForumState findById(Long id);

    ForumState findOrSave(ForumState t);

    ForumState findByReferenceEntity(ForumState t);

    ForumState findWithAssociatedLists(Long id);

    List<ForumState> findAllOptimized();

    List<ForumState> findAll();

    List<ForumState> findByCriteria(ForumStateCriteria criteria);

    List<ForumState> findPaginatedByCriteria(ForumStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ForumStateCriteria criteria);

    List<ForumState> delete(List<ForumState> ts);

    boolean deleteById(Long id);

    List<List<ForumState>> getToBeSavedAndToBeDeleted(List<ForumState> oldList, List<ForumState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
