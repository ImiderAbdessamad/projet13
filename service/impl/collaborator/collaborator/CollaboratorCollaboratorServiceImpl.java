package ma.zs.zyn.service.impl.collaborator.collaborator;


import ma.zs.zyn.service.facade.collaborator.collaborator.CityCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.collaborator.CountryCollaboratorService;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.collaborator.CollaboratorDao;
import ma.zs.zyn.dao.specification.core.collaborator.CollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService;

import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.security.core.context.SecurityContextHolder;
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


import ma.zs.zyn.zynerator.security.service.facade.UserService;
import ma.zs.zyn.zynerator.security.service.facade.RoleService;
import ma.zs.zyn.zynerator.security.service.facade.RoleUserService;
import ma.zs.zyn.zynerator.security.service.facade.ModelPermissionUserService;

@Service
public class CollaboratorCollaboratorServiceImpl implements CollaboratorCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Collaborator update(Collaborator t) {
        Collaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Collaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Collaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Collaborator findOrSave(Collaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Collaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Collaborator> findAll() {
        return dao.findAll();
    }

    public List<Collaborator> findByCriteria(CollaboratorCriteria criteria) {
        List<Collaborator> content = null;
        if (criteria != null) {
            CollaboratorSpecification mySpecification = constructSpecification(criteria);
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


    private CollaboratorSpecification constructSpecification(CollaboratorCriteria criteria) {
        CollaboratorSpecification mySpecification =  (CollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(CollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<Collaborator> findPaginatedByCriteria(CollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        CollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CollaboratorCriteria criteria) {
        CollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Collaborator> findByCountryId(Long id){
        return dao.findByCountryId(id);
    }
    public int deleteByCountryId(Long id){
        return dao.deleteByCountryId(id);
    }
    public long countByCountryCode(String code){
        return dao.countByCountryCode(code);
    }
    public List<Collaborator> findByCityId(Long id){
        return dao.findByCityId(id);
    }
    public int deleteByCityId(Long id){
        return dao.deleteByCityId(id);
    }
    public long countByCityCode(String code){
        return dao.countByCityCode(code);
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
    public List<Collaborator> delete(List<Collaborator> list) {
		List<Collaborator> result = new ArrayList();
        if (list != null) {
            for (Collaborator t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }


    public Collaborator findWithAssociatedLists(Long id){
        Collaborator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Collaborator> update(List<Collaborator> ts, boolean createIfNotExist) {
        List<Collaborator> result = new ArrayList<>();
        if (ts != null) {
            for (Collaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Collaborator loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Collaborator t, Collaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Collaborator findByReferenceEntity(Collaborator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Collaborator t){
        if( t != null) {
            t.setCountry(countryService.findOrSave(t.getCountry()));
            t.setCity(cityService.findOrSave(t.getCity()));
        }
    }



    public List<Collaborator> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Collaborator>> getToBeSavedAndToBeDeleted(List<Collaborator> oldList, List<Collaborator> newList) {
        List<List<Collaborator>> result = new ArrayList<>();
        List<Collaborator> resultDelete = new ArrayList<>();
        List<Collaborator> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Collaborator> oldList, List<Collaborator> newList, List<Collaborator> resultUpdateOrSave, List<Collaborator> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Collaborator myOld = oldList.get(i);
                Collaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Collaborator myNew = newList.get(i);
                Collaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    @Override
    public Collaborator create(Collaborator t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setCity(null);
        t.setCountry(null);
        Collaborator mySaved = dao.save(t);
        inscriptionCollaboratorService.initInscriptionAsFree(mySaved);
        return mySaved;
     }

    public Collaborator findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }



    @Override
    public Collaborator getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof Collaborator) {
            return (Collaborator) principal;
        } else if (principal != null && principal instanceof String) {
            return findByUsername(principal.toString());
        } else {
            return null;
        }
    }

    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;

    @Autowired
    private CountryCollaboratorService countryService ;
    @Autowired
    private CityCollaboratorService cityService ;
    @Autowired
    private InscriptionCollaboratorCollaboratorService inscriptionCollaboratorService ;

    public CollaboratorCollaboratorServiceImpl(CollaboratorDao dao) {
        this.dao = dao;
    }

    private CollaboratorDao dao;
}
