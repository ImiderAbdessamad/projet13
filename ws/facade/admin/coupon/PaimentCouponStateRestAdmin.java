package ma.zs.zyn.ws.facade.admin.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.coupon.PaimentCouponState;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponStateCriteria;
import ma.zs.zyn.service.facade.admin.coupon.PaimentCouponStateAdminService;
import ma.zs.zyn.ws.converter.coupon.PaimentCouponStateConverter;
import ma.zs.zyn.ws.dto.coupon.PaimentCouponStateDto;
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
@RequestMapping("/api/admin/paimentCouponState/")
public class PaimentCouponStateRestAdmin {




    @Operation(summary = "Finds a list of all paimentCouponStates")
    @GetMapping("")
    public ResponseEntity<List<PaimentCouponStateDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentCouponStateDto>> res = null;
        List<PaimentCouponState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentCouponStates")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentCouponStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentCouponStateDto>> res = null;
        List<PaimentCouponState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentCouponState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentCouponStateDto> findById(@PathVariable Long id) {
        PaimentCouponState t = service.findById(id);
        if (t != null) {
            PaimentCouponStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paimentCouponState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaimentCouponStateDto> findByLibelle(@PathVariable String libelle) {
	    PaimentCouponState t = service.findByReferenceEntity(new PaimentCouponState(libelle));
        if (t != null) {
            PaimentCouponStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paimentCouponState")
    @PostMapping("")
    public ResponseEntity<PaimentCouponStateDto> save(@RequestBody PaimentCouponStateDto dto) throws Exception {
        if(dto!=null){
            PaimentCouponState myT = converter.toItem(dto);
            PaimentCouponState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentCouponStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentCouponState")
    @PutMapping("")
    public ResponseEntity<PaimentCouponStateDto> update(@RequestBody PaimentCouponStateDto dto) throws Exception {
        ResponseEntity<PaimentCouponStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentCouponState t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentCouponState updated = service.update(t);
            PaimentCouponStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentCouponState")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentCouponStateDto>> delete(@RequestBody List<PaimentCouponStateDto> dtos) throws Exception {
        ResponseEntity<List<PaimentCouponStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PaimentCouponState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentCouponState")
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


    @Operation(summary = "Finds a paimentCouponState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentCouponStateDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentCouponState loaded =  service.findWithAssociatedLists(id);
        PaimentCouponStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentCouponStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentCouponStateDto>> findByCriteria(@RequestBody PaimentCouponStateCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentCouponStateDto>> res = null;
        List<PaimentCouponState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentCouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentCouponStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentCouponStateCriteria criteria) throws Exception {
        List<PaimentCouponState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaimentCouponStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentCouponState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentCouponStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentCouponStateDto> findDtos(List<PaimentCouponState> list){
        List<PaimentCouponStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentCouponStateDto> getDtoResponseEntity(PaimentCouponStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentCouponStateAdminService service;
    @Autowired private PaimentCouponStateConverter converter;





}
