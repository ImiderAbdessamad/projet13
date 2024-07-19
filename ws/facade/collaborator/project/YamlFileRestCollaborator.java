package  ma.zs.zyn.ws.facade.collaborator.project;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.YamlFileCriteria;
import ma.zs.zyn.service.facade.collaborator.project.YamlFileCollaboratorService;
import ma.zs.zyn.ws.converter.project.YamlFileConverter;
import ma.zs.zyn.ws.dto.project.YamlFileDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/yamlFile/")
public class YamlFileRestCollaborator {

    @Operation(summary = "count microservices")
    @PostMapping("count-microservices")
    public int countUniqueMicroservices(@RequestBody YamlFile yamlContent) {
        return service.countUniqueMicroservices(yamlContent);
    }

    @Operation(summary = "Finds a list of all yamlFiles")
    @GetMapping("")
    public ResponseEntity<List<YamlFileDto>> findAll() throws Exception {
        ResponseEntity<List<YamlFileDto>> res = null;
        List<YamlFile> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<YamlFileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Finds a list of all yamlFiles for a specific collaborator")
    @GetMapping("collaborator")
    public ResponseEntity<List<YamlFileDto>> findByCollaboratorUsername() {
        ResponseEntity<List<YamlFileDto>> res = null;
        List<YamlFile> list = service.findByCollaboratorUsername();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<YamlFileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;

    }

    @Operation(summary = "Finds an optimized list of all yamlFiles")
    @GetMapping("optimized")
    public ResponseEntity<List<YamlFileDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<YamlFileDto>> res = null;
        List<YamlFile> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<YamlFileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a yamlFile by id")
    @GetMapping("id/{id}")
    public ResponseEntity<YamlFileDto> findById(@PathVariable Long id) {
        YamlFile t = service.findById(id);
        if (t != null) {
            converter.init(true);
            YamlFileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a yamlFile by title")
    @GetMapping("title/{title}")
    public ResponseEntity<YamlFileDto> findByTitle(@PathVariable String title) {
	    YamlFile t = service.findByReferenceEntity(new YamlFile(title));
        if (t != null) {
            converter.init(true);
            YamlFileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  yamlFile")
    @PostMapping("")
    public ResponseEntity<YamlFileDto> save(@RequestBody YamlFileDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            YamlFile myT = converter.toItem(dto);
            YamlFile t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                YamlFileDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  yamlFile")
    @PutMapping("")
    public ResponseEntity<YamlFileDto> update(@RequestBody YamlFileDto dto) throws Exception {
        ResponseEntity<YamlFileDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            YamlFile t = service.findById(dto.getId());
            converter.copy(dto,t);
            YamlFile updated = service.update(t);
            YamlFileDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of yamlFile")
    @PostMapping("multiple")
    public ResponseEntity<List<YamlFileDto>> delete(@RequestBody List<YamlFileDto> dtos) throws Exception {
        ResponseEntity<List<YamlFileDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<YamlFile> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified yamlFile")
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


    @Operation(summary = "Finds a yamlFile and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<YamlFileDto> findWithAssociatedLists(@PathVariable Long id) {
        YamlFile loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        YamlFileDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds yamlFiles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<YamlFileDto>> findByCriteria(@RequestBody YamlFileCriteria criteria) throws Exception {
        ResponseEntity<List<YamlFileDto>> res = null;
        List<YamlFile> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<YamlFileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated yamlFiles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody YamlFileCriteria criteria) throws Exception {
        List<YamlFile> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<YamlFileDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets yamlFile data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody YamlFileCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<YamlFileDto> findDtos(List<YamlFile> list){
        converter.initObject(true);
        List<YamlFileDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<YamlFileDto> getDtoResponseEntity(YamlFileDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private YamlFileCollaboratorService service;
    @Autowired private YamlFileConverter converter;





}
