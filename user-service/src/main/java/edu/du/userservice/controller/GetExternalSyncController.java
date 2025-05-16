package edu.du.userservice.controller;

import edu.du.userservice.dto.GetExternalBarcodeDto;
import edu.du.userservice.dto.GetExternalCertDto;
import edu.du.userservice.service.GetExternalBarcodeSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sync")
public class GetExternalSyncController {
    private final GetExternalBarcodeSyncService service;

    @PostMapping("/barcode")
    public ResponseEntity<String> receiveBarcode(@RequestBody GetExternalBarcodeDto dto) {
        service.saveBarcode(dto);
        return ResponseEntity.ok("바코드 저장 완료");
    }

    @DeleteMapping("/barcode")
    public ResponseEntity<String> deleteBarcode(@RequestParam("barcode") String barcode) {
        service.deleteBarcode(barcode);
        return ResponseEntity.ok("바코드 삭제 완료");
    }

    @PostMapping("/cert")
    public ResponseEntity<String> receiveCert(@RequestBody GetExternalCertDto dto) {
        service.saveCert(dto);
        return ResponseEntity.ok("인증정보 저장 완료");
    }

    @DeleteMapping("/cert")
    public ResponseEntity<String> deleteCert(@RequestParam("certId") Long certId) {
        service.deleteCert(certId);
        return ResponseEntity.ok("인증정보 삭제 완료");
    }
}
