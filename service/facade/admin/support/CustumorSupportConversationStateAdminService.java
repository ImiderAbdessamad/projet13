package ma.zs.zyn.service.facade.admin.support;

import java.util.List;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CustumorSupportConversationStateAdminService {







	CustumorSupportConversationState create(CustumorSupportConversationState t);

    CustumorSupportConversationState update(CustumorSupportConversationState t);

    List<CustumorSupportConversationState> update(List<CustumorSupportConversationState> ts,boolean createIfNotExist);

    CustumorSupportConversationState findById(Long id);

    CustumorSupportConversationState findOrSave(CustumorSupportConversationState t);

    CustumorSupportConversationState findByReferenceEntity(CustumorSupportConversationState t);

    CustumorSupportConversationState findWithAssociatedLists(Long id);

    List<CustumorSupportConversationState> findAllOptimized();

    List<CustumorSupportConversationState> findAll();

    List<CustumorSupportConversationState> findByCriteria(CustumorSupportConversationStateCriteria criteria);

    List<CustumorSupportConversationState> findPaginatedByCriteria(CustumorSupportConversationStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CustumorSupportConversationStateCriteria criteria);

    List<CustumorSupportConversationState> delete(List<CustumorSupportConversationState> ts);

    boolean deleteById(Long id);

    List<List<CustumorSupportConversationState>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationState> oldList, List<CustumorSupportConversationState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
