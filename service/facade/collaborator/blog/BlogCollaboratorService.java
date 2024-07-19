package ma.zs.zyn.service.facade.collaborator.blog;

import java.util.List;
import ma.zs.zyn.bean.core.blog.Blog;
import ma.zs.zyn.dao.criteria.core.blog.BlogCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface BlogCollaboratorService {



    List<Blog> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<Blog> findByBlogSubjectId(Long id);
    int deleteByBlogSubjectId(Long id);
    long countByBlogSubjectCode(String code);
    List<Blog> findByBlogStateCode(String code);
    int deleteByBlogStateCode(String code);
    long countByBlogStateCode(String code);




	Blog create(Blog t);

    Blog update(Blog t);

    List<Blog> update(List<Blog> ts,boolean createIfNotExist);

    Blog findById(Long id);

    Blog findOrSave(Blog t);

    Blog findByReferenceEntity(Blog t);

    Blog findWithAssociatedLists(Long id);

    List<Blog> findAllOptimized();

    List<Blog> findAll();

    List<Blog> findByCriteria(BlogCriteria criteria);

    List<Blog> findPaginatedByCriteria(BlogCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BlogCriteria criteria);

    List<Blog> delete(List<Blog> ts);

    boolean deleteById(Long id);

    List<List<Blog>> getToBeSavedAndToBeDeleted(List<Blog> oldList, List<Blog> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
