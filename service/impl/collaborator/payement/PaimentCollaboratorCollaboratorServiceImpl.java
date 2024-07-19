package ma.zs.zyn.service.impl.collaborator.payement;


import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.payement.PaimentCollaboratorDao;
import ma.zs.zyn.dao.specification.core.payement.PaimentCollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.CityCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.collaborator.CountryCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorStateCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.payement.*;
import ma.zs.zyn.service.facade.influenceur.coupon.PaimentCouponInfluenceurService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static ma.zs.zyn.service.util.PaymentStateConstant.*;
import static ma.zs.zyn.zynerator.util.ListUtil.isEmpty;
import static ma.zs.zyn.zynerator.util.ListUtil.isNotEmpty;

@Service
public class PaimentCollaboratorCollaboratorServiceImpl implements PaimentCollaboratorCollaboratorService {


    @Autowired
    private PaimentCouponInfluenceurService paimentCouponInfluenceurService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator create(PaimentCollaborator t) {
        return payByCardOrTransfer(t, STRIPE, OK);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator transfer(PaimentCollaborator t) {
        return payByCardOrTransfer(t, TRANSFER, PENDING);
    }

    public PaimentCollaborator payByCardOrTransfer(PaimentCollaborator t, String paiementTypeCode, String paiementStateCode) {
        Collaborator collaborator = collaboratorService.getCurrentUser();
        if (collaborator == null) {
            return null;
        }
        City city = cityService.findOrSave(t.getCity());
        collaborator.setCity(city);
        collaborator.setCountry(t.getCountry());
        collaborator.setPostal(t.getPostal());
        collaboratorService.update(collaborator);
        InscriptionCollaborator loadedInscriptionCollaborator = inscriptionCollaboratorService.findByCollaboratorId(collaborator.getId());
        if (loadedInscriptionCollaborator != null && loadedInscriptionCollaborator.getPackaging() != null) {
            loadedInscriptionCollaborator.setPackaging(t.getPackaging());
            initInscription(loadedInscriptionCollaborator);
            initInscriptionState(loadedInscriptionCollaborator, paiementStateCode);
            loadedInscriptionCollaborator = inscriptionCollaboratorService.update(loadedInscriptionCollaborator);
            //log.info("update : payement avec ssuccess : ");
        } else if (loadedInscriptionCollaborator == null) {
            InscriptionCollaborator constrcutedInscription = constructInscription(t);
            initInscriptionState(constrcutedInscription, paiementStateCode);
            loadedInscriptionCollaborator = inscriptionCollaboratorService.create(constrcutedInscription);
            //log.info("save : payement avec ssuccess : ");
        }
        t.setCollaborator(collaborator);
        init(t, city, loadedInscriptionCollaborator, paiementTypeCode, paiementStateCode);
        paimentCouponInfluenceurService.saveOrUpdatePaiment(t);
        return dao.save(t);
    }

    private void initInscriptionState(InscriptionCollaborator inscriptionCollaborator, String paiementStateCode) {
        if (PENDING.equals(paiementStateCode)) {
            inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorStateCollaboratorService.findByCode(PENDING));
        } else {
            inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorStateCollaboratorService.findByCode(OK));
        }
    }

    private void init(PaimentCollaborator t, City city, InscriptionCollaborator inscriptionCollaborator, String paiementTypeCode, String paiementStateCode) {
        t.setInscriptionCollaborator(inscriptionCollaborator);
        t.setPaimentCollaboratorType(paimentCollaboratorTypeService.findByCode(paiementTypeCode));
        t.setPaimentCollaboratorState(paimentCollaboratorStateService.findByCode(paiementStateCode));
        t.setStartDate(LocalDateTime.now());
        t.setPaiementDate(LocalDateTime.now());
        t.setEndDate(LocalDateTime.now().plus(30L, ChronoUnit.DAYS));
        t.setCity(city);
    }

    private InscriptionCollaborator constructInscription(PaimentCollaborator t) {
        InscriptionCollaborator inscriptionCollaborator = new InscriptionCollaborator();
        inscriptionCollaborator.setPackaging(t.getPackaging());
        inscriptionCollaborator.setCollaborator(t.getCollaborator());
        return initInscription(inscriptionCollaborator);
    }

    private InscriptionCollaborator initInscription(InscriptionCollaborator inscriptionCollaborator) {
        inscriptionCollaborator.setConsumedAttribut(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedEntity(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedProjet(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedTokenInput(BigDecimal.ZERO);
        inscriptionCollaborator.setConsumedTokenOutput(BigDecimal.ZERO);

        return inscriptionCollaborator;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator update(PaimentCollaborator t) {
        PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaborator findOrSave(PaimentCollaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaimentCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCollaborator> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria) {
        List<PaimentCollaborator> content = null;
        if (criteria != null) {
            PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCollaboratorSpecification constructSpecification(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification = (PaimentCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PaimentCollaborator> findByCountryId(Long id) {
        return dao.findByCountryId(id);
    }

    public int deleteByCountryId(Long id) {
        return dao.deleteByCountryId(id);
    }

    public long countByCountryCode(String code) {
        return dao.countByCountryCode(code);
    }

    public List<PaimentCollaborator> findByCityId(Long id) {
        return dao.findByCityId(id);
    }

    public int deleteByCityId(Long id) {
        return dao.deleteByCityId(id);
    }

    public long countByCityCode(String code) {
        return dao.countByCityCode(code);
    }

    public List<PaimentCollaborator> findByCollaboratorId(Long id) {
        return dao.findByCollaboratorId(id);
    }

    public int deleteByCollaboratorId(Long id) {
        return dao.deleteByCollaboratorId(id);
    }

    public long countByCollaboratorId(Long id) {
        return dao.countByCollaboratorId(id);
    }

    public List<PaimentCollaborator> findByPackagingId(Long id) {
        return dao.findByPackagingId(id);
    }

    public int deleteByPackagingId(Long id) {
        return dao.deleteByPackagingId(id);
    }

    public long countByPackagingCode(String code) {
        return dao.countByPackagingCode(code);
    }

    public List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code) {
        return dao.findByPaimentCollaboratorStateCode(code);
    }

    public int deleteByPaimentCollaboratorStateCode(String code) {
        return dao.deleteByPaimentCollaboratorStateCode(code);
    }

    public long countByPaimentCollaboratorStateCode(String code) {
        return dao.countByPaimentCollaboratorStateCode(code);
    }

    public List<PaimentCollaborator> findByPaimentCollaboratorTypeCode(String code) {
        return dao.findByPaimentCollaboratorTypeCode(code);
    }

    public int deleteByPaimentCollaboratorTypeCode(String code) {
        return dao.deleteByPaimentCollaboratorTypeCode(code);
    }

    public long countByPaimentCollaboratorTypeCode(String code) {
        return dao.countByPaimentCollaboratorTypeCode(code);
    }

    public List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id) {
        return dao.findByInscriptionCollaboratorId(id);
    }

    public int deleteByInscriptionCollaboratorId(Long id) {
        return dao.deleteByInscriptionCollaboratorId(id);
    }

    public long countByInscriptionCollaboratorId(Long id) {
        return dao.countByInscriptionCollaboratorId(id);
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
    public List<PaimentCollaborator> delete(List<PaimentCollaborator> list) {
        List<PaimentCollaborator> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaborator t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                }
            }
        }
        return result;
    }


    public PaimentCollaborator findWithAssociatedLists(Long id) {
        PaimentCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> update(List<PaimentCollaborator> ts, boolean createIfNotExist) {
        List<PaimentCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCollaborator t, PaimentCollaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public PaimentCollaborator findByReferenceEntity(PaimentCollaborator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }

    public void findOrSaveAssociatedObject(PaimentCollaborator t) {
        if (t != null) {
            t.setCountry(countryService.findOrSave(t.getCountry()));
            t.setCity(cityService.findOrSave(t.getCity()));
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setPackaging(packagingService.findOrSave(t.getPackaging()));
            t.setPaimentCollaboratorState(paimentCollaboratorStateService.findOrSave(t.getPaimentCollaboratorState()));
            t.setPaimentCollaboratorType(paimentCollaboratorTypeService.findOrSave(t.getPaimentCollaboratorType()));
            t.setInscriptionCollaborator(inscriptionCollaboratorService.findOrSave(t.getInscriptionCollaborator()));
        }
    }


    public List<PaimentCollaborator> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList) {
        List<List<PaimentCollaborator>> result = new ArrayList<>();
        List<PaimentCollaborator> resultDelete = new ArrayList<>();
        List<PaimentCollaborator> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList, List<PaimentCollaborator> resultUpdateOrSave, List<PaimentCollaborator> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            PaimentCollaborator myOld = oldList.get(i);
            PaimentCollaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            PaimentCollaborator myNew = newList.get(i);
            PaimentCollaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CollaboratorCollaboratorService collaboratorService;

    @Autowired
    private InscriptionCollaboratorStateCollaboratorService inscriptionCollaboratorStateCollaboratorService;

    @Autowired
    private PaimentCollaboratorStateCollaboratorService paimentCollaboratorStateService;
    @Autowired
    private PaimentCollaboratorTypeCollaboratorService paimentCollaboratorTypeService;
    @Autowired
    private PackagingCollaboratorService packagingService;
    @Autowired
    private CountryCollaboratorService countryService;
    @Autowired
    private CityCollaboratorService cityService;
    @Autowired
    private InscriptionCollaboratorCollaboratorService inscriptionCollaboratorService;

    public PaimentCollaboratorCollaboratorServiceImpl(PaimentCollaboratorDao dao) {
        this.dao = dao;
    }

    private PaimentCollaboratorDao dao;
}
