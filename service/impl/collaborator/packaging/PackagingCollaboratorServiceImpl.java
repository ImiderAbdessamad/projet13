package ma.zs.zyn.service.impl.collaborator.packaging;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.dao.facade.core.packaging.PackagingDao;
import ma.zs.zyn.dao.specification.core.packaging.PackagingSpecification;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class PackagingCollaboratorServiceImpl implements PackagingCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Packaging update(Packaging t) {
        Packaging loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Packaging.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Packaging findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Packaging findOrSave(Packaging t) {
        if (t != null) {
            Packaging result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Packaging> findAll() {
        return dao.findAll();
    }

    public List<Packaging> findByCriteria(PackagingCriteria criteria) {
        List<Packaging> content = null;
        if (criteria != null) {
            PackagingSpecification mySpecification = constructSpecification(criteria);
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


    private PackagingSpecification constructSpecification(PackagingCriteria criteria) {
        PackagingSpecification mySpecification =  (PackagingSpecification) RefelexivityUtil.constructObjectUsingOneParam(PackagingSpecification.class, criteria);
        return mySpecification;
    }

    public List<Packaging> findPaginatedByCriteria(PackagingCriteria criteria, int page, int pageSize, String order, String sortField) {
        PackagingSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PackagingCriteria criteria) {
        PackagingSpecification mySpecification = constructSpecification(criteria);
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
    public List<Packaging> delete(List<Packaging> list) {
		List<Packaging> result = new ArrayList();
        if (list != null) {
            for (Packaging t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Packaging create(Packaging t) {
        Packaging loaded = findByReferenceEntity(t);
        Packaging saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Packaging findWithAssociatedLists(Long id){
        Packaging result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Packaging> update(List<Packaging> ts, boolean createIfNotExist) {
        List<Packaging> result = new ArrayList<>();
        if (ts != null) {
            for (Packaging t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Packaging loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Packaging t, Packaging loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Packaging findByReferenceEntity(Packaging t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Packaging> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Packaging>> getToBeSavedAndToBeDeleted(List<Packaging> oldList, List<Packaging> newList) {
        List<List<Packaging>> result = new ArrayList<>();
        List<Packaging> resultDelete = new ArrayList<>();
        List<Packaging> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Packaging> oldList, List<Packaging> newList, List<Packaging> resultUpdateOrSave, List<Packaging> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Packaging myOld = oldList.get(i);
                Packaging t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Packaging myNew = newList.get(i);
                Packaging t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public PackagingCollaboratorServiceImpl(PackagingDao dao) {
        this.dao = dao;
    }

    private PackagingDao dao;
}
