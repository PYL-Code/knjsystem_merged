<template>
  <div class="list-container">
    <h2 class="list-title"><strong>바코드 조회</strong></h2>

    <!-- 사용자 정보 -->
    <div class="user-info" v-if="userInfo">
      <div class="user-card">
        <span class="label">업체명</span>
        <span class="value">{{ userInfo.companyName }}</span>
      </div>
      <div class="user-card">
        <span class="label">사업자등록번호</span>
        <span class="value">{{ userInfo.bnsNo }}</span>
      </div>
      <div class="user-card">
        <span class="label">GLN(업체코드)</span>
        <span class="value">{{ userInfo.company880Code || '-' }}</span>
      </div>
    </div>

    <!-- 검색 필터 -->
    <div class="filter-wrapper">
      <form @submit.prevent="fetchBarcodes" class="filter-form">
        <select v-model="searchField">
          <option value="">전체</option>
          <option value="barcodeNo">바코드번호</option>
          <option value="barcodeName">상품명</option>
          <option value="stdCertNo">인증번호</option>
          <option value="repItemName">대표품목명</option>
        </select>
        <input v-model="searchText" type="text" placeholder="검색어 입력" />
        <select v-model="search.productType">
          <option value="">전체 유형</option>
          <option value="단일상품">단일상품</option>
          <option value="혼합상품">혼합상품</option>
        </select>
        <button type="submit">조회</button>
      </form>
    </div>

    <!-- 바코드 목록 -->
    <table class="barcode-table">
      <thead>
      <tr>
        <th>번호</th>
        <th>바코드번호</th>
        <th>상품명</th>
        <th>등록일</th>
        <th>상품유형</th>
        <th>인증번호</th>
        <th>대표품목명</th>
      </tr>
      </thead>
      <tbody>
      <template v-if="barcodes.length > 0">
        <tr v-for="(barcode, index) in barcodes" :key="barcode.barcodeNo">
          <td>{{ barcodes.length - index }}</td>
          <td class="clickable" @click="goToDetail(barcode.barcodeId)">
            {{ barcode.barcodeNo }}
          </td>
          <td>{{ barcode.barcodeName }}</td>
          <td>{{ formatDate(barcode.barcodeRegDate) }}</td>
          <td>{{ barcode.productType }}</td>
          <td>{{ barcode.stdCertNo }}</td>
          <td>{{ barcode.repItemName }}</td>
        </tr>
      </template>
      <tr v-else>
        <td colspan="7" class="no-data">조회된 데이터가 없습니다.</td>
      </tr>
      </tbody>
    </table>

    <div class="bottom-action">
      <router-link to="/barcode/insert" class="btn btn-add">바코드 등록</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { getToken } from '@/utils/auth';

const router = useRouter();
const barcodes = ref([]);
const userInfo = ref(null);
const search = ref({ productType: '' });
const searchField = ref('');
const searchText = ref('');

const fetchUserInfo = async () => {
  const token = getToken();
  try {
    const { data } = await axios.get('/api/list/member', {
      headers: { Authorization: `Bearer ${token}` }
    });
    userInfo.value = data;
  } catch (error) {
    console.error('사용자 정보 조회 실패:', error);
  }
};

const fetchBarcodes = async () => {
  const token = getToken();
  const params = {};

  if (searchField.value === '') {
    params.all = searchText.value;
  } else if (searchText.value) {
    params[searchField.value] = searchText.value;
  }

  if (search.value.productType) {
    params.productType = search.value.productType;
  }

  try {
    const { data } = await axios.get('/api/barcode/list', {
      params,
      headers: { Authorization: `Bearer ${token}` }
    });
    barcodes.value = data;
  } catch (error) {
    console.error('바코드 조회 실패:', error);
    barcodes.value = [];
  }
};

const goToDetail = (barcodeId) => {
  router.push({ path: '/barcode/detail', query: { barcodeId } });
};

const formatDate = (dateStr) => {
  if (!dateStr) return '-';
  return new Date(dateStr).toLocaleDateString();
};

onMounted(() => {
  fetchUserInfo();
  fetchBarcodes();
});
</script>

<style scoped>
.list-container {
  max-width: 960px;
  margin: auto;
  padding: 2rem;
  font-family: 'Segoe UI', sans-serif;
  color: #333;
}
.list-title {
  text-align: center;
  color: #2e7d32; /* 초록 */
  font-size: 2rem;
  margin-bottom: 2rem;
}
.user-info {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
  margin-bottom: 2rem;
}
.user-card {
  flex: 1;
  background: #e8f5e9; /* 연한 초록 */
  border: 1px solid #c8e6c9; /* 초록 테두리 */
  border-radius: 6px;
  padding: 1rem;
  text-align: center;
}
.label {
  font-weight: bold;
}
.value {
  display: block;
  margin-top: 0.4rem;
}
.filter-wrapper {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}
.filter-form {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
  justify-content: center;
}
.filter-form select {
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 160px;
}
.filter-form input {
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 260px;
}
.filter-form button {
  padding: 0.6rem 1.2rem;
  background-color: #2e7d32; /* 초록 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.barcode-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 2rem;
}
.barcode-table thead th {
  background-color: #e8f5e9; /* 연한 초록 */
}
.barcode-table th {
  background-color: #e8f5e9;
  color: #2e7d32;
  font-weight: 600;
  font-size: 0.95rem;
  border-top: 2px solid #c8e6c9;
  padding: 0.75rem;
  text-align: center;
}
.barcode-table td {
  border: 1px solid #ddd;
  padding: 0.75rem;
  text-align: center;
}
.barcode-table tr:hover {
  background-color: #f1f8f4; /* 연한 초록톤 호버 */
}
.no-data {
  text-align: center;
  color: #999;
  font-style: italic;
}
.bottom-action {
  text-align: right;
}
.btn-add {
  background-color: #1f8249;
  color: white;
  text-decoration: none;
  padding: 0.6rem 1.2rem;
  border-radius: 5px;
  font-weight: bold;
}
.clickable {
  color: #2e7d32;
  cursor: pointer;
}
.clickable:hover {
  color: #1b5e20;
}
</style>

