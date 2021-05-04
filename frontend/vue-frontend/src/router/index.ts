import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import IntroPage from "@/components/IntroPage.vue";
import Catalog from "@/components/Catalog.vue";
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