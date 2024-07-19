package ma.zs.zyn.service.facade.admin.forum;

import java.util.List;
import ma.zs.zyn.bean.core.forum.ForumComment;
import ma.zs.zyn.dao.criteria.core.forum.ForumCommentCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ForumCommentAdminService {



    List<ForumComment> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<ForumComment> findByForumId(Long id);
    int deleteByForumId(Long id);
    long countByForumId(Long id);




	ForumComment create(ForumComment t);

    ForumComment update(ForumComment t);

    List<ForumComment> update(List<ForumComment> ts,boolean createIfNotExist);

    ForumComment findById(Long id);

    ForumComment findOrSave(ForumComment t);

    ForumComment findByReferenceEntity(ForumComment t);

    ForumComment findWithAssociatedLists(Long id);

    List<ForumComment> findAllOptimized();

    List<ForumComment> findAll();

    List<ForumComment> findByCriteria(ForumCommentCriteria criteria);

    List<ForumComment> findPaginatedByCriteria(ForumCommentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ForumCommentCriteria criteria);

    List<ForumComment> delete(List<ForumComment> ts);

    boolean deleteById(Long id);

    List<List<ForumComment>> getToBeSavedAndToBeDeleted(List<ForumComment> oldList, List<ForumComment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
