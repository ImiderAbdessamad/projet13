package ma.zs.zyn.ws.facade.admin.support;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCriteria;
import ma.zs.zyn.service.facade.admin.support.CustumorSupportConversationAdminService;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationConverter;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationDto;
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
@RequestMapping("/api/admin/custumorSupportConversation/")
public class CustumorSupportConversationRestAdmin {




    @Operation(summary = "Finds a list of all custumorSupportConversations")
    @GetMapping("")
    public ResponseEntity<List<CustumorSupportConversationDto>> findAll() throws Exception {
        ResponseEntity<List<CustumorSupportConversationDto>> res = null;
        List<CustumorSupportConversation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<CustumorSupportConversationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a custumorSupportConversation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CustumorSupportConversationDto> findById(@PathVariable Long id) {
        CustumorSupportConversation t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CustumorSupportConversationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  custumorSupportConversation")
    @PostMapping("")
    public ResponseEntity<CustumorSupportConversationDto> save(@RequestBody CustumorSupportConversationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CustumorSupportConversation myT = converter.toItem(dto);
            CustumorSupportConversation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CustumorSupportConversationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  custumorSupportConversation")
    @PutMapping("")
    public ResponseEntity<CustumorSupportConversationDto> update(@RequestBody CustumorSupportConversationDto dto) throws Exception {
        ResponseEntity<CustumorSupportConversationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CustumorSupportConversation t = service.findById(dto.getId());
            converter.copy(dto,t);
            CustumorSupportConversation updated = service.update(t);
            CustumorSupportConversationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of custumorSupportConversation")
    @PostMapping("multiple")
    public ResponseEntity<List<CustumorSupportConversationDto>> delete(@RequestBody List<CustumorSupportConversationDto> dtos) throws Exception {
        ResponseEntity<List<CustumorSupportConversationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CustumorSupportConversation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified custumorSupportConversation")
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

    @Operation(summary = "find by agent id")
    @GetMapping("agent/id/{id}")
    public List<CustumorSupportConversationDto> findByAgentId(@PathVariable Long id){
        return findDtos(service.findByAgentId(id));
    }
    @Operation(summary = "delete by agent id")
    @DeleteMapping("agent/id/{id}")
    public int deleteByAgentId(@PathVariable Long id){
        return service.deleteByAgentId(id);
    }

    @Operation(summary = "Finds a custumorSupportConversation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CustumorSupportConversationDto> findWithAssociatedLists(@PathVariable Long id) {
        CustumorSupportConversation loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CustumorSupportConversationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds custumorSupportConversations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CustumorSupportConversationDto>> findByCriteria(@RequestBody CustumorSupportConversationCriteria criteria) throws Exception {
        ResponseEntity<List<CustumorSupportConversationDto>> res = null;
        List<CustumorSupportConversation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CustumorSupportConversationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated custumorSupportConversations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CustumorSupportConversationCriteria criteria) throws Exception {
        List<CustumorSupportConversation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<CustumorSupportConversationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets custumorSupportConversation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CustumorSupportConversationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CustumorSupportConversationDto> findDtos(List<CustumorSupportConversation> list){
        converter.initList(false);
        converter.initObject(true);
        List<CustumorSupportConversationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CustumorSupportConversationDto> getDtoResponseEntity(CustumorSupportConversationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CustumorSupportConversationRestAdmin(CustumorSupportConversationAdminService service, CustumorSupportConversationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CustumorSupportConversationAdminService service;
    private final CustumorSupportConversationConverter converter;





}
