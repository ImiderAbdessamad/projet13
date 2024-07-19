package ma.zs.zyn.service.facade.collaborator.forum;

import java.util.List;
import ma.zs.zyn.bean.core.forum.Forum;
import ma.zs.zyn.dao.criteria.core.forum.ForumCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ForumCollaboratorService {



    List<Forum> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Forum> findByForumSubjectId(Long id);
    int deleteByForumSubjectId(Long id);
    long countByForumSubjectCode(String code);
    List<Forum> findByForumStateCode(String code);
    int deleteByForumStateCode(String code);
    long countByForumStateCode(String code);




	Forum create(Forum t);

    Forum update(Forum t);

    List<Forum> update(List<Forum> ts,boolean createIfNotExist);

    Forum findById(Long id);

    Forum findOrSave(Forum t);

    Forum findByReferenceEntity(Forum t);

    Forum findWithAssociatedLists(Long id);

    List<Forum> findAllOptimized();

    List<Forum> findAll();

    List<Forum> findByCriteria(ForumCriteria criteria);

    List<Forum> findPaginatedByCriteria(ForumCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ForumCriteria criteria);

    List<Forum> delete(List<Forum> ts);

    boolean deleteById(Long id);

    List<List<Forum>> getToBeSavedAndToBeDeleted(List<Forum> oldList, List<Forum> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
