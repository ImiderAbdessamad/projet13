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

import ma.zs.zyn.bean.core.coupon.CouponState;
import ma.zs.zyn.dao.criteria.core.coupon.CouponStateCriteria;
import ma.zs.zyn.service.facade.admin.coupon.CouponStateAdminService;
import ma.zs.zyn.ws.converter.coupon.CouponStateConverter;
import ma.zs.zyn.ws.dto.coupon.CouponStateDto;
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
@RequestMapping("/api/admin/couponState/")
public class CouponStateRestAdmin {




    @Operation(summary = "Finds a list of all couponStates")
    @GetMapping("")
    public ResponseEntity<List<CouponStateDto>> findAll() throws Exception {
        ResponseEntity<List<CouponStateDto>> res = null;
        List<CouponState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all couponStates")
    @GetMapping("optimized")
    public ResponseEntity<List<CouponStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CouponStateDto>> res = null;
        List<CouponState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a couponState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CouponStateDto> findById(@PathVariable Long id) {
        CouponState t = service.findById(id);
        if (t != null) {
            CouponStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a couponState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CouponStateDto> findByLibelle(@PathVariable String libelle) {
	    CouponState t = service.findByReferenceEntity(new CouponState(libelle));
        if (t != null) {
            CouponStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  couponState")
    @PostMapping("")
    public ResponseEntity<CouponStateDto> save(@RequestBody CouponStateDto dto) throws Exception {
        if(dto!=null){
            CouponState myT = converter.toItem(dto);
            CouponState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CouponStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  couponState")
    @PutMapping("")
    public ResponseEntity<CouponStateDto> update(@RequestBody CouponStateDto dto) throws Exception {
        ResponseEntity<CouponStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CouponState t = service.findById(dto.getId());
            converter.copy(dto,t);
            CouponState updated = service.update(t);
            CouponStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of couponState")
    @PostMapping("multiple")
    public ResponseEntity<List<CouponStateDto>> delete(@RequestBody List<CouponStateDto> dtos) throws Exception {
        ResponseEntity<List<CouponStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CouponState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified couponState")
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


    @Operation(summary = "Finds a couponState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CouponStateDto> findWithAssociatedLists(@PathVariable Long id) {
        CouponState loaded =  service.findWithAssociatedLists(id);
        CouponStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds couponStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CouponStateDto>> findByCriteria(@RequestBody CouponStateCriteria criteria) throws Exception {
        ResponseEntity<List<CouponStateDto>> res = null;
        List<CouponState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CouponStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated couponStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CouponStateCriteria criteria) throws Exception {
        List<CouponState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CouponStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets couponState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CouponStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CouponStateDto> findDtos(List<CouponState> list){
        List<CouponStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CouponStateDto> getDtoResponseEntity(CouponStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CouponStateAdminService service;
    @Autowired private CouponStateConverter converter;





}
