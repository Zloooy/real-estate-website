import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import IntroPage from "@/components/IntroPage.vue";
import Catalog from "@/components/Catalog.vue";
import ComplexPage from "@/components/СomplexPage.vue";
import FlatPage from "@/components/FlatPage.vue";
import LoginForm from "@/components/LoginForm.vue";
import AdminPage from "@/components/AdminPage.vue";
import RequestCallPage from "@/components/RequestCallPage.vue";
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Главная',
        component: IntroPage
    },
    {
        path: '/search',
        name: 'Поиск',
        component: Catalog
    },
    {
        path: '/complex/:id',
        name: 'Комплекс',
        component: ComplexPage
    },
    {
        path: '/flat/:id',
        name: 'Квартира',
        component: FlatPage
    },
    {
        path: '/login',
        name: 'Вход',
        component: LoginForm
    },
    {
        path: '/admin_panel',
        name: 'Панель администратора',
        component: AdminPage
    },
    {
        path: '/request_call',
        name: 'Заказать звонок',
        component: RequestCallPage
    }
/*
    {
        path: '/404',
        name: '404',
        component: NotFound
    },
    {
        path: '*',
        redirect: '/404'
    }
 */
];

const router = createRouter({
    history: createWebHistory('/'),
    routes
})

export default router