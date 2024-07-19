package ma.zs.zyn.service.impl.admin.payement;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorState;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorStateCriteria;
import ma.zs.zyn.dao.facade.core.payement.PaimentCollaboratorStateDao;
import ma.zs.zyn.dao.specification.core.payement.PaimentCollaboratorStateSpecification;
import ma.zs.zyn.service.facade.admin.payement.PaimentCollaboratorStateAdminService;

import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaimentCollaboratorStateAdminServiceImpl implements PaimentCollaboratorStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorState update(PaimentCollaboratorState t) {
        PaimentCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaboratorState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaboratorState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaboratorState findOrSave(PaimentCollaboratorState t) {
        if (t != null) {
            PaimentCollaboratorState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCollaboratorState> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaboratorState> findByCriteria(PaimentCollaboratorStateCriteria criteria) {
        List<PaimentCollaboratorState> content = null;
        if (criteria != null) {
            PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCollaboratorStateSpecification constructSpecification(PaimentCollaboratorStateCriteria criteria) {
        PaimentCollaboratorStateSpecification mySpecification =  (PaimentCollaboratorStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaboratorState> findPaginatedByCriteria(PaimentCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorStateCriteria criteria) {
        PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<PaimentCollaboratorState> delete(List<PaimentCollaboratorState> list) {
		List<PaimentCollaboratorState> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaboratorState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Override
    public PaimentCollaboratorState findByCode(String code) {
        return dao.findByCode(code);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorState create(PaimentCollaboratorState t) {
        PaimentCollaboratorState loaded = findByReferenceEntity(t);
        PaimentCollaboratorState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaimentCollaboratorState findWithAssociatedLists(Long id){
        PaimentCollaboratorState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaboratorState> update(List<PaimentCollaboratorState> ts, boolean createIfNotExist) {
        List<PaimentCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaboratorState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCollaboratorState t, PaimentCollaboratorState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaimentCollaboratorState findByReferenceEntity(PaimentCollaboratorState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<PaimentCollaboratorState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaboratorState>> getToBeSavedAndToBeDeleted(List<PaimentCollaboratorState> oldList, List<PaimentCollaboratorState> newList) {
        List<List<PaimentCollaboratorState>> result = new ArrayList<>();
        List<PaimentCollaboratorState> resultDelete = new ArrayList<>();
        List<PaimentCollaboratorState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaimentCollaboratorState> oldList, List<PaimentCollaboratorState> newList, List<PaimentCollaboratorState> resultUpdateOrSave, List<PaimentCollaboratorState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaimentCollaboratorState myOld = oldList.get(i);
                PaimentCollaboratorState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaimentCollaboratorState myNew = newList.get(i);
                PaimentCollaboratorState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public PaimentCollaboratorStateAdminServiceImpl(PaimentCollaboratorStateDao dao) {
        this.dao = dao;
    }

    private PaimentCollaboratorStateDao dao;
}
