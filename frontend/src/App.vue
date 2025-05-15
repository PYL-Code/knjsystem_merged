<script setup>
import { onMounted, ref } from 'vue';
import Header from '@/views/Header.vue';
import Footer from './views/Footer.vue';
import { currentUser } from '@/stores/currentUser';
import axios from 'axios';

const isReady = ref(false); // 추가

onMounted(async () => {
  const savedUser = localStorage.getItem('user');
  const savedToken = localStorage.getItem('token');

  if (savedUser && savedToken) {
    try {
      await axios.get('/user/users/check', {
        headers: { Authorization: `Bearer ${savedToken}` }
      });
      currentUser.user = JSON.parse(savedUser);
      currentUser.token = savedToken;
    } catch (err) {
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      currentUser.user = null;
      currentUser.token = null;
    }
  }
  isReady.value = true;
});
</script>

<template>
  <div v-if="isReady" class="main-wrapper">
    <Header />
    <main class="main-content">
      <router-view />
    </main>
    <Footer />
  </div>
</template>