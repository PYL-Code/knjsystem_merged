<template>
  <div class="board-wrapper">
    <h2 class="title">공지사항</h2>

    <div class="search-wrap">
      <select v-model="searchField" class="search-select">
        <option value="all">전체</option>
        <option value="title">제목</option>
        <option value="content">내용</option>
      </select>
      <input v-model="searchText" type="text" placeholder="검색어를 입력해주세요" class="search-input" />
      <button class="search-btn" @click="searchBoards">검색</button>
    </div>

    <table class="board-table">
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>첨부</th>
        <th>등록일</th>
        <th>조회</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(board, index) in boards" :key="board.id">
        <td>{{ index + 1 }}</td>
        <td class="title-cell">
          <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
        </td>
        <td>
          <span v-if="board.attachmentUrl" class="attachment-icon">📎</span>
        </td>
        <td>{{ formatDate(board.createdAt) }}</td>
        <td>{{ board.views }}</td>
      </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button @click="goToPage(currentPage - 1)" :disabled="currentPage === 1">«</button>
      <button class="active">{{ currentPage }}</button>
      <button @click="goToPage(currentPage + 1)">»</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      searchField: 'all',
      searchText: '',
      boards: [],
      currentPage: 1,
    };
  },
  mounted() {
    this.fetchBoards();
  },
  methods: {
    fetchBoards() {
       axios.get(`/boards`, {
        params: {
          keyword: this.searchText,
          page: this.currentPage - 1,
        }
      })
          .then((res) => {
            this.boards = res.data.content;
          });
    },
    searchBoards() {
      this.currentPage = 1;
      this.fetchBoards();
    },
    goToPage(page) {
      if (page < 1) return;
      this.currentPage = page;
      this.fetchBoards();
    },
    formatDate(dateStr) {
      return new Date(dateStr).toISOString().split('T')[0];
    },
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 32px;
  color: #4b5b32;
  margin: 40px 0 30px;
  font-weight: bold;
}

.search-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 24px;
  gap: 10px;
}
.search-select,
.search-input {
  padding: 10px;
  font-size: 15px;
  border: 1px solid #ccc;
  border-radius: 6px;
}
.search-btn {
  background-color: #6a865f;
  color: white;
  border: none;
  padding: 10px 16px;
  font-size: 15px;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}
.search-btn:hover {
  background-color: #557941;
}

.board-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 16px;
}
.board-table th,
.board-table td {
  border: 1px solid #ccc;
  padding: 14px;
  text-align: center;
}
.board-table th {
  background-color: #f4f9f7;
  color: #003366;
  font-weight: bold;
}
.title-cell {
  text-align: left;
  padding-left: 20px;
}

.attach-icon {
  width: 20px;
  height: 20px;
  object-fit: contain;
}

.pagination {
  text-align: center;
  margin-top: 30px;
}
.pagination button {
  margin: 0 4px;
  padding: 8px 14px;
  font-size: 15px;
  border: 1px solid #ccc;
  background-color: white;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}
.pagination button:hover {
  background-color: #f0f4f1;
}
.pagination .active {
  background-color: #4b5b32;
  color: white;
  font-weight: bold;
}


</style>
