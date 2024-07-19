package ma.zs.zyn.ws.facade.admin.project;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.project.ProjectDetail;
import ma.zs.zyn.dao.criteria.core.project.ProjectDetailCriteria;
import ma.zs.zyn.service.facade.admin.project.ProjectDetailAdminService;
import ma.zs.zyn.ws.converter.project.ProjectDetailConverter;
import ma.zs.zyn.ws.dto.project.ProjectDetailDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/projectDetail/")
public class ProjectDetailRestAdmin {




    @Operation(summary = "Finds a list of all projectDetails")
    @GetMapping("")
    public ResponseEntity<List<ProjectDetailDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectDetailDto>> res = null;
        List<ProjectDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProjectDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectDetails")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectDetailDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectDetailDto>> res = null;
        List<ProjectDetail> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectDetailDto> findById(@PathVariable Long id) {
        ProjectDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectDetail by title")
    @GetMapping("title/{title}")
    public ResponseEntity<ProjectDetailDto> findByTitle(@PathVariable String title) {
	    ProjectDetail t = service.findByReferenceEntity(new ProjectDetail(title));
        if (t != null) {
            converter.init(true);
            ProjectDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectDetail")
    @PostMapping("")
    public ResponseEntity<ProjectDetailDto> save(@RequestBody ProjectDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ProjectDetail myT = converter.toItem(dto);
            ProjectDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectDetail")
    @PutMapping("")
    public ResponseEntity<ProjectDetailDto> update(@RequestBody ProjectDetailDto dto) throws Exception {
        ResponseEntity<ProjectDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectDetail updated = service.update(t);
            ProjectDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectDetailDto>> delete(@RequestBody List<ProjectDetailDto> dtos) throws Exception {
        ResponseEntity<List<ProjectDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjectDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectDetail")
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

    @Operation(summary = "find by projectTechnology id")
    @GetMapping("projectTechnology/id/{id}")
    public List<ProjectDetailDto> findByProjectTechnologyId(@PathVariable Long id){
        return findDtos(service.findByProjectTechnologyId(id));
    }
    @Operation(summary = "delete by projectTechnology id")
    @DeleteMapping("projectTechnology/id/{id}")
    public int deleteByProjectTechnologyId(@PathVariable Long id){
        return service.deleteByProjectTechnologyId(id);
    }
    @Operation(summary = "find by project id")
    @GetMapping("project/id/{id}")
    public List<ProjectDetailDto> findByProjectId(@PathVariable Long id){
        return findDtos(service.findByProjectId(id));
    }
    @Operation(summary = "delete by project id")
    @DeleteMapping("project/id/{id}")
    public int deleteByProjectId(@PathVariable Long id){
        return service.deleteByProjectId(id);
    }
    @Operation(summary = "find by projectTechnologyProfile code")
    @GetMapping("projectTechnologyProfile/code/{code}")
    public List<ProjectDetailDto> findByProjectTechnologyProfileCode(@PathVariable String code){
        return findDtos(service.findByProjectTechnologyProfileCode(code));
    }
    @Operation(summary = "delete by projectTechnologyProfile code")
    @DeleteMapping("projectTechnologyProfile/code/{code}")
    public int deleteByProjectTechnologyProfileCode(@PathVariable String code){
        return service.deleteByProjectTechnologyProfileCode(code);
    }

    @Operation(summary = "Finds a projectDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectDetailDto>> findByCriteria(@RequestBody ProjectDetailCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectDetailDto>> res = null;
        List<ProjectDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectDetailCriteria criteria) throws Exception {
        List<ProjectDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProjectDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectDetailDto> findDtos(List<ProjectDetail> list){
        converter.initObject(true);
        List<ProjectDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectDetailDto> getDtoResponseEntity(ProjectDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectDetailAdminService service;
    @Autowired private ProjectDetailConverter converter;





}
