import Home from '@/components/Home.vue';
import Waiting from '@/components/Waiting.vue';
import Success from '@/components/Success.vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    {
        path: '/', 
        component: Home
    }, {
        path: '/waiting',
        component: Waiting
    }, {
        path: '/success',
        component: Success
    }]

const Router = createRouter({
    history: createWebHistory(),
    routes
})

export default Router
