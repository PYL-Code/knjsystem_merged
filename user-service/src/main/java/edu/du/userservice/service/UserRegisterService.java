package edu.du.userservice.service;

import edu.du.userservice.dto.UserRegisterRequestDto;
import edu.du.userservice.entity.TsgA001;
import edu.du.userservice.repository.TsgA001Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final TsgA001Repository repository;

    public void register(UserRegisterRequestDto dto) {
        // 기존과 동일하게 사업자 번호 중복 체크
        if (repository.existsByBnsNo(dto.getBnsNo())) {
            throw new IllegalArgumentException("이미 등록된 사업자번호입니다.");
        }

        TsgA001 entity = TsgA001.builder()
                .loginId(dto.getLoginId())       // 로그인 아이디
                .password(dto.getPassword())     // 비밀번호
                .bnsNo(dto.getBnsNo())           // 사업자번호
                .compNm(dto.getCompNm())         // 업체명
                .compCode(dto.getCompCode())     // ★ 새로 추가된 업체 코드
                .comp880Cd(dto.getComp880Cd())   // GLN 코드
                .compType(dto.getCompType())     // 업태구분
                .repNm(dto.getRepNm())           // 담당자명
                .repPhoneNo(dto.getRepPhoneNo()) // 담당자 전화번호
                .repFaxNo(dto.getRepFaxNo())     // 담당자 팩스번호
                .zipcode(dto.getZipcode())       // 우편번호
                .addr1(dto.getAddr1())           // 주소1
                .addr2(dto.getAddr2())           // 주소2
                .approvalYn("N")                 // 승인여부 기본 N
                .authMagYn("N")
                .delYn("N")                      // 삭제여부 기본 N
                .build();

        repository.save(entity);
    }
}