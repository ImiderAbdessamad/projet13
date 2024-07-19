package ma.zs.zyn.ws.facade.admin.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.ProjectTechnologyProfile;
import ma.zs.zyn.dao.criteria.core.project.ProjectTechnologyProfileCriteria;
import ma.zs.zyn.service.facade.admin.project.ProjectTechnologyProfileAdminService;
import ma.zs.zyn.ws.converter.project.ProjectTechnologyProfileConverter;
import ma.zs.zyn.ws.dto.project.ProjectTechnologyProfileDto;
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
@RequestMapping("/api/admin/projectTechnologyProfile/")
public class ProjectTechnologyProfileRestAdmin {




    @Operation(summary = "Finds a list of all projectTechnologyProfiles")
    @GetMapping("")
    public ResponseEntity<List<ProjectTechnologyProfileDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTechnologyProfileDto>> res = null;
        List<ProjectTechnologyProfile> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectTechnologyProfiles")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectTechnologyProfileDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectTechnologyProfileDto>> res = null;
        List<ProjectTechnologyProfile> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectTechnologyProfile by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTechnologyProfileDto> findById(@PathVariable Long id) {
        ProjectTechnologyProfile t = service.findById(id);
        if (t != null) {
            ProjectTechnologyProfileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectTechnologyProfile by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProjectTechnologyProfileDto> findByLibelle(@PathVariable String libelle) {
	    ProjectTechnologyProfile t = service.findByReferenceEntity(new ProjectTechnologyProfile(libelle));
        if (t != null) {
            ProjectTechnologyProfileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectTechnologyProfile")
    @PostMapping("")
    public ResponseEntity<ProjectTechnologyProfileDto> save(@RequestBody ProjectTechnologyProfileDto dto) throws Exception {
        if(dto!=null){
            ProjectTechnologyProfile myT = converter.toItem(dto);
            ProjectTechnologyProfile t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectTechnologyProfileDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectTechnologyProfile")
    @PutMapping("")
    public ResponseEntity<ProjectTechnologyProfileDto> update(@RequestBody ProjectTechnologyProfileDto dto) throws Exception {
        ResponseEntity<ProjectTechnologyProfileDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectTechnologyProfile t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectTechnologyProfile updated = service.update(t);
            ProjectTechnologyProfileDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectTechnologyProfile")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTechnologyProfileDto>> delete(@RequestBody List<ProjectTechnologyProfileDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTechnologyProfileDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProjectTechnologyProfile> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectTechnologyProfile")
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


    @Operation(summary = "Finds a projectTechnologyProfile and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTechnologyProfileDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectTechnologyProfile loaded =  service.findWithAssociatedLists(id);
        ProjectTechnologyProfileDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTechnologyProfiles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTechnologyProfileDto>> findByCriteria(@RequestBody ProjectTechnologyProfileCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTechnologyProfileDto>> res = null;
        List<ProjectTechnologyProfile> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTechnologyProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTechnologyProfiles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTechnologyProfileCriteria criteria) throws Exception {
        List<ProjectTechnologyProfile> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProjectTechnologyProfileDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectTechnologyProfile data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTechnologyProfileCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectTechnologyProfileDto> findDtos(List<ProjectTechnologyProfile> list){
        List<ProjectTechnologyProfileDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTechnologyProfileDto> getDtoResponseEntity(ProjectTechnologyProfileDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectTechnologyProfileAdminService service;
    @Autowired private ProjectTechnologyProfileConverter converter;





}
