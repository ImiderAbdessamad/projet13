package  ma.zs.zyn.ws.facade.collaborator.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.bean.core.project.YamlFile;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.service.facade.collaborator.project.ProjectCollaboratorService;
import ma.zs.zyn.service.impl.collaborator.util.ProcessResult;
import ma.zs.zyn.ws.converter.project.ConversationConverter;
import ma.zs.zyn.ws.converter.project.ProjectConverter;
import ma.zs.zyn.ws.converter.project.YamlFileConverter;
import ma.zs.zyn.ws.dto.project.ConversationDto;
import ma.zs.zyn.ws.dto.project.ProjectDto;
import ma.zs.zyn.ws.dto.project.YamlFileDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/collaborator/project/")
public class ProjectRestCollaborator {
    @PostMapping("prompt")
    public ResponseEntity<Object> getResponse(@RequestBody ConversationDto conversation) throws JsonProcessingException {
        converter.initObject(true);
        Conversation myT = conversationConverter.toItem(conversation);
        try {
            Conversation item = service.callApi(myT);
            ConversationDto dto = conversationConverter.toDto(item);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (
                ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("classifyProjects")
    public Map<String, List<ProjectDto>> classifyProjects() {
        converter.initObject(true);
        converter.initList(false);
        Map<String, List<Project>> stringListMap = service.classifyProjects();
        return stringListMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> converter.toDto(e.getValue())
                ));
    }


    @GetMapping("newChat")
    public ProjectDto newChat() {
        converter.initObject(true);
        Project myT = service.newProject();
        ProjectDto dto = converter.toDto(myT);
        return dto;
    }

    @PutMapping("generateTitle/{projectDescription}")
    public String generateTitle(@PathVariable String projectDescription) throws JsonProcessingException {
        return service.generateTitle(projectDescription);
    }

    @GetMapping("collaborator/{username}")
    public List<ProjectDto> findByCollaboratorUsername(@PathVariable String username) {
        return findDtos(service.findByCollaboratorUsername(username));
    }
    @Operation(summary = "Generate project")
    @PostMapping("generate")
    public ProcessResult generate(@RequestBody YamlFileDto yamlFile) {
        converter.init(true);
        yamlFileConverter.init(true);
        YamlFile myT = yamlFileConverter.toItem(yamlFile);
        ProcessResult projectResult = service.generate(myT);
        return projectResult;
    }

    @Operation(summary = "Finds a list of all projects")
    @GetMapping("")
    public ResponseEntity<List<ProjectDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projects")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a project by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        Project t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a project by titleChat")
    @GetMapping("titleChat/{titleChat}")
    public ResponseEntity<ProjectDto> findByTitleChat(@PathVariable String titleChat) {
        Project t = service.findByReferenceEntity(new Project(titleChat));
        if (t != null) {
            converter.init(true);
            ProjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  project")
    @PostMapping("")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Project myT = converter.toItem(dto);
            Project t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  project")
    @PutMapping("")
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto dto) throws Exception {
        ResponseEntity<ProjectDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Project t = service.findById(dto.getId());
            converter.copy(dto,t);
            Project updated = service.update(t);
            ProjectDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of project")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectDto>> delete(@RequestBody List<ProjectDto> dtos) throws Exception {
        ResponseEntity<List<ProjectDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Project> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified project")
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


    @Operation(summary = "Finds a project and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectDto> findWithAssociatedLists(@PathVariable Long id) {
        Project loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projects by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectDto>> findByCriteria(@RequestBody ProjectCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projects by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectCriteria criteria) throws Exception {
        List<Project> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets project data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<ProjectDto> findDtos(List<Project> list){
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectDto> getDtoResponseEntity(ProjectDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectCollaboratorService service;
    @Autowired private ProjectConverter converter;
    @Autowired private YamlFileConverter yamlFileConverter;
    @Autowired private ConversationConverter conversationConverter;




}
