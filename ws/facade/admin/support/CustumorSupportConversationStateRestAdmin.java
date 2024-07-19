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

import ma.zs.zyn.bean.core.support.CustumorSupportConversationState;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationStateCriteria;
import ma.zs.zyn.service.facade.admin.support.CustumorSupportConversationStateAdminService;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationStateConverter;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationStateDto;
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
@RequestMapping("/api/admin/custumorSupportConversationState/")
public class CustumorSupportConversationStateRestAdmin {




    @Operation(summary = "Finds a list of all custumorSupportConversationStates")
    @GetMapping("")
    public ResponseEntity<List<CustumorSupportConversationStateDto>> findAll() throws Exception {
        ResponseEntity<List<CustumorSupportConversationStateDto>> res = null;
        List<CustumorSupportConversationState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all custumorSupportConversationStates")
    @GetMapping("optimized")
    public ResponseEntity<List<CustumorSupportConversationStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CustumorSupportConversationStateDto>> res = null;
        List<CustumorSupportConversationState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a custumorSupportConversationState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CustumorSupportConversationStateDto> findById(@PathVariable Long id) {
        CustumorSupportConversationState t = service.findById(id);
        if (t != null) {
            CustumorSupportConversationStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a custumorSupportConversationState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CustumorSupportConversationStateDto> findByLibelle(@PathVariable String libelle) {
	    CustumorSupportConversationState t = service.findByReferenceEntity(new CustumorSupportConversationState(libelle));
        if (t != null) {
            CustumorSupportConversationStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  custumorSupportConversationState")
    @PostMapping("")
    public ResponseEntity<CustumorSupportConversationStateDto> save(@RequestBody CustumorSupportConversationStateDto dto) throws Exception {
        if(dto!=null){
            CustumorSupportConversationState myT = converter.toItem(dto);
            CustumorSupportConversationState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CustumorSupportConversationStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  custumorSupportConversationState")
    @PutMapping("")
    public ResponseEntity<CustumorSupportConversationStateDto> update(@RequestBody CustumorSupportConversationStateDto dto) throws Exception {
        ResponseEntity<CustumorSupportConversationStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CustumorSupportConversationState t = service.findById(dto.getId());
            converter.copy(dto,t);
            CustumorSupportConversationState updated = service.update(t);
            CustumorSupportConversationStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of custumorSupportConversationState")
    @PostMapping("multiple")
    public ResponseEntity<List<CustumorSupportConversationStateDto>> delete(@RequestBody List<CustumorSupportConversationStateDto> dtos) throws Exception {
        ResponseEntity<List<CustumorSupportConversationStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CustumorSupportConversationState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified custumorSupportConversationState")
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


    @Operation(summary = "Finds a custumorSupportConversationState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CustumorSupportConversationStateDto> findWithAssociatedLists(@PathVariable Long id) {
        CustumorSupportConversationState loaded =  service.findWithAssociatedLists(id);
        CustumorSupportConversationStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds custumorSupportConversationStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CustumorSupportConversationStateDto>> findByCriteria(@RequestBody CustumorSupportConversationStateCriteria criteria) throws Exception {
        ResponseEntity<List<CustumorSupportConversationStateDto>> res = null;
        List<CustumorSupportConversationState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated custumorSupportConversationStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CustumorSupportConversationStateCriteria criteria) throws Exception {
        List<CustumorSupportConversationState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CustumorSupportConversationStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets custumorSupportConversationState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CustumorSupportConversationStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CustumorSupportConversationStateDto> findDtos(List<CustumorSupportConversationState> list){
        List<CustumorSupportConversationStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CustumorSupportConversationStateDto> getDtoResponseEntity(CustumorSupportConversationStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CustumorSupportConversationStateRestAdmin(CustumorSupportConversationStateAdminService service, CustumorSupportConversationStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CustumorSupportConversationStateAdminService service;
    private final CustumorSupportConversationStateConverter converter;





}
