package ma.zs.zyn.service.facade.admin.forum;

import java.util.List;
import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.dao.criteria.core.forum.ForumSubjectCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface ForumSubjectAdminService {







	ForumSubject create(ForumSubject t);

    ForumSubject update(ForumSubject t);

    List<ForumSubject> update(List<ForumSubject> ts,boolean createIfNotExist);

    ForumSubject findById(Long id);

    ForumSubject findOrSave(ForumSubject t);

    ForumSubject findByReferenceEntity(ForumSubject t);

    ForumSubject findWithAssociatedLists(Long id);

    List<ForumSubject> findAllOptimized();

    List<ForumSubject> findAll();

    List<ForumSubject> findByCriteria(ForumSubjectCriteria criteria);

    List<ForumSubject> findPaginatedByCriteria(ForumSubjectCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ForumSubjectCriteria criteria);

    List<ForumSubject> delete(List<ForumSubject> ts);

    boolean deleteById(Long id);

    List<List<ForumSubject>> getToBeSavedAndToBeDeleted(List<ForumSubject> oldList, List<ForumSubject> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
