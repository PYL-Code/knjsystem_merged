<template>
  <div class="directions-page">
    <!-- 소개 영역 -->
    <section class="content-top">
      <h1 class="title">친환경농산물 안심유통시스템</h1>
      <p class="description">
        “안전한 유통, 믿을 수 있는 우리 농산물”<br />
        국립농산물품질관리원과 대한상공회의소가 공동으로 친환경농산물에<br />
        국제표준 바코드를 부여하여 유통사 전산망이나 소비자 스마트폰을 통해<br />
        인증상태를 실시간으로 확인할 수 있는 서비스입니다.
      </p>
    </section>

    <!-- 본문 -->
    <section class="content-body">
      <h2 class="content-title">오시는 길</h2>

      <!-- 주소 정보 -->
      <div class="info-box">
        <h3>주소 및 연락처</h3>
        <ul>
          <li><strong>주소:</strong> (우)04513 서울특별시 중구 세종대로 39 상공회의소회관 17층</li>
          <li><strong>전화:</strong> 02-6050-1484</li>
          <li><strong>팩스:</strong> 02-6050-1450</li>
        </ul>
      </div>

      <!-- 지하철 안내 -->
      <div class="info-box">
        <h3>찾아오시는 길</h3>
        <ul>
          <li><strong>지하철 1호선:</strong> 서울역 3번출구 남대문 방향</li>
          <li><strong>지하철 2호선:</strong> 시청역 9번출구 신한은행본점 방향</li>
          <li><strong>지하철 4호선:</strong> 회현역 5번출구 남대문 방향</li>
        </ul>
      </div>
    </section>
  </div>
</template>

<script setup>
// 특별한 스크립트 없음
</script>

<style scoped>
.directions-page {
  padding: 40px;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f7f7f2;
}

/* 소개 영역 */
.content-top {
  background-color: #f0f1ef;
  padding: 36px 28px;
  border-radius: 12px;
  margin-bottom: 36px;
  text-align: center;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
}
.title {
  font-size: 30px;
  color: #4b5b32;
  font-weight: 700;
}
.description {
  margin-top: 18px;
  font-size: 15.5px;
  color: #444;
  line-height: 1.9;
}

/* 본문 제목 */
.content-title {
  font-size: 24px;
  margin-bottom: 24px;
  font-weight: bold;
  color: #4b5b32;
}

/* 안내 박스 */
.info-box {
  background: #ffffff;
  border-radius: 10px;
  padding: 24px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.06);
  margin-bottom: 28px;
}
.info-box h3 {
  margin-bottom: 14px;
  font-size: 18px;
  font-weight: 600;
  color: #3c5523;
}
.info-box ul {
  list-style: none;
  padding-left: 0;
}
.info-box li {
  margin-bottom: 10px;
  font-size: 15.5px;
  color: #333;
  line-height: 1.6;
}
</style>