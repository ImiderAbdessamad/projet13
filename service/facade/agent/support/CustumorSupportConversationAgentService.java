package ma.zs.zyn.service.facade.agent.support;

import java.util.List;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CustumorSupportConversationAgentService {



    List<CustumorSupportConversation> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<CustumorSupportConversation> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentId(Long id);
    List<CustumorSupportConversation> findByCustumorSupportConversationCategoryId(Long id);
    int deleteByCustumorSupportConversationCategoryId(Long id);
    long countByCustumorSupportConversationCategoryCode(String code);
    List<CustumorSupportConversation> findByCustumorSupportConversationStateCode(String code);
    int deleteByCustumorSupportConversationStateCode(String code);
    long countByCustumorSupportConversationStateCode(String code);




	CustumorSupportConversation create(CustumorSupportConversation t);

    CustumorSupportConversation update(CustumorSupportConversation t);

    List<CustumorSupportConversation> update(List<CustumorSupportConversation> ts,boolean createIfNotExist);

    CustumorSupportConversation findById(Long id);

    CustumorSupportConversation findOrSave(CustumorSupportConversation t);

    CustumorSupportConversation findByReferenceEntity(CustumorSupportConversation t);

    CustumorSupportConversation findWithAssociatedLists(Long id);

    List<CustumorSupportConversation> findAllOptimized();

    List<CustumorSupportConversation> findAll();

    List<CustumorSupportConversation> findByCriteria(CustumorSupportConversationCriteria criteria);

    List<CustumorSupportConversation> findPaginatedByCriteria(CustumorSupportConversationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CustumorSupportConversationCriteria criteria);

    List<CustumorSupportConversation> delete(List<CustumorSupportConversation> ts);

    boolean deleteById(Long id);

    List<List<CustumorSupportConversation>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversation> oldList, List<CustumorSupportConversation> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
