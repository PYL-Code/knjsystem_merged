<template>
  <div class="signup">
    <h2>회원가입</h2>

    <!-- 로그인 정보 -->
    <section class="card">
      <h3>로그인 정보</h3>
      <div class="form-row">
        <label for="loginId">아이디</label>
        <input
            id="loginId"
            v-model="form.loginId"
            placeholder="아이디"
            @input="idChecked = false; idError = ''"
        />
        <button type="button" @click="checkLoginId">중복확인</button>
      </div>
      <p v-if="idError" :class="{'error': !idChecked, 'success': idChecked}">
        {{ idError }}
      </p>

      <div class="form-row">
        <label for="password">비밀번호</label>
        <input
            id="password"
            type="password"
            v-model="form.password"
            placeholder="비밀번호"
        />
      </div>
    </section>

    <!-- 담당자 정보 -->
    <section class="card">
      <h3>담당자 정보</h3>
      <div class="form-row">
        <label for="repNm">성명</label>
        <input id="repNm" v-model="form.repNm" placeholder="담당자 이름" />
      </div>
      <div class="form-row">
        <label for="repPhoneNo">전화번호</label>
        <input
            id="repPhoneNo"
            v-model="form.repPhoneNo"
            placeholder="010-0000-0000"
        />
      </div>
      <div class="form-row">
        <label for="repFaxNo">팩스번호</label>
        <input
            id="repFaxNo"
            v-model="form.repFaxNo"
            placeholder="팩스번호 (선택)"
        />
      </div>
    </section>

    <!-- 업체 정보 -->
    <section class="card">
      <h3>업체 정보</h3>
      <div class="form-row">
        <label for="compNm">업체명</label>
        <input id="compNm" v-model="form.compNm" placeholder="회사 이름" />
      </div>
      <div class="form-row">
        <label for="comp880Cd">업체 코드 (GLN)</label>
        <input
            id="comp880Cd"
            v-model="form.comp880Cd"
            placeholder="예) 8801234567890"
        />
      </div>
      <div class="form-row">
        <label for="bnsNo">사업자등록번호</label>
        <input id="bnsNo" v-model="form.bnsNo" placeholder="10자리 숫자" />
      </div>
      <div class="form-row">
        <label for="compType">업태구분</label>
        <select id="compType" v-model="form.compType">
          <option value="P">생산자</option>
          <option value="S">판매자</option>
        </select>
      </div>
      <div class="form-row">
        <label for="zipcode">우편번호</label>
        <input id="zipcode" v-model="form.zipcode" placeholder="우편번호" />
      </div>
      <div class="form-row">
        <label for="addr1">주소</label>
        <input id="addr1" v-model="form.addr1" placeholder="도로명 주소" />
      </div>
      <div class="form-row">
        <label for="addr2">&nbsp;</label>
        <input id="addr2" v-model="form.addr2" placeholder="상세 주소" />
      </div>
    </section>

    <!-- 약관 동의 -->
    <section class="card">
      <h3>약관 동의</h3>
      <div class="terms-box">
        <h4>제1조 (목적)</h4>
        <p>친환경농산물 안심유통시스템 회원 이용약관(이하 "본 약관")은 …</p>
        <h4>제2조 (약관의 효력 및 개정)</h4>
        <p>1. 본 약관은 시스템 홈페이지에 공지함으로써 …</p>
        <h4>제3조 (약관의 적용범위)</h4>
        <p>본 약관은 상의가 제공하는 …</p>
        <h4>제4조 (용어의 정의)</h4>
        <p>1. 본 약관에서 사용하는 용어의 정의는 다음과 같습니다. …</p>
      </div>
      <div class="agree-group">
        <label>
          <input type="checkbox" v-model="agree.terms" />
          이용약관에 동의합니다.
        </label>
        <label>
          <input type="checkbox" v-model="agree.privacy" />
          개인정보 수집에 동의합니다.
        </label>
      </div>
    </section>

    <!-- 버튼 -->
    <div class="button-group">
      <button class="btn-primary" @click="submitForm">회원가입</button>
      <button class="btn-secondary" @click="cancelForm">취소</button>
    </div>

    <p v-if="message" class="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Signup',
  data() {
    return {
      form: {
        loginId: '',
        password: '',
        bnsNo: '',
        compNm: '',
        comp880Cd: '',
        compType: 'P',
        repNm: '',
        repPhoneNo: '',
        repFaxNo: '',
        zipcode: '',
        addr1: '',
        addr2: '',
      },
      agree: {
        terms: false,
        privacy: false,
      },
      message: '',
      idError: '',
      idChecked: false,
    };
  },
  methods: {
    async checkLoginId() {
      if (!this.form.loginId) {
        this.idError = '아이디를 먼저 입력하세요.';
        this.idChecked = false;
        return;
      }
      try {
        const { data } = await axios.get('/user/users/check-id', {
          params: { loginId: this.form.loginId }
        });
        if (data.exists) {
          this.idError = '이미 사용 중인 아이디입니다.';
          this.idChecked = false;
        } else {
          this.idError = '사용 가능한 아이디입니다!';
          this.idChecked = true;
        }
      } catch {
        this.idError = '중복 확인 중 오류가 발생했습니다.';
        this.idChecked = false;
      }
    },
    async submitForm() {
      if (!this.agree.terms || !this.agree.privacy) {
        this.message = '약관과 개인정보 수집에 모두 동의해야 합니다.';
        return;
      }
      if (!this.idChecked) {
        this.message = '아이디 중복 확인을 먼저 해주세요.';
        return;
      }
      try {
        await axios.post('/user/users/register', this.form);
        this.$router.push('/');
      } catch (err) {
        this.message = '회원가입 실패: ' + (err.response?.data || '서버 오류');
      }
    },
    cancelForm() {
      this.$router.push('/');
    },
  },
};
</script>

<style scoped>
.signup {
  max-width: 800px;
  margin: 60px auto;
  padding: 0 24px;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f7f7f2;
}

h2 {
  text-align: center;
  font-size: 32px;
  margin-bottom: 36px;
  color: #4b5b32;
  font-weight: 700;
}

.card {
  background: #ffffff;
  border: 1px solid #d5e1d2;
  border-radius: 10px;
  padding: 24px;
  margin-bottom: 28px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.card h3 {
  margin-bottom: 18px;
  font-size: 20px;
  color: #3c5523;
  font-weight: 600;
}

.form-row {
  display: flex;
  align-items: center;
  margin-bottom: 14px;
  gap: 12px;
}

.form-row label {
  width: 140px;
  font-weight: 500;
  color: #333;
}

.form-row input,
.form-row select {
  flex: 1;
  padding: 10px 12px;
  font-size: 15px;
  border: 1px solid #bbb;
  border-radius: 6px;
  background: #fff;
}

.form-row button {
  padding: 8px 14px;
  background-color: #6a865f;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.2s ease;
}

.form-row button:hover {
  background-color: #557941;
}

.terms-box {
  max-height: 200px;
  overflow-y: auto;
  border: 1px solid #cdd9c4;
  background: #f4f9f7;
  padding: 14px;
  margin-bottom: 14px;
  font-size: 14px;
  line-height: 1.7;
}

.agree-group {
  display: flex;
  flex-direction: column;
  gap: 10px;
  font-size: 14px;
  color: #333;
}

.agree-group label {
  display: flex;
  align-items: center;
  gap: 8px;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 24px;
  margin: 36px 0;
}

button {
  padding: 12px 28px;
  font-size: 16px;
  border-radius: 6px;
  cursor: pointer;
}

.btn-primary {
  background-color: #6a865f;
  color: white;
  border: none;
  font-weight: bold;
}

.btn-secondary {
  background-color: #e0e0e0;
  color: #333;
  border: none;
  font-weight: bold;
}

button:hover {
  opacity: 0.92;
}

.message {
  text-align: center;
  color: #c0392b;
  font-weight: bold;
  margin-top: 12px;
  font-size: 15px;
}

.error {
  color: #c0392b;
  margin: 6px 0 12px;
}

.success {
  color: #2e7d32;
  margin: 6px 0 12px;
}
</style>