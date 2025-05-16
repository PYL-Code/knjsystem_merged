package edu.du.userservice.service;

import edu.du.userservice.dto.GetExternalBarcodeDto;
import edu.du.userservice.dto.GetExternalCertDto;
import edu.du.userservice.entity.TsgB001;
import edu.du.userservice.entity.TsgB002;
import edu.du.userservice.repository.TsgB001Repository;
import edu.du.userservice.repository.TsgB002Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExternalBarcodeSyncService {

    private final TsgB001Repository b001Repository;
    private final TsgB002Repository b002Repository;

    public void saveBarcode(GetExternalBarcodeDto dto) {
        // 바코드 중복 방지
        TsgB001 barcode = b001Repository.findByBarcodeNumber(dto.getBarcode())
                .orElseGet(() -> {
                    TsgB001 newB001 = new TsgB001();
                    newB001.setBarcodeNumber(dto.getBarcode());
                    newB001.setProductName(dto.getProductName());
                    newB001.setProductType(dto.getProductType());
                    newB001.setProductStatus(dto.getProductStatus());
                    newB001.setHandlerName(dto.getHandlerName());
                    return b001Repository.save(newB001);
                });
    }

    public void saveCert(GetExternalCertDto dto) {
        TsgB001 parent = b001Repository.findByBarcodeNumber(dto.getBarcode())
                .orElseThrow(() -> new IllegalArgumentException("바코드가 존재하지 않습니다: " + dto.getBarcode()));

        TsgB002 cert = new TsgB002();
        cert.setCertificationCode(dto.getCertificationCode());
        cert.setFarmerName(dto.getFarmerName());
        cert.setCertificationStatus(dto.getCertificationStatus());
        cert.setProductCodeName(dto.getProductCodeName());
        cert.setCertificationAgency(dto.getCertificationAgency());
        cert.setTsgB001(parent); // 연관관계 설정

        b002Repository.save(cert);
    }

    public void deleteBarcode(String barcode) {
        b001Repository.findByBarcodeNumber(barcode)
                .ifPresent(b001Repository::delete); // cascade 설정에 따라 cert도 같이 삭제됨
    }

    public void deleteCert(Long certId) {
        b002Repository.deleteById(certId);
    }
}
