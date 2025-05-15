<template>
  <section class="mobile-service">
    <!-- 제목 -->
    <h2 class="page-title">모바일 서비스</h2>

    <!-- 소개 영역 -->
    <div class="intro-box">
      <h3 class="intro-title">모바일 서비스 소개</h3>
      <p>
        국립농산물품질관리원과 대한상공회의소에서 개발한 모바일 앱을 통해<br />
        상품바코드를 스캔하여 상품의 인증상태와 유통이력정보를 바로 확인할 수 있습니다.
      </p>
    </div>

    <!-- 사용 방법 -->
    <div class="usage-area">
      <h3 class="usage-title">모바일 앱 사용방법</h3>
      <div class="app-logo">
        <img src="@/imgs/intro1.png" alt="앱 로고" />
      </div>

      <div class="platform-box">
        <!-- 아이폰 -->
        <div class="platform-card ios">
          <div class="platform-title">
            <i class="icon"></i> 아이폰 사용자
          </div>
          <ol>
            <li>1. 아이폰 앱스토어에서 <strong>‘친환경농산물 안심유통시스템’</strong> 입력 후 검색</li>
            <li>2. 스마트폰에 앱 다운로드 설치</li>
          </ol>
        </div>

        <!-- 안드로이드 -->
        <div class="platform-card android">
          <div class="platform-title">
            <i class="icon">🤖</i> 안드로이드 사용자
          </div>
          <ol>
            <li>1. 플레이스토어에서 <strong>‘친환경농산물 안심유통시스템’</strong> 입력 후 검색</li>
            <li>2. 스마트폰에 앱 다운로드 설치</li>
          </ol>
        </div>
      </div>

      <p class="scan-text">친환경농산물 바코드 스캔하기</p>
    </div>
  </section>
</template>

<style scoped>
.mobile-service {
  max-width: 960px;
  margin: 80px auto;
  padding: 0 24px;
  font-family: 'Noto Sans KR', sans-serif;
}

/* 제목 */
.page-title {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: #4b5b32; /* 자연톤 텍스트 */
  margin-bottom: 40px;
}

/* 소개 박스 */
.intro-box {
  border: 1px solid #d8e3d2;
  padding: 40px 30px;
  text-align: center;
  margin-bottom: 60px;
  background-color: #f8f9f4;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}
.intro-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  color: #5d7441;
}
.intro-box p {
  font-size: 16px;
  color: #444;
  line-height: 1.8;
}

/* 사용 방법 */
.usage-title {
  font-size: 22px;
  text-align: center;
  font-weight: bold;
  margin-bottom: 30px;
  color: #4b5b32;
}
.app-logo {
  text-align: center;
  margin-bottom: 40px;
}
.app-logo img {
  width: 120px;
}

/* 플랫폼별 안내 박스 */
.platform-box {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: center;
  margin-bottom: 40px;
}
.platform-card {
  flex: 1 1 280px;
  border: 1px solid #b4c6a6;
  padding: 24px;
  border-top: 6px solid #6a865f; /* 기본 녹색 */
  background-color: #fcfcfa;
  border-radius: 8px;
  box-shadow: 0 3px 8px rgba(0,0,0,0.04);
  transition: transform 0.3s ease;
}
.platform-card:hover {
  transform: translateY(-5px);
}

.ios {
  border-top-color: #5d7441;
}
.android {
  border-top-color: #88b95c;
}
.platform-title {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  gap: 8px;
  color: #333;
}
.icon {
  font-size: 22px;
}
ol {
  list-style: none;
  padding: 0;
  margin: 0;
}
ol li {
  font-size: 15px;
  margin-bottom: 10px;
  color: #444;
}

/* 스캔 문구 */
.scan-text {
  text-align: center;
  font-weight: bold;
  font-size: 16px;
  margin-top: 40px;
  color: #4b5b32;
}
</style>