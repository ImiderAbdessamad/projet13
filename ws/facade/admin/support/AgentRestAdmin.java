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

import ma.zs.zyn.bean.core.support.Agent;
import ma.zs.zyn.dao.criteria.core.support.AgentCriteria;
import ma.zs.zyn.service.facade.admin.support.AgentAdminService;
import ma.zs.zyn.ws.converter.support.AgentConverter;
import ma.zs.zyn.ws.dto.support.AgentDto;
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
@RequestMapping("/api/admin/agent/")
public class AgentRestAdmin {




    @Operation(summary = "Finds a list of all agents")
    @GetMapping("")
    public ResponseEntity<List<AgentDto>> findAll() throws Exception {
        ResponseEntity<List<AgentDto>> res = null;
        List<Agent> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AgentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a agent by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AgentDto> findById(@PathVariable Long id) {
        Agent t = service.findById(id);
        if (t != null) {
            AgentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  agent")
    @PostMapping("")
    public ResponseEntity<AgentDto> save(@RequestBody AgentDto dto) throws Exception {
        if(dto!=null){
            Agent myT = converter.toItem(dto);
            Agent t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AgentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  agent")
    @PutMapping("")
    public ResponseEntity<AgentDto> update(@RequestBody AgentDto dto) throws Exception {
        ResponseEntity<AgentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Agent t = service.findById(dto.getId());
            converter.copy(dto,t);
            Agent updated = service.update(t);
            AgentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of agent")
    @PostMapping("multiple")
    public ResponseEntity<List<AgentDto>> delete(@RequestBody List<AgentDto> dtos) throws Exception {
        ResponseEntity<List<AgentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Agent> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified agent")
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


    @Operation(summary = "Finds a agent and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AgentDto> findWithAssociatedLists(@PathVariable Long id) {
        Agent loaded =  service.findWithAssociatedLists(id);
        AgentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds agents by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AgentDto>> findByCriteria(@RequestBody AgentCriteria criteria) throws Exception {
        ResponseEntity<List<AgentDto>> res = null;
        List<Agent> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AgentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated agents by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AgentCriteria criteria) throws Exception {
        List<Agent> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AgentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets agent data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AgentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AgentDto> findDtos(List<Agent> list){
        List<AgentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AgentDto> getDtoResponseEntity(AgentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public AgentRestAdmin(AgentAdminService service, AgentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AgentAdminService service;
    private final AgentConverter converter;





}
