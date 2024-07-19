package ma.zs.zyn.ws.facade.admin.payement;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.payement.PaimentCollaboratorState;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorStateCriteria;
import ma.zs.zyn.service.facade.admin.payement.PaimentCollaboratorStateAdminService;
import ma.zs.zyn.ws.converter.payement.PaimentCollaboratorStateConverter;
import ma.zs.zyn.ws.dto.payement.PaimentCollaboratorStateDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/paimentCollaboratorState/")
public class PaimentCollaboratorStateRestAdmin {




    @Operation(summary = "Finds a list of all paimentCollaboratorStates")
    @GetMapping("")
    public ResponseEntity<List<PaimentCollaboratorStateDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentCollaboratorStateDto>> res = null;
        List<PaimentCollaboratorState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentCollaboratorStates")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentCollaboratorStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentCollaboratorStateDto>> res = null;
        List<PaimentCollaboratorState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentCollaboratorState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentCollaboratorStateDto> findById(@PathVariable Long id) {
        PaimentCollaboratorState t = service.findById(id);
        if (t != null) {
            PaimentCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paimentCollaboratorState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaimentCollaboratorStateDto> findByLibelle(@PathVariable String libelle) {
	    PaimentCollaboratorState t = service.findByReferenceEntity(new PaimentCollaboratorState(libelle));
        if (t != null) {
            PaimentCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paimentCollaboratorState")
    @PostMapping("")
    public ResponseEntity<PaimentCollaboratorStateDto> save(@RequestBody PaimentCollaboratorStateDto dto) throws Exception {
        if(dto!=null){
            PaimentCollaboratorState myT = converter.toItem(dto);
            PaimentCollaboratorState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentCollaboratorStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentCollaboratorState")
    @PutMapping("")
    public ResponseEntity<PaimentCollaboratorStateDto> update(@RequestBody PaimentCollaboratorStateDto dto) throws Exception {
        ResponseEntity<PaimentCollaboratorStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentCollaboratorState t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentCollaboratorState updated = service.update(t);
            PaimentCollaboratorStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentCollaboratorState")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentCollaboratorStateDto>> delete(@RequestBody List<PaimentCollaboratorStateDto> dtos) throws Exception {
        ResponseEntity<List<PaimentCollaboratorStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PaimentCollaboratorState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentCollaboratorState")
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


    @Operation(summary = "Finds a paimentCollaboratorState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentCollaboratorStateDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentCollaboratorState loaded =  service.findWithAssociatedLists(id);
        PaimentCollaboratorStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentCollaboratorStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentCollaboratorStateDto>> findByCriteria(@RequestBody PaimentCollaboratorStateCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentCollaboratorStateDto>> res = null;
        List<PaimentCollaboratorState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentCollaboratorStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentCollaboratorStateCriteria criteria) throws Exception {
        List<PaimentCollaboratorState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaimentCollaboratorStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentCollaboratorState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentCollaboratorStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentCollaboratorStateDto> findDtos(List<PaimentCollaboratorState> list){
        List<PaimentCollaboratorStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentCollaboratorStateDto> getDtoResponseEntity(PaimentCollaboratorStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentCollaboratorStateAdminService service;
    @Autowired private PaimentCollaboratorStateConverter converter;





}
