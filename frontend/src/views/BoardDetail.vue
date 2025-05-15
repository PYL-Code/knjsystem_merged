<template>
  <div class="board-detail">
    <h2 class="title">{{ board.title }}</h2>

    <div class="meta-box">
      <div><strong>등록일:</strong> {{ formatDate(board.createdAt) }}</div>
      <div><strong>조회수:</strong> {{ board.views }}</div>
      <div v-if="board.attachmentUrl">
        <strong>첨부파일:</strong>
        <a :href="`/files/${board.attachmentUrl}`" target="_blank" class="file-link">
          {{ board.attachmentUrl }}
        </a>
      </div>
    </div>

    <div class="content-box">
      <p class="content">{{ board.content }}</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const board = ref({});

const fetchBoard = async () => {
  const res = await axios.get(`/board/boards/${route.params.id}`);
  board.value = res.data;
};

const formatDate = (dateStr) => {
  if (!dateStr) return '-';
  const date = new Date(dateStr);
  return isNaN(date) ? '-' : date.toISOString().split('T')[0];
};

onMounted(fetchBoard);
</script>

<style scoped>
.board-detail {
  max-width: 900px;
  margin: 40px auto;
  padding: 30px;
  background-color: #fefefe;
  border: 1px solid #dcdcdc;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
  font-family: 'Noto Sans KR', sans-serif;
}

.title {
  font-size: 28px;
  font-weight: bold;
  color: #4b5b32;
  margin-bottom: 24px;
  text-align: center;
}

.meta-box {
  display: flex;
  flex-direction: column;
  gap: 10px;
  font-size: 15px;
  color: #333;
  background: #f9f9f9;
  padding: 16px;
  border-radius: 8px;
  margin-bottom: 20px;
}

.file-link {
  color: #3a6c3c;
  text-decoration: underline;
  font-weight: 500;
}

.content-box {
  font-size: 16px;
  line-height: 1.8;
  color: #444;
  white-space: pre-wrap;
  background-color: #fcfcfc;
  padding: 20px;
  border-radius: 8px;
  border: 1px solid #eee;
}
</style>