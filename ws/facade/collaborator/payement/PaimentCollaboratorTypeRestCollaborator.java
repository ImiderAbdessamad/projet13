package ma.zs.zyn.ws.facade.collaborator.payement;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import ma.zs.zyn.bean.core.payement.PaimentCollaboratorType;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorTypeCriteria;
import ma.zs.zyn.service.facade.collaborator.payement.PaimentCollaboratorTypeCollaboratorService;
import ma.zs.zyn.ws.converter.payement.PaimentCollaboratorTypeConverter;
import ma.zs.zyn.ws.dto.payement.PaimentCollaboratorTypeDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/collaborator/paimentCollaboratorType/")
public class PaimentCollaboratorTypeRestCollaborator {




    @Operation(summary = "Finds a list of all paimentCollaboratorTypes")
    @GetMapping("")
    public ResponseEntity<List<PaimentCollaboratorTypeDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentCollaboratorTypeDto>> res = null;
        List<PaimentCollaboratorType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentCollaboratorTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentCollaboratorTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentCollaboratorTypeDto>> res = null;
        List<PaimentCollaboratorType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentCollaboratorType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentCollaboratorTypeDto> findById(@PathVariable Long id) {
        PaimentCollaboratorType t = service.findById(id);
        if (t != null) {
            PaimentCollaboratorTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paimentCollaboratorType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaimentCollaboratorTypeDto> findByLibelle(@PathVariable String libelle) {
	    PaimentCollaboratorType t = service.findByReferenceEntity(new PaimentCollaboratorType(libelle));
        if (t != null) {
            PaimentCollaboratorTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paimentCollaboratorType")
    @PostMapping("")
    public ResponseEntity<PaimentCollaboratorTypeDto> save(@RequestBody PaimentCollaboratorTypeDto dto) throws Exception {
        if(dto!=null){
            PaimentCollaboratorType myT = converter.toItem(dto);
            PaimentCollaboratorType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentCollaboratorTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentCollaboratorType")
    @PutMapping("")
    public ResponseEntity<PaimentCollaboratorTypeDto> update(@RequestBody PaimentCollaboratorTypeDto dto) throws Exception {
        ResponseEntity<PaimentCollaboratorTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentCollaboratorType t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentCollaboratorType updated = service.update(t);
            PaimentCollaboratorTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentCollaboratorType")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentCollaboratorTypeDto>> delete(@RequestBody List<PaimentCollaboratorTypeDto> dtos) throws Exception {
        ResponseEntity<List<PaimentCollaboratorTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PaimentCollaboratorType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentCollaboratorType")
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


    @Operation(summary = "Finds a paimentCollaboratorType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentCollaboratorTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentCollaboratorType loaded =  service.findWithAssociatedLists(id);
        PaimentCollaboratorTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentCollaboratorTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentCollaboratorTypeDto>> findByCriteria(@RequestBody PaimentCollaboratorTypeCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentCollaboratorTypeDto>> res = null;
        List<PaimentCollaboratorType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentCollaboratorTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentCollaboratorTypeCriteria criteria) throws Exception {
        List<PaimentCollaboratorType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaimentCollaboratorTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentCollaboratorType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentCollaboratorTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentCollaboratorTypeDto> findDtos(List<PaimentCollaboratorType> list){
        List<PaimentCollaboratorTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentCollaboratorTypeDto> getDtoResponseEntity(PaimentCollaboratorTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentCollaboratorTypeCollaboratorService service;
    @Autowired private PaimentCollaboratorTypeConverter converter;





}
