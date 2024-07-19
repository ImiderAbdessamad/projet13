package  ma.zs.zyn.ws.facade.collaborator.forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.forum.ForumComment;
import ma.zs.zyn.dao.criteria.core.forum.ForumCommentCriteria;
import ma.zs.zyn.service.facade.collaborator.forum.ForumCommentCollaboratorService;
import ma.zs.zyn.ws.converter.forum.ForumCommentConverter;
import ma.zs.zyn.ws.dto.forum.ForumCommentDto;
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
@RequestMapping("/api/collaborator/forumComment/")
public class ForumCommentRestCollaborator {




    @Operation(summary = "Finds a list of all forumComments")
    @GetMapping("")
    public ResponseEntity<List<ForumCommentDto>> findAll() throws Exception {
        ResponseEntity<List<ForumCommentDto>> res = null;
        List<ForumComment> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ForumCommentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a forumComment by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ForumCommentDto> findById(@PathVariable Long id) {
        ForumComment t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ForumCommentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  forumComment")
    @PostMapping("")
    public ResponseEntity<ForumCommentDto> save(@RequestBody ForumCommentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ForumComment myT = converter.toItem(dto);
            ForumComment t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ForumCommentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  forumComment")
    @PutMapping("")
    public ResponseEntity<ForumCommentDto> update(@RequestBody ForumCommentDto dto) throws Exception {
        ResponseEntity<ForumCommentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ForumComment t = service.findById(dto.getId());
            converter.copy(dto,t);
            ForumComment updated = service.update(t);
            ForumCommentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of forumComment")
    @PostMapping("multiple")
    public ResponseEntity<List<ForumCommentDto>> delete(@RequestBody List<ForumCommentDto> dtos) throws Exception {
        ResponseEntity<List<ForumCommentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ForumComment> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified forumComment")
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

    @Operation(summary = "find by forum id")
    @GetMapping("forum/id/{id}")
    public List<ForumCommentDto> findByForumId(@PathVariable Long id){
        return findDtos(service.findByForumId(id));
    }
    @Operation(summary = "delete by forum id")
    @DeleteMapping("forum/id/{id}")
    public int deleteByForumId(@PathVariable Long id){
        return service.deleteByForumId(id);
    }

    @Operation(summary = "Finds a forumComment and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ForumCommentDto> findWithAssociatedLists(@PathVariable Long id) {
        ForumComment loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ForumCommentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds forumComments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ForumCommentDto>> findByCriteria(@RequestBody ForumCommentCriteria criteria) throws Exception {
        ResponseEntity<List<ForumCommentDto>> res = null;
        List<ForumComment> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ForumCommentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated forumComments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ForumCommentCriteria criteria) throws Exception {
        List<ForumComment> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ForumCommentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets forumComment data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ForumCommentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ForumCommentDto> findDtos(List<ForumComment> list){
        converter.initObject(true);
        List<ForumCommentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ForumCommentDto> getDtoResponseEntity(ForumCommentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ForumCommentCollaboratorService service;
    @Autowired private ForumCommentConverter converter;





}
