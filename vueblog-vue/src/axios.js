import axios from 'axios';
import Element from 'element-ui'
import router from './router'
import store from './store'

axios.defaults.baseURL = "http://localhost:8081";

axios.interceptors.request.use(config => {
    return config;
});

axios.interceptors.response.use(response => {
        let res = response.data

        console.log("==================");
        console.log(res);
        console.log("==================");

        if (res.code === 200) {
            return response;
        } else {
            // 弹窗异常信息
            Element.Message({
                message: response.data.msg,
                type: 'error',
                //弹窗消失时间
                duration: 2 * 1000
            })
            // 直接拒绝往下面返回结果信息
            return Promise.reject(response.data.msg)
        }
    },
    error => {
        //如果error.response.data不为空
        if(error.response.data) {
            error.message = error.response.data.msg
        }
        // 根据请求状态觉得是否登录或者提示其他
        if (error.response.status === 401) {
            store.commit('REMOVE_INFO');
            router.push({
                path: '/login'
            });
            error.message = '请重新登录';
        }
        if (error.response.status === 403) {
            error.message = '权限不足，无法访问';
        }
        Element.Message({
            message: error.message,
            type: 'error',
            duration: 2 * 1000
        })
        return Promise.reject(error)

    });