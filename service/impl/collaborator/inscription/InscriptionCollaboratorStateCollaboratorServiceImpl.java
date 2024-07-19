package ma.zs.zyn.service.impl.collaborator.inscription;


import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.dao.facade.core.payement.InscriptionCollaboratorStateDao;
import ma.zs.zyn.dao.specification.core.payement.InscriptionCollaboratorStateSpecification;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorStateCollaboratorService;
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
public class InscriptionCollaboratorStateCollaboratorServiceImpl implements InscriptionCollaboratorStateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorState update(InscriptionCollaboratorState t) {
        InscriptionCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionCollaboratorState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionCollaboratorState findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    public InscriptionCollaboratorState findByCode(String code) {
        return dao.findByCode(code);
    }


    public InscriptionCollaboratorState findOrSave(InscriptionCollaboratorState t) {
        if (t != null) {
            InscriptionCollaboratorState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<InscriptionCollaboratorState> findAll() {
        return dao.findAll();
    }

    public List<InscriptionCollaboratorState> findByCriteria(InscriptionCollaboratorStateCriteria criteria) {
        List<InscriptionCollaboratorState> content = null;
        if (criteria != null) {
            InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionCollaboratorStateSpecification constructSpecification(InscriptionCollaboratorStateCriteria criteria) {
        InscriptionCollaboratorStateSpecification mySpecification = (InscriptionCollaboratorStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionCollaboratorStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionCollaboratorState> findPaginatedByCriteria(InscriptionCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCollaboratorStateCriteria criteria) {
        InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<InscriptionCollaboratorState> delete(List<InscriptionCollaboratorState> list) {
        List<InscriptionCollaboratorState> result = new ArrayList();
        if (list != null) {
            for (InscriptionCollaboratorState t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorState create(InscriptionCollaboratorState t) {
        InscriptionCollaboratorState loaded = findByReferenceEntity(t);
        InscriptionCollaboratorState saved;
        if (loaded == null) {
            saved = dao.save(t);
        } else {
            saved = null;
        }
        return saved;
    }

    public InscriptionCollaboratorState findWithAssociatedLists(Long id) {
        InscriptionCollaboratorState result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorState> update(List<InscriptionCollaboratorState> ts, boolean createIfNotExist) {
        List<InscriptionCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaboratorState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, InscriptionCollaboratorState t, InscriptionCollaboratorState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public InscriptionCollaboratorState findByReferenceEntity(InscriptionCollaboratorState t) {
        return t == null ? null : dao.findByCode(t.getCode());
    }


    public List<InscriptionCollaboratorState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<InscriptionCollaboratorState>> getToBeSavedAndToBeDeleted(List<InscriptionCollaboratorState> oldList, List<InscriptionCollaboratorState> newList) {
        List<List<InscriptionCollaboratorState>> result = new ArrayList<>();
        List<InscriptionCollaboratorState> resultDelete = new ArrayList<>();
        List<InscriptionCollaboratorState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<InscriptionCollaboratorState> oldList, List<InscriptionCollaboratorState> newList, List<InscriptionCollaboratorState> resultUpdateOrSave, List<InscriptionCollaboratorState> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            InscriptionCollaboratorState myOld = oldList.get(i);
            InscriptionCollaboratorState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            InscriptionCollaboratorState myNew = newList.get(i);
            InscriptionCollaboratorState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew); // create
            }
        }
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    public InscriptionCollaboratorStateCollaboratorServiceImpl(InscriptionCollaboratorStateDao dao) {
        this.dao = dao;
    }

    private InscriptionCollaboratorStateDao dao;
}
