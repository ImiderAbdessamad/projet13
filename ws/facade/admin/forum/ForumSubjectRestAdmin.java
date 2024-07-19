package  ma.zs.zyn.ws.facade.admin.forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.forum.ForumSubject;
import ma.zs.zyn.dao.criteria.core.forum.ForumSubjectCriteria;
import ma.zs.zyn.service.facade.admin.forum.ForumSubjectAdminService;
import ma.zs.zyn.ws.converter.forum.ForumSubjectConverter;
import ma.zs.zyn.ws.dto.forum.ForumSubjectDto;
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
@RequestMapping("/api/admin/forumSubject/")
public class ForumSubjectRestAdmin {




    @Operation(summary = "Finds a list of all forumSubjects")
    @GetMapping("")
    public ResponseEntity<List<ForumSubjectDto>> findAll() throws Exception {
        ResponseEntity<List<ForumSubjectDto>> res = null;
        List<ForumSubject> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all forumSubjects")
    @GetMapping("optimized")
    public ResponseEntity<List<ForumSubjectDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ForumSubjectDto>> res = null;
        List<ForumSubject> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a forumSubject by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ForumSubjectDto> findById(@PathVariable Long id) {
        ForumSubject t = service.findById(id);
        if (t != null) {
            ForumSubjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a forumSubject by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ForumSubjectDto> findByLibelle(@PathVariable String libelle) {
	    ForumSubject t = service.findByReferenceEntity(new ForumSubject(libelle));
        if (t != null) {
            ForumSubjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  forumSubject")
    @PostMapping("")
    public ResponseEntity<ForumSubjectDto> save(@RequestBody ForumSubjectDto dto) throws Exception {
        if(dto!=null){
            ForumSubject myT = converter.toItem(dto);
            ForumSubject t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ForumSubjectDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  forumSubject")
    @PutMapping("")
    public ResponseEntity<ForumSubjectDto> update(@RequestBody ForumSubjectDto dto) throws Exception {
        ResponseEntity<ForumSubjectDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ForumSubject t = service.findById(dto.getId());
            converter.copy(dto,t);
            ForumSubject updated = service.update(t);
            ForumSubjectDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of forumSubject")
    @PostMapping("multiple")
    public ResponseEntity<List<ForumSubjectDto>> delete(@RequestBody List<ForumSubjectDto> dtos) throws Exception {
        ResponseEntity<List<ForumSubjectDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ForumSubject> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified forumSubject")
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


    @Operation(summary = "Finds a forumSubject and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ForumSubjectDto> findWithAssociatedLists(@PathVariable Long id) {
        ForumSubject loaded =  service.findWithAssociatedLists(id);
        ForumSubjectDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds forumSubjects by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ForumSubjectDto>> findByCriteria(@RequestBody ForumSubjectCriteria criteria) throws Exception {
        ResponseEntity<List<ForumSubjectDto>> res = null;
        List<ForumSubject> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ForumSubjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated forumSubjects by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ForumSubjectCriteria criteria) throws Exception {
        List<ForumSubject> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ForumSubjectDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets forumSubject data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ForumSubjectCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ForumSubjectDto> findDtos(List<ForumSubject> list){
        List<ForumSubjectDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ForumSubjectDto> getDtoResponseEntity(ForumSubjectDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ForumSubjectAdminService service;
    @Autowired private ForumSubjectConverter converter;





}
