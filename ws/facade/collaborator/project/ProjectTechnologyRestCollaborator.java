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

import ma.zs.zyn.bean.core.project.ProjectTechnology;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyCriteria;
import ma.zs.zyn.service.facade.collaborator.project.ProjectTechnologyCollaboratorService;
import ma.zs.zyn.ws.converter.project.ProjectTechnologyConverter;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyDto;
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
@RequestMapping("/api/collaborator/projectTechnology/")
public class ProjectTechnologyRestCollaborator {


    @Operation(summary = "Finds a list of all projectTechnologys")
    @GetMapping("")
    public ResponseEntity<List<ProjectTechnologyDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTechnologyDto>> res = null;
        List<ProjectTechnology> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectTechnologyDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectTechnologys")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectTechnologyDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectTechnologyDto>> res = null;
        List<ProjectTechnology> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectTechnologyDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a projectTechnology by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTechnologyDto> findById(@PathVariable Long id) {
        ProjectTechnology t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectTechnologyDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectTechnology by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProjectTechnologyDto> findByLibelle(@PathVariable String libelle) {
        ProjectTechnology t = service.findByReferenceEntity(new ProjectTechnology(libelle));
        if (t != null) {
            converter.init(true);
            ProjectTechnologyDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectTechnology")
    @PostMapping("")
    public ResponseEntity<ProjectTechnologyDto> save(@RequestBody ProjectTechnologyDto dto) throws Exception {
        if (dto != null) {
            converter.init(true);
            ProjectTechnology myT = converter.toItem(dto);
            ProjectTechnology t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                ProjectTechnologyDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectTechnology")
    @PutMapping("")
    public ResponseEntity<ProjectTechnologyDto> update(@RequestBody ProjectTechnologyDto dto) throws Exception {
        ResponseEntity<ProjectTechnologyDto> res;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectTechnology t = service.findById(dto.getId());
            converter.copy(dto, t);
            ProjectTechnology updated = service.update(t);
            ProjectTechnologyDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectTechnology")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTechnologyDto>> delete(@RequestBody List<ProjectTechnologyDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTechnologyDto>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjectTechnology> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectTechnology")
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

    @Operation(summary = "find by projectTechnologyType code")
    @GetMapping("projectTechnologyType/code/{code}")
    public List<ProjectTechnologyDto> findByProjectTechnologyTypeCode(@PathVariable String code) {
        return findDtos(service.findByProjectTechnologyTypeCode(code));
    }

    @Operation(summary = "find by projectTechnologyType code")
    @GetMapping("projectTechnologyTypeDb")
    public List<ProjectTechnologyDto> findByProjectTechnologyTypeDb() {
        List<ProjectTechnology> byProjectTechnologyTypeCodeDb = service.findByProjectTechnologyTypeCodeDb();
        return findDtos(byProjectTechnologyTypeCodeDb);
    }

    @Operation(summary = "find by projectTechnologyType code")
    @GetMapping("projectTechnologyTypeBack")
    public List<ProjectTechnologyDto> findByProjectTechnologyTypeBack() {
        List<ProjectTechnology> byProjectTechnologyTypeCodeBack = service.findByProjectTechnologyTypeCodeBack();
        return findDtos(byProjectTechnologyTypeCodeBack);
    }

    @Operation(summary = "find by projectTechnologyType code")
    @GetMapping("projectTechnologyTypeFront")
    public List<ProjectTechnologyDto> findByProjectTechnologyTypeFront() {
        List<ProjectTechnology> byProjectTechnologyTypeCodeFront = service.findByProjectTechnologyTypeCodeFront();
        return findDtos(byProjectTechnologyTypeCodeFront);
    }

    @Operation(summary = "delete by projectTechnologyType code")
    @DeleteMapping("projectTechnologyType/code/{code}")
    public int deleteByProjectTechnologyTypeCode(@PathVariable String code) {
        return service.deleteByProjectTechnologyTypeCode(code);
    }

    @Operation(summary = "Finds a projectTechnology and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTechnologyDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectTechnology loaded = service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectTechnologyDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTechnologys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTechnologyDto>> findByCriteria(@RequestBody ProjectTechnologyCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTechnologyDto>> res = null;
        List<ProjectTechnology> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectTechnologyDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTechnologys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTechnologyCriteria criteria) throws Exception {
        List<ProjectTechnology> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProjectTechnologyDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectTechnology data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTechnologyCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<ProjectTechnologyDto> findDtos(List<ProjectTechnology> list) {
        converter.initObject(true);
        List<ProjectTechnologyDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTechnologyDto> getDtoResponseEntity(ProjectTechnologyDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Autowired
    private ProjectTechnologyCollaboratorService service;
    @Autowired
    private ProjectTechnologyConverter converter;


}
