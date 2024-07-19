package ma.zs.zyn.service.impl.collaborator.cloud;


import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.OffreCloudProviderCriteria;
import ma.zs.zyn.dao.facade.core.cloud.OffreCloudProviderDao;
import ma.zs.zyn.dao.specification.core.cloud.OffreCloudProviderSpecification;
import ma.zs.zyn.service.facade.collaborator.cloud.CloudProviderCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.cloud.OffreCloudProviderCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;

@Service
public class OffreCloudProviderCollaboratorServiceImpl implements OffreCloudProviderCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OffreCloudProvider update(OffreCloudProvider t) {
        OffreCloudProvider loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{OffreCloudProvider.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public OffreCloudProvider findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public OffreCloudProvider findOrSave(OffreCloudProvider t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            OffreCloudProvider result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<OffreCloudProvider> findAll() {
        return dao.findAll();
    }

    public List<OffreCloudProvider> findByCriteria(OffreCloudProviderCriteria criteria) {
        List<OffreCloudProvider> content = null;
        if (criteria != null) {
            OffreCloudProviderSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private OffreCloudProviderSpecification constructSpecification(OffreCloudProviderCriteria criteria) {
        OffreCloudProviderSpecification mySpecification =  (OffreCloudProviderSpecification) RefelexivityUtil.constructObjectUsingOneParam(OffreCloudProviderSpecification.class, criteria);
        return mySpecification;
    }

    public List<OffreCloudProvider> findPaginatedByCriteria(OffreCloudProviderCriteria criteria, int page, int pageSize, String order, String sortField) {
        OffreCloudProviderSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OffreCloudProviderCriteria criteria) {
        OffreCloudProviderSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<OffreCloudProvider> findByCloudProviderId(Long id){
        return dao.findByCloudProviderId(id);
    }
    public int deleteByCloudProviderId(Long id){
        return dao.deleteByCloudProviderId(id);
    }
    public long countByCloudProviderCode(String code){
        return dao.countByCloudProviderCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OffreCloudProvider> delete(List<OffreCloudProvider> list) {
		List<OffreCloudProvider> result = new ArrayList();
        if (list != null) {
            for (OffreCloudProvider t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OffreCloudProvider create(OffreCloudProvider t) {
        OffreCloudProvider loaded = findByReferenceEntity(t);
        OffreCloudProvider saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public OffreCloudProvider findWithAssociatedLists(Long id){
        OffreCloudProvider result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OffreCloudProvider> update(List<OffreCloudProvider> ts, boolean createIfNotExist) {
        List<OffreCloudProvider> result = new ArrayList<>();
        if (ts != null) {
            for (OffreCloudProvider t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    OffreCloudProvider loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, OffreCloudProvider t, OffreCloudProvider loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public OffreCloudProvider findByReferenceEntity(OffreCloudProvider t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(OffreCloudProvider t){
        if( t != null) {
            t.setCloudProvider(cloudProviderService.findOrSave(t.getCloudProvider()));
        }
    }



    public List<OffreCloudProvider> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<OffreCloudProvider>> getToBeSavedAndToBeDeleted(List<OffreCloudProvider> oldList, List<OffreCloudProvider> newList) {
        List<List<OffreCloudProvider>> result = new ArrayList<>();
        List<OffreCloudProvider> resultDelete = new ArrayList<>();
        List<OffreCloudProvider> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<OffreCloudProvider> oldList, List<OffreCloudProvider> newList, List<OffreCloudProvider> resultUpdateOrSave, List<OffreCloudProvider> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                OffreCloudProvider myOld = oldList.get(i);
                OffreCloudProvider t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                OffreCloudProvider myNew = newList.get(i);
                OffreCloudProvider t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CloudProviderCollaboratorService cloudProviderService ;

    public OffreCloudProviderCollaboratorServiceImpl(OffreCloudProviderDao dao) {
        this.dao = dao;
    }

    private OffreCloudProviderDao dao;
}
