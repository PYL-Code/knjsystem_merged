package edu.du.knjsystem_product.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetExternalMember {
    private Long seqNoA001;        // 회원 일련번호
    private String loginId;        // 로그인 ID
    private String bnsNo;          // 사업자등록번호
    private String compNm;         // 회사명
    private String comp880Cd;      // GLN 코드
    private String compType;       // 업체구분 (P, S)
    private String repNm;          // 담당자명
    private String repPhoneNo;     // 담당자 전화번호
    private String repFaxNo;       // 팩스 번호
    private String zipcode;        // 우편번호
    private String addr1;          // 주소1
    private String addr2;          // 주소2
    private String registDate;  // 등록일자
    private String approvalYn;     // 승인 여부
    private String delYn;          // 삭제 여부
}
