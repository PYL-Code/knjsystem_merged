<template>
  <div class="user-info">
    <section class="title-section">
      <h2>회원 정보 확인</h2>
      <p>"안전한 유통, 믿을 수 있는 우리 농산물"</p>
      <p>
        아래는 회원님의 인증 정보입니다.<br />
        등록된 기업과 인증 현황을 확인할 수 있습니다.
      </p>
    </section>

    <section class="info-box" v-if="user">
      <p><strong>{{ user.repNm }}</strong> 회원님</p>
      <p>① 기업 관리자:
        <input type="checkbox" disabled :checked="user.authMagYn === 'Y'" />
      </p>
      <p>② 상태:
        <span :class="{ approved: user.approvalYn === 'Y' }">
          {{ user.approvalYn === 'Y' ? '승인' : '미승인' }}
        </span>
      </p>
      <hr />
      <ul>
        <li>업체명: {{ user.compNm }}</li>
        <!-- 여기 추가 -->
        <li>업체 코드: {{ user.compCode }}</li>
        <li>사업자등록번호: {{ user.bnsNo }}</li>
        <li>상품바코드 수: {{ user.barcodeCount || 0 }}</li>
        <li>인증번호 등록 수: {{ user.certCount || 0 }}</li>
        <li>GLN: {{ user.comp880Cd }}</li>
      </ul>
    </section>

    <section v-else>
      <p style="color:red; text-align: center; font-weight: bold;">
        로그인 정보가 없습니다. 메인페이지로 이동합니다.
      </p>
    </section>

    <button class="logout-btn" @click="logout">로그아웃</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const user = ref(null);

onMounted(() => {
  const stored = localStorage.getItem('user');
  if (stored) {
    user.value = JSON.parse(stored);
  } else {
    router.push('/');
  }
});

const logout = () => {
  localStorage.removeItem('user');
  localStorage.removeItem('token');
  router.push('/');
};
</script>

<style scoped>
.user-info {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
  background: #fff;
  border: 1px solid #ddd;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  font-family: 'Noto Sans KR', sans-serif;
}
.title-section {
  text-align: center;
  margin-bottom: 20px;
}
.info-box p {
  margin: 8px 0;
}
.info-box ul {
  list-style: none;
  padding: 0;
  margin: 16px 0;
}
.info-box li {
  margin-bottom: 6px;
}
.approved {
  color: green;
  font-weight: bold;
}
.logout-btn {
  display: block;
  margin: 20px auto;
  padding: 10px 30px;
  background: #003366;
  color: #fff;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}
.logout-btn:hover {
  background: #005599;
}
</style>