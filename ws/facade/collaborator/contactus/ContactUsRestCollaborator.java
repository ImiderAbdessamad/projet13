package ma.zs.zyn.ws.facade.collaborator.contactus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.contactus.ContactUs;
import ma.zs.zyn.dao.criteria.core.contactus.ContactUsCriteria;
import ma.zs.zyn.service.facade.collaborator.contactus.ContactUsCollaboratorService;
import ma.zs.zyn.ws.converter.contactus.ContactUsConverter;
import ma.zs.zyn.ws.dto.contactus.ContactUsDto;
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
@RequestMapping("/api/collaborator/contactUs/")
public class ContactUsRestCollaborator {




    @Operation(summary = "Finds a list of all contactUss")
    @GetMapping("")
    public ResponseEntity<List<ContactUsDto>> findAll() throws Exception {
        ResponseEntity<List<ContactUsDto>> res = null;
        List<ContactUs> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ContactUsDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a contactUs by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ContactUsDto> findById(@PathVariable Long id) {
        ContactUs t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ContactUsDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  contactUs")
    @PostMapping("")
    public ResponseEntity<ContactUsDto> save(@RequestBody ContactUsDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ContactUs myT = converter.toItem(dto);
            ContactUs t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ContactUsDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  contactUs")
    @PutMapping("")
    public ResponseEntity<ContactUsDto> update(@RequestBody ContactUsDto dto) throws Exception {
        ResponseEntity<ContactUsDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ContactUs t = service.findById(dto.getId());
            converter.copy(dto,t);
            ContactUs updated = service.update(t);
            ContactUsDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of contactUs")
    @PostMapping("multiple")
    public ResponseEntity<List<ContactUsDto>> delete(@RequestBody List<ContactUsDto> dtos) throws Exception {
        ResponseEntity<List<ContactUsDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ContactUs> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified contactUs")
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

    @Operation(summary = "find by contactUsCategory id")
    @GetMapping("contactUsCategory/id/{id}")
    public List<ContactUsDto> findByContactUsCategoryId(@PathVariable Long id){
        return findDtos(service.findByContactUsCategoryId(id));
    }
    @Operation(summary = "delete by contactUsCategory id")
    @DeleteMapping("contactUsCategory/id/{id}")
    public int deleteByContactUsCategoryId(@PathVariable Long id){
        return service.deleteByContactUsCategoryId(id);
    }

    @Operation(summary = "Finds a contactUs and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ContactUsDto> findWithAssociatedLists(@PathVariable Long id) {
        ContactUs loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ContactUsDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds contactUss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ContactUsDto>> findByCriteria(@RequestBody ContactUsCriteria criteria) throws Exception {
        ResponseEntity<List<ContactUsDto>> res = null;
        List<ContactUs> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ContactUsDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated contactUss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ContactUsCriteria criteria) throws Exception {
        List<ContactUs> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ContactUsDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets contactUs data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ContactUsCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ContactUsDto> findDtos(List<ContactUs> list){
        converter.initObject(true);
        List<ContactUsDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ContactUsDto> getDtoResponseEntity(ContactUsDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ContactUsCollaboratorService service;
    @Autowired private ContactUsConverter converter;





}
