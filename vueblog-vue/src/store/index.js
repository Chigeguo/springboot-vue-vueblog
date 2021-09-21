import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: '',
        userinfo: {}
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token;
            //保存的久
            localStorage.setItem("token", token);
        },
        SET_USERINFO: (state, userinfo) => {
            state.userinfo = userinfo;
            //一次会话的结果
            sessionStorage.setItem("userinfo", JSON.stringify(userinfo));
        },
        REMOVE_INFO: state => {
            state.token = '';
            state.userinfo = {};
            localStorage.setItem("token", '');
            sessionStorage.setItem("userinfo", '');

        }
    },
    getters: {
        // get
        GET_TOKEN: state => {
            return state.token;
        },
        GET_USERINFO: state => {
            return state.userinfo;
        }
    },
    actions: {},
    modules: {}
})
