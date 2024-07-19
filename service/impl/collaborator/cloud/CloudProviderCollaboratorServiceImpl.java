package ma.zs.zyn.service.impl.collaborator.cloud;


import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.CloudProviderCriteria;
import ma.zs.zyn.dao.facade.core.cloud.CloudProviderDao;
import ma.zs.zyn.dao.specification.core.cloud.CloudProviderSpecification;
import ma.zs.zyn.service.facade.collaborator.cloud.CloudProviderCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
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
public class CloudProviderCollaboratorServiceImpl implements CloudProviderCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CloudProvider update(CloudProvider t) {
        CloudProvider loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CloudProvider.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CloudProvider findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CloudProvider findOrSave(CloudProvider t) {
        if (t != null) {
            CloudProvider result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CloudProvider> findAll() {
        return dao.findAll();
    }

    public List<CloudProvider> findByCriteria(CloudProviderCriteria criteria) {
        List<CloudProvider> content = null;
        if (criteria != null) {
            CloudProviderSpecification mySpecification = constructSpecification(criteria);
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


    private CloudProviderSpecification constructSpecification(CloudProviderCriteria criteria) {
        CloudProviderSpecification mySpecification =  (CloudProviderSpecification) RefelexivityUtil.constructObjectUsingOneParam(CloudProviderSpecification.class, criteria);
        return mySpecification;
    }

    public List<CloudProvider> findPaginatedByCriteria(CloudProviderCriteria criteria, int page, int pageSize, String order, String sortField) {
        CloudProviderSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CloudProviderCriteria criteria) {
        CloudProviderSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<CloudProvider> delete(List<CloudProvider> list) {
		List<CloudProvider> result = new ArrayList();
        if (list != null) {
            for (CloudProvider t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CloudProvider create(CloudProvider t) {
        CloudProvider loaded = findByReferenceEntity(t);
        CloudProvider saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CloudProvider findWithAssociatedLists(Long id){
        CloudProvider result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CloudProvider> update(List<CloudProvider> ts, boolean createIfNotExist) {
        List<CloudProvider> result = new ArrayList<>();
        if (ts != null) {
            for (CloudProvider t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CloudProvider loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CloudProvider t, CloudProvider loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CloudProvider findByReferenceEntity(CloudProvider t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CloudProvider> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CloudProvider>> getToBeSavedAndToBeDeleted(List<CloudProvider> oldList, List<CloudProvider> newList) {
        List<List<CloudProvider>> result = new ArrayList<>();
        List<CloudProvider> resultDelete = new ArrayList<>();
        List<CloudProvider> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CloudProvider> oldList, List<CloudProvider> newList, List<CloudProvider> resultUpdateOrSave, List<CloudProvider> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CloudProvider myOld = oldList.get(i);
                CloudProvider t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CloudProvider myNew = newList.get(i);
                CloudProvider t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CloudProviderCollaboratorServiceImpl(CloudProviderDao dao) {
        this.dao = dao;
    }

    private CloudProviderDao dao;
}
