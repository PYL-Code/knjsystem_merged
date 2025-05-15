import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'
import MainPage from "@/views/MainPage.vue";
import FormView from '../views/FormView.vue'
import UserInfo from "../views/UserInfo.vue";
import LoginPage from "@/views/LoginPage.vue";
import IntroPage from "@/views/IntroPage.vue";
import UsageProcedure from "@/views/UsageProcedure.vue";
import MobileService from "@/views/MobileService.vue";
import BarcodeSearch from "@/views/BarcodeSearch.vue";
import MyBarcodeList from "../views/MyBarcodeList.vue";
import BarcodeDetail from "@/views/BarcodeDetail.vue";
import BarcodeAddCertList from "@/views/BarcodeAddCertList.vue";
import BarcodeInsertForm from "@/views/BarcodeInsertForm.vue";


const routes = [

    {
        path: '/',
        name: 'Main',
        component: MainPage
    },

    {
        path: '/form',
        name: 'Form',
        component: FormView
    },

    {
        path: '/info',
        name: 'Info',
        component: UserInfo
    },

    {
        path: '/info/edit',
        name: 'Edit',
        component: () => import('@/views/UserEdit.vue')
    },

    {
        path: '/login',
        name: 'Login',
        component: LoginPage
    },

    {
        path: '/directions',
        name: 'DirectionsPage',
        component: () => import('@/views/DirectionsPage.vue')
    },

    {
        path: '/intro',
        name: 'Intro',
        component: IntroPage
    },
    {
        path: '/Procedure',
        name: 'UsageProcedure',
        component: UsageProcedure
    },

    {
        path: '/MobileService',
        name:'MobileService',
        component: MobileService
    },
    {
        path: '/board',
        name: 'BoardList',
        component: () => import('@/views/BoardList.vue')
    },

    {
        path: '/board/:id',
        name: 'BoardDetail',
        component: () => import('@/views/BoardDetail.vue') // ⬅️ 상세보기 컴포넌트
    },

    {
        path: '/barcode',
        name: 'BarcodeSearch',
        component: BarcodeSearch,
    },
    {
        path: '/barcode/list',
        name: 'barcodeList',
        component: MyBarcodeList
    },
    {
        path: '/barcode/detail',
        name: 'barcodeDetail',
        component: BarcodeDetail
    },
    {
        path: '/barcode/update/cert',
        name: 'barcodeAddCertList',
        component: BarcodeAddCertList
    },
    {
        path: '/barcode/insert',
        name: 'barcodeInsert',
        component: BarcodeInsertForm
    }


]

const router = createRouter({
    history: createWebHashHistory(),  // ✅ hash 모드 적용
    routes
})

// ✅ 전역 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
    const isLoggedIn = !!localStorage.getItem('token') // 로그인 여부 판단

    // 로그인한 사용자가 /login 또는 /form 접근 시 → 메인으로 리다이렉트
    if (isLoggedIn && (to.path === '/login' || to.path === '/form')) {
        next('/')
    } else {
        next()
    }
})


export default router
