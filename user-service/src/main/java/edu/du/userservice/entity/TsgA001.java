package edu.du.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_SG_A001")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TsgA001 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NO_A001")
    private Long seqNoA001;

    // ✅ 로그인 ID
    @Column(name = "LOGIN_ID", nullable = false, unique = true, length = 50)
    private String loginId;

    // ✅ 비밀번호
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    // ✅ 사업자등록번호
    @Column(name = "BNS_NO", nullable = false, length = 10)
    private String bnsNo;

    // ✅ 업체명
    @Column(name = "COMP_NM", length = 100)
    private String compNm;

    // ✅ 새로 추가된 업체 코드
    @Column(name = "COMP_CODE", length = 20)
    private String compCode;

    // ✅ GLN 코드 (기존)
    @Column(name = "COMP880_CD", length = 13)
    private String comp880Cd;

    // ✅ 업태구분 (P=생산자, S=판매자)
    @Column(name = "COMP_TYPE", nullable = false, length = 1)
    private String compType;

    // ✅ 담당자명
    @Column(name = "REP_NM", length = 100)
    private String repNm;

    // ✅ 담당자 전화번호
    @Column(name = "REP_PHONE_NO", length = 18)
    private String repPhoneNo;

    // ✅ 팩스번호
    @Column(name = "REP_FAX_NO", length = 18)
    private String repFaxNo;

    // ✅ 우편번호
    @Column(name = "ZIPCODE", length = 10)
    private String zipcode;

    // ✅ 주소1
    @Column(name = "ADDR1", length = 100)
    private String addr1;

    // ✅ 주소2
    @Column(name = "ADDR2", length = 100)
    private String addr2;

    // ✅ 등록일
    @Column(name = "REGIST_DATE")
    private LocalDateTime registDate = LocalDateTime.now();

    // ✅ 승인여부 (Y/N)
    @Column(name = "APPROVAL_YN", nullable = false, length = 1)
    private String approvalYn = "N";

    // ✅ 삭제여부 (Y/N)
    @Column(name = "DEL_YN", nullable = false, length = 1)
    private String delYn = "N";

    @Column(name = "AUTH_MAG_YN", nullable = false, length = 1)
    private String authMagYn = "N";

    /**
     * 편의 메서드: PRODUCER / SELLER 역할
     */
    public String getRole() {
        return this.compType.equals("P") ? "PRODUCER" : "SELLER";
    }
}