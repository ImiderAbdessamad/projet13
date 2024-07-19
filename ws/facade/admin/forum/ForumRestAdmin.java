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

import ma.zs.zyn.bean.core.forum.Forum;
import ma.zs.zyn.dao.criteria.core.forum.ForumCriteria;
import ma.zs.zyn.service.facade.admin.forum.ForumAdminService;
import ma.zs.zyn.ws.converter.forum.ForumConverter;
import ma.zs.zyn.ws.dto.forum.ForumDto;
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
@RequestMapping("/api/admin/forum/")
public class ForumRestAdmin {




    @Operation(summary = "Finds a list of all forums")
    @GetMapping("")
    public ResponseEntity<List<ForumDto>> findAll() throws Exception {
        ResponseEntity<List<ForumDto>> res = null;
        List<Forum> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ForumDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a forum by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ForumDto> findById(@PathVariable Long id) {
        Forum t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ForumDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  forum")
    @PostMapping("")
    public ResponseEntity<ForumDto> save(@RequestBody ForumDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Forum myT = converter.toItem(dto);
            Forum t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ForumDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  forum")
    @PutMapping("")
    public ResponseEntity<ForumDto> update(@RequestBody ForumDto dto) throws Exception {
        ResponseEntity<ForumDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Forum t = service.findById(dto.getId());
            converter.copy(dto,t);
            Forum updated = service.update(t);
            ForumDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of forum")
    @PostMapping("multiple")
    public ResponseEntity<List<ForumDto>> delete(@RequestBody List<ForumDto> dtos) throws Exception {
        ResponseEntity<List<ForumDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Forum> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified forum")
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

    @Operation(summary = "find by forumState code")
    @GetMapping("forumState/code/{code}")
    public List<ForumDto> findByForumStateCode(@PathVariable String code){
        return findDtos(service.findByForumStateCode(code));
    }
    @Operation(summary = "delete by forumState code")
    @DeleteMapping("forumState/code/{code}")
    public int deleteByForumStateCode(@PathVariable String code){
        return service.deleteByForumStateCode(code);
    }

    @Operation(summary = "Finds a forum and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ForumDto> findWithAssociatedLists(@PathVariable Long id) {
        Forum loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ForumDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds forums by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ForumDto>> findByCriteria(@RequestBody ForumCriteria criteria) throws Exception {
        ResponseEntity<List<ForumDto>> res = null;
        List<Forum> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ForumDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated forums by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ForumCriteria criteria) throws Exception {
        List<Forum> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ForumDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets forum data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ForumCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ForumDto> findDtos(List<Forum> list){
        converter.initList(false);
        converter.initObject(true);
        List<ForumDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ForumDto> getDtoResponseEntity(ForumDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ForumAdminService service;
    @Autowired private ForumConverter converter;





}
