package ma.zs.zyn.ws.facade.collaborator.inscription;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorStateCollaboratorService;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorStateConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorStateDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/inscriptionCollaboratorState/")
public class InscriptionCollaboratorStateRestCollaborator {




    @Operation(summary = "Finds a list of all inscriptionCollaboratorStates")
    @GetMapping("")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all inscriptionCollaboratorStates")
    @GetMapping("optimized")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a inscriptionCollaboratorState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findById(@PathVariable Long id) {
        InscriptionCollaboratorState t = service.findById(id);
        if (t != null) {
            InscriptionCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a inscriptionCollaboratorState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findByLibelle(@PathVariable String libelle) {
	    InscriptionCollaboratorState t = service.findByReferenceEntity(new InscriptionCollaboratorState(libelle));
        if (t != null) {
            InscriptionCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  inscriptionCollaboratorState")
    @PostMapping("")
    public ResponseEntity<InscriptionCollaboratorStateDto> save(@RequestBody InscriptionCollaboratorStateDto dto) throws Exception {
        if(dto!=null){
            InscriptionCollaboratorState myT = converter.toItem(dto);
            InscriptionCollaboratorState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionCollaboratorStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionCollaboratorState")
    @PutMapping("")
    public ResponseEntity<InscriptionCollaboratorStateDto> update(@RequestBody InscriptionCollaboratorStateDto dto) throws Exception {
        ResponseEntity<InscriptionCollaboratorStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionCollaboratorState t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionCollaboratorState updated = service.update(t);
            InscriptionCollaboratorStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionCollaboratorState")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> delete(@RequestBody List<InscriptionCollaboratorStateDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<InscriptionCollaboratorState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionCollaboratorState")
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


    @Operation(summary = "Finds a inscriptionCollaboratorState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionCollaboratorState loaded =  service.findWithAssociatedLists(id);
        InscriptionCollaboratorStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionCollaboratorStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findByCriteria(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionCollaboratorStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        List<InscriptionCollaboratorState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InscriptionCollaboratorStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionCollaboratorState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionCollaboratorStateDto> findDtos(List<InscriptionCollaboratorState> list){
        List<InscriptionCollaboratorStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionCollaboratorStateDto> getDtoResponseEntity(InscriptionCollaboratorStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionCollaboratorStateCollaboratorService service;
    @Autowired private InscriptionCollaboratorStateConverter converter;





}
