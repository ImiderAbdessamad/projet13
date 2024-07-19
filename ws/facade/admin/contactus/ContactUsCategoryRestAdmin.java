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

import ma.zs.zyn.bean.core.contactus.ContactUsCategory;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCategoryCriteria;
import ma.zs.zyn.service.facade.admin.contactus.ContactUsCategoryAdminService;
import ma.zs.zyn.ws.converter.contactus.ContactUsCategoryConverter;
import ma.zs.zyn.ws.dto.contactus.ContactUsCategoryDto;
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
@RequestMapping("/api/admin/contactUsCategory/")
public class ContactUsCategoryRestAdmin {




    @Operation(summary = "Finds a list of all contactUsCategorys")
    @GetMapping("")
    public ResponseEntity<List<ContactUsCategoryDto>> findAll() throws Exception {
        ResponseEntity<List<ContactUsCategoryDto>> res = null;
        List<ContactUsCategory> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all contactUsCategorys")
    @GetMapping("optimized")
    public ResponseEntity<List<ContactUsCategoryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ContactUsCategoryDto>> res = null;
        List<ContactUsCategory> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a contactUsCategory by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ContactUsCategoryDto> findById(@PathVariable Long id) {
        ContactUsCategory t = service.findById(id);
        if (t != null) {
            ContactUsCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a contactUsCategory by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ContactUsCategoryDto> findByLibelle(@PathVariable String libelle) {
	    ContactUsCategory t = service.findByReferenceEntity(new ContactUsCategory(libelle));
        if (t != null) {
            ContactUsCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  contactUsCategory")
    @PostMapping("")
    public ResponseEntity<ContactUsCategoryDto> save(@RequestBody ContactUsCategoryDto dto) throws Exception {
        if(dto!=null){
            ContactUsCategory myT = converter.toItem(dto);
            ContactUsCategory t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ContactUsCategoryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  contactUsCategory")
    @PutMapping("")
    public ResponseEntity<ContactUsCategoryDto> update(@RequestBody ContactUsCategoryDto dto) throws Exception {
        ResponseEntity<ContactUsCategoryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ContactUsCategory t = service.findById(dto.getId());
            converter.copy(dto,t);
            ContactUsCategory updated = service.update(t);
            ContactUsCategoryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of contactUsCategory")
    @PostMapping("multiple")
    public ResponseEntity<List<ContactUsCategoryDto>> delete(@RequestBody List<ContactUsCategoryDto> dtos) throws Exception {
        ResponseEntity<List<ContactUsCategoryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ContactUsCategory> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified contactUsCategory")
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


    @Operation(summary = "Finds a contactUsCategory and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ContactUsCategoryDto> findWithAssociatedLists(@PathVariable Long id) {
        ContactUsCategory loaded =  service.findWithAssociatedLists(id);
        ContactUsCategoryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds contactUsCategorys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ContactUsCategoryDto>> findByCriteria(@RequestBody ContactUsCategoryCriteria criteria) throws Exception {
        ResponseEntity<List<ContactUsCategoryDto>> res = null;
        List<ContactUsCategory> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ContactUsCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated contactUsCategorys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ContactUsCategoryCriteria criteria) throws Exception {
        List<ContactUsCategory> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ContactUsCategoryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets contactUsCategory data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ContactUsCategoryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ContactUsCategoryDto> findDtos(List<ContactUsCategory> list){
        List<ContactUsCategoryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ContactUsCategoryDto> getDtoResponseEntity(ContactUsCategoryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ContactUsCategoryAdminService service;
    @Autowired private ContactUsCategoryConverter converter;





}
