import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// 전역 상태(user/token) 스토어
import { currentUser } from '@/stores/currentUser'

const app = createApp(App)

// provide/inject 방식으로 쓰고 싶으시면 남겨두세요
app.provide('currentUser', currentUser)

app.use(router)

// **앱 마운트는 초기화(init) 이후에** 🚩
currentUser.init().then(() => {
    app.mount('#app')
})