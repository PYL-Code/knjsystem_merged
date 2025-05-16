package edu.du.knjsystem_product.externalDB.controller;

import edu.du.knjsystem_product.externalDB.dto.CertOutListWrapper;
import edu.du.knjsystem_product.externalDB.service.OutDBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/external/api/")
public class OutDBController {
    private final OutDBService outDBService;

    public OutDBController(OutDBService outDBService) {
        this.outDBService = outDBService;
    }

    @GetMapping(value = "/cert-info", produces = MediaType.APPLICATION_XML_VALUE)
    public CertOutListWrapper getAllCerts(@RequestParam("key") String key) {
        if (!"123456".equals(key)) { // 🔐 실제 인증 키로 비교
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 접근입니다.");
        }

        CertOutListWrapper wrapper = new CertOutListWrapper();
        wrapper.setCerts(outDBService.selectCertOutAll());
        return wrapper;
    }
}
