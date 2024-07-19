package ma.zs.zyn.service.facade.collaborator.configuration;

import java.util.List;
import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.dao.criteria.core.configuration.BankTransferConfigurationCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface BankTransferConfigurationCollaboratorService {







	BankTransferConfiguration create(BankTransferConfiguration t);

    BankTransferConfiguration update(BankTransferConfiguration t);

    List<BankTransferConfiguration> update(List<BankTransferConfiguration> ts,boolean createIfNotExist);

    BankTransferConfiguration findById(Long id);

    BankTransferConfiguration findOrSave(BankTransferConfiguration t);

    BankTransferConfiguration findByReferenceEntity(BankTransferConfiguration t);

    BankTransferConfiguration findWithAssociatedLists(Long id);

    List<BankTransferConfiguration> findAllOptimized();

    List<BankTransferConfiguration> findAll();

    List<BankTransferConfiguration> findByCriteria(BankTransferConfigurationCriteria criteria);

    List<BankTransferConfiguration> findPaginatedByCriteria(BankTransferConfigurationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BankTransferConfigurationCriteria criteria);

    List<BankTransferConfiguration> delete(List<BankTransferConfiguration> ts);

    boolean deleteById(Long id);

    List<List<BankTransferConfiguration>> getToBeSavedAndToBeDeleted(List<BankTransferConfiguration> oldList, List<BankTransferConfiguration> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
