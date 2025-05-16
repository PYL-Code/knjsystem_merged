package edu.du.knjsystem_product.externalDB.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "certOutDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class CertOutDto {
    private Integer seqNoA003;
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
    private String registDate;
    private String status;
}
