package ma.zs.zyn.ws.facade.collaborator.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.ProjectTechnologyType;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyTypeCriteria;
import ma.zs.zyn.service.facade.collaborator.project.ProjectTechnologyTypeCollaboratorService;
import ma.zs.zyn.ws.converter.project.ProjectTechnologyTypeConverter;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyTypeDto;
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
@RequestMapping("/api/collaborator/projectTechnologyType/")
public class ProjectTechnologyTypeRestCollaborator {




    @Operation(summary = "Finds a list of all projectTechnologyTypes")
    @GetMapping("")
    public ResponseEntity<List<ProjectTechnologyTypeDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTechnologyTypeDto>> res = null;
        List<ProjectTechnologyType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectTechnologyTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectTechnologyTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectTechnologyTypeDto>> res = null;
        List<ProjectTechnologyType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectTechnologyType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTechnologyTypeDto> findById(@PathVariable Long id) {
        ProjectTechnologyType t = service.findById(id);
        if (t != null) {
            ProjectTechnologyTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectTechnologyType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProjectTechnologyTypeDto> findByLibelle(@PathVariable String libelle) {
	    ProjectTechnologyType t = service.findByReferenceEntity(new ProjectTechnologyType(libelle));
        if (t != null) {
            ProjectTechnologyTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectTechnologyType")
    @PostMapping("")
    public ResponseEntity<ProjectTechnologyTypeDto> save(@RequestBody ProjectTechnologyTypeDto dto) throws Exception {
        if(dto!=null){
            ProjectTechnologyType myT = converter.toItem(dto);
            ProjectTechnologyType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectTechnologyTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectTechnologyType")
    @PutMapping("")
    public ResponseEntity<ProjectTechnologyTypeDto> update(@RequestBody ProjectTechnologyTypeDto dto) throws Exception {
        ResponseEntity<ProjectTechnologyTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectTechnologyType t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectTechnologyType updated = service.update(t);
            ProjectTechnologyTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectTechnologyType")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTechnologyTypeDto>> delete(@RequestBody List<ProjectTechnologyTypeDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTechnologyTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProjectTechnologyType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectTechnologyType")
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


    @Operation(summary = "Finds a projectTechnologyType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTechnologyTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectTechnologyType loaded =  service.findWithAssociatedLists(id);
        ProjectTechnologyTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTechnologyTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTechnologyTypeDto>> findByCriteria(@RequestBody ProjectTechnologyTypeCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTechnologyTypeDto>> res = null;
        List<ProjectTechnologyType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTechnologyTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTechnologyTypeCriteria criteria) throws Exception {
        List<ProjectTechnologyType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProjectTechnologyTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectTechnologyType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTechnologyTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectTechnologyTypeDto> findDtos(List<ProjectTechnologyType> list){
        List<ProjectTechnologyTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTechnologyTypeDto> getDtoResponseEntity(ProjectTechnologyTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectTechnologyTypeCollaboratorService service;
    @Autowired private ProjectTechnologyTypeConverter converter;





}
