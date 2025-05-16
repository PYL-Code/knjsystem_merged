package edu.du.knjsystem_product.controller;

import edu.du.knjsystem_product.dto.BarcodeAddCertListDto;
import edu.du.knjsystem_product.dto.BarcodeAddCertSearchDto;
import edu.du.knjsystem_product.dto.BarcodeDetailCertInfoDto;
import edu.du.knjsystem_product.externalDB.dto.CertOutDto;
import edu.du.knjsystem_product.service.ExternalCertDomParserService;
import edu.du.knjsystem_product.service.CertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cert")
public class CertRestController {

    private final CertService certService;
    private final ExternalCertDomParserService externalCertDomParserService;

    public CertRestController(CertService certService, ExternalCertDomParserService externalCertDomParserService) {
        this.certService = certService;
        this.externalCertDomParserService = externalCertDomParserService;
    }


    @GetMapping("/external-list")
    public List<CertOutDto> getExternalCertList() {
        return externalCertDomParserService.fetchCertInfoFromExternal();
    }


    @GetMapping("barcode/detail")
    public ResponseEntity<BarcodeDetailCertInfoDto> getBarcodeDetail(@RequestParam("seqNoA004") Long seqNoA004) {
        BarcodeDetailCertInfoDto certInfo = certService.selectProductCertInfo(seqNoA004);
        return ResponseEntity.ok(certInfo);
    }

    @GetMapping("/barcode/update")
    public ResponseEntity<List<BarcodeAddCertListDto>> getBarcodeUpdateCertList(@ModelAttribute BarcodeAddCertSearchDto search) {
        List<BarcodeAddCertListDto> list = certService.selectBarcodeAddCertList(search);
        return ResponseEntity.ok(list);
    }
}
