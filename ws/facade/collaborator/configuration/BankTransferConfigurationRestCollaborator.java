package ma.zs.zyn.ws.facade.collaborator.configuration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.dao.criteria.core.configuration.BankTransferConfigurationCriteria;
import ma.zs.zyn.service.facade.collaborator.configuration.BankTransferConfigurationCollaboratorService;
import ma.zs.zyn.ws.converter.configuration.BankTransferConfigurationConverter;
import ma.zs.zyn.ws.dto.configuration.BankTransferConfigurationDto;
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
@RequestMapping("/api/collaborator/bankTransferConfiguration/")
public class BankTransferConfigurationRestCollaborator {




    @Operation(summary = "Finds a list of all bankTransferConfigurations")
    @GetMapping("")
    public ResponseEntity<List<BankTransferConfigurationDto>> findAll() throws Exception {
        ResponseEntity<List<BankTransferConfigurationDto>> res = null;
        List<BankTransferConfiguration> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BankTransferConfigurationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a bankTransferConfiguration by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BankTransferConfigurationDto> findById(@PathVariable Long id) {
        BankTransferConfiguration t = service.findById(id);
        if (t != null) {
            BankTransferConfigurationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  bankTransferConfiguration")
    @PostMapping("")
    public ResponseEntity<BankTransferConfigurationDto> save(@RequestBody BankTransferConfigurationDto dto) throws Exception {
        if(dto!=null){
            BankTransferConfiguration myT = converter.toItem(dto);
            BankTransferConfiguration t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BankTransferConfigurationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  bankTransferConfiguration")
    @PutMapping("")
    public ResponseEntity<BankTransferConfigurationDto> update(@RequestBody BankTransferConfigurationDto dto) throws Exception {
        ResponseEntity<BankTransferConfigurationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BankTransferConfiguration t = service.findById(dto.getId());
            converter.copy(dto,t);
            BankTransferConfiguration updated = service.update(t);
            BankTransferConfigurationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of bankTransferConfiguration")
    @PostMapping("multiple")
    public ResponseEntity<List<BankTransferConfigurationDto>> delete(@RequestBody List<BankTransferConfigurationDto> dtos) throws Exception {
        ResponseEntity<List<BankTransferConfigurationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<BankTransferConfiguration> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified bankTransferConfiguration")
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


    @Operation(summary = "Finds a bankTransferConfiguration and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BankTransferConfigurationDto> findWithAssociatedLists(@PathVariable Long id) {
        BankTransferConfiguration loaded =  service.findWithAssociatedLists(id);
        BankTransferConfigurationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds bankTransferConfigurations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BankTransferConfigurationDto>> findByCriteria(@RequestBody BankTransferConfigurationCriteria criteria) throws Exception {
        ResponseEntity<List<BankTransferConfigurationDto>> res = null;
        List<BankTransferConfiguration> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BankTransferConfigurationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated bankTransferConfigurations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BankTransferConfigurationCriteria criteria) throws Exception {
        List<BankTransferConfiguration> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<BankTransferConfigurationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets bankTransferConfiguration data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BankTransferConfigurationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BankTransferConfigurationDto> findDtos(List<BankTransferConfiguration> list){
        List<BankTransferConfigurationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BankTransferConfigurationDto> getDtoResponseEntity(BankTransferConfigurationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BankTransferConfigurationCollaboratorService service;
    @Autowired private BankTransferConfigurationConverter converter;





}
