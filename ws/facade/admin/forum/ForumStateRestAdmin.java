package ma.zs.zyn.ws.facade.admin.forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.forum.ForumState;
import ma.zs.zyn.dao.criteria.core.forum.ForumStateCriteria;
import ma.zs.zyn.service.facade.admin.forum.ForumStateAdminService;
import ma.zs.zyn.ws.converter.forum.ForumStateConverter;
import ma.zs.zyn.ws.dto.forum.ForumStateDto;
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
@RequestMapping("/api/admin/forumState/")
public class ForumStateRestAdmin {




    @Operation(summary = "Finds a list of all forumStates")
    @GetMapping("")
    public ResponseEntity<List<ForumStateDto>> findAll() throws Exception {
        ResponseEntity<List<ForumStateDto>> res = null;
        List<ForumState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all forumStates")
    @GetMapping("optimized")
    public ResponseEntity<List<ForumStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ForumStateDto>> res = null;
        List<ForumState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a forumState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ForumStateDto> findById(@PathVariable Long id) {
        ForumState t = service.findById(id);
        if (t != null) {
            ForumStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a forumState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ForumStateDto> findByLibelle(@PathVariable String libelle) {
	    ForumState t = service.findByReferenceEntity(new ForumState(libelle));
        if (t != null) {
            ForumStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  forumState")
    @PostMapping("")
    public ResponseEntity<ForumStateDto> save(@RequestBody ForumStateDto dto) throws Exception {
        if(dto!=null){
            ForumState myT = converter.toItem(dto);
            ForumState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ForumStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  forumState")
    @PutMapping("")
    public ResponseEntity<ForumStateDto> update(@RequestBody ForumStateDto dto) throws Exception {
        ResponseEntity<ForumStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ForumState t = service.findById(dto.getId());
            converter.copy(dto,t);
            ForumState updated = service.update(t);
            ForumStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of forumState")
    @PostMapping("multiple")
    public ResponseEntity<List<ForumStateDto>> delete(@RequestBody List<ForumStateDto> dtos) throws Exception {
        ResponseEntity<List<ForumStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ForumState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified forumState")
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


    @Operation(summary = "Finds a forumState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ForumStateDto> findWithAssociatedLists(@PathVariable Long id) {
        ForumState loaded =  service.findWithAssociatedLists(id);
        ForumStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds forumStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ForumStateDto>> findByCriteria(@RequestBody ForumStateCriteria criteria) throws Exception {
        ResponseEntity<List<ForumStateDto>> res = null;
        List<ForumState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated forumStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ForumStateCriteria criteria) throws Exception {
        List<ForumState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ForumStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets forumState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ForumStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ForumStateDto> findDtos(List<ForumState> list){
        List<ForumStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ForumStateDto> getDtoResponseEntity(ForumStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ForumStateAdminService service;
    @Autowired private ForumStateConverter converter;





}
