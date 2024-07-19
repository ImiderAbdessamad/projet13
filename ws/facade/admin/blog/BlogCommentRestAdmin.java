package ma.zs.zyn.ws.facade.admin.blog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.blog.BlogComment;
import ma.zs.zyn.dao.criteria.core.blog.BlogCommentCriteria;
import ma.zs.zyn.service.facade.admin.blog.BlogCommentAdminService;
import ma.zs.zyn.ws.converter.blog.BlogCommentConverter;
import ma.zs.zyn.ws.dto.blog.BlogCommentDto;
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
@RequestMapping("/api/admin/blogComment/")
public class BlogCommentRestAdmin {




    @Operation(summary = "Finds a list of all blogComments")
    @GetMapping("")
    public ResponseEntity<List<BlogCommentDto>> findAll() throws Exception {
        ResponseEntity<List<BlogCommentDto>> res = null;
        List<BlogComment> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<BlogCommentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a blogComment by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BlogCommentDto> findById(@PathVariable Long id) {
        BlogComment t = service.findById(id);
        if (t != null) {
            converter.init(true);
            BlogCommentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  blogComment")
    @PostMapping("")
    public ResponseEntity<BlogCommentDto> save(@RequestBody BlogCommentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            BlogComment myT = converter.toItem(dto);
            BlogComment t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BlogCommentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  blogComment")
    @PutMapping("")
    public ResponseEntity<BlogCommentDto> update(@RequestBody BlogCommentDto dto) throws Exception {
        ResponseEntity<BlogCommentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BlogComment t = service.findById(dto.getId());
            converter.copy(dto,t);
            BlogComment updated = service.update(t);
            BlogCommentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of blogComment")
    @PostMapping("multiple")
    public ResponseEntity<List<BlogCommentDto>> delete(@RequestBody List<BlogCommentDto> dtos) throws Exception {
        ResponseEntity<List<BlogCommentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<BlogComment> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified blogComment")
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

    @Operation(summary = "find by collaborator id")
    @GetMapping("collaborator/id/{id}")
    public List<BlogCommentDto> findByCollaboratorId(@PathVariable Long id){
        return findDtos(service.findByCollaboratorId(id));
    }
    @Operation(summary = "delete by collaborator id")
    @DeleteMapping("collaborator/id/{id}")
    public int deleteByCollaboratorId(@PathVariable Long id){
        return service.deleteByCollaboratorId(id);
    }
    @Operation(summary = "find by blog id")
    @GetMapping("blog/id/{id}")
    public List<BlogCommentDto> findByBlogId(@PathVariable Long id){
        return findDtos(service.findByBlogId(id));
    }
    @Operation(summary = "delete by blog id")
    @DeleteMapping("blog/id/{id}")
    public int deleteByBlogId(@PathVariable Long id){
        return service.deleteByBlogId(id);
    }

    @Operation(summary = "Finds a blogComment and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BlogCommentDto> findWithAssociatedLists(@PathVariable Long id) {
        BlogComment loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        BlogCommentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds blogComments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BlogCommentDto>> findByCriteria(@RequestBody BlogCommentCriteria criteria) throws Exception {
        ResponseEntity<List<BlogCommentDto>> res = null;
        List<BlogComment> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<BlogCommentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated blogComments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BlogCommentCriteria criteria) throws Exception {
        List<BlogComment> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<BlogCommentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets blogComment data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BlogCommentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BlogCommentDto> findDtos(List<BlogComment> list){
        converter.initObject(true);
        List<BlogCommentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BlogCommentDto> getDtoResponseEntity(BlogCommentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BlogCommentAdminService service;
    @Autowired private BlogCommentConverter converter;





}
