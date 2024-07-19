package ma.zs.zyn.service.facade.collaborator.blog;

import java.util.List;
import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.dao.criteria.core.blog.BlogSubjectCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface BlogSubjectCollaboratorService {







	BlogSubject create(BlogSubject t);

    BlogSubject update(BlogSubject t);

    List<BlogSubject> update(List<BlogSubject> ts,boolean createIfNotExist);

    BlogSubject findById(Long id);

    BlogSubject findOrSave(BlogSubject t);

    BlogSubject findByReferenceEntity(BlogSubject t);

    BlogSubject findWithAssociatedLists(Long id);

    List<BlogSubject> findAllOptimized();

    List<BlogSubject> findAll();

    List<BlogSubject> findByCriteria(BlogSubjectCriteria criteria);

    List<BlogSubject> findPaginatedByCriteria(BlogSubjectCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BlogSubjectCriteria criteria);

    List<BlogSubject> delete(List<BlogSubject> ts);

    boolean deleteById(Long id);

    List<List<BlogSubject>> getToBeSavedAndToBeDeleted(List<BlogSubject> oldList, List<BlogSubject> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
