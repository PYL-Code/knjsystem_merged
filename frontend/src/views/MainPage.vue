<template>
  <div class="main-wrapper">
    <!-- 타이틀 영역 -->
    <section class="title-section">
      <p class="title">친환경농산물 안심유통시스템</p>
      <p class="description">
        “안전한 유통, 믿을 수 있는 우리 농산물”<br />
        국립농산물품질관리원과 대한상공회의소가 공동으로 친환경농산물에<br />
        국제표준 바코드를 부여하여 유통사 전산망이나 소비자 스마트폰을 통해<br />
        인증상태를 실시간으로 확인할 수 있는 서비스입니다.
      </p>
      <div class="search-bar">
        <input type="text" placeholder="검색어를 입력하세요" />
        <button>검색</button>
      </div>
    </section>

    <!-- 메인 콘텐츠 -->
    <section class="main-content-section">
      <!-- 왼쪽 로그인 or 회원정보 -->
      <div class="main-left">
        <!-- ✅ 로그인 전 -->
        <div v-if="!currentUser.user" class="login-box">
          <h3>회원 로그인</h3>
          <input type="text" v-model="loginId" placeholder="아이디" />
          <input type="password" v-model="password" placeholder="비밀번호" />
          <button @click="login">로그인</button>
          <ul class="login-links">
            <li><RouterLink to="/form">회원가입</RouterLink></li>
            <li><a href="#">아이디 찾기</a></li>
            <li><a href="#">비밀번호 찾기</a></li>
          </ul>
          <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </div>

        <!-- ✅ 로그인 후 -->
        <div v-else class="info-box">
          <h3>회원 정보</h3>
          <p><strong>{{ currentUser.user.repNm }}</strong> 회원님</p>
          <p>기업 관리자:
            <input type="checkbox" disabled :checked="currentUser.user.authMagYn === 'Y'" />
          </p>
          <p>상태:
            <span :class="{ approved: currentUser.user.approvalYn === 'Y' }">
              {{ currentUser.user.approvalYn === 'Y' ? '승인' : '미승인' }}
            </span>
          </p>
          <hr />
          <ul>
            <li>업체명: {{ currentUser.user.compNm }}</li>
            <li>사업자등록번호: {{ currentUser.user.bnsNo }}</li>
            <li>상품바코드 수: {{ currentUser.user.barcodeCount || 0 }}</li>
            <li>인증번호 등록 수: {{ currentUser.user.certCount || 0 }}</li>
            <li>GLN: {{ currentUser.user.comp880Cd }}</li>
          </ul>
          <button @click="logout">로그아웃</button>
        </div>
      </div>

      <!-- 오른쪽 바로가기 버튼 + 공지 -->
      <div class="main-right">
        <div class="service-buttons">
          <RouterLink to="/intro" class="btn-tile blue">사업소개</RouterLink>
          <RouterLink to="/Procedure" class="btn-tile green">이용절차</RouterLink>
          <RouterLink to="/MobileService" class="btn-tile orange">모바일 서비스</RouterLink>
          <RouterLink to="/directions" class="btn-tile gray">오시는 길</RouterLink>
        </div>
        <div class="notice-box">
          <h4>공지사항</h4>
          <ul>
            <li>
              <a>친환경농산물 사용자 매뉴얼</a>
              <span class="notice-date">2025-05-08</span>
            </li>
          </ul>
          <div class="notice-more">
            <RouterLink to="/board" class="more-link">더보기</RouterLink>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { currentUser } from '@/stores/currentUser'; // ✅ 전역 상태 import

const loginId = ref('');
const password = ref('');
const errorMessage = ref('');
const router = useRouter();

const login = async () => {
  try {
    const res = await axios.post('/user/users/info', {
      loginId: loginId.value,
      password: password.value,
    });

    // ✅ 로컬 저장
    localStorage.setItem('user', JSON.stringify(res.data.user));
    localStorage.setItem('token', res.data.token);

    // ✅ 전역 상태 갱신
    currentUser.user = res.data.user;
    currentUser.token = res.data.token;
  } catch (err) {
    errorMessage.value = '로그인 실패: ' + (err.response?.data?.message || '서버 오류');
  }
};

const logout = () => {
  localStorage.removeItem('user');
  localStorage.removeItem('token');
  currentUser.user = null;
  currentUser.token = null;
};
</script>

<style scoped>

.main-wrapper {
  font-family: 'Noto Sans KR', sans-serif;
  min-height: 100vh;
  background: url('@/imgs/image.png') no-repeat center center;
  background-size: cover;
  padding: 80px 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title-section {
  text-align: center;
  margin-bottom: 50px;
}
.title-section .title {
  font-size: 48px; /* 확대됨 */
  font-weight: bold;
  color: #2f3b1f;
}
.description {
  font-size: 21px;
  color: #444;
  margin: 20px auto;
  line-height: 2;
}
.search-bar {
  margin-top: 24px;
}
.search-bar input {
  padding: 12px;
  width: 360px;
  font-size: 18px;
}
.search-bar button {
  padding: 12px 20px;
  margin-left: 12px;
  background-color: #6a865f;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 18px;
  border-radius: 8px;
  transition: transform 0.2s ease;
}
.search-bar button:hover {
  transform: scale(1.05);
}

.main-content-section {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 80px;
  flex-wrap: wrap;
  max-width: 1200px;
  width: 100%;
}

.main-left {
  width: 360px;
}
.login-box, .info-box {
  padding: 30px;
  background: white;
  border: 1px solid #ccc;
  box-shadow: 3px 3px 12px rgba(0,0,0,0.1);
  border-radius: 10px;
}
.login-box h3, .info-box h3 {
  text-align: center;
  margin-bottom: 20px;
  color: #2f3b1f;
  font-size: 28px;
}
.login-box input {
  width: 100%;
  padding: 14px;
  margin: 8px 0;
  font-size: 18px;
  box-sizing: border-box;
}
.login-box button, .info-box button {
  width: 100%;
  padding: 14px;
  margin-top: 16px;
  background: #6a865f;
  color: white;
  border: none;
  font-size: 18px;
  border-radius: 8px;
  cursor: pointer;
  transition: transform 0.2s ease;
}
.login-box button:hover, .info-box button:hover {
  transform: scale(1.05);
}
.login-links {
  list-style: none;
  display: flex;
  justify-content: space-between;
  padding: 0;
  margin-top: 14px;
  font-size: 16px;
}
.login-links a {
  color: #2f3b1f;
  text-decoration: none;
}
.error-message {
  color: red;
  margin-top: 14px;
  font-size: 16px;
}
.info-box ul {
  margin-top: 14px;
  padding-left: 22px;
  font-size: 18px;
}
.approved {
  color: green;
  font-weight: bold;
}

.main-right {
  display: flex;
  flex-direction: column;
  gap: 30px;
}
.service-buttons {
  display: grid;
  grid-template-columns: repeat(2, 180px);
  gap: 20px;
}
.btn-tile {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px;
  font-weight: bold;
  color: white;
  font-size: 22px;
  text-align: center;
  border-radius: 10px;
  text-decoration: none;
  transition: transform 0.2s ease;
}
.btn-tile:hover {
  transform: scale(1.05);
}
.btn-tile.blue { background-color: #0073e6; }
.btn-tile.green { background-color: #33aa33; }
.btn-tile.orange { background-color: #ff9933; }
.btn-tile.gray { background-color: #666; }

.notice-box {
  background: rgba(255,255,255,0.95);
  padding: 20px;
  border-radius: 8px;
  width: 380px;
  box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
}
.notice-box h4 {
  margin-bottom: 16px;
  font-size: 22px;
  color: #2f3b1f;
}
.notice-box ul {
  list-style: none;
  padding: 0;
  font-size: 18px;
}
.notice-box li {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.notice-box li span {
  font-size: 16px;
  color: #888;
}
.notice-more {
  text-align: right;
  margin-top: 10px;
}
.more-link {
  font-size: 16px;
  color: #2f3b1f;
  text-decoration: underline;
}
</style>

