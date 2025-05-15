package edu.du.userservice.repository;

import edu.du.userservice.entity.TsgA001;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TsgA001Repository extends JpaRepository<TsgA001, Long> {
    /** 사업자 번호 중복 체크용 */
    boolean existsByBnsNo(String bnsNo);

    /** 아이디 중복 체크용 */
    boolean existsByLoginId(String loginId);

    /** 로그인 처리용 */

    Optional<TsgA001> findByLoginId(String loginId);
}