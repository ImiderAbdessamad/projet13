package ma.zs.zyn.service.facade.admin.support;

import java.util.List;
import ma.zs.zyn.bean.core.support.Agent;
import ma.zs.zyn.dao.criteria.core.support.AgentCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface AgentAdminService {


    Agent findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Agent create(Agent t);

    Agent update(Agent t);

    List<Agent> update(List<Agent> ts,boolean createIfNotExist);

    Agent findById(Long id);

    Agent findOrSave(Agent t);

    Agent findByReferenceEntity(Agent t);

    Agent findWithAssociatedLists(Long id);

    List<Agent> findAllOptimized();

    List<Agent> findAll();

    List<Agent> findByCriteria(AgentCriteria criteria);

    List<Agent> findPaginatedByCriteria(AgentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AgentCriteria criteria);

    List<Agent> delete(List<Agent> ts);

    boolean deleteById(Long id);

    List<List<Agent>> getToBeSavedAndToBeDeleted(List<Agent> oldList, List<Agent> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
