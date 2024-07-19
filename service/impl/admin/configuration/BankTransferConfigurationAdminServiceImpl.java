package ma.zs.zyn.service.impl.admin.configuration;


import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.dao.criteria.core.configuration.BankTransferConfigurationCriteria;
import ma.zs.zyn.dao.facade.core.configuration.BankTransferConfigurationDao;
import ma.zs.zyn.dao.specification.core.configuration.BankTransferConfigurationSpecification;
import ma.zs.zyn.service.facade.admin.configuration.BankTransferConfigurationAdminService;
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
public class BankTransferConfigurationAdminServiceImpl implements BankTransferConfigurationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BankTransferConfiguration update(BankTransferConfiguration t) {
        BankTransferConfiguration loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{BankTransferConfiguration.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public BankTransferConfiguration findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public BankTransferConfiguration findOrSave(BankTransferConfiguration t) {
        if (t != null) {
            BankTransferConfiguration result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<BankTransferConfiguration> findAll() {
        return dao.findAll();
    }

    public List<BankTransferConfiguration> findByCriteria(BankTransferConfigurationCriteria criteria) {
        List<BankTransferConfiguration> content = null;
        if (criteria != null) {
            BankTransferConfigurationSpecification mySpecification = constructSpecification(criteria);
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


    private BankTransferConfigurationSpecification constructSpecification(BankTransferConfigurationCriteria criteria) {
        BankTransferConfigurationSpecification mySpecification = (BankTransferConfigurationSpecification) RefelexivityUtil.constructObjectUsingOneParam(BankTransferConfigurationSpecification.class, criteria);
        return mySpecification;
    }

    public List<BankTransferConfiguration> findPaginatedByCriteria(BankTransferConfigurationCriteria criteria, int page, int pageSize, String order, String sortField) {
        BankTransferConfigurationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BankTransferConfigurationCriteria criteria) {
        BankTransferConfigurationSpecification mySpecification = constructSpecification(criteria);
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
    public List<BankTransferConfiguration> delete(List<BankTransferConfiguration> list) {
        List<BankTransferConfiguration> result = new ArrayList();
        if (list != null) {
            for (BankTransferConfiguration t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BankTransferConfiguration create(BankTransferConfiguration t) {
        BankTransferConfiguration loaded = findByReferenceEntity(t);
        BankTransferConfiguration saved;
        if (loaded == null) {
            saved = dao.save(t);
        } else {
            saved = null;
        }
        return saved;
    }

    public BankTransferConfiguration findWithAssociatedLists(Long id) {
        BankTransferConfiguration result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BankTransferConfiguration> update(List<BankTransferConfiguration> ts, boolean createIfNotExist) {
        List<BankTransferConfiguration> result = new ArrayList<>();
        if (ts != null) {
            for (BankTransferConfiguration t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    BankTransferConfiguration loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, BankTransferConfiguration t, BankTransferConfiguration loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public BankTransferConfiguration findByReferenceEntity(BankTransferConfiguration t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }


    public List<BankTransferConfiguration> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<BankTransferConfiguration>> getToBeSavedAndToBeDeleted(List<BankTransferConfiguration> oldList, List<BankTransferConfiguration> newList) {
        List<List<BankTransferConfiguration>> result = new ArrayList<>();
        List<BankTransferConfiguration> resultDelete = new ArrayList<>();
        List<BankTransferConfiguration> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<BankTransferConfiguration> oldList, List<BankTransferConfiguration> newList, List<BankTransferConfiguration> resultUpdateOrSave, List<BankTransferConfiguration> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            BankTransferConfiguration myOld = oldList.get(i);
            BankTransferConfiguration t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            BankTransferConfiguration myNew = newList.get(i);
            BankTransferConfiguration t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew); // create
            }
        }
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    public BankTransferConfigurationAdminServiceImpl(BankTransferConfigurationDao dao) {
        this.dao = dao;
    }

    private BankTransferConfigurationDao dao;
}
