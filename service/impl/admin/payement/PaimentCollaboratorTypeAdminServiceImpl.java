package ma.zs.zyn.service.impl.admin.payement;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorTypeCriteria;
import ma.zs.zyn.dao.facade.core.payement.PaimentCollaboratorTypeDao;
import ma.zs.zyn.dao.specification.core.payement.PaimentCollaboratorTypeSpecification;
import ma.zs.zyn.service.facade.admin.payement.PaimentCollaboratorTypeAdminService;

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
public class PaimentCollaboratorTypeAdminServiceImpl implements PaimentCollaboratorTypeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorType update(PaimentCollaboratorType t) {
        PaimentCollaboratorType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaboratorType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaboratorType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaboratorType findOrSave(PaimentCollaboratorType t) {
        if (t != null) {
            PaimentCollaboratorType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCollaboratorType> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaboratorType> findByCriteria(PaimentCollaboratorTypeCriteria criteria) {
        List<PaimentCollaboratorType> content = null;
        if (criteria != null) {
            PaimentCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCollaboratorTypeSpecification constructSpecification(PaimentCollaboratorTypeCriteria criteria) {
        PaimentCollaboratorTypeSpecification mySpecification =  (PaimentCollaboratorTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaboratorType> findPaginatedByCriteria(PaimentCollaboratorTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorTypeCriteria criteria) {
        PaimentCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
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
    public List<PaimentCollaboratorType> delete(List<PaimentCollaboratorType> list) {
		List<PaimentCollaboratorType> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaboratorType t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorType create(PaimentCollaboratorType t) {
        PaimentCollaboratorType loaded = findByReferenceEntity(t);
        PaimentCollaboratorType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaimentCollaboratorType findWithAssociatedLists(Long id){
        PaimentCollaboratorType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaboratorType> update(List<PaimentCollaboratorType> ts, boolean createIfNotExist) {
        List<PaimentCollaboratorType> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaboratorType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaboratorType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCollaboratorType t, PaimentCollaboratorType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaimentCollaboratorType findByReferenceEntity(PaimentCollaboratorType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<PaimentCollaboratorType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaboratorType>> getToBeSavedAndToBeDeleted(List<PaimentCollaboratorType> oldList, List<PaimentCollaboratorType> newList) {
        List<List<PaimentCollaboratorType>> result = new ArrayList<>();
        List<PaimentCollaboratorType> resultDelete = new ArrayList<>();
        List<PaimentCollaboratorType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaimentCollaboratorType> oldList, List<PaimentCollaboratorType> newList, List<PaimentCollaboratorType> resultUpdateOrSave, List<PaimentCollaboratorType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaimentCollaboratorType myOld = oldList.get(i);
                PaimentCollaboratorType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaimentCollaboratorType myNew = newList.get(i);
                PaimentCollaboratorType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public PaimentCollaboratorTypeAdminServiceImpl(PaimentCollaboratorTypeDao dao) {
        this.dao = dao;
    }

    private PaimentCollaboratorTypeDao dao;
}
