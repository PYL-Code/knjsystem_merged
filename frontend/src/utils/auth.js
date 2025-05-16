// src/utils/auth.js

const TOKEN_KEY = 'token'; // 👉 토큰 이름 (수정 가능)

export function getToken() {
    return localStorage.getItem(TOKEN_KEY);
}

