package ma.zs.zyn.service.facade.admin.support;

import java.util.List;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCategoryCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CustumorSupportConversationCategoryAdminService {







	CustumorSupportConversationCategory create(CustumorSupportConversationCategory t);

    CustumorSupportConversationCategory update(CustumorSupportConversationCategory t);

    List<CustumorSupportConversationCategory> update(List<CustumorSupportConversationCategory> ts,boolean createIfNotExist);

    CustumorSupportConversationCategory findById(Long id);

    CustumorSupportConversationCategory findOrSave(CustumorSupportConversationCategory t);

    CustumorSupportConversationCategory findByReferenceEntity(CustumorSupportConversationCategory t);

    CustumorSupportConversationCategory findWithAssociatedLists(Long id);

    List<CustumorSupportConversationCategory> findAllOptimized();

    List<CustumorSupportConversationCategory> findAll();

    List<CustumorSupportConversationCategory> findByCriteria(CustumorSupportConversationCategoryCriteria criteria);

    List<CustumorSupportConversationCategory> findPaginatedByCriteria(CustumorSupportConversationCategoryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CustumorSupportConversationCategoryCriteria criteria);

    List<CustumorSupportConversationCategory> delete(List<CustumorSupportConversationCategory> ts);

    boolean deleteById(Long id);

    List<List<CustumorSupportConversationCategory>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationCategory> oldList, List<CustumorSupportConversationCategory> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
