<template>
  <footer class="footer">
    <div class="footer-container">
      <p>서울시 중구 세종대로 39 상공회의소회관 17층</p>
      <p>전화: 02)1234-1234 / 팩스: 02)1234-1234</p>
      <p>COPYRIGHT© GS1KR.ORG ALL RIGHTS RESERVED.</p>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #6a865f; /* 올리브 그린 */
  color: #fdfcf8; /* 약간 부드러운 흰색 */
  text-align: center;
  padding: 30px 0;
  margin-top: 60px;
  font-size: 18px;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.05);
  animation: fadeInUp 0.5s ease-out;
}

.footer-container p {
  margin: 8px 0;
  font-weight: 400;
}

/* 부드러운 등장 애니메이션 */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>