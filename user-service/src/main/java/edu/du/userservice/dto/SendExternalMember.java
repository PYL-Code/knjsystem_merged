package edu.du.userservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendExternalMember {
    private Long seqNoA001;
    private String loginId;
    private String bnsNo;
    private String compNm;
    private String comp880Cd;
    private String compType;
    private String repNm;
    private String repPhoneNo;
    private String repFaxNo;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String registDate;
    private String approvalYn;
    private String delYn;
}
