package ma.zs.zyn.ws.facade.collaborator.inscription;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorCriteria;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/inscriptionCollaborator/")
public class InscriptionCollaboratorRestCollaborator {




    @Operation(summary = "Finds a list of all inscriptionCollaborators")
    @GetMapping("")
    public ResponseEntity<List<InscriptionCollaboratorDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorDto>> res = null;
        List<InscriptionCollaborator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InscriptionCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a inscriptionCollaborator by id")
    @GetMapping("connected-collaborator")
    public ResponseEntity<InscriptionCollaboratorDto> findByConnectedCollaboratorId() {
        InscriptionCollaborator t = service.findByConnectedCollaboratorId();
        if (t != null) {
            converter.init(true);
            InscriptionCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a inscriptionCollaborator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionCollaboratorDto> findById(@PathVariable Long id) {
        InscriptionCollaborator t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InscriptionCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  inscriptionCollaborator")
    @PostMapping("")
    public ResponseEntity<InscriptionCollaboratorDto> save(@RequestBody InscriptionCollaboratorDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            InscriptionCollaborator myT = converter.toItem(dto);
            InscriptionCollaborator t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionCollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionCollaborator")
    @PutMapping("")
    public ResponseEntity<InscriptionCollaboratorDto> update(@RequestBody InscriptionCollaboratorDto dto) throws Exception {
        ResponseEntity<InscriptionCollaboratorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionCollaborator t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionCollaborator updated = service.update(t);
            InscriptionCollaboratorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionCollaborator")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionCollaboratorDto>> delete(@RequestBody List<InscriptionCollaboratorDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<InscriptionCollaborator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionCollaborator")
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


    @Operation(summary = "Finds a inscriptionCollaborator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionCollaboratorDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionCollaborator loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InscriptionCollaboratorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionCollaborators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionCollaboratorDto>> findByCriteria(@RequestBody InscriptionCollaboratorCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorDto>> res = null;
        List<InscriptionCollaborator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InscriptionCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionCollaborators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCollaboratorCriteria criteria) throws Exception {
        List<InscriptionCollaborator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InscriptionCollaboratorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionCollaborator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCollaboratorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionCollaboratorDto> findDtos(List<InscriptionCollaborator> list){
        converter.initObject(true);
        List<InscriptionCollaboratorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionCollaboratorDto> getDtoResponseEntity(InscriptionCollaboratorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionCollaboratorCollaboratorService service;
    @Autowired private InscriptionCollaboratorConverter converter;





}
