package edu.du.userservice.dto;

import edu.du.userservice.entity.TsgB002;
import lombok.Getter;

@Getter
public class CertificationDto {

    private String certificationCode;     // 인증번호
    private String farmerName;            // 농가명
    private String certificationStatus;   // 인증상태
    private String productCodeName;       // 상품명(코드)
    private String certificationAgency;   // 인증기관

    // 생성자
    public CertificationDto(TsgB002 entity) {
        this.certificationCode = entity.getCertificationCode();
        this.farmerName = entity.getFarmerName();
        this.certificationStatus = entity.getCertificationStatus();
        this.productCodeName = entity.getProductCodeName();
        this.certificationAgency = entity.getCertificationAgency();
    }

    // getter 생략 (필요 시 lombok @Getter 사용 가능)
}