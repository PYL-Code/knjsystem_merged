package edu.du.knjsystem_product.controller;

import edu.du.knjsystem_product.dto.BarcodeAddCertListDto;
import edu.du.knjsystem_product.dto.BarcodeAddCertSearchDto;
import edu.du.knjsystem_product.dto.BarcodeDetailCertInfoDto;
import edu.du.knjsystem_product.external.dto.CertOutDto;
import edu.du.knjsystem_product.service.CertDomParserService;
import edu.du.knjsystem_product.service.CertService;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cert")
public class CertRestController {

    private final CertService certService;
    private final CertDomParserService certDomParserService;

    public CertRestController(CertService certService, CertDomParserService certDomParserService) {
        this.certService = certService;
        this.certDomParserService = certDomParserService;
    }


    @GetMapping("/external-list")
    public List<CertOutDto> getExternalCertList() {
        return certDomParserService.fetchCertInfoFromExternal();
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
