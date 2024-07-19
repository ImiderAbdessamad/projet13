package ma.zs.zyn.ws.facade.admin.contactus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.contactus.ContactUsState;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsStateCriteria;
import ma.zs.zyn.service.facade.admin.contactus.ContactUsStateAdminService;
import ma.zs.zyn.ws.converter.contactus.ContactUsStateConverter;
import ma.zs.zyn.ws.dto.contactus.ContactUsStateDto;
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
@RequestMapping("/api/admin/contactUsState/")
public class ContactUsStateRestAdmin {




    @Operation(summary = "Finds a list of all contactUsStates")
    @GetMapping("")
    public ResponseEntity<List<ContactUsStateDto>> findAll() throws Exception {
        ResponseEntity<List<ContactUsStateDto>> res = null;
        List<ContactUsState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all contactUsStates")
    @GetMapping("optimized")
    public ResponseEntity<List<ContactUsStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ContactUsStateDto>> res = null;
        List<ContactUsState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a contactUsState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ContactUsStateDto> findById(@PathVariable Long id) {
        ContactUsState t = service.findById(id);
        if (t != null) {
            ContactUsStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a contactUsState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ContactUsStateDto> findByLibelle(@PathVariable String libelle) {
	    ContactUsState t = service.findByReferenceEntity(new ContactUsState(libelle));
        if (t != null) {
            ContactUsStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  contactUsState")
    @PostMapping("")
    public ResponseEntity<ContactUsStateDto> save(@RequestBody ContactUsStateDto dto) throws Exception {
        if(dto!=null){
            ContactUsState myT = converter.toItem(dto);
            ContactUsState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ContactUsStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  contactUsState")
    @PutMapping("")
    public ResponseEntity<ContactUsStateDto> update(@RequestBody ContactUsStateDto dto) throws Exception {
        ResponseEntity<ContactUsStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ContactUsState t = service.findById(dto.getId());
            converter.copy(dto,t);
            ContactUsState updated = service.update(t);
            ContactUsStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of contactUsState")
    @PostMapping("multiple")
    public ResponseEntity<List<ContactUsStateDto>> delete(@RequestBody List<ContactUsStateDto> dtos) throws Exception {
        ResponseEntity<List<ContactUsStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ContactUsState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified contactUsState")
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


    @Operation(summary = "Finds a contactUsState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ContactUsStateDto> findWithAssociatedLists(@PathVariable Long id) {
        ContactUsState loaded =  service.findWithAssociatedLists(id);
        ContactUsStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds contactUsStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ContactUsStateDto>> findByCriteria(@RequestBody ContactUsStateCriteria criteria) throws Exception {
        ResponseEntity<List<ContactUsStateDto>> res = null;
        List<ContactUsState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated contactUsStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ContactUsStateCriteria criteria) throws Exception {
        List<ContactUsState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ContactUsStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets contactUsState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ContactUsStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ContactUsStateDto> findDtos(List<ContactUsState> list){
        List<ContactUsStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ContactUsStateDto> getDtoResponseEntity(ContactUsStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ContactUsStateAdminService service;
    @Autowired private ContactUsStateConverter converter;





}
