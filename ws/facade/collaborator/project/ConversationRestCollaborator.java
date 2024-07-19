package ma.zs.zyn.ws.facade.collaborator.project;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.project.Conversation;
import ma.zs.zyn.dao.criteria.core.project.ConversationCriteria;
import ma.zs.zyn.service.facade.collaborator.project.ConversationCollaboratorService;
import ma.zs.zyn.ws.converter.project.ConversationConverter;
import ma.zs.zyn.ws.dto.project.ConversationDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/conversation/")
public class ConversationRestCollaborator {



    @GetMapping("title/{title}")
    public List<ConversationDto> findByProjectTitleChat(@PathVariable String title) {
        return findDtos(service.findByProjectTitleChat(title));
    }

    @Operation(summary = "Finds a list of all conversations")
    @GetMapping("")
    public ResponseEntity<List<ConversationDto>> findAll() throws Exception {
        ResponseEntity<List<ConversationDto>> res = null;
        List<Conversation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ConversationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a conversation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConversationDto> findById(@PathVariable Long id) {
        Conversation t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ConversationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  conversation")
    @PostMapping("")
    public ResponseEntity<ConversationDto> save(@RequestBody ConversationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Conversation myT = converter.toItem(dto);
            Conversation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ConversationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  conversation")
    @PutMapping("")
    public ResponseEntity<ConversationDto> update(@RequestBody ConversationDto dto) throws Exception {
        ResponseEntity<ConversationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Conversation t = service.findById(dto.getId());
            converter.copy(dto,t);
            Conversation updated = service.update(t);
            ConversationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of conversation")
    @PostMapping("multiple")
    public ResponseEntity<List<ConversationDto>> delete(@RequestBody List<ConversationDto> dtos) throws Exception {
        ResponseEntity<List<ConversationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Conversation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified conversation")
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

    @Operation(summary = "find by project id")
    @GetMapping("project/id/{id}")
    public List<ConversationDto> findByProjectId(@PathVariable Long id){
        return findDtos(service.findByProjectId(id));
    }
    @Operation(summary = "delete by project id")
    @DeleteMapping("project/id/{id}")
    public int deleteByProjectId(@PathVariable Long id){
        return service.deleteByProjectId(id);
    }

    @Operation(summary = "Finds a conversation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ConversationDto> findWithAssociatedLists(@PathVariable Long id) {
        Conversation loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ConversationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds conversations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ConversationDto>> findByCriteria(@RequestBody ConversationCriteria criteria) throws Exception {
        ResponseEntity<List<ConversationDto>> res = null;
        List<Conversation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ConversationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated conversations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ConversationCriteria criteria) throws Exception {
        List<Conversation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ConversationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets conversation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ConversationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ConversationDto> findDtos(List<Conversation> list){
        converter.initObject(true);
        List<ConversationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ConversationDto> getDtoResponseEntity(ConversationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ConversationCollaboratorService service;
    @Autowired private ConversationConverter converter;





}
