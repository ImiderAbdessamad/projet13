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

import ma.zs.zyn.bean.core.blog.Blog;
import ma.zs.zyn.dao.criteria.core.blog.BlogCriteria;
import ma.zs.zyn.service.facade.admin.blog.BlogAdminService;
import ma.zs.zyn.ws.converter.blog.BlogConverter;
import ma.zs.zyn.ws.dto.blog.BlogDto;
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
@RequestMapping("/api/admin/blog/")
public class BlogRestAdmin {




    @Operation(summary = "Finds a list of all blogs")
    @GetMapping("")
    public ResponseEntity<List<BlogDto>> findAll() throws Exception {
        ResponseEntity<List<BlogDto>> res = null;
        List<Blog> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<BlogDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a blog by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BlogDto> findById(@PathVariable Long id) {
        Blog t = service.findById(id);
        if (t != null) {
            converter.init(true);
            BlogDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  blog")
    @PostMapping("")
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Blog myT = converter.toItem(dto);
            Blog t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BlogDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  blog")
    @PutMapping("")
    public ResponseEntity<BlogDto> update(@RequestBody BlogDto dto) throws Exception {
        ResponseEntity<BlogDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Blog t = service.findById(dto.getId());
            converter.copy(dto,t);
            Blog updated = service.update(t);
            BlogDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of blog")
    @PostMapping("multiple")
    public ResponseEntity<List<BlogDto>> delete(@RequestBody List<BlogDto> dtos) throws Exception {
        ResponseEntity<List<BlogDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Blog> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified blog")
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

    @Operation(summary = "find by blogState code")
    @GetMapping("blogState/code/{code}")
    public List<BlogDto> findByBlogStateCode(@PathVariable String code){
        return findDtos(service.findByBlogStateCode(code));
    }
    @Operation(summary = "delete by blogState code")
    @DeleteMapping("blogState/code/{code}")
    public int deleteByBlogStateCode(@PathVariable String code){
        return service.deleteByBlogStateCode(code);
    }

    @Operation(summary = "Finds a blog and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BlogDto> findWithAssociatedLists(@PathVariable Long id) {
        Blog loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        BlogDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds blogs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BlogDto>> findByCriteria(@RequestBody BlogCriteria criteria) throws Exception {
        ResponseEntity<List<BlogDto>> res = null;
        List<Blog> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<BlogDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated blogs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BlogCriteria criteria) throws Exception {
        List<Blog> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<BlogDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets blog data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BlogCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BlogDto> findDtos(List<Blog> list){
        converter.initList(false);
        converter.initObject(true);
        List<BlogDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BlogDto> getDtoResponseEntity(BlogDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BlogAdminService service;
    @Autowired private BlogConverter converter;





}
