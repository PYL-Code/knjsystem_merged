package edu.du.userservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetExternalCertDto {
    private Long certId;
    private String certificationCode;
    private String farmerName;
    private String certificationStatus;
    private String productCodeName;
    private String certificationAgency;
    private String barcode;
}
