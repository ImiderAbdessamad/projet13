package ma.zs.zyn.service.facade.collaborator.blog;

import java.util.List;
import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.dao.criteria.core.blog.BlogCommentCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface BlogCommentCollaboratorService {



    List<BlogComment> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<BlogComment> findByBlogId(Long id);
    int deleteByBlogId(Long id);
    long countByBlogId(Long id);




	BlogComment create(BlogComment t);

    BlogComment update(BlogComment t);

    List<BlogComment> update(List<BlogComment> ts,boolean createIfNotExist);

    BlogComment findById(Long id);

    BlogComment findOrSave(BlogComment t);

    BlogComment findByReferenceEntity(BlogComment t);

    BlogComment findWithAssociatedLists(Long id);

    List<BlogComment> findAllOptimized();

    List<BlogComment> findAll();

    List<BlogComment> findByCriteria(BlogCommentCriteria criteria);

    List<BlogComment> findPaginatedByCriteria(BlogCommentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BlogCommentCriteria criteria);

    List<BlogComment> delete(List<BlogComment> ts);

    boolean deleteById(Long id);

    List<List<BlogComment>> getToBeSavedAndToBeDeleted(List<BlogComment> oldList, List<BlogComment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
