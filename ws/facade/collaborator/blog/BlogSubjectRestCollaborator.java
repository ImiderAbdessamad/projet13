package ma.zs.zyn.ws.facade.collaborator.blog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.blog.BlogSubject;
import ma.zs.zyn.dao.criteria.core.blog.BlogSubjectCriteria;
import ma.zs.zyn.service.facade.collaborator.blog.BlogSubjectCollaboratorService;
import ma.zs.zyn.ws.converter.blog.BlogSubjectConverter;
import ma.zs.zyn.ws.dto.blog.BlogSubjectDto;
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
@RequestMapping("/api/collaborator/blogSubject/")
public class BlogSubjectRestCollaborator {




    @Operation(summary = "Finds a list of all blogSubjects")
    @GetMapping("")
    public ResponseEntity<List<BlogSubjectDto>> findAll() throws Exception {
        ResponseEntity<List<BlogSubjectDto>> res = null;
        List<BlogSubject> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all blogSubjects")
    @GetMapping("optimized")
    public ResponseEntity<List<BlogSubjectDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<BlogSubjectDto>> res = null;
        List<BlogSubject> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a blogSubject by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BlogSubjectDto> findById(@PathVariable Long id) {
        BlogSubject t = service.findById(id);
        if (t != null) {
            BlogSubjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a blogSubject by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<BlogSubjectDto> findByLibelle(@PathVariable String libelle) {
	    BlogSubject t = service.findByReferenceEntity(new BlogSubject(libelle));
        if (t != null) {
            BlogSubjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  blogSubject")
    @PostMapping("")
    public ResponseEntity<BlogSubjectDto> save(@RequestBody BlogSubjectDto dto) throws Exception {
        if(dto!=null){
            BlogSubject myT = converter.toItem(dto);
            BlogSubject t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BlogSubjectDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  blogSubject")
    @PutMapping("")
    public ResponseEntity<BlogSubjectDto> update(@RequestBody BlogSubjectDto dto) throws Exception {
        ResponseEntity<BlogSubjectDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BlogSubject t = service.findById(dto.getId());
            converter.copy(dto,t);
            BlogSubject updated = service.update(t);
            BlogSubjectDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of blogSubject")
    @PostMapping("multiple")
    public ResponseEntity<List<BlogSubjectDto>> delete(@RequestBody List<BlogSubjectDto> dtos) throws Exception {
        ResponseEntity<List<BlogSubjectDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<BlogSubject> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified blogSubject")
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


    @Operation(summary = "Finds a blogSubject and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BlogSubjectDto> findWithAssociatedLists(@PathVariable Long id) {
        BlogSubject loaded =  service.findWithAssociatedLists(id);
        BlogSubjectDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds blogSubjects by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BlogSubjectDto>> findByCriteria(@RequestBody BlogSubjectCriteria criteria) throws Exception {
        ResponseEntity<List<BlogSubjectDto>> res = null;
        List<BlogSubject> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BlogSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated blogSubjects by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BlogSubjectCriteria criteria) throws Exception {
        List<BlogSubject> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<BlogSubjectDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets blogSubject data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BlogSubjectCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BlogSubjectDto> findDtos(List<BlogSubject> list){
        List<BlogSubjectDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BlogSubjectDto> getDtoResponseEntity(BlogSubjectDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BlogSubjectCollaboratorService service;
    @Autowired private BlogSubjectConverter converter;





}
