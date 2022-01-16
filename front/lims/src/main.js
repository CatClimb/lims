import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//导入elementui
import ElementUI from 'element-ui'
//导入elementui样式
import 'element-ui/lib/theme-chalk/index.css'
//用不了 我不会 学废了
// import lottie from 'vue-lottie';
// Vue.component('lottie', lottie)
import axios from 'axios';
axios.interceptors.request.use(
  config=>{
    let token =localStorage.getItem("token");
    if(token){
      config.headers.Authorization=token
    }
    return config
  },
  error=>{
    return Promise.reject(error)
  }
)

Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
