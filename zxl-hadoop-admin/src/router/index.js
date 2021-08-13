// router/index.js
import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@/views/Index.vue'
import Login from "@/views/Login.vue";
import Software from "@/views/Software.vue";
import SoftwareAdd from "@/views/SoftwareAdd.vue";
import Server from "@/views/Server.vue";
import ServerAdd from "@/views/ServerAdd.vue";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'index',
            component: Index
        },

        {
            path: '/login',
            name: 'login',
            component: Login
        },

        {
            path: '/software',
            name: 'software',
            component: Software
        },
        {
            path: '/software/add',
            name: 'softwareAdd',
            component: SoftwareAdd
        },

        {
            path: '/server',
            name: 'server',
            component: Server
        },
        {
            path: '/server/add',
            name: 'serverAdd',
            component: ServerAdd
        },
    ]
})

export default router