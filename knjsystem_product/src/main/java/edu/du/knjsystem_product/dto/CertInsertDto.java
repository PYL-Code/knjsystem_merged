package edu.du.knjsystem_product.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CertInsertDto {

    private String rcvSeq;
    private String modSeq;
    private String prdrGrpNo;
    private String regSeq;
    private String stdCertNo;
    private String certGbCd;
    private String certGbNm;
    private String certStrtDt;
    private String certEndDt;
    private String frmrCertStt;
    private String certStateNm;
    private String psnGroupGb;
    private String psnGroupNm;
    private String prdrGrpNm;
    private String repItemCd;
    private String repItemNm;
    private String frmrNo;
    private String frmrNm;
    private String itemCd;
    private String itemNm;
    private String registDate; // String으로 처리 (DOM 파싱 시 문자열로 들어오기 때문)
    private String status;
}