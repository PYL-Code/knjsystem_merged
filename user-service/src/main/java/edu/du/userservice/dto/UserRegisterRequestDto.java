package edu.du.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    private String loginId;     // 🔹 아이디
    private String password;    // 🔹 비밀번호

    private String bnsNo;       // 사업자등록번호
    private String compNm;      // 업체명
    private String compCode;    // ★ 새로 추가된 업체 코드
    private String comp880Cd;   // GLN 코드
    private String compType;    // 업태구분 (P/S)
    private String repNm;       // 담당자 이름
    private String repPhoneNo;  // 담당자 전화번호
    private String repFaxNo;    // 담당자 팩스번호
    private String zipcode;     // 우편번호
    private String addr1;       // 주소1
    private String addr2;       // 주소2
}