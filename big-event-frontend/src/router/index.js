import {createRouter, createWebHistory} from "vue-router";

//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'


//定义路由关系
const routes = [
    {path: '/login', component: LoginVue},
    {path: '/', component: LayoutVue}
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router