package ma.zs.zyn.service.facade.collaborator.cloud;

import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.OffreCloudProviderCriteria;

import java.util.List;


public interface OffreCloudProviderCollaboratorService {



    List<OffreCloudProvider> findByCloudProviderId(Long id);
    int deleteByCloudProviderId(Long id);
    long countByCloudProviderCode(String code);




	OffreCloudProvider create(OffreCloudProvider t);

    OffreCloudProvider update(OffreCloudProvider t);

    List<OffreCloudProvider> update(List<OffreCloudProvider> ts,boolean createIfNotExist);

    OffreCloudProvider findById(Long id);

    OffreCloudProvider findOrSave(OffreCloudProvider t);

    OffreCloudProvider findByReferenceEntity(OffreCloudProvider t);

    OffreCloudProvider findWithAssociatedLists(Long id);

    List<OffreCloudProvider> findAllOptimized();

    List<OffreCloudProvider> findAll();

    List<OffreCloudProvider> findByCriteria(OffreCloudProviderCriteria criteria);

    List<OffreCloudProvider> findPaginatedByCriteria(OffreCloudProviderCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OffreCloudProviderCriteria criteria);

    List<OffreCloudProvider> delete(List<OffreCloudProvider> ts);

    boolean deleteById(Long id);

    List<List<OffreCloudProvider>> getToBeSavedAndToBeDeleted(List<OffreCloudProvider> oldList, List<OffreCloudProvider> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
