import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  base: '/',
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },


  server: {
    historyApiFallback: true, // 중요: 새로고침 시 index.html로 리디렉션
    proxy: {
      '/user': {
        target: 'http://localhost:8081',  // API 서버 주소
        changeOrigin: true,  // 원본 출처를 변경하여 요청 보내기
        secure: false,  // HTTPS를 사용할 경우 설정
      },
      '/board': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false
      },
      '/api/barcode': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/api')
      },
      '/api/list': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/api')
      },
      '/api/cert': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/api')
      }
    },
  },
})