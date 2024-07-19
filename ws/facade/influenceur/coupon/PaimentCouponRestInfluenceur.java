package ma.zs.zyn.ws.facade.influenceur.coupon;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.coupon.PaimentCoupon;
import ma.zs.zyn.dao.criteria.core.coupon.PaimentCouponCriteria;
import ma.zs.zyn.service.facade.influenceur.coupon.PaimentCouponInfluenceurService;
import ma.zs.zyn.ws.converter.coupon.PaimentCouponConverter;
import ma.zs.zyn.ws.dto.coupon.PaimentCouponDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/influencer/paimentCoupon/")
public class PaimentCouponRestInfluenceur {




    @Operation(summary = "Finds a list of all paimentCoupons")
    @GetMapping("")
    public ResponseEntity<List<PaimentCouponDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentCouponDto>> res = null;
        List<PaimentCoupon> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<PaimentCouponDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a paimentCoupon by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentCouponDto> findById(@PathVariable Long id) {
        PaimentCoupon t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PaimentCouponDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  paimentCoupon")
    @PostMapping("")
    public ResponseEntity<PaimentCouponDto> save(@RequestBody PaimentCouponDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            PaimentCoupon myT = converter.toItem(dto);
            PaimentCoupon t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentCouponDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentCoupon")
    @PutMapping("")
    public ResponseEntity<PaimentCouponDto> update(@RequestBody PaimentCouponDto dto) throws Exception {
        ResponseEntity<PaimentCouponDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentCoupon t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentCoupon updated = service.update(t);
            PaimentCouponDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentCoupon")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentCouponDto>> delete(@RequestBody List<PaimentCouponDto> dtos) throws Exception {
        ResponseEntity<List<PaimentCouponDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PaimentCoupon> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentCoupon")
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

    @Operation(summary = "find by coupon id")
    @GetMapping("coupon/id/{id}")
    public List<PaimentCouponDto> findByCouponId(@PathVariable Long id){
        return findDtos(service.findByCouponId(id));
    }
    @Operation(summary = "delete by coupon id")
    @DeleteMapping("coupon/id/{id}")
    public int deleteByCouponId(@PathVariable Long id){
        return service.deleteByCouponId(id);
    }
    @Operation(summary = "find by paimentCouponState libelle")
    @GetMapping("paimentCouponState/libelle/{libelle}")
    public List<PaimentCouponDto> findByPaimentCouponStateLibelle(@PathVariable String libelle){
        return findDtos(service.findByPaimentCouponStateLibelle(libelle));
    }
    @Operation(summary = "delete by paimentCouponState libelle")
    @DeleteMapping("paimentCouponState/libelle/{libelle}")
    public int deleteByPaimentCouponStateLibelle(@PathVariable String libelle){
        return service.deleteByPaimentCouponStateLibelle(libelle);
    }

    @Operation(summary = "Finds a paimentCoupon and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentCouponDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentCoupon loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PaimentCouponDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentCoupons by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentCouponDto>> findByCriteria(@RequestBody PaimentCouponCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentCouponDto>> res = null;
        List<PaimentCoupon> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentCouponDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentCoupons by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentCouponCriteria criteria) throws Exception {
        List<PaimentCoupon> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PaimentCouponDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentCoupon data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentCouponCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentCouponDto> findDtos(List<PaimentCoupon> list){
        converter.initObject(true);
        List<PaimentCouponDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentCouponDto> getDtoResponseEntity(PaimentCouponDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentCouponInfluenceurService service;
    @Autowired private PaimentCouponConverter converter;





}
