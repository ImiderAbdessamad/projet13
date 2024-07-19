package ma.zs.zyn.service.facade.collaborator.collaborator;

import ma.zs.zyn.bean.core.collaborator.City;
import ma.zs.zyn.dao.criteria.core.collaborator.CityCriteria;

import java.util.List;


public interface CityCollaboratorService {



    List<City> findByCountryId(Long id);
    int deleteByCountryId(Long id);
    long countByCountryCode(String code);




	City create(City t);

    City update(City t);

    List<City> update(List<City> ts,boolean createIfNotExist);

    City findById(Long id);

    City findOrSave(City t);

    City findByReferenceEntity(City t);

    City findWithAssociatedLists(Long id);

    List<City> findAllOptimized();

    List<City> findAll();

    List<City> findByCriteria(CityCriteria criteria);

    List<City> findPaginatedByCriteria(CityCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CityCriteria criteria);

    List<City> delete(List<City> ts);

    boolean deleteById(Long id);

    List<List<City>> getToBeSavedAndToBeDeleted(List<City> oldList, List<City> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
