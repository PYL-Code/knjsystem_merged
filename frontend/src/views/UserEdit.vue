<template>
  <div class="user-edit">
    <h2>회원정보 수정</h2>

    <section>
      <h3>담당자 정보</h3>
      <label>성명</label>
      <input v-model="form.repNm" placeholder="담당자 이름" />

      <label>전화번호</label>
      <input v-model="form.repPhoneNo" placeholder="010-0000-0000" />

      <label>팩스번호</label>
      <input v-model="form.repFaxNo" placeholder="팩스번호 (선택)" />
    </section>

    <section>
      <h3>업체 정보</h3>
      <label>업체명</label>
      <input v-model="form.compNm" placeholder="업체명" />

      <label>업체 코드 (GLN)</label>
      <input v-model="form.comp880Cd" placeholder="예) 8801234567890" />

      <label>사업자등록번호</label>
      <input v-model="form.bnsNo" placeholder="10자리 숫자" />

      <label>업태구분</label>
      <select v-model="form.compType">
        <option value="P">생산자</option>
        <option value="S">판매자</option>
      </select>

      <label>우편번호</label>
      <input v-model="form.zipcode" />

      <label>주소</label>
      <input v-model="form.addr1" placeholder="도로명 주소" />
      <input v-model="form.addr2" placeholder="상세 주소" />
    </section>

    <div class="button-group">
      <button @click="submitEdit">수정 완료</button>
      <button class="cancel-btn" @click="goBack">취소</button>
    </div>

    <p v-if="message" class="message">{{ message }}</p>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { currentUser } from '@/stores/currentUser';

const router  = useRouter();
const message = ref('');

// 1️⃣ reactive 폼 초기화 (comp880Cd 추가)
const form = reactive({
  repNm:      '',
  repPhoneNo: '',
  repFaxNo:   '',
  compNm:     '',
  comp880Cd:  '',    // GLN 코드
  bnsNo:      '',
  compType:   'P',
  zipcode:    '',
  addr1:      '',
  addr2:      ''
});

onMounted(() => {
  // 2️⃣ 로그인 정보가 없으면 홈으로
  if (!currentUser.user) {
    router.push('/');
    return;
  }
  // 3️⃣ 전역 상태에서 받아온 user 를 form 에 복사
  Object.assign(form, currentUser.user);
});

const submitEdit = async () => {
  try {
    const token = currentUser.token;
    await axios.put(
        '/user/users/update',
        form,
        { headers: { Authorization: `Bearer ${token}` }}
    );

    // 4️⃣ 수정된 정보를 전역 상태와 localStorage 에 반영
    currentUser.user = { ...currentUser.user, ...form };
    localStorage.setItem('user', JSON.stringify(currentUser.user));

    message.value = '정보가 수정되었습니다.';
    router.push('/');
  }
  catch (err) {
    message.value = '수정 실패: ' +
        (err.response?.data || err.message || '서버 오류');
  }
};

const goBack = () => router.push('/info');
</script>

<style scoped>
.user-edit {
  max-width: 700px;
  margin: 30px auto;
  font-family: Arial, sans-serif;
  padding: 20px;
  background: #fff;
  border: 1px solid #ccc;
  box-shadow: 2px 2px 10px rgba(0,0,0,0.05);
}

h2 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 30px;
}

section {
  margin-bottom: 30px;
}

label {
  display: block;
  margin: 10px 0 4px;
  font-weight: bold;
  font-size: 14px;
}

input,
select {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  gap: 10px;
  margin-top: 20px;
}

button {
  flex: 1;
  padding: 12px;
  font-weight: bold;
  background-color: #003366;
  color: white;
  border: none;
  cursor: pointer;
}

.cancel-btn {
  background-color: #ccc;
  color: #333;
}

.message {
  text-align: center;
  margin-top: 20px;
  font-weight: bold;
  color: green;
}
</style>