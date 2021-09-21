import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'
import Blogs from '../views/Blogs.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Index',
        redirect: { name: 'Blogs' }
    },

    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
    },

    {
        path: '/blog/:blogId/edit',
        name: 'BlogEdit',
        meta: {
            requireAuth: true
        },
        component: BlogEdit,
    },
    {
        path: '/blog/add',
        name: 'BlogAdd',
        meta: {
            requireAuth: true
        },
        component: BlogEdit,
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: BlogDetail
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
