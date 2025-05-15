package edu.du.userservice.controller;

import edu.du.userservice.dto.UserLoginRequestDto;
import edu.du.userservice.dto.UserRegisterRequestDto;
import edu.du.userservice.entity.TsgA001;
import edu.du.userservice.repository.TsgA001Repository;
import edu.du.userservice.security.JwtUtil;
import edu.du.userservice.service.ExternalSyncService;
import edu.du.userservice.service.UserRegisterService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserRegisterService registerService;
    private final TsgA001Repository userRepo;
    private final JwtUtil jwtUtil;
    private final ExternalSyncService externalSyncService;

    // 1) 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto dto) {
        registerService.register(dto);

        TsgA001 savedUser = userRepo.findByLoginId(dto.getLoginId())
                .orElseThrow(() -> new RuntimeException("회원 등록 실패"));
        externalSyncService.sendMemberInfo(savedUser);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 2) 로그인 → JWT 발급 & 엔티티 그대로 반환
    @PostMapping("/info")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto dto) {
        TsgA001 user = userRepo.findByLoginId(dto.getLoginId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }

        // compType 필드를 role로 사용한다고 가정
        String token = jwtUtil.createToken(user.getLoginId(), user.getCompType(), user.getSeqNoA001());

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", user);    // → 엔티티를 그대로 넣는다

        return ResponseEntity.ok(body);
    }

    // 3) 토큰 유효성 체크
    @GetMapping("/check")
    public ResponseEntity<?> checkToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            jwtUtil.parseToken(token);  // 예외 발생 시 401
            return ResponseEntity.ok("유효한 토큰입니다.");
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰 확인 중 오류 발생");
        }
    }

    @GetMapping("/check-id")  //아이디 중복 확인용
    public ResponseEntity<Map<String, Boolean>> checkLoginId(@RequestParam("loginId") String loginId) {
        boolean exists = userRepo.existsByLoginId(loginId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return ResponseEntity.ok(result);
    }

    // 4) 회원정보 수정 (엔티티 그대로 반영 & 반환)
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TsgA001 dto  // DTO 대신 엔티티 타입을 직접 받습니다
    ) {
        // 토큰에서 loginId 꺼내기
        String token = authHeader.replace("Bearer ", "");
        Claims claims = jwtUtil.parseToken(token);
        String loginId = claims.getSubject();

        // DB에서 해당 사용자 로드
        TsgA001 user = userRepo.findByLoginId(loginId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 정보가 없습니다."));

        // 요청 바디의 필드를 엔티티에 복사
        user.setRepNm(dto.getRepNm());
        user.setRepPhoneNo(dto.getRepPhoneNo());
        user.setRepFaxNo(dto.getRepFaxNo());
        user.setCompNm(dto.getCompNm());
        user.setBnsNo(dto.getBnsNo());
        user.setCompType(dto.getCompType());
        user.setComp880Cd(dto.getComp880Cd());
        user.setZipcode(dto.getZipcode());
        user.setAddr1(dto.getAddr1());
        user.setAddr2(dto.getAddr2());

        userRepo.save(user);

        externalSyncService.sendMemberInfo(user);

        // 갱신된 엔티티 그대로 반환
        return ResponseEntity.ok(user);
    }


}