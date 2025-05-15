package edu.du.knjsystem_product.controller;

import edu.du.knjsystem_product.dto.GetExternalMember;
import edu.du.knjsystem_product.dto.MemberDto;
import edu.du.knjsystem_product.security.CustomUserDetails;
import edu.du.knjsystem_product.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/list")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDto> getMemberAtList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        Long userNo = user.getSeqNoA001();

        MemberDto member = memberService.getMemberByNo(userNo);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/external/member/sync")
    public ResponseEntity<String> recieveMemberInfo(@RequestBody GetExternalMember dto) {
        memberService.saveOrUpdate(dto);
        return ResponseEntity.ok("회원정보 동기화 완료");
    }
}
