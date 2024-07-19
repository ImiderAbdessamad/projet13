package ma.zs.zyn.ws.facade.collaborator.project;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.project.RemoteRepoInfo;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoInfoCriteria;
import ma.zs.zyn.service.facade.collaborator.project.RemoteRepoInfoCollaboratorService;
import ma.zs.zyn.ws.converter.project.RemoteRepoInfoConverter;
import ma.zs.zyn.ws.dto.project.RemoteRepoInfoDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/remoteRepoInfo/")
public class RemoteRepoInfoRestCollaborator {





    @Operation(summary = "Finds a list of all remoteRepoInfos")
    @GetMapping("")
    public ResponseEntity<List<RemoteRepoInfoDto>> findAll() throws Exception {
        ResponseEntity<List<RemoteRepoInfoDto>> res = null;
        List<RemoteRepoInfo> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RemoteRepoInfoDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all remoteRepoInfos")
    @GetMapping("optimized")
    public ResponseEntity<List<RemoteRepoInfoDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RemoteRepoInfoDto>> res = null;
        List<RemoteRepoInfo> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RemoteRepoInfoDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a remoteRepoInfo by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RemoteRepoInfoDto> findById(@PathVariable Long id) {
        RemoteRepoInfo t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RemoteRepoInfoDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a remoteRepoInfo by title")
    @GetMapping("title/{title}")
    public ResponseEntity<RemoteRepoInfoDto> findByTitle(@PathVariable String title) {
	    RemoteRepoInfo t = service.findByReferenceEntity(new RemoteRepoInfo(title));
        if (t != null) {
            converter.init(true);
            RemoteRepoInfoDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  remoteRepoInfo")
    @PostMapping("")
    public ResponseEntity<RemoteRepoInfoDto> save(@RequestBody RemoteRepoInfoDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            converter.setCollaborator(false);
            RemoteRepoInfo myT = converter.toItem(dto);
            RemoteRepoInfo t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                converter.setCollaborator(true);
                RemoteRepoInfoDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  remoteRepoInfo")
    @PutMapping("")
    public ResponseEntity<RemoteRepoInfoDto> update(@RequestBody RemoteRepoInfoDto dto) throws Exception {
        ResponseEntity<RemoteRepoInfoDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RemoteRepoInfo t = service.findById(dto.getId());
            converter.copy(dto,t);
            RemoteRepoInfo updated = service.update(t);
            RemoteRepoInfoDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of remoteRepoInfo")
    @PostMapping("multiple")
    public ResponseEntity<List<RemoteRepoInfoDto>> delete(@RequestBody List<RemoteRepoInfoDto> dtos) throws Exception {
        ResponseEntity<List<RemoteRepoInfoDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RemoteRepoInfo> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified remoteRepoInfo")
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

    @Operation(summary = "find by remoteRepoType code")
    @GetMapping("remoteRepoType/code/{code}")
    public List<RemoteRepoInfoDto> findByRemoteRepoTypeCode(@PathVariable String code){
        return findDtos(service.findByRemoteRepoTypeCode(code));
    }
    @Operation(summary = "delete by remoteRepoType code")
    @DeleteMapping("remoteRepoType/code/{code}")
    public int deleteByRemoteRepoTypeCode(@PathVariable String code){
        return service.deleteByRemoteRepoTypeCode(code);
    }
    @Operation(summary = "find by collaborator id")
    @GetMapping("collaborator")
    public List<RemoteRepoInfoDto> findByCollaboratorId(){
        return findDtos(service.findByCollaboratorId());
    }
    @Operation(summary = "delete by collaborator id")
    @DeleteMapping("collaborator/id/{id}")
    public int deleteByCollaboratorId(@PathVariable Long id){
        return service.deleteByCollaboratorId(id);
    }

    @Operation(summary = "Finds a remoteRepoInfo and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RemoteRepoInfoDto> findWithAssociatedLists(@PathVariable Long id) {
        RemoteRepoInfo loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RemoteRepoInfoDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds remoteRepoInfos by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RemoteRepoInfoDto>> findByCriteria(@RequestBody RemoteRepoInfoCriteria criteria) throws Exception {
        ResponseEntity<List<RemoteRepoInfoDto>> res = null;
        List<RemoteRepoInfo> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RemoteRepoInfoDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated remoteRepoInfos by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RemoteRepoInfoCriteria criteria) throws Exception {
        List<RemoteRepoInfo> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RemoteRepoInfoDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets remoteRepoInfo data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RemoteRepoInfoCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RemoteRepoInfoDto> findDtos(List<RemoteRepoInfo> list){
        converter.initObject(true);
        List<RemoteRepoInfoDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RemoteRepoInfoDto> getDtoResponseEntity(RemoteRepoInfoDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RemoteRepoInfoCollaboratorService service;
    @Autowired private RemoteRepoInfoConverter converter;





}
