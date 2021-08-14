// router/index.js
import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@/views/Index.vue'
import Login from "@/views/Login.vue";
import Software from "@/views/Software.vue";
import SoftwareAdd from "@/views/SoftwareAdd.vue";
import Server from "@/views/Server.vue";
import ServerAdd from "@/views/ServerAdd.vue";
import SoftwareConfig from "@/views/SoftwareConfig.vue";
import SoftwareConfigDetail from "@/views/SoftwareConfigDetail.vue";
import Script from "@/views/Script.vue";
import ScriptAdd from "@/views/ScriptAdd.vue";
import Init from "@/views/Init.vue";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'index',
            component: Index
        },

        {
            path: '/init',
            name: 'init',
            component: Init
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
            path: '/software/config',
            name: 'softwareConfig',
            component: SoftwareConfig
        },

        {
            path: '/softwareConfig/detail',
            name: 'softwareConfigDetail',
            component: SoftwareConfigDetail
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

        {
            path: '/script',
            name: 'script',
            component: Script
        },
        {
            path: '/script/add',
            name: 'scriptAdd',
            component: ScriptAdd
        },
    ]
})

export default router