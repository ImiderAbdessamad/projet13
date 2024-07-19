package ma.zs.zyn.ws.facade.collaborator.payement;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.payement.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.payement.PaimentCollaboratorCriteria;
import ma.zs.zyn.service.facade.collaborator.payement.PaimentCollaboratorCollaboratorService;
import ma.zs.zyn.ws.converter.payement.PaimentCollaboratorConverter;
import ma.zs.zyn.ws.dto.payement.PaimentCollaboratorDto;
import ma.zs.zyn.zynerator.transverse.cloud.MinIOInfos;
import ma.zs.zyn.zynerator.transverse.cloud.MinIOService;
import ma.zs.zyn.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator/paimentCollaborator/")
public class PaimentCollaboratorRestCollaborator {

    public static final String ZYN_TRANSFER_BUCKET = "zyn-transfer";
    @Autowired
    private MinIOService minIOService;



    @Operation(summary = "Finds a list of all paimentCollaborators")
    @GetMapping("")
    public ResponseEntity<List<PaimentCollaboratorDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentCollaboratorDto>> res = null;
        List<PaimentCollaborator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentCollaboratorDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentCollaborators")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentCollaboratorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentCollaboratorDto>> res = null;
        List<PaimentCollaborator> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentCollaboratorDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentCollaborator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentCollaboratorDto> findById(@PathVariable Long id) {
        PaimentCollaborator t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PaimentCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  paimentCollaborator")
    @PostMapping("pay")
    public ResponseEntity<PaimentCollaboratorDto> save(@RequestBody PaimentCollaboratorDto dto) throws Exception {
        if (dto != null) {
            converter.init(true);
            converter.setCollaborator(false);
            PaimentCollaborator myT = converter.toItem(dto);
            PaimentCollaborator t = service.create(myT);
            converter.setCollaborator(true);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                PaimentCollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {

            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Upload a file to the bucket")
    @PostMapping("upload-transfer")
    public MinIOInfos uploadToMinio(@RequestParam("file") MultipartFile file) {
        return minIOService.uploadToMinio(file, ZYN_TRANSFER_BUCKET);
    }

    @Operation(summary = "Saves the specified  paimentCollaborator")
    @PostMapping("transfer")
    public ResponseEntity<PaimentCollaboratorDto> transfer(@RequestBody PaimentCollaboratorDto dto) throws Exception {
        if (dto != null) {
            converter.init(true);
            converter.setCollaborator(false);
            PaimentCollaborator myT = converter.toItem(dto);
            PaimentCollaborator t = service.transfer(myT);
            converter.setCollaborator(true);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                PaimentCollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentCollaborator")
    @PutMapping("")
    public ResponseEntity<PaimentCollaboratorDto> update(@RequestBody PaimentCollaboratorDto dto) throws Exception {
        ResponseEntity<PaimentCollaboratorDto> res;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentCollaborator t = service.findById(dto.getId());
            converter.copy(dto, t);
            PaimentCollaborator updated = service.update(t);
            PaimentCollaboratorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentCollaborator")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentCollaboratorDto>> delete(@RequestBody List<PaimentCollaboratorDto> dtos) throws Exception {
        ResponseEntity<List<PaimentCollaboratorDto>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PaimentCollaborator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentCollaborator")
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

    @Operation(summary = "find by country id")
    @GetMapping("country/id/{id}")
    public List<PaimentCollaboratorDto> findByCountryId(@PathVariable Long id) {
        return findDtos(service.findByCountryId(id));
    }

    @Operation(summary = "delete by country id")
    @DeleteMapping("country/id/{id}")
    public int deleteByCountryId(@PathVariable Long id) {
        return service.deleteByCountryId(id);
    }

    @Operation(summary = "find by collaborator id")
    @GetMapping("collaborator/id/{id}")
    public List<PaimentCollaboratorDto> findByCollaboratorId(@PathVariable Long id) {
        return findDtos(service.findByCollaboratorId(id));
    }

    @Operation(summary = "delete by collaborator id")
    @DeleteMapping("collaborator/id/{id}")
    public int deleteByCollaboratorId(@PathVariable Long id) {
        return service.deleteByCollaboratorId(id);
    }

    @Operation(summary = "find by packaging id")
    @GetMapping("packaging/id/{id}")
    public List<PaimentCollaboratorDto> findByPackagingId(@PathVariable Long id) {
        return findDtos(service.findByPackagingId(id));
    }

    @Operation(summary = "delete by packaging id")
    @DeleteMapping("packaging/id/{id}")
    public int deleteByPackagingId(@PathVariable Long id) {
        return service.deleteByPackagingId(id);
    }

    @Operation(summary = "find by paimentCollaboratorType code")
    @GetMapping("paimentCollaboratorType/code/{code}")
    public List<PaimentCollaboratorDto> findByPaimentCollaboratorTypeCode(@PathVariable String code) {
        return findDtos(service.findByPaimentCollaboratorTypeCode(code));
    }

    @Operation(summary = "delete by paimentCollaboratorType code")
    @DeleteMapping("paimentCollaboratorType/code/{code}")
    public int deleteByPaimentCollaboratorTypeCode(@PathVariable String code) {
        return service.deleteByPaimentCollaboratorTypeCode(code);
    }

    @Operation(summary = "Finds a paimentCollaborator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentCollaboratorDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentCollaborator loaded = service.findWithAssociatedLists(id);
        converter.init(true);
        PaimentCollaboratorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentCollaborators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentCollaboratorDto>> findByCriteria(@RequestBody PaimentCollaboratorCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentCollaboratorDto>> res = null;
        List<PaimentCollaborator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentCollaboratorDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentCollaborators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentCollaboratorCriteria criteria) throws Exception {
        List<PaimentCollaborator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PaimentCollaboratorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentCollaborator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentCollaboratorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }


    public List<PaimentCollaboratorDto> findDtos(List<PaimentCollaborator> list) {
        converter.initObject(true);
        List<PaimentCollaboratorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentCollaboratorDto> getDtoResponseEntity(PaimentCollaboratorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Autowired
    private PaimentCollaboratorCollaboratorService service;
    @Autowired
    private PaimentCollaboratorConverter converter;


}
