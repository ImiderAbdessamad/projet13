package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoInfoCriteria;
import ma.zs.zyn.dao.facade.core.project.RemoteRepoInfoDao;
import ma.zs.zyn.dao.specification.core.project.RemoteRepoInfoSpecification;
import ma.zs.zyn.service.facade.admin.collaborator.CollaboratorAdminService;
import ma.zs.zyn.service.facade.admin.project.RemoteRepoInfoAdminService;
import ma.zs.zyn.service.facade.admin.project.RemoteRepoTypeAdminService;
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
public class RemoteRepoInfoAdminServiceImpl implements RemoteRepoInfoAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RemoteRepoInfo update(RemoteRepoInfo t) {
        RemoteRepoInfo loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RemoteRepoInfo.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RemoteRepoInfo findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RemoteRepoInfo findOrSave(RemoteRepoInfo t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RemoteRepoInfo result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RemoteRepoInfo> findAll() {
        return dao.findAll();
    }

    public List<RemoteRepoInfo> findByCriteria(RemoteRepoInfoCriteria criteria) {
        List<RemoteRepoInfo> content = null;
        if (criteria != null) {
            RemoteRepoInfoSpecification mySpecification = constructSpecification(criteria);
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


    private RemoteRepoInfoSpecification constructSpecification(RemoteRepoInfoCriteria criteria) {
        RemoteRepoInfoSpecification mySpecification =  (RemoteRepoInfoSpecification) RefelexivityUtil.constructObjectUsingOneParam(RemoteRepoInfoSpecification.class, criteria);
        return mySpecification;
    }

    public List<RemoteRepoInfo> findPaginatedByCriteria(RemoteRepoInfoCriteria criteria, int page, int pageSize, String order, String sortField) {
        RemoteRepoInfoSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RemoteRepoInfoCriteria criteria) {
        RemoteRepoInfoSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<RemoteRepoInfo> findByRemoteRepoTypeCode(String code){
        return dao.findByRemoteRepoTypeCode(code);
    }
    public int deleteByRemoteRepoTypeCode(String code){
        return dao.deleteByRemoteRepoTypeCode(code);
    }
    public long countByRemoteRepoTypeCode(String code){
        return dao.countByRemoteRepoTypeCode(code);
    }
    public List<RemoteRepoInfo> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
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
    public List<RemoteRepoInfo> delete(List<RemoteRepoInfo> list) {
		List<RemoteRepoInfo> result = new ArrayList();
        if (list != null) {
            for (RemoteRepoInfo t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RemoteRepoInfo create(RemoteRepoInfo t) {
        RemoteRepoInfo loaded = findByReferenceEntity(t);
        RemoteRepoInfo saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RemoteRepoInfo findWithAssociatedLists(Long id){
        RemoteRepoInfo result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RemoteRepoInfo> update(List<RemoteRepoInfo> ts, boolean createIfNotExist) {
        List<RemoteRepoInfo> result = new ArrayList<>();
        if (ts != null) {
            for (RemoteRepoInfo t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RemoteRepoInfo loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RemoteRepoInfo t, RemoteRepoInfo loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RemoteRepoInfo findByReferenceEntity(RemoteRepoInfo t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RemoteRepoInfo t){
        if( t != null) {
            t.setRemoteRepoType(remoteRepoTypeService.findOrSave(t.getRemoteRepoType()));
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
        }
    }



    public List<RemoteRepoInfo> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RemoteRepoInfo>> getToBeSavedAndToBeDeleted(List<RemoteRepoInfo> oldList, List<RemoteRepoInfo> newList) {
        List<List<RemoteRepoInfo>> result = new ArrayList<>();
        List<RemoteRepoInfo> resultDelete = new ArrayList<>();
        List<RemoteRepoInfo> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RemoteRepoInfo> oldList, List<RemoteRepoInfo> newList, List<RemoteRepoInfo> resultUpdateOrSave, List<RemoteRepoInfo> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RemoteRepoInfo myOld = oldList.get(i);
                RemoteRepoInfo t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RemoteRepoInfo myNew = newList.get(i);
                RemoteRepoInfo t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CollaboratorAdminService collaboratorService ;
    @Autowired
    private RemoteRepoTypeAdminService remoteRepoTypeService ;

    public RemoteRepoInfoAdminServiceImpl(RemoteRepoInfoDao dao) {
        this.dao = dao;
    }

    private RemoteRepoInfoDao dao;
}
