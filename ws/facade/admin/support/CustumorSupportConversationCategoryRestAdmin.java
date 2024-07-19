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

import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCategoryCriteria;
import ma.zs.zyn.service.facade.admin.support.CustumorSupportConversationCategoryAdminService;
import ma.zs.zyn.ws.converter.support.CustumorSupportConversationCategoryConverter;
import ma.zs.zyn.ws.dto.support.CustumorSupportConversationCategoryDto;
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
@RequestMapping("/api/admin/custumorSupportConversationCategory/")
public class CustumorSupportConversationCategoryRestAdmin {




    @Operation(summary = "Finds a list of all custumorSupportConversationCategorys")
    @GetMapping("")
    public ResponseEntity<List<CustumorSupportConversationCategoryDto>> findAll() throws Exception {
        ResponseEntity<List<CustumorSupportConversationCategoryDto>> res = null;
        List<CustumorSupportConversationCategory> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all custumorSupportConversationCategorys")
    @GetMapping("optimized")
    public ResponseEntity<List<CustumorSupportConversationCategoryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CustumorSupportConversationCategoryDto>> res = null;
        List<CustumorSupportConversationCategory> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a custumorSupportConversationCategory by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CustumorSupportConversationCategoryDto> findById(@PathVariable Long id) {
        CustumorSupportConversationCategory t = service.findById(id);
        if (t != null) {
            CustumorSupportConversationCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a custumorSupportConversationCategory by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CustumorSupportConversationCategoryDto> findByLibelle(@PathVariable String libelle) {
	    CustumorSupportConversationCategory t = service.findByReferenceEntity(new CustumorSupportConversationCategory(libelle));
        if (t != null) {
            CustumorSupportConversationCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  custumorSupportConversationCategory")
    @PostMapping("")
    public ResponseEntity<CustumorSupportConversationCategoryDto> save(@RequestBody CustumorSupportConversationCategoryDto dto) throws Exception {
        if(dto!=null){
            CustumorSupportConversationCategory myT = converter.toItem(dto);
            CustumorSupportConversationCategory t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CustumorSupportConversationCategoryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  custumorSupportConversationCategory")
    @PutMapping("")
    public ResponseEntity<CustumorSupportConversationCategoryDto> update(@RequestBody CustumorSupportConversationCategoryDto dto) throws Exception {
        ResponseEntity<CustumorSupportConversationCategoryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CustumorSupportConversationCategory t = service.findById(dto.getId());
            converter.copy(dto,t);
            CustumorSupportConversationCategory updated = service.update(t);
            CustumorSupportConversationCategoryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of custumorSupportConversationCategory")
    @PostMapping("multiple")
    public ResponseEntity<List<CustumorSupportConversationCategoryDto>> delete(@RequestBody List<CustumorSupportConversationCategoryDto> dtos) throws Exception {
        ResponseEntity<List<CustumorSupportConversationCategoryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CustumorSupportConversationCategory> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified custumorSupportConversationCategory")
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


    @Operation(summary = "Finds a custumorSupportConversationCategory and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CustumorSupportConversationCategoryDto> findWithAssociatedLists(@PathVariable Long id) {
        CustumorSupportConversationCategory loaded =  service.findWithAssociatedLists(id);
        CustumorSupportConversationCategoryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds custumorSupportConversationCategorys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CustumorSupportConversationCategoryDto>> findByCriteria(@RequestBody CustumorSupportConversationCategoryCriteria criteria) throws Exception {
        ResponseEntity<List<CustumorSupportConversationCategoryDto>> res = null;
        List<CustumorSupportConversationCategory> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CustumorSupportConversationCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated custumorSupportConversationCategorys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CustumorSupportConversationCategoryCriteria criteria) throws Exception {
        List<CustumorSupportConversationCategory> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CustumorSupportConversationCategoryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets custumorSupportConversationCategory data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CustumorSupportConversationCategoryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CustumorSupportConversationCategoryDto> findDtos(List<CustumorSupportConversationCategory> list){
        List<CustumorSupportConversationCategoryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CustumorSupportConversationCategoryDto> getDtoResponseEntity(CustumorSupportConversationCategoryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CustumorSupportConversationCategoryRestAdmin(CustumorSupportConversationCategoryAdminService service, CustumorSupportConversationCategoryConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CustumorSupportConversationCategoryAdminService service;
    private final CustumorSupportConversationCategoryConverter converter;





}
