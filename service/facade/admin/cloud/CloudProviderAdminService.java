package ma.zs.zyn.service.facade.admin.cloud;

import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.CloudProviderCriteria;

import java.util.List;


public interface CloudProviderAdminService {







	CloudProvider create(CloudProvider t);

    CloudProvider update(CloudProvider t);

    List<CloudProvider> update(List<CloudProvider> ts,boolean createIfNotExist);

    CloudProvider findById(Long id);

    CloudProvider findOrSave(CloudProvider t);

    CloudProvider findByReferenceEntity(CloudProvider t);

    CloudProvider findWithAssociatedLists(Long id);

    List<CloudProvider> findAllOptimized();

    List<CloudProvider> findAll();

    List<CloudProvider> findByCriteria(CloudProviderCriteria criteria);

    List<CloudProvider> findPaginatedByCriteria(CloudProviderCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CloudProviderCriteria criteria);

    List<CloudProvider> delete(List<CloudProvider> ts);

    boolean deleteById(Long id);

    List<List<CloudProvider>> getToBeSavedAndToBeDeleted(List<CloudProvider> oldList, List<CloudProvider> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
