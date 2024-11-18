import Home from '@/components/Home.vue';
import Waiting from '@/components/Waiting.vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    {
        path: '/', 
        component: Home
    }, {
        path: '/waiting',
        component: Waiting
    }]

const Router = createRouter({
    history: createWebHistory(),
    routes
})

export default Router