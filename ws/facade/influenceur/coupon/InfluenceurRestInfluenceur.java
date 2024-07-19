package ma.zs.zyn.ws.facade.influenceur.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.coupon.Influenceur;
import ma.zs.zyn.dao.criteria.core.coupon.InfluenceurCriteria;
import ma.zs.zyn.service.facade.influenceur.coupon.InfluenceurInfluenceurService;
import ma.zs.zyn.ws.converter.coupon.InfluenceurConverter;
import ma.zs.zyn.ws.dto.coupon.InfluenceurDto;
import ma.zs.zyn.zynerator.controller.AbstractController;
import ma.zs.zyn.zynerator.dto.AuditEntityDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import ma.zs.zyn.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.zyn.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.zyn.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/influencer/influencer/")
public class InfluenceurRestInfluenceur {




    @Operation(summary = "Finds a list of all influenceurs")
    @GetMapping("")
    public ResponseEntity<List<InfluenceurDto>> findAll() throws Exception {
        ResponseEntity<List<InfluenceurDto>> res = null;
        List<Influenceur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InfluenceurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a influenceur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InfluenceurDto> findById(@PathVariable Long id) {
        Influenceur t = service.findById(id);
        if (t != null) {
            InfluenceurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  influenceur")
    @PostMapping("")
    public ResponseEntity<InfluenceurDto> save(@RequestBody InfluenceurDto dto) throws Exception {
        if(dto!=null){
            Influenceur myT = converter.toItem(dto);
            Influenceur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InfluenceurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  influenceur")
    @PutMapping("")
    public ResponseEntity<InfluenceurDto> update(@RequestBody InfluenceurDto dto) throws Exception {
        ResponseEntity<InfluenceurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Influenceur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Influenceur updated = service.update(t);
            InfluenceurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of influenceur")
    @PostMapping("multiple")
    public ResponseEntity<List<InfluenceurDto>> delete(@RequestBody List<InfluenceurDto> dtos) throws Exception {
        ResponseEntity<List<InfluenceurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Influenceur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified influenceur")
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


    @Operation(summary = "Finds a influenceur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InfluenceurDto> findWithAssociatedLists(@PathVariable Long id) {
        Influenceur loaded =  service.findWithAssociatedLists(id);
        InfluenceurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds influenceurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InfluenceurDto>> findByCriteria(@RequestBody InfluenceurCriteria criteria) throws Exception {
        ResponseEntity<List<InfluenceurDto>> res = null;
        List<Influenceur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InfluenceurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated influenceurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InfluenceurCriteria criteria) throws Exception {
        List<Influenceur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InfluenceurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets influenceur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InfluenceurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InfluenceurDto> findDtos(List<Influenceur> list){
        List<InfluenceurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InfluenceurDto> getDtoResponseEntity(InfluenceurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private InfluenceurInfluenceurService service;
    @Autowired private InfluenceurConverter converter;





}
