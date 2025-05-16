<template>
  <div class="login-page">
    <div class="login-top">
      <h2>친환경농산물 안심유통시스템</h2>
      <p class="subtitle">“안전한 유통, 믿을 수 있는 우리 농산물”</p>
      <p>
        국립농산물품질관리원과 대한상공회의소가 공동으로 친환경농산물에 국제표준 바코드를 부여하여<br />
        유통사 전산망이나 소비자 스마트폰을 통해 인증상태를 실시간으로 확인할 수 있는 서비스입니다.
      </p>
    </div>

    <div class="login-box">
      <h3>회원 로그인</h3>
      <input type="text" v-model="loginId" placeholder="아이디" />
      <input type="password" v-model="password" placeholder="비밀번호" />
      <button @click="login">로그인</button>

      <ul class="login-options">
        <li><RouterLink to="/find-id">아이디 찾기</RouterLink></li>
        <li><RouterLink to="/find-password">비밀번호 찾기</RouterLink></li>
        <li><RouterLink to="/register">회원가입</RouterLink></li>
      </ul>

      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </div>
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
    const response = await axios.post('/users/info', {
      loginId: loginId.value,
      password: password.value
    });

    // ✅ JWT와 사용자 정보 저장
    const userData = response.data.user;
    const token = response.data.token;

    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(userData));

    // ✅ 전역 상태 반영 (반응형 유지)
    currentUser.token = token;
    currentUser.user  = userData;

    // ✅ 메인 페이지로 이동
    router.push('/');
  } catch (err) {
    errorMessage.value = '로그인 실패: 아이디 또는 비밀번호를 확인하세요.';
  }
};
</script>

<style scoped>
.login-page {
  max-width: 520px;
  margin: 80px auto;
  padding: 30px 24px;
  background: #fefefe;
  border: 1px solid #d8e3d2;
  border-radius: 12px;
  box-shadow: 0 4px 14px rgba(0, 0, 0, 0.08);
  font-family: 'Noto Sans KR', sans-serif;
  text-align: center;
}

.login-top h2 {
  color: #4b5b32;
  margin-bottom: 10px;
  font-size: 28px;
  font-weight: 700;
}

.login-top p {
  font-size: 14.5px;
  color: #444;
  line-height: 1.7;
  margin-bottom: 10px;
}

.login-box h3 {
  margin: 28px 0 16px;
  font-size: 20px;
  color: #003366;
  font-weight: 600;
}

.login-box input {
  width: 100%;
  padding: 12px;
  margin: 8px 0;
  font-size: 15px;
  border: 1px solid #ccc;
  border-radius: 6px;
  box-sizing: border-box;
}

.login-box button {
  width: 100%;
  padding: 12px;
  margin-top: 14px;
  background: #6a865f;
  color: white;
  border: none;
  font-size: 16px;
  font-weight: bold;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-box button:hover {
  background: #557941;
}

.login-options {
  list-style: none;
  padding: 0;
  margin-top: 16px;
  display: flex;
  justify-content: space-between;
  font-size: 14px;
}

.login-options a {
  color: #3c5523;
  text-decoration: none;
  padding: 2px 6px;
  border-radius: 4px;
  transition: background-color 0.2s ease;
}

.login-options a:hover {
  background-color: #e4eddc;
}

.error {
  color: red;
  margin-top: 12px;
  font-size: 14px;
}
</style>