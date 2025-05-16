<template>
  <div class="wrapper">
    <h2 class="title">친환경농산물 바코드 조회</h2>
    <input
        type="text"
        v-model="barcode"
        placeholder="바코드를 입력하세요"
        class="input-barcode"
    />
    <button @click="searchBarcode" class="btn-search">조회</button>

    <div v-if="product" class="result-area">
      <h3 class="section-title">📦 상품 기본 정보</h3>
      <ul class="info-list">
        <li><strong>바코드 번호:</strong> {{ product.barcodeNumber }}</li>
        <li><strong>상품명:</strong> {{ product.productName }}</li>
        <li><strong>상품 구분:</strong> {{ product.productType }}</li>
        <li><strong>상품 상태:</strong> {{ product.productStatus }}</li>
        <li><strong>생산자명:</strong> {{ product.handlerName }}</li>
      </ul>

      <h3 class="section-title">✅ 인증 정보 목록</h3>
      <div
          v-for="cert in product.certifications"
          :key="cert.certificationCode"
          class="cert-item"
      >
        <ul class="info-list">
          <li><strong>인증번호:</strong> {{ cert.certificationCode }}</li>
          <li><strong>농가명:</strong> {{ cert.farmerName }}</li>
          <li><strong>인증상태:</strong> {{ cert.certificationStatus }}</li>
          <li><strong>상품명:</strong> {{ cert.productCodeName }}</li>
          <li><strong>인증구분:</strong> {{ cert.certificationAgency }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      barcode: "",
      product: null,
    };
  },
  methods: {
    async searchBarcode() {
      try {
        const res = await axios.get(`/products/barcode/${this.barcode}`);
        this.product = res.data;
      } catch (error) {
        alert("해당 바코드로 등록된 상품이 없습니다.");
        this.product = null;
      }
    },
  },
};
</script>

<style scoped>
.wrapper {
  max-width: 520px;
  margin: 0 auto;
  padding: 20px;
  font-family: "Noto Sans KR", sans-serif;
}

.title {
  font-size: 20px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 15px;
  color: #2d3e50;
}

.input-barcode {
  width: 100%;
  padding: 12px;
  margin-bottom: 12px;
  border: 1px solid #bbb;
  border-radius: 4px;
  font-size: 16px;
}

.btn-search {
  width: 100%;
  padding: 12px;
  background-color: #2f593d;
  color: #fff;
  font-size: 16px;
  border: 1px solid #2f593d;
  font-weight: 600;
  border-radius: 4px;
  transition: background 0.2s ease;
}
.btn-search:hover {
  background-color: #264d34;
}

.section-title {
  font-size: 17px;
  font-weight: 700;
  margin-top: 20px;
  margin-bottom: 8px;
  color: #2d3e50;
}

.result-area {
  margin-top: 20px;
}

.info-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.cert-item {
  margin-top: 10px;
  padding: 12px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 6px;
  box-shadow: 0 1px 2px rgba(0,0,0,0.05);
}
</style>