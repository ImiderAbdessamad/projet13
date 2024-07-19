package ma.zs.zyn.ws.facade.open.packaging;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open/packaging/")
public class PackagingRestOpen {




    @Operation(summary = "Finds a list of all packagings")
    @GetMapping("")
    public ResponseEntity<List<PackagingDto>> findAll() throws Exception {
        ResponseEntity<List<PackagingDto>> res = null;
        List<Packaging> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Autowired private PackagingCollaboratorService service;
    @Autowired private PackagingConverter converter;





}
