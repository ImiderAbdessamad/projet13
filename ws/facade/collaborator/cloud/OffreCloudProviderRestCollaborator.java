package ma.zs.zyn.ws.facade.collaborator.cloud;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.cloud.OffreCloudProvider;
import ma.zs.zyn.dao.criteria.core.cloud.OffreCloudProviderCriteria;
import ma.zs.zyn.service.facade.collaborator.cloud.OffreCloudProviderCollaboratorService;
import ma.zs.zyn.ws.converter.cloud.OffreCloudProviderConverter;
import ma.zs.zyn.ws.dto.cloud.OffreCloudProviderDto;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/offreCloudProvider/")
public class OffreCloudProviderRestCollaborator {




    @Operation(summary = "Finds a list of all offreCloudProviders")
    @GetMapping("")
    public ResponseEntity<List<OffreCloudProviderDto>> findAll() throws Exception {
        ResponseEntity<List<OffreCloudProviderDto>> res = null;
        List<OffreCloudProvider> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<OffreCloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all offreCloudProviders")
    @GetMapping("optimized")
    public ResponseEntity<List<OffreCloudProviderDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<OffreCloudProviderDto>> res = null;
        List<OffreCloudProvider> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<OffreCloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a offreCloudProvider by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OffreCloudProviderDto> findById(@PathVariable Long id) {
        OffreCloudProvider t = service.findById(id);
        if (t != null) {
            converter.init(true);
            OffreCloudProviderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a offreCloudProvider by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<OffreCloudProviderDto> findByLibelle(@PathVariable String libelle) {
	    OffreCloudProvider t = service.findByReferenceEntity(new OffreCloudProvider(libelle));
        if (t != null) {
            converter.init(true);
            OffreCloudProviderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  offreCloudProvider")
    @PostMapping("")
    public ResponseEntity<OffreCloudProviderDto> save(@RequestBody OffreCloudProviderDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            OffreCloudProvider myT = converter.toItem(dto);
            OffreCloudProvider t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OffreCloudProviderDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  offreCloudProvider")
    @PutMapping("")
    public ResponseEntity<OffreCloudProviderDto> update(@RequestBody OffreCloudProviderDto dto) throws Exception {
        ResponseEntity<OffreCloudProviderDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            OffreCloudProvider t = service.findById(dto.getId());
            converter.copy(dto,t);
            OffreCloudProvider updated = service.update(t);
            OffreCloudProviderDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of offreCloudProvider")
    @PostMapping("multiple")
    public ResponseEntity<List<OffreCloudProviderDto>> delete(@RequestBody List<OffreCloudProviderDto> dtos) throws Exception {
        ResponseEntity<List<OffreCloudProviderDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<OffreCloudProvider> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified offreCloudProvider")
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


    @Operation(summary = "Finds a offreCloudProvider and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OffreCloudProviderDto> findWithAssociatedLists(@PathVariable Long id) {
        OffreCloudProvider loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        OffreCloudProviderDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds offreCloudProviders by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OffreCloudProviderDto>> findByCriteria(@RequestBody OffreCloudProviderCriteria criteria) throws Exception {
        ResponseEntity<List<OffreCloudProviderDto>> res = null;
        List<OffreCloudProvider> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<OffreCloudProviderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated offreCloudProviders by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OffreCloudProviderCriteria criteria) throws Exception {
        List<OffreCloudProvider> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<OffreCloudProviderDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets offreCloudProvider data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OffreCloudProviderCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OffreCloudProviderDto> findDtos(List<OffreCloudProvider> list){
        converter.initObject(true);
        List<OffreCloudProviderDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OffreCloudProviderDto> getDtoResponseEntity(OffreCloudProviderDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private OffreCloudProviderCollaboratorService service;
    @Autowired private OffreCloudProviderConverter converter;





}
