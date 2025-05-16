package edu.du.userservice.service;

import edu.du.userservice.dto.SendExternalMemberDto;
import edu.du.userservice.entity.TsgA001;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExternalSyncService {

    private final RestTemplate restTemplate;

    // 외부 시스템 URL
    private static final String SYNC_URL = "http://localhost:9876/api/list/external/member/sync";

    public void sendMemberInfo(TsgA001 user) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        SendExternalMemberDto dto = SendExternalMemberDto.builder()
                .seqNoA001(user.getSeqNoA001())
                .loginId(user.getLoginId())
                .bnsNo(user.getBnsNo())
                .compNm(user.getCompNm())
                .comp880Cd(user.getComp880Cd())
                .compType(user.getCompType())
                .repNm(user.getRepNm())
                .repPhoneNo(user.getRepPhoneNo())
                .repFaxNo(user.getRepFaxNo())
                .zipcode(user.getZipcode())
                .addr1(user.getAddr1())
                .addr2(user.getAddr2())
                .registDate(user.getRegistDate()!= null ? user.getRegistDate().format(formatter) : null)
                .approvalYn(user.getApprovalYn())
                .delYn(user.getDelYn())
                .build();

        restTemplate.postForEntity(SYNC_URL, dto, String.class);
    }
}
