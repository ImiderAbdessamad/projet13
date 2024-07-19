package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.dao.criteria.core.project.ConversationCriteria;


public interface ConversationCollaboratorService {


    List<Conversation> findByProjectTitle(String title);
    List<Conversation> findByProjectTitleChat(String title);
    List<Conversation> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectId(Long id);




    Conversation create(Conversation t);

    Conversation update(Conversation t);

    List<Conversation> update(List<Conversation> ts,boolean createIfNotExist);

    Conversation findById(Long id);

    Conversation findOrSave(Conversation t);

    Conversation findByReferenceEntity(Conversation t);

    Conversation findWithAssociatedLists(Long id);

    List<Conversation> findAllOptimized();

    List<Conversation> findAll();

    List<Conversation> findByCriteria(ConversationCriteria criteria);

    List<Conversation> findPaginatedByCriteria(ConversationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ConversationCriteria criteria);

    List<Conversation> delete(List<Conversation> ts);

    boolean deleteById(Long id);

    List<List<Conversation>> getToBeSavedAndToBeDeleted(List<Conversation> oldList, List<Conversation> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
