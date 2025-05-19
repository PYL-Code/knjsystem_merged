// src/stores/currentUser.js
import { reactive } from 'vue'
import axios from 'axios'

export const currentUser = reactive({
    user: null,
    token: null,
    ready: false,   // 초기화 완료 여부

    async init() {
        // 1) 로컬스토리지에서 꺼내오기
        const savedUser  = localStorage.getItem('user')
        const savedToken = localStorage.getItem('token')

        if (savedUser && savedToken) {
            this.user  = JSON.parse(savedUser)
            this.token = savedToken

            try {
                await axios.get('/users/check', {
                    headers: { Authorization: `Bearer ${this.token}` }
                });
            } catch (err) {
                if (err.response?.status === 401) {
                    // 401 Unauthorized 응답일 경우에만 로그아웃 처리
                    this.user = null;
                    this.token = null;
                    localStorage.removeItem('user');
                    localStorage.removeItem('token');
                }
            }
}
        // 초기화 끝
        this.ready = true
    }
})