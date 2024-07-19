package ma.zs.zyn.service.facade.collaborator.blog;

import java.util.List;
import ma.zs.zyn.bean.core.blog.BlogState;
import ma.zs.zyn.dao.criteria.core.blog.BlogStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface BlogStateCollaboratorService {







	BlogState create(BlogState t);

    BlogState update(BlogState t);

    List<BlogState> update(List<BlogState> ts,boolean createIfNotExist);

    BlogState findById(Long id);

    BlogState findOrSave(BlogState t);

    BlogState findByReferenceEntity(BlogState t);

    BlogState findWithAssociatedLists(Long id);

    List<BlogState> findAllOptimized();

    List<BlogState> findAll();

    List<BlogState> findByCriteria(BlogStateCriteria criteria);

    List<BlogState> findPaginatedByCriteria(BlogStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BlogStateCriteria criteria);

    List<BlogState> delete(List<BlogState> ts);

    boolean deleteById(Long id);

    List<List<BlogState>> getToBeSavedAndToBeDeleted(List<BlogState> oldList, List<BlogState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
