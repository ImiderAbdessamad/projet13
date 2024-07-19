package ma.zs.zyn.ws.facade.admin.cloud;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.cloud.CloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.CloudProviderCriteria;
import ma.zs.zyn.service.facade.admin.cloud.CloudProviderAdminService;
import ma.zs.zyn.ws.converter.cloud.CloudProviderConverter;
import ma.zs.zyn.ws.dto.cloud.CloudProviderDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cloudProvider/")
public class CloudProviderRestAdmin {




    @Operation(summary = "Finds a list of all cloudProviders")
    @GetMapping("")
    public ResponseEntity<List<CloudProviderDto>> findAll() throws Exception {
        ResponseEntity<List<CloudProviderDto>> res = null;
        List<CloudProvider> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all cloudProviders")
    @GetMapping("optimized")
    public ResponseEntity<List<CloudProviderDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CloudProviderDto>> res = null;
        List<CloudProvider> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a cloudProvider by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CloudProviderDto> findById(@PathVariable Long id) {
        CloudProvider t = service.findById(id);
        if (t != null) {
            CloudProviderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a cloudProvider by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CloudProviderDto> findByLibelle(@PathVariable String libelle) {
	    CloudProvider t = service.findByReferenceEntity(new CloudProvider(libelle));
        if (t != null) {
            CloudProviderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  cloudProvider")
    @PostMapping("")
    public ResponseEntity<CloudProviderDto> save(@RequestBody CloudProviderDto dto) throws Exception {
        if(dto!=null){
            CloudProvider myT = converter.toItem(dto);
            CloudProvider t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CloudProviderDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  cloudProvider")
    @PutMapping("")
    public ResponseEntity<CloudProviderDto> update(@RequestBody CloudProviderDto dto) throws Exception {
        ResponseEntity<CloudProviderDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CloudProvider t = service.findById(dto.getId());
            converter.copy(dto,t);
            CloudProvider updated = service.update(t);
            CloudProviderDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of cloudProvider")
    @PostMapping("multiple")
    public ResponseEntity<List<CloudProviderDto>> delete(@RequestBody List<CloudProviderDto> dtos) throws Exception {
        ResponseEntity<List<CloudProviderDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CloudProvider> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified cloudProvider")
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


    @Operation(summary = "Finds a cloudProvider and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CloudProviderDto> findWithAssociatedLists(@PathVariable Long id) {
        CloudProvider loaded =  service.findWithAssociatedLists(id);
        CloudProviderDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds cloudProviders by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CloudProviderDto>> findByCriteria(@RequestBody CloudProviderCriteria criteria) throws Exception {
        ResponseEntity<List<CloudProviderDto>> res = null;
        List<CloudProvider> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated cloudProviders by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CloudProviderCriteria criteria) throws Exception {
        List<CloudProvider> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CloudProviderDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets cloudProvider data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CloudProviderCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CloudProviderDto> findDtos(List<CloudProvider> list){
        List<CloudProviderDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CloudProviderDto> getDtoResponseEntity(CloudProviderDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CloudProviderAdminService service;
    @Autowired private CloudProviderConverter converter;





}
