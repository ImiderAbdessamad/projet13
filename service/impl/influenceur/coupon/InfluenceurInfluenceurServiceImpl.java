package ma.zs.zyn.service.impl.influenceur.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.dao.criteria.core.coupon.InfluenceurCriteria;
import ma.zs.zyn.dao.facade.core.coupon.InfluenceurDao;
import ma.zs.zyn.dao.specification.core.coupon.InfluenceurSpecification;
import ma.zs.zyn.service.facade.influenceur.coupon.InfluenceurInfluenceurService;
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


import java.time.LocalDateTime;
import ma.zs.zyn.zynerator.security.service.facade.UserService;
import ma.zs.zyn.zynerator.security.service.facade.RoleService;
import ma.zs.zyn.zynerator.security.service.facade.RoleUserService;
import ma.zs.zyn.zynerator.security.bean.Role;
import ma.zs.zyn.zynerator.security.bean.RoleUser;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class InfluenceurInfluenceurServiceImpl implements InfluenceurInfluenceurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Influenceur update(Influenceur t) {
        Influenceur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Influenceur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Influenceur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Influenceur findOrSave(Influenceur t) {
        if (t != null) {
            Influenceur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Influenceur> findAll() {
        return dao.findAll();
    }

    public List<Influenceur> findByCriteria(InfluenceurCriteria criteria) {
        List<Influenceur> content = null;
        if (criteria != null) {
            InfluenceurSpecification mySpecification = constructSpecification(criteria);
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


    private InfluenceurSpecification constructSpecification(InfluenceurCriteria criteria) {
        InfluenceurSpecification mySpecification =  (InfluenceurSpecification) RefelexivityUtil.constructObjectUsingOneParam(InfluenceurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Influenceur> findPaginatedByCriteria(InfluenceurCriteria criteria, int page, int pageSize, String order, String sortField) {
        InfluenceurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InfluenceurCriteria criteria) {
        InfluenceurSpecification mySpecification = constructSpecification(criteria);
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
    public List<Influenceur> delete(List<Influenceur> list) {
		List<Influenceur> result = new ArrayList();
        if (list != null) {
            for (Influenceur t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }


    public Influenceur findWithAssociatedLists(Long id){
        Influenceur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Influenceur> update(List<Influenceur> ts, boolean createIfNotExist) {
        List<Influenceur> result = new ArrayList<>();
        if (ts != null) {
            for (Influenceur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Influenceur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Influenceur t, Influenceur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Influenceur findByReferenceEntity(Influenceur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Influenceur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Influenceur>> getToBeSavedAndToBeDeleted(List<Influenceur> oldList, List<Influenceur> newList) {
        List<List<Influenceur>> result = new ArrayList<>();
        List<Influenceur> resultDelete = new ArrayList<>();
        List<Influenceur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Influenceur> oldList, List<Influenceur> newList, List<Influenceur> resultUpdateOrSave, List<Influenceur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Influenceur myOld = oldList.get(i);
                Influenceur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Influenceur myNew = newList.get(i);
                Influenceur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


//    @Override
//    public Influenceur create(Influenceur t) {
//        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
//        t.setPassword(userService.cryptPassword(t.getPassword()));
//        t.setEnabled(true);
//        t.setAccountNonExpired(true);
//        t.setAccountNonLocked(true);
//        t.setCredentialsNonExpired(true);
//        t.setPasswordChanged(false);
//
//        Role role = new Role();
//        role.setAuthority(AuthoritiesConstants.INFLUENCER);
//        role.setCreatedAt(LocalDateTime.now());
//        Role myRole = roleService.create(role);
//        RoleUser roleUser = new RoleUser();
//        roleUser.setRole(myRole);
//        if (t.getRoleUsers() == null)
//            t.setRoleUsers(new ArrayList<>());
//
//        t.getRoleUsers().add(roleUser);
//        if (t.getModelPermissionUsers() == null)
//            t.setModelPermissionUsers(new ArrayList<>());
//
//        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());
//
//        Influenceur mySaved = dao.save(t);
//
//        if (t.getModelPermissionUsers() != null) {
//            t.getModelPermissionUsers().forEach(e -> {
//                e.setUser(mySaved);
//                modelPermissionUserService.create(e);
//            });
//        }
//        if (t.getRoleUsers() != null) {
//            t.getRoleUsers().forEach(element-> {
//                element.setUser(mySaved);
//                roleUserService.create(element);
//            });
//        }
//
//        return mySaved;
//     }

    @Override
    public Influenceur create(Influenceur t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) {
            return null;
        }

        Influenceur mySaved = dao.save(t);
        return mySaved;
    }


    public Influenceur findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    public InfluenceurInfluenceurServiceImpl(InfluenceurDao dao) {
        this.dao = dao;
    }

    private InfluenceurDao dao;
}
