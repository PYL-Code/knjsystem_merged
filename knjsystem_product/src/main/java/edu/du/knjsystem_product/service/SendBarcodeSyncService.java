package edu.du.knjsystem_product.service;

import edu.du.knjsystem_product.dto.SendExternalBarcodeDto;
import edu.du.knjsystem_product.dto.SendExternalCertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SendBarcodeSyncService {
    private final RestTemplate restTemplate;

    private final String SYNC_BARCODE_URL = "http://localhost:8081/api/sync/barcode";
    private final String SYNC_CERT_URL = "http://localhost:8081/api/sync/cert";

    public void sendBarcode(SendExternalBarcodeDto dto) {
        restTemplate.postForEntity(SYNC_BARCODE_URL, dto, String.class);
    }

    public void deleteBarcode(String barcode) {
        restTemplate.delete(SYNC_BARCODE_URL + "?barcode=" + barcode);
    }

    public void sendNewCert(SendExternalCertDto dto) {
        restTemplate.postForEntity(SYNC_CERT_URL, dto, String.class);
    }

    public void deleteCert(Long certId) {
        restTemplate.delete(SYNC_CERT_URL + "?certId=" + certId);
    }
}
