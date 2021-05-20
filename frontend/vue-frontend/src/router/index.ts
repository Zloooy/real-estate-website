import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import IntroPage from "@/components/IntroPage.vue";
import Catalog from "@/components/Catalog.vue";
import ComplexPage from "@/components/СomplexPage.vue";
import FlatPage from "@/components/FlatPage.vue";
import LoginForm from "@/components/LoginForm.vue";
import AdminPage from "@/components/AdminPage.vue";
import TariffsPage from "@/components/TariffsPage.vue";
import BlogPage from "@/components/BlogPage.vue";
import DeleteButton from "@/components/DeleteButton.vue";
import EditButton from "@/components/EditButton.vue";
import CatalogRedactor from "@/components/CatalogRedactor.vue";
import FlatRedactor from "@/components/FlatRedactor.vue";
import ArticleRedactor from "@/components/ArticleRedactor.vue";

import RequestCallPage from "@/components/RequestCallPage.vue";
import BLogPage from "@/components/BlogPage.vue";
import ArticlePage from "@/components/ArticlePage.vue";
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
        path: '/complex/:id/edit',
        name: 'Редактор Комплекса',
        component: CatalogRedactor
    },
    {
        path: '/flat/:id',
        name: 'Квартира',
        component: FlatPage
    },
    {
        path: '/flat/:id/edit',
        name: 'Редактор квартиры',
        component: FlatRedactor
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
        path: '/tariffs',
        name: 'Тарифы',
        component: TariffsPage
    },
    {
        path: '/request_call',
        name: 'Заказать звонок',
        component: RequestCallPage
    },
    {
        path: '/blog',
        name: 'Блог',
        component: BLogPage
    },
    {
        path: '/article/:id',
        name: 'Статья',
        component: ArticlePage
    },
    {
        path: '/article/:id/edit',
        name: 'Редактор Статьи',
        component: ArticleRedactor
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