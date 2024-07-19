package ma.zs.zyn.service.impl.collaborator.inscription;


import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.payement.InscriptionCollaboratorDao;
import ma.zs.zyn.dao.specification.core.payement.InscriptionCollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorStateCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ma.zs.zyn.service.util.PaymentStateConstant.*;
import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;

@Service
public class InscriptionCollaboratorCollaboratorServiceImpl implements InscriptionCollaboratorCollaboratorService {


    @Override
    public boolean isFreeInscription(Packaging packaging) {
        Packaging loaded = packagingService.findByReferenceEntity(packaging);
        return FREE.equals(loaded.getCode());
    }

    @Override
    public InscriptionCollaborator initInscriptionAsFree(Collaborator collaborator) {
        if (collaborator == null) {
            return null;
        }
        InscriptionCollaborator inscriptionCollaborator = new InscriptionCollaborator();
        inscriptionCollaborator.setCollaborator(collaborator);
        Packaging packaging = packagingService.findByReferenceEntity(new Packaging(FREE));
        if (packaging == null) {
            return null;
        }
        inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorStateCollaboratorService.findByCode(OK));
        inscriptionCollaborator.setPackaging(packaging);
        inscriptionCollaborator.setConsumedAttribut(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedEntity(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedProjet(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedTokenInput(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedTokenOutput(BigDecimal.ZERO);
        inscriptionCollaborator.setStartDate(LocalDateTime.now());
        inscriptionCollaborator.setEndDate(null);
        return dao.save(inscriptionCollaborator);
    }

    @Override
    public int resetInscription(Collaborator collaborator) {
        InscriptionCollaborator inscriptionCollaborator = dao.findByCollaboratorId(collaborator.getId());
        LocalDateTime now = LocalDateTime.now();
        if (inscriptionCollaborator == null || inscriptionCollaborator.getPackaging() == null)
            return -1;
        else {
            if (inscriptionCollaborator.getEndDate() != null
                    && FREE.equals(inscriptionCollaborator.getPackaging().getCode())
                    && inscriptionCollaborator.getEndDate().plusDays(1).isBefore(now)) {
                inscriptionCollaborator.setConsumedEntity(BigDecimal.ZERO);
                inscriptionCollaborator.setConsumedProjet(BigDecimal.ZERO);
                inscriptionCollaborator.setConsumedAttribut(BigDecimal.ZERO);
                inscriptionCollaborator.setConsumedTokenInput(BigDecimal.ZERO);
                inscriptionCollaborator.setConsumedTokenOutput(BigDecimal.ZERO);
                inscriptionCollaborator.setEndDate(now);
            } else if (inscriptionCollaborator.getEndDate() != null &&
                    inscriptionCollaborator.getEndDate().plusDays(1).isAfter(now) &&
                    !FREE.equals(inscriptionCollaborator.getPackaging().getCode()) &&
                    inscriptionCollaborator.getInscriptionCollaboratorState() != null &&
                    !PAYMENT_EXPIRED.equals(inscriptionCollaborator.getInscriptionCollaboratorState().getCode())) {
                inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorStateCollaboratorService.findByCode(PAYMENT_EXPIRED));
            }
            dao.save(inscriptionCollaborator);
            return 1;
        }
    }


    @Override
    public boolean incrementConsumedTokenInput(InscriptionCollaborator inscriptionCollaborator, int increment) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            inscriptionCollaborator.setConsumedTokenInput(inscriptionCollaborator.getConsumedTokenInput().add(BigDecimal.valueOf(increment)));
            update(inscriptionCollaborator);
        }
        return valide;
    }

    @Override
    public boolean incrementConsumedTokenOutput(InscriptionCollaborator inscriptionCollaborator, int countTokenOutput) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            inscriptionCollaborator.setConsumedTokenOutput(inscriptionCollaborator.getConsumedTokenOutput().add(BigDecimal.valueOf(countTokenOutput)));
            update(inscriptionCollaborator);
        }
        return valide;
    }

    @Override
    public boolean checkTokenInputCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            valide = inscriptionCollaborator.getConsumedTokenInput().compareTo(packaging.getMaxTokenInput()) < 0;
        }
        return valide;
    }

    @Override
    public boolean checkTokenOutputCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            valide = inscriptionCollaborator.getConsumedTokenOutput().compareTo(packaging.getMaxTokenOutput()) < 0;
        }
        return valide;
    }

    @Override
    public boolean checkProjectCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            valide = inscriptionCollaborator.getConsumedProjet().compareTo(packaging.getMaxProjet()) < 0;
        }
        return valide;
    }


    @Override
    public boolean checkEntityCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            valide = inscriptionCollaborator.getConsumedEntity().compareTo(packaging.getMaxEntity()) < 0;
        }
        return valide;
    }


    @Override
    public boolean checkAttributCollaborator(InscriptionCollaborator inscriptionCollaborator, Packaging packaging) {
        boolean valide = false;
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            valide = inscriptionCollaborator.getConsumedAttribut().compareTo(packaging.getMaxAttribut()) < 0;
        }
        return valide;
    }


    @Override
    public void incrementConsumed(InscriptionCollaborator inscriptionCollaborator, BigDecimal incrementEntity, BigDecimal incrementAttribut, BigDecimal incrementProject) {
        if (inscriptionCollaborator != null && inscriptionCollaborator.getPackaging() != null) {
            inscriptionCollaborator.setConsumedEntity(inscriptionCollaborator.getConsumedEntity().add(incrementEntity));
            inscriptionCollaborator.setConsumedAttribut(inscriptionCollaborator.getConsumedAttribut().add(incrementAttribut));
            inscriptionCollaborator.setConsumedProjet(inscriptionCollaborator.getConsumedProjet().add(incrementProject));
            update(inscriptionCollaborator);
        }
    }

    @Override
    public InscriptionCollaborator findByConnectedCollaboratorId() {
        Collaborator currentUser = getCurrentUser();
        if (currentUser != null) {
            return dao.findByCollaboratorUsername(currentUser.getUsername());
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaborator update(InscriptionCollaborator t) {
        InscriptionCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionCollaborator findOrSave(InscriptionCollaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            InscriptionCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<InscriptionCollaborator> findAll() {
        return dao.findAll();
    }

    public List<InscriptionCollaborator> findByCriteria(InscriptionCollaboratorCriteria criteria) {
        List<InscriptionCollaborator> content = null;
        if (criteria != null) {
            InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionCollaboratorSpecification constructSpecification(InscriptionCollaboratorCriteria criteria) {
        InscriptionCollaboratorSpecification mySpecification = (InscriptionCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionCollaborator> findPaginatedByCriteria(InscriptionCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCollaboratorCriteria criteria) {
        InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


    public InscriptionCollaborator findByCollaboratorId(Long id) {
        return dao.findByCollaboratorId(id);
    }

    public int deleteByCollaboratorId(Long id) {
        return dao.deleteByCollaboratorId(id);
    }

    public long countByCollaboratorId(Long id) {
        return dao.countByCollaboratorId(id);
    }

    public List<InscriptionCollaborator> findByPackagingId(Long id) {
        return dao.findByPackagingId(id);
    }

    public int deleteByPackagingId(Long id) {
        return dao.deleteByPackagingId(id);
    }

    public long countByPackagingCode(String code) {
        return dao.countByPackagingCode(code);
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
    public List<InscriptionCollaborator> delete(List<InscriptionCollaborator> list) {
        List<InscriptionCollaborator> result = new ArrayList();
        if (list != null) {
            for (InscriptionCollaborator t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaborator create(InscriptionCollaborator t) {
        InscriptionCollaborator loaded = findByReferenceEntity(t);
        InscriptionCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
        } else {
            saved = null;
        }
        return saved;
    }

    public InscriptionCollaborator findWithAssociatedLists(Long id) {
        InscriptionCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaborator> update(List<InscriptionCollaborator> ts, boolean createIfNotExist) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, InscriptionCollaborator t, InscriptionCollaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public InscriptionCollaborator findByReferenceEntity(InscriptionCollaborator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }

    public void findOrSaveAssociatedObject(InscriptionCollaborator t) {
        if (t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setPackaging(packagingService.findOrSave(t.getPackaging()));
        }
    }


    public List<InscriptionCollaborator> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<InscriptionCollaborator>> getToBeSavedAndToBeDeleted(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList) {
        List<List<InscriptionCollaborator>> result = new ArrayList<>();
        List<InscriptionCollaborator> resultDelete = new ArrayList<>();
        List<InscriptionCollaborator> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList, List<InscriptionCollaborator> resultUpdateOrSave, List<InscriptionCollaborator> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            InscriptionCollaborator myOld = oldList.get(i);
            InscriptionCollaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            InscriptionCollaborator myNew = newList.get(i);
            InscriptionCollaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew); // create
            }
        }
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    public Collaborator getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof Collaborator) {
            return (Collaborator) principal;
        } else if (principal != null && principal instanceof String) {
            return collaboratorService.findByUsername(principal.toString());
        } else {
            return null;
        }
    }


    @Autowired
    private InscriptionCollaboratorStateCollaboratorService inscriptionCollaboratorStateCollaboratorService;
    @Autowired
    private CollaboratorCollaboratorService collaboratorService;
    @Autowired
    private PackagingCollaboratorService packagingService;

    public InscriptionCollaboratorCollaboratorServiceImpl(InscriptionCollaboratorDao dao) {
        this.dao = dao;
    }

    private InscriptionCollaboratorDao dao;
}