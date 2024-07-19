package ma.zs.zyn.ws.facade.collaborator.support;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationMessageCriteria;
import ma.zs.zyn.service.facade.collaborator.support.CustumorSupportConversationMessageCollaboratorService;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationMessageConverter;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationMessageDto;
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
@RequestMapping("/api/collaborator/custumorSupportConversationMessage/")
public class CustumorSupportConversationMessageRestCollaborator {




    @Operation(summary = "Finds a list of all custumorSupportConversationMessages")
    @GetMapping("")
    public ResponseEntity<List<CustumorSupportConversationMessageDto>> findAll() throws Exception {
        ResponseEntity<List<CustumorSupportConversationMessageDto>> res = null;
        List<CustumorSupportConversationMessage> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CustumorSupportConversationMessageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a custumorSupportConversationMessage by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CustumorSupportConversationMessageDto> findById(@PathVariable Long id) {
        CustumorSupportConversationMessage t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CustumorSupportConversationMessageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  custumorSupportConversationMessage")
    @PostMapping("")
    public ResponseEntity<CustumorSupportConversationMessageDto> save(@RequestBody CustumorSupportConversationMessageDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CustumorSupportConversationMessage myT = converter.toItem(dto);
            CustumorSupportConversationMessage t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CustumorSupportConversationMessageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  custumorSupportConversationMessage")
    @PutMapping("")
    public ResponseEntity<CustumorSupportConversationMessageDto> update(@RequestBody CustumorSupportConversationMessageDto dto) throws Exception {
        ResponseEntity<CustumorSupportConversationMessageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CustumorSupportConversationMessage t = service.findById(dto.getId());
            converter.copy(dto,t);
            CustumorSupportConversationMessage updated = service.update(t);
            CustumorSupportConversationMessageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of custumorSupportConversationMessage")
    @PostMapping("multiple")
    public ResponseEntity<List<CustumorSupportConversationMessageDto>> delete(@RequestBody List<CustumorSupportConversationMessageDto> dtos) throws Exception {
        ResponseEntity<List<CustumorSupportConversationMessageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CustumorSupportConversationMessage> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified custumorSupportConversationMessage")
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

    @Operation(summary = "find by custumorSupportConversation id")
    @GetMapping("custumorSupportConversation/id/{id}")
    public List<CustumorSupportConversationMessageDto> findByCustumorSupportConversationId(@PathVariable Long id){
        return findDtos(service.findByCustumorSupportConversationId(id));
    }
    @Operation(summary = "delete by custumorSupportConversation id")
    @DeleteMapping("custumorSupportConversation/id/{id}")
    public int deleteByCustumorSupportConversationId(@PathVariable Long id){
        return service.deleteByCustumorSupportConversationId(id);
    }

    @Operation(summary = "Finds a custumorSupportConversationMessage and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CustumorSupportConversationMessageDto> findWithAssociatedLists(@PathVariable Long id) {
        CustumorSupportConversationMessage loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CustumorSupportConversationMessageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds custumorSupportConversationMessages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CustumorSupportConversationMessageDto>> findByCriteria(@RequestBody CustumorSupportConversationMessageCriteria criteria) throws Exception {
        ResponseEntity<List<CustumorSupportConversationMessageDto>> res = null;
        List<CustumorSupportConversationMessage> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CustumorSupportConversationMessageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated custumorSupportConversationMessages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CustumorSupportConversationMessageCriteria criteria) throws Exception {
        List<CustumorSupportConversationMessage> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CustumorSupportConversationMessageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets custumorSupportConversationMessage data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CustumorSupportConversationMessageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CustumorSupportConversationMessageDto> findDtos(List<CustumorSupportConversationMessage> list){
        converter.initObject(true);
        List<CustumorSupportConversationMessageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CustumorSupportConversationMessageDto> getDtoResponseEntity(CustumorSupportConversationMessageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CustumorSupportConversationMessageCollaboratorService service;
    @Autowired private CustumorSupportConversationMessageConverter converter;





}
