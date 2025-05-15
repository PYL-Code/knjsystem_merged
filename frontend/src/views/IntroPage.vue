<template>
  <div class="service-page">
    <!-- 제목 -->
    <section class="info-section">
      <h2 class="info-title">친환경농산물 안심유통시스템으로 확인 할 수 있는 정보</h2>

      <!-- 인증/유통 정보 테이블 -->
      <table class="info-table">
        <tr>
          <th>상품별 인증정보</th>
          <td>품목명, 생산자(소속농가), 인증번호, 인증상태, 인증일자, 인증기간, 인증종류, 인증기관명 등</td>
        </tr>
        <tr>
          <th>상품별 유통이력정보</th>
          <td>포장일자, 납품일자, 납품처(유통사)</td>
        </tr>
      </table>

      <!-- 효과 박스 3개 -->
      <div class="effect-boxes">
        <div class="box">국산 농산물 경쟁력 강화</div>
        <div class="box">소비자 신뢰도 향상</div>
        <div class="box">안전한 농산물 유통</div>
      </div>
    </section>

    <!-- 사용자 정보 -->
    <section class="user-section">
      <h2 class="section-title">서비스 사용자</h2>

      <div class="user-list">
        <!-- 중간취급업자 -->
        <div class="user-item">
          <img src="@/imgs/intro1.png"  alt="중간취급업자" class="user-icon" />
          <div class="user-content">
            <h3 class="user-role">중간취급업자</h3>
            <ul>
              <li>생산자로부터 받은 친환경농산물을 유통판매단위로 포장하여 유통사에 납품</li>
              <li>시스템에 접속하여 바코드 및 유통이력정보 등록</li>
              <li>상품 라벨에 바코드, 유통이력 코드 표기</li>
            </ul>
          </div>
        </div>

        <!-- 유통사 -->
        <div class="user-item">
          <img src="@/imgs/intro2.png" alt="유통사" class="user-icon" />
          <div class="user-content">
            <h3 class="user-role">유통사</h3>
            <ul>
              <li>인증정보와 유통이력정보 수신</li>
              <li>입고 시 인증여부 확인</li>
              <li>부적합 상품의 판매 중지 및 회수</li>
              <li>소비자 대상 매장 홍보</li>
            </ul>
          </div>
        </div>

        <!-- 소비자 -->
        <div class="user-item">
          <img src="@/imgs/intro3.png"  alt="소비자" class="user-icon" />
          <div class="user-content">
            <h3 class="user-role">소비자</h3>
            <ul>
              <li>스마트폰 앱을 이용하여 인증 여부 확인</li>
              <li>상품 유통이력정보 조회</li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
// 별도 로직 없음
</script>

<style scoped>
.service-page {
  max-width: 960px;
  margin: 80px auto;
  padding: 0 24px;
  font-family: 'Noto Sans KR', sans-serif;
}

/* 정보 섹션 */
.info-section {
  margin-bottom: 70px;
}
.info-title {
  text-align: center;
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 40px;
  color: #4b5b32; /* 진한 녹색 텍스트 */
}
.info-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 50px;
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.06);
}
.info-table th,
.info-table td {
  border: 1px solid #ccc;
  padding: 20px;
  font-size: 17px;
  text-align: left;
  line-height: 1.7;
}
.info-table th {
  width: 240px;
  background-color: #f2f5ec; /* 연한 그린 */
  font-weight: 600;
  color: #5a743f; /* 녹색 포인트 */
}
.effect-boxes {
  display: flex;
  justify-content: space-between;
  gap: 24px;
  flex-wrap: wrap;
}
.box {
  flex: 1;
  min-width: 240px;
  text-align: center;
  padding: 24px;
  border: 2px solid #6a865f;
  border-radius: 10px;
  font-size: 18px;
  font-weight: 600;
  background-color: #fbfbf7;
  transition: background-color 0.4s ease, transform 0.3s ease;
}
.box:hover {
  background-color: #e6f2e6;
  transform: scale(1.05);
  cursor: default;
}

/* 사용자 섹션 */
.section-title {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 50px;
  color: #4b5b32;
}
.user-list {
  display: flex;
  flex-direction: column;
  gap: 50px;
}
.user-item {
  display: flex;
  align-items: flex-start;
  gap: 30px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 24px;
}
.user-icon {
  width: 100px;
  height: 100px;
  object-fit: contain;
}
.user-content {
  flex: 1;
}
.user-role {
  font-size: 22px;
  font-weight: 700;
  color: #5c7d4c;
  margin-bottom: 14px;
}
.user-content ul {
  padding-left: 20px;
  margin: 0;
  color: #333;
  font-size: 17px;
  line-height: 1.8;
}
.user-content li {
  list-style-type: disc;
}

/* 반응형 */
@media (max-width: 600px) {
  .info-table th,
  .info-table td {
    display: block;
    width: 100%;
  }
  .effect-boxes {
    flex-direction: column;
  }
  .box {
    margin-bottom: 15px;
  }
  .user-item {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  .user-content ul {
    padding-left: 0;
  }
}
</style>