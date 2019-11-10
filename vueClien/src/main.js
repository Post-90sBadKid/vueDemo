// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'


import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

// 导入共用组件
import axios from 'axios'
import qs from 'qs'

axios.defaults.baseURL='/api'       //公用的uri
axios.defaults.timeout= 5000        //访问超时事件
axios.defaults.withCredentials=true //允许携带cookie
axios.defaults.transformRequest = [function (data) {
  return qs.stringify(data) ;       //数据序列化
}]

//http request 请求拦截器
axios.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    return Promise.reject(err);
  }
);
 
//http response 响应拦截器
axios.interceptors.response.use(
  response => {
    //如果响应结果是code == 403,则返回登录页
    if(response.data.code ==403){
      router.push({
        path:"/login",
        querry:{redirect:router.currentRoute.fullPath}//从哪个页面跳转
      })
    }
    return response;
  },
  error => {
    //响应结果出错的要做的事情
  
    return Promise.reject(error)
  }
)

//挂载到Vue属性上
Vue.prototype.$axios=axios;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
