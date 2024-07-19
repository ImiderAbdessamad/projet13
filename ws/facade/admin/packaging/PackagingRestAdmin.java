package ma.zs.zyn.ws.facade.admin.packaging;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.service.facade.admin.packaging.PackagingAdminService;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/packaging/")
public class PackagingRestAdmin {




    @Operation(summary = "Finds a list of all packagings")
    @GetMapping("")
    public ResponseEntity<List<PackagingDto>> findAll() throws Exception {
        ResponseEntity<List<PackagingDto>> res = null;
        List<Packaging> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all packagings")
    @GetMapping("optimized")
    public ResponseEntity<List<PackagingDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PackagingDto>> res = null;
        List<Packaging> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a packaging by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PackagingDto> findById(@PathVariable Long id) {
        Packaging t = service.findById(id);
        if (t != null) {
            PackagingDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a packaging by code")
    @GetMapping("code/{code}")
    public ResponseEntity<PackagingDto> findByCode(@PathVariable String code) {
	    Packaging t = service.findByReferenceEntity(new Packaging(code));
        if (t != null) {
            PackagingDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  packaging")
    @PostMapping("")
    public ResponseEntity<PackagingDto> save(@RequestBody PackagingDto dto) throws Exception {
        if(dto!=null){
            Packaging myT = converter.toItem(dto);
            Packaging t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PackagingDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  packaging")
    @PutMapping("")
    public ResponseEntity<PackagingDto> update(@RequestBody PackagingDto dto) throws Exception {
        ResponseEntity<PackagingDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Packaging t = service.findById(dto.getId());
            converter.copy(dto,t);
            Packaging updated = service.update(t);
            PackagingDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of packaging")
    @PostMapping("multiple")
    public ResponseEntity<List<PackagingDto>> delete(@RequestBody List<PackagingDto> dtos) throws Exception {
        ResponseEntity<List<PackagingDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Packaging> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified packaging")
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


    @Operation(summary = "Finds a packaging and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PackagingDto> findWithAssociatedLists(@PathVariable Long id) {
        Packaging loaded =  service.findWithAssociatedLists(id);
        PackagingDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds packagings by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PackagingDto>> findByCriteria(@RequestBody PackagingCriteria criteria) throws Exception {
        ResponseEntity<List<PackagingDto>> res = null;
        List<Packaging> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated packagings by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PackagingCriteria criteria) throws Exception {
        List<Packaging> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PackagingDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets packaging data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PackagingCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PackagingDto> findDtos(List<Packaging> list){
        List<PackagingDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PackagingDto> getDtoResponseEntity(PackagingDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PackagingAdminService service;
    @Autowired private PackagingConverter converter;





}
