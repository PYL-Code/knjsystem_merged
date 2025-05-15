DROP TABLE IF EXISTS T_SG_A001;

CREATE TABLE T_SG_A001 (
                           SEQ_NO_A001   BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 일련번호 (PK)

                           LOGIN_ID      VARCHAR(50)  NOT NULL UNIQUE,         -- 로그인용 아이디 (중복 불가)
                           PASSWORD      VARCHAR(255) NOT NULL,                -- 비밀번호 (암호화 필요)

                           BNS_NO        VARCHAR(10)  NOT NULL,                -- 사업자등록번호
                           COMP_NM       VARCHAR(100),                         -- 회사명
                           COMP_CODE     VARCHAR(20),                          -- 업체 코드 (새로 추가)
                           COMP880_CD    VARCHAR(20),                          -- GLN 코드
                           COMP_TYPE     CHAR(1)     NOT NULL,                 -- 업태구분 (P=생산자, S=판매자)

                           REP_NM        VARCHAR(100),                         -- 담당자 이름
                           REP_PHONE_NO  VARCHAR(18),                          -- 담당자 전화번호
                           REP_FAX_NO    VARCHAR(18),                          -- 팩스번호

                           ZIPCODE       VARCHAR(10),                          -- 우편번호
                           ADDR1         VARCHAR(100),                         -- 주소1
                           ADDR2         VARCHAR(100),                         -- 주소2

                           REGIST_DATE   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,-- 등록일자

                           AUTH_MAG_YN   CHAR(1)      NOT NULL DEFAULT 'N',     -- 기업 관리자 여부 (Y/N)

                           APPROVAL_YN   CHAR(1)      NOT NULL DEFAULT 'N',     -- 승인 여부 (Y/N)
                           DEL_YN        CHAR(1)      NOT NULL DEFAULT 'N'      -- 삭제 여부 (Y/N)
);

-- ✅ 바코드 상품 기본정보 테이블
DROP TABLE IF EXISTS TSG_B001;

CREATE TABLE TSG_B001 (
                          ID               BIGINT AUTO_INCREMENT PRIMARY KEY,
                          BARCODE_NUMBER   VARCHAR(50) NOT NULL,
                          PRODUCT_NAME     VARCHAR(100),
                          PRODUCT_TYPE     VARCHAR(50),
                          PRODUCT_STATUS   VARCHAR(50),
                          HANDLER_NAME     VARCHAR(100)
);

-- ✅ 인증 이력 정보 테이블 (1:N 관계: B001 → B002)
DROP TABLE IF EXISTS TSG_B002;

CREATE TABLE TSG_B002 (
                          ID                      BIGINT AUTO_INCREMENT PRIMARY KEY,
                          CERTIFICATION_CODE      VARCHAR(50),
                          FARMER_NAME             VARCHAR(100),
                          CERTIFICATION_STATUS    VARCHAR(20),
                          PRODUCT_CODE_NAME       VARCHAR(100),
                          CERTIFICATION_AGENCY    VARCHAR(100),
                          BARCODE_PRODUCT_ID      BIGINT,
                          CONSTRAINT FK_BARCODE_PRODUCT
                              FOREIGN KEY (BARCODE_PRODUCT_ID)
                                  REFERENCES TSG_B001(ID)
                                  ON DELETE CASCADE
);