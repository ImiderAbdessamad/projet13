package ma.zs.zyn.ws.facade.admin.collaborator;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.service.facade.admin.collaborator.CollaboratorAdminService;
import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.ws.dto.collaborator.CollaboratorDto;
import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/collaborator/")
public class CollaboratorRestAdmin {




    @Operation(summary = "Finds a list of all collaborators")
    @GetMapping("")
    public ResponseEntity<List<CollaboratorDto>> findAll() throws Exception {
        ResponseEntity<List<CollaboratorDto>> res = null;
        List<Collaborator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a collaborator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CollaboratorDto> findById(@PathVariable Long id) {
        Collaborator t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  collaborator")
    @PostMapping("")
    public ResponseEntity<CollaboratorDto> save(@RequestBody CollaboratorDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Collaborator myT = converter.toItem(dto);
            Collaborator t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  collaborator")
    @PutMapping("")
    public ResponseEntity<CollaboratorDto> update(@RequestBody CollaboratorDto dto) throws Exception {
        ResponseEntity<CollaboratorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Collaborator t = service.findById(dto.getId());
            converter.copy(dto,t);
            Collaborator updated = service.update(t);
            CollaboratorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of collaborator")
    @PostMapping("multiple")
    public ResponseEntity<List<CollaboratorDto>> delete(@RequestBody List<CollaboratorDto> dtos) throws Exception {
        ResponseEntity<List<CollaboratorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Collaborator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified collaborator")
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

    @Operation(summary = "find by city id")
    @GetMapping("city/id/{id}")
    public List<CollaboratorDto> findByCityId(@PathVariable Long id){
        return findDtos(service.findByCityId(id));
    }
    @Operation(summary = "delete by city id")
    @DeleteMapping("city/id/{id}")
    public int deleteByCityId(@PathVariable Long id){
        return service.deleteByCityId(id);
    }

    @Operation(summary = "Finds a collaborator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CollaboratorDto> findWithAssociatedLists(@PathVariable Long id) {
        Collaborator loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CollaboratorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds collaborators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CollaboratorDto>> findByCriteria(@RequestBody CollaboratorCriteria criteria) throws Exception {
        ResponseEntity<List<CollaboratorDto>> res = null;
        List<Collaborator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated collaborators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CollaboratorCriteria criteria) throws Exception {
        List<Collaborator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CollaboratorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets collaborator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CollaboratorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CollaboratorDto> findDtos(List<Collaborator> list){
        converter.initObject(true);
        List<CollaboratorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CollaboratorDto> getDtoResponseEntity(CollaboratorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private CollaboratorAdminService service;
    @Autowired private CollaboratorConverter converter;





}
