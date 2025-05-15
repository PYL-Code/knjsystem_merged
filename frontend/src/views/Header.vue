<template>
  <header class="main-header">
    <div class="top-bar">
      <div class="container">
        <h1 class="logo">
          <RouterLink to="/">친환경농산물 안심유통시스템</RouterLink>
        </h1>
        <nav class="top-menu">
          <ul>
            <li><RouterLink to="/">메인페이지</RouterLink></li>
            <li><RouterLink to="/directions">오시는 길</RouterLink></li>
            <template v-if="!currentUser.user">
              <li><RouterLink to="/login">로그인</RouterLink></li>
              <li><RouterLink to="/form">회원가입</RouterLink></li>
            </template>
            <template v-else>
              <li><RouterLink to="/info/edit">내 정보</RouterLink></li>
              <li><a href="#" @click.prevent="logout">로그아웃</a></li>
            </template>
          </ul>
        </nav>
      </div>
    </div>

    <!-- ▼ GNB 영역 -->
    <nav class="gnb-bar">
      <ul class="gnb-menu">
        <li><RouterLink to="/intro">사업소개</RouterLink></li>
        <li><RouterLink to="/Procedure">이용절차</RouterLink></li>
        <li><RouterLink to="/barcode">모바일 서비스</RouterLink></li>
        <li><RouterLink to="/board">공지사항</RouterLink></li>
        <li class="dropdown">
          <template v-if="currentUser.user">
            <a href="#">상품관리</a>
            <ul class="submenu">
              <li><RouterLink to="#">인증번호</RouterLink></li>
              <li><RouterLink to="/barcode/list">상품바코드정보</RouterLink></li>
              <li><RouterLink to="#">이력정보</RouterLink></li>
            </ul>
          </template>
          <template v-else>
            <a href="#" @click.prevent="showLoginPopup">상품관리</a>
          </template>
        </li>
      </ul>
    </nav>

    <!-- 로그인 안내 메시지 -->
    <div v-if="loginPopupVisible" class="login-popup">
      <p>로그인 전용 페이지입니다.<br />로그인해주세요!</p>
      <button @click="goToLogin">로그인하러 가기</button>
    </div>
  </header>
</template>

<script setup>
import { ref, inject } from 'vue';
import { useRouter } from 'vue-router';
import { currentUser } from '@/stores/currentUser';

const router = useRouter();
const loginPopupVisible = ref(false);

const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  currentUser.user = null;
  currentUser.token = null;
  router.push('/');
};

const showLoginPopup = () => {
  loginPopupVisible.value = true;
  setTimeout(() => {
    loginPopupVisible.value = false;
  }, 3000);
};

const goToLogin = () => {
  loginPopupVisible.value = false;
  router.push('/login');
};
</script>


<style scoped>
.main-header {
  background-color: #f4f1e1;
  font-family: 'Noto Sans KR', sans-serif;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
}

/* 상단 바 */
.top-bar {
  background-color: #f4f1e1;
  padding: 18px 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  flex: 1;
}

.logo a {
  color: #2f3b1f;
  font-size: 35px;
  font-weight: 700;
  text-decoration: none;
  transition: color 0.3s ease;
}

.logo a:hover {
  color: #557941;
}

/* 메뉴 */
.top-menu {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.top-menu ul {
  display: flex;
  align-items: center;
  gap: 30px;
  list-style: none;
  margin: 0;
  padding: 0;
}

.top-menu li a {
  color: #333;
  font-size: 20px;
  padding: 10px 20px;
  border-radius: 12px;
  background-color: #f0f5ec;
  text-decoration: none; /* 밑줄 제거 */
  font-weight: 500;
  border: 1px solid #ccd9c3;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;
}

.top-menu li a:hover {
  background-color: #dcead8;
  color: #1a3b1a;
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* GNB 바 */
.gnb-bar {
  background-color: #6a865f;
  padding: 14px 0;
  border-radius: 0 0 12px 12px;
}

.gnb-menu {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  align-items: center;
  gap: 40px;
  list-style: none;
}

.gnb-menu li a {
  color: white;
  font-size: 23px;
  padding: 6px 16px;
  text-decoration: none;
  border-radius: 18px;
  transition: background-color 0.3s;
}

.gnb-menu li a:hover {
  background-color: rgba(255, 255, 255, 0.25);
}

/* 드롭다운 */
.dropdown {
  position: relative;
}

.submenu {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  background-color: white;
  min-width: 180px;
  z-index: 1000;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  border-radius: 8px;
  overflow: hidden;
}

.submenu li {
  border-bottom: 1px solid #eee;
}

.submenu li:last-child {
  border-bottom: none;
}

:deep(.submenu a) {
  display: block;
  padding: 12px 16px;
  text-decoration: none;
  color: #333 !important;
  font-size: 15px;
}

:deep(.submenu a:hover) {
  background-color: #f9f9f9;
}

.dropdown:hover .submenu {
  display: block;
}

/* 로그인 팝업 */
.login-popup {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  color: #333;
  padding: 24px 30px;
  border-radius: 10px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.2);
  z-index: 9999;
  text-align: center;
  font-size: 16px;
  animation: fadeIn 0.3s ease-out;
}

.login-popup button {
  margin-top: 15px;
  padding: 10px 18px;
  background-color: #6a865f;
  color: white;
  border: none;
  border-radius: 20px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-popup button:hover {
  background-color: #557941;
}

/* 애니메이션 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translate(-50%, -40%);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%);
  }
}
</style>
