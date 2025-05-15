<template>
  <section class="usage-section">
    <!-- 타이틀 -->
    <h2 class="page-title">이용절차</h2>

    <!-- 소개 영역 -->
    <div class="intro-box">
      <p class="intro-title">“<strong>친환경농산물 안심유통시스템</strong>”을 활용하시면,</p>
      <p class="intro-desc">
        취급 상품의 인증정보와 유통이력정보를 유통사, 소비자들이 손쉽게<br />
        확인 할 수 있어 상품의 시장 경쟁력과 소비자 신뢰도가 더욱 향상됩니다.
      </p>
      <div class="divider"></div>
      <div class="info-row">
        <div class="info-item">
          <i class="icon-user"></i> 대한상공회의소 데이터정보팀
        </div>
        <div class="info-item">
          <i class="icon-phone"></i> 문의전화 02-1234-5678
        </div>
      </div>
    </div>

    <!-- 절차 단계 -->
    <div class="step-box">
      <!-- Step 1 -->
      <div class="step-item highlight">
        <span class="step-title">홈페이지 접속</span>
        <p>친환경농산물 안심유통시스템 홈페이지에 접속하여 시작합니다.</p>
      </div>

      <!-- Step 2 -->
      <div class="step-item">
        <span class="step-title">회원가입</span>
        <ul class="custom-list">
          <li><strong>회원가입</strong>: 메인 페이지 상단 로그인 창에서 ‘회원가입’ 버튼 클릭</li>
          <li><strong>사업자등록번호</strong>: 개인/법인사업자 번호 입력 (등록자만 가입 가능)</li>
          <li><strong>GLN (업체코드)</strong>: 유통표준코드 사용을 위해 바코드 회원가입 필요</li>
          <li>
            <strong>업체구분</strong>:
            <ul>
              <li>– 생산자/중간취급자: 인증번호 보유</li>
              <li>– 유통업체: 인증번호 없이 상품만 납품</li>
            </ul>
          </li>
        </ul>
        <button class="btn">회원가입 바로가기</button>
      </div>

      <!-- Step 3 -->
      <div class="step-item">
        <span class="step-title">서비스 이용</span>
        <p>회원 승인 이메일 수신 후 로그인하여 서비스를 이용하실 수 있습니다.</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.usage-section {
  max-width: 960px;
  margin: 60px auto;
  padding: 0 24px;
  font-family: 'Noto Sans KR', sans-serif;
}

.page-title {
  text-align: center;
  font-size: 32px;
  color: #4b5b32; /* 진한 녹색 */
  font-weight: 800;
  margin-bottom: 40px;
}

.intro-box {
  background-color: #f7f7f0;
  border: 1px solid #d5d8c0;
  padding: 40px 30px;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.05);
}

.intro-title {
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 16px;
}

.intro-desc {
  font-size: 16px;
  color: #444;
  line-height: 1.8;
}

.divider {
  width: 50px;
  height: 3px;
  background-color: #6a865f; /* 톤다운된 녹색 */
  margin: 30px auto 20px;
  border-radius: 2px;
}

.info-row {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 50px;
  font-size: 15px;
  color: #333;
}

.info-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.icon-user::before { content: "👤"; font-size: 18px; }
.icon-phone::before { content: "📞"; font-size: 18px; }

.step-box {
  margin-top: 60px;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.step-item {
  background-color: #fff;
  border-left: 5px solid #6a865f;
  padding: 28px 28px;
  border-radius: 10px;
  box-shadow: 0 3px 10px rgba(0,0,0,0.06);
  transition: transform 0.25s ease;
}

.step-item:hover {
  transform: translateY(-3px);
}

.step-item.highlight {
  background-color: #f0f7f0;
}

.step-title {
  font-size: 20px;
  font-weight: bold;
  color: #4b5b32;
  margin-bottom: 14px;
}

.custom-list {
  padding-left: 22px;
  font-size: 15px;
  line-height: 1.7;
  color: #333;
}

.custom-list ul {
  padding-left: 16px;
  margin-top: 5px;
  font-size: 14px;
  color: #555;
}

.note {
  font-size: 13px;
  color: #666;
  margin-top: 8px;
}

.btn {
  margin-top: 20px;
  background-color: #6a865f;
  color: white;
  font-size: 15px;
  padding: 12px 22px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.25s ease, transform 0.15s ease;
}

.btn:hover {
  background-color: #4d6b48;
  transform: scale(1.05);
}

/* Responsive */
@media (max-width: 600px) {
  .step-item {
    padding: 20px;
  }

  .custom-list {
    padding-left: 14px;
  }
}
</style>