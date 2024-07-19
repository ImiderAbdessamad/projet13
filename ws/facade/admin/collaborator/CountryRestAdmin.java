package ma.zs.zyn.ws.facade.admin.collaborator;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.collaborator.Country;
import ma.zs.zyn.dao.criteria.core.collaborator.CountryCriteria;
import ma.zs.zyn.service.facade.admin.collaborator.CountryAdminService;
import ma.zs.zyn.ws.converter.collaborator.CountryConverter;
import ma.zs.zyn.ws.dto.collaborator.CountryDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/country/")
public class CountryRestAdmin {




    @Operation(summary = "Finds a list of all countrys")
    @GetMapping("")
    public ResponseEntity<List<CountryDto>> findAll() throws Exception {
        ResponseEntity<List<CountryDto>> res = null;
        List<Country> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CountryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all countrys")
    @GetMapping("optimized")
    public ResponseEntity<List<CountryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CountryDto>> res = null;
        List<Country> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CountryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a country by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        Country t = service.findById(id);
        if (t != null) {
            CountryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a country by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CountryDto> findByLibelle(@PathVariable String libelle) {
	    Country t = service.findByReferenceEntity(new Country(libelle));
        if (t != null) {
            CountryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  country")
    @PostMapping("")
    public ResponseEntity<CountryDto> save(@RequestBody CountryDto dto) throws Exception {
        if(dto!=null){
            Country myT = converter.toItem(dto);
            Country t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CountryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  country")
    @PutMapping("")
    public ResponseEntity<CountryDto> update(@RequestBody CountryDto dto) throws Exception {
        ResponseEntity<CountryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Country t = service.findById(dto.getId());
            converter.copy(dto,t);
            Country updated = service.update(t);
            CountryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of country")
    @PostMapping("multiple")
    public ResponseEntity<List<CountryDto>> delete(@RequestBody List<CountryDto> dtos) throws Exception {
        ResponseEntity<List<CountryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Country> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified country")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a country and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CountryDto> findWithAssociatedLists(@PathVariable Long id) {
        Country loaded =  service.findWithAssociatedLists(id);
        CountryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds countrys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CountryDto>> findByCriteria(@RequestBody CountryCriteria criteria) throws Exception {
        ResponseEntity<List<CountryDto>> res = null;
        List<Country> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CountryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated countrys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CountryCriteria criteria) throws Exception {
        List<Country> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CountryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets country data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CountryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CountryDto> findDtos(List<Country> list){
        List<CountryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CountryDto> getDtoResponseEntity(CountryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CountryAdminService service;
    @Autowired private CountryConverter converter;





}
