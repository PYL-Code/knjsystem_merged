# 🌱 친환경 농산물 인증/조회 시스템

Vue.js + Spring Boot 기반의 친환경 농산물 인증 및 유통 정보 관리 시스템입니다.  
MSA 구조와 Gateway, JWT 기반 인증을 도입하여 확장성과 보안성을 고려해 설계되었습니다.

---

## 📌 1. 프로젝트 개요

- **목표**: 친환경 농산물의 인증 정보를 효율적으로 관리하고, 사용자에게 안전한 상품 정보를 제공합니다.
- **구조**: Vue.js 프론트엔드 + Spring Boot 백엔드 (MSA 기반)
- **특징**:
  - JWT 기반 사용자 인증
  - API Gateway 및 Eureka를 활용한 서비스 간 통신
  - 인증 후 로그인 상태 유지(localStorage 기반)
  - 게시판 및 공지사항 시스템 포함

---

## ⚙️ 2. 기술 스택

### 📦 Backend
- Java 17
- Spring Boot 3.1.5
- Spring Cloud Gateway (WebFlux)
- Spring Security + JWT
- Eureka Server (Service Discovery)
- Gradle (빌드 시스템)
- H2 Database (개발 환경용)

### 🌐 Frontend
- Vue.js 3 (Composition API)
- JavaScript
- ✅ **Tailwind CSS 미사용**
- Vite (개발 서버 및 번들러)
- Axios (REST API 통신)
- 상태 관리: `localStorage` 기반 (Pinia 미사용)

---

## 🗂️ 3. 프로젝트 구조

admin/
├── frontend/               # Vue.js 프론트엔드
├── user-service/           # 사용자 인증 및 회원 관리
├── board-service/          # 공지사항 등 게시판 기능
├── gateway-service/        # API Gateway (Spring Cloud Gateway)
├── eureka-server/          # Eureka 서비스 등록 서버
├── build.gradle            # 루트 빌드 설정
├── settings.gradle         # 서브모듈 포함 설정

---

## 🚀 4. 실행 방법

### ▶️ 프론트엔드 실행 (Vite)

```bash
cd frontend
npm install
npm run dev

▶️ 백엔드 실행

eureka-server ->  user-service ->  board-service -> gateway-service 순서로 실행


⸻

✅ 5. 주요 기능

사용자 측 기능
	•	회원가입 / 로그인 / 로그아웃
	•	로그인 상태 유지 (localStorage 기반)
	•	마이페이지 (사용자 정보 수정 예정)
	•	공지사항 목록 조회 / 상세 보기

관리자 또는 시스템 기능
	•	JWT 인증 기반 보안 처리
	•	Spring Cloud Gateway를 통한 서비스 라우팅
	•	Eureka를 통한 서비스 등록/탐색
	•	MSA 기반 서비스 간 분리 구조 설계

⸻

📌 향후 개발 예정
	•	✅ 관리자 페이지에서 인증 정보 등록 및 연결 기능

⸻

🧑‍💻 개발자 메모
	•	상태 관리 방식은 Pinia가 아닌 localStorage 기반으로 구현되어 있으며, 초기 상태 처리 및 인증 확인은 main.js에서 이루어집니다.
	•	Tailwind CSS는 도입되지 않았으며, 기본적인 CSS 스타일링만 포함되어 있습니다.

