package ma.zs.zyn.ws.facade.collaborator.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.RemoteRepoType;
import ma.zs.zyn.dao.criteria.core.project.RemoteRepoTypeCriteria;
import ma.zs.zyn.service.facade.collaborator.project.RemoteRepoTypeCollaboratorService;
import ma.zs.zyn.ws.converter.project.RemoteRepoTypeConverter;
import ma.zs.zyn.ws.dto.project.RemoteRepoTypeDto;
import ma.zs.zyn.zynerator.controller.AbstractController;
import ma.zs.zyn.zynerator.dto.AuditEntityDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.zyn.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.zyn.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/collaborator/remoteRepoType/")
public class RemoteRepoTypeRestCollaborator {




    @Operation(summary = "Finds a list of all remoteRepoTypes")
    @GetMapping("")
    public ResponseEntity<List<RemoteRepoTypeDto>> findAll() throws Exception {
        ResponseEntity<List<RemoteRepoTypeDto>> res = null;
        List<RemoteRepoType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RemoteRepoTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all remoteRepoTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<RemoteRepoTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RemoteRepoTypeDto>> res = null;
        List<RemoteRepoType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RemoteRepoTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a remoteRepoType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RemoteRepoTypeDto> findById(@PathVariable Long id) {
        RemoteRepoType t = service.findById(id);
        if (t != null) {
            RemoteRepoTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a remoteRepoType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RemoteRepoTypeDto> findByLibelle(@PathVariable String libelle) {
	    RemoteRepoType t = service.findByReferenceEntity(new RemoteRepoType(libelle));
        if (t != null) {
            RemoteRepoTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  remoteRepoType")
    @PostMapping("")
    public ResponseEntity<RemoteRepoTypeDto> save(@RequestBody RemoteRepoTypeDto dto) throws Exception {
        if(dto!=null){
            RemoteRepoType myT = converter.toItem(dto);
            RemoteRepoType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RemoteRepoTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  remoteRepoType")
    @PutMapping("")
    public ResponseEntity<RemoteRepoTypeDto> update(@RequestBody RemoteRepoTypeDto dto) throws Exception {
        ResponseEntity<RemoteRepoTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RemoteRepoType t = service.findById(dto.getId());
            converter.copy(dto,t);
            RemoteRepoType updated = service.update(t);
            RemoteRepoTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of remoteRepoType")
    @PostMapping("multiple")
    public ResponseEntity<List<RemoteRepoTypeDto>> delete(@RequestBody List<RemoteRepoTypeDto> dtos) throws Exception {
        ResponseEntity<List<RemoteRepoTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<RemoteRepoType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified remoteRepoType")
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


    @Operation(summary = "Finds a remoteRepoType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RemoteRepoTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        RemoteRepoType loaded =  service.findWithAssociatedLists(id);
        RemoteRepoTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds remoteRepoTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RemoteRepoTypeDto>> findByCriteria(@RequestBody RemoteRepoTypeCriteria criteria) throws Exception {
        ResponseEntity<List<RemoteRepoTypeDto>> res = null;
        List<RemoteRepoType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RemoteRepoTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated remoteRepoTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RemoteRepoTypeCriteria criteria) throws Exception {
        List<RemoteRepoType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RemoteRepoTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets remoteRepoType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RemoteRepoTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RemoteRepoTypeDto> findDtos(List<RemoteRepoType> list){
        List<RemoteRepoTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RemoteRepoTypeDto> getDtoResponseEntity(RemoteRepoTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RemoteRepoTypeCollaboratorService service;
    @Autowired private RemoteRepoTypeConverter converter;





}
