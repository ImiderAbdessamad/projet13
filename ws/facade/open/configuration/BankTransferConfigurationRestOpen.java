package ma.zs.zyn.ws.facade.open.configuration;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.bean.core.configuration.BankTransferConfiguration;
import ma.zs.zyn.service.facade.collaborator.configuration.BankTransferConfigurationCollaboratorService;
import ma.zs.zyn.ws.converter.configuration.BankTransferConfigurationConverter;
import ma.zs.zyn.ws.dto.configuration.BankTransferConfigurationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// Zouani&Mehedi docker et Zouizza pipeline
@RestController
@RequestMapping("/api/open/bankTransferConfiguration/")
public class BankTransferConfigurationRestOpen {


    @Operation(summary = "Finds a list of all bankTransferConfigurations")
    @GetMapping("")
    public ResponseEntity<List<BankTransferConfigurationDto>> findAll() throws Exception {
        ResponseEntity<List<BankTransferConfigurationDto>> res = null;
        List<BankTransferConfiguration> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BankTransferConfigurationDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Autowired
    private BankTransferConfigurationCollaboratorService service;
    @Autowired
    private BankTransferConfigurationConverter converter;


}
