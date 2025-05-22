# 🌱 친환경 농산물 인증/조회 시스템

Vue.js + Spring Boot 기반의 친환경 농산물 인증 및 유통 정보 관리 시스템  
MSA 구조와 Gateway, JWT 기반 인증을 도입하여 확장성과 보안성을 고려해 설계

---

## 📌 1. 프로젝트 개요

- **목표**: 친환경 농산물의 인증 정보 효율적으로 관리, 사용자에게 안전한 상품 정보를 제공
- **구조**: Vue.js 프론트엔드 + Spring Boot 백엔드   
  - MSA 기반: 서비스 간 독립 배포를 위한 모듈 분리
- **특징**:
  - JWT 기반 사용자 인증
  - API Gateway 및 Eureka를 활용한 서비스 간 통신
  - 인증 후 로그인 상태 유지(localStorage 기반)
  - 게시판 및 공지사항 시스템 포함

---

## ⚙️ 2. 기술 스택

### 📦 Backend
- Java 17
- Spring Boot 3.4.4
- Spring Cloud Gateway (WebFlux)
- Spring Security + JWT
- Eureka Server (Service Discovery)
- Gradle (빌드 시스템)
- H2 Database (개발 환경용 인메모리 DB)
- JPA, MyBatis

### 🌐 Frontend
- Vue.js 3 (Composition API)
- JavaScript
- CSS
- Vite (개발 서버 및 번들러)
- Axios (REST API 통신)
- localStorage (로그인 상태 유지)

---

## 🗂️ 3. 프로젝트 구조
```
admin/   
├── frontend/               # Vue.js 프론트엔드   
├── user-service/           # 사용자 인증 및 회원 관리   
├── knjsystem_product/      # 상품 바코드, 상품 바코드별 인증번호 관리   
├── board-service/          # 공지사항 등 게시판 기능   
├── gateway-service/        # API Gateway (Spring Cloud Gateway)   
├── eureka-server/          # Eureka 서비스 등록 서버   
├── build.gradle            # 루트 빌드 설정   
├── settings.gradle         # 서브모듈 포함 설정   
```

---

## 🚀 4. 실행 방법

### ▶️ 프론트엔드 실행 (Vite)

```bash
cd frontend
npm install
npm run dev
```

### ▶️ 백엔드 실행

다음 순서로 실행
1. eureka-server
2. user-service
3. board-service 
4. knjsystem_product 
5. gateway-service

***

## ✅ 5. 주요 기능

### 사용자 측 기능
- 회원가입 / 로그인 / 로그아웃 (localStorage 기반 인증 상태 유지)
- 마이페이지 (사용자 정보 수정)
- 공지사항 목록 조회 / 상세 보기
- 상품 바코드 관리 (생산자/취급자)
  - 상품 바코드 등록 및 삭제
  - 상품 바코드별 인증정보 등록 및 삭제
- 바코드 조회 시스템 (소비자)

### 시스템 기능
- JWT 인증 기반 보안 처리
- Spring Cloud Gateway를 통한 서비스 라우팅
- Eureka를 통한 서비스 등록/탐색
- MSA 기반 분리 구조 서비스 간 RESTful 연동
- 외부 데이터베이스 자동 연동
  - 외부 기관의 url에 주기적으로 접근 (스케줄링 기반)
  - xml 형식 응답을 DOM Parser로 파싱 후 내부 db 데이터에 저장

***

## 6. 서비스 설명
### 1. gateway-service
- CORS 처리

### 2. eureka-server
- 각 서비스 등록/관리

### 3. board-service
- 공지사항 조회

### 4. user-service
- 로그인 및 회원가입
  - 테스트 id: test123
  - 테스트 pw: test123
- JWT 발급 및 유효성 검증
- 마이 페이지 (회원정보 수정)
- 소비자용 바코드 조회
- 타 모듈과의 데이터 연동
  - rest api를 사용한 knjsystem-product 모듈과의 데이터 연동

### 5. knjsystem_product
- 사용자별 상품 바코드 조회 및 관리
  - 상품 바코드 등록 및 삭제
  - 상품 바코드별 인증번호 등록 및 삭제
- 타 모듈과의 데이터 연동
  - rest api를 사용한 user-service 모듈과의 데이터 연동
- 외부 DB와 웹 방식으로 데이터 연동

## 💻 개발 노트

### 📌 향후 개발 예정
- ✅ 관리자 페이지에서 농산물 인증 정보 등록 및 사용자 권한 부여
