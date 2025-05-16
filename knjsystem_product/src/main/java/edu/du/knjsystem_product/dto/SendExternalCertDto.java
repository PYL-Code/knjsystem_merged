package edu.du.knjsystem_product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendExternalCertDto {
    private Long certId;
    private String certificationCode;
    private String farmerName;
    private String certificationStatus;
    private String productCodeName;
    private String certificationAgency;
    private String barcode;  // 연결된 바코드
}
