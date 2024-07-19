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

import ma.zs.zyn.bean.core.blog.BlogState;
import ma.zs.zyn.dao.criteria.core.blog.BlogStateCriteria;
import ma.zs.zyn.service.facade.admin.blog.BlogStateAdminService;
import ma.zs.zyn.ws.converter.blog.BlogStateConverter;
import ma.zs.zyn.ws.dto.blog.BlogStateDto;
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
@RequestMapping("/api/admin/blogState/")
public class BlogStateRestAdmin {




    @Operation(summary = "Finds a list of all blogStates")
    @GetMapping("")
    public ResponseEntity<List<BlogStateDto>> findAll() throws Exception {
        ResponseEntity<List<BlogStateDto>> res = null;
        List<BlogState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all blogStates")
    @GetMapping("optimized")
    public ResponseEntity<List<BlogStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<BlogStateDto>> res = null;
        List<BlogState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a blogState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BlogStateDto> findById(@PathVariable Long id) {
        BlogState t = service.findById(id);
        if (t != null) {
            BlogStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a blogState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<BlogStateDto> findByLibelle(@PathVariable String libelle) {
	    BlogState t = service.findByReferenceEntity(new BlogState(libelle));
        if (t != null) {
            BlogStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  blogState")
    @PostMapping("")
    public ResponseEntity<BlogStateDto> save(@RequestBody BlogStateDto dto) throws Exception {
        if(dto!=null){
            BlogState myT = converter.toItem(dto);
            BlogState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BlogStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  blogState")
    @PutMapping("")
    public ResponseEntity<BlogStateDto> update(@RequestBody BlogStateDto dto) throws Exception {
        ResponseEntity<BlogStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BlogState t = service.findById(dto.getId());
            converter.copy(dto,t);
            BlogState updated = service.update(t);
            BlogStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of blogState")
    @PostMapping("multiple")
    public ResponseEntity<List<BlogStateDto>> delete(@RequestBody List<BlogStateDto> dtos) throws Exception {
        ResponseEntity<List<BlogStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<BlogState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified blogState")
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


    @Operation(summary = "Finds a blogState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BlogStateDto> findWithAssociatedLists(@PathVariable Long id) {
        BlogState loaded =  service.findWithAssociatedLists(id);
        BlogStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds blogStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BlogStateDto>> findByCriteria(@RequestBody BlogStateCriteria criteria) throws Exception {
        ResponseEntity<List<BlogStateDto>> res = null;
        List<BlogState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated blogStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BlogStateCriteria criteria) throws Exception {
        List<BlogState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<BlogStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets blogState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BlogStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BlogStateDto> findDtos(List<BlogState> list){
        List<BlogStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BlogStateDto> getDtoResponseEntity(BlogStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BlogStateAdminService service;
    @Autowired private BlogStateConverter converter;





}
