import Vue from 'vue'
import VueRouter from 'vue-router'
import jwtDecode from 'jwt-decode'

import Login from '../views/login/Login'
import SysLogin from '../views/login/SysLogin'
import Home from '../Home'
import axios from 'axios';

//用户
import PersonInfo from '../views/user/PersonInfo'
import ObjInfo from '../views/user/ObjInfo'
import MyObjInfo from '../views/user/MyObjInfo'
import MyLabInfo from '../views/user/MyLabInfo'
import MyDeviceInfo from '../views/user/MyDeviceInfo'
import MyConsumeInfo from '../views/user/MyConsumeInfo'
import LabInfo from '../views/user/LabInfo'
import DeviceInfo from '../views/user/DeviceInfo'
import ConsumeInfo from '../views/user/ConsumeInfo'

Vue.use(VueRouter)


const routes = [
  
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component:
      Login,
      beforeEnter:((to,from,next)=>{
        
        next();
     
    }),
    
  },
  {
    path: '/sysLogin',
    name: 'SysLogin',
    component: SysLogin
  },
  {
    path: '/sysLogin2',
    name: 'SysLogin2',
    component: SysLogin
  },
  {
    path: '/home',
    name: 'Home',
    component:
      Home,
    meta: {

      auth: true
    },
    // beforeEnter: ((to, from, next) => {
    //   let token = jwtDecode(localStorage.getItem("token"));
    //   if (to.meta.requiredLoginAuth) {


    //     if (token.Auth === '普通用户') {//授权验证
    //       to.params.loginName = token.loginName;
    //       to.params.Auth = token.Auth;
    //       //放行
    //       next()
    //     } else if (token.Auth === '管理员') {
    //       to.params.loginName = token.loginName;
    //       to.params.Auth = token.Auth;
    //       // to.menuContent=
    //       next()

    //     }
    //     else if (token.Auth === '超级管理员') {
    //       to.params.loginName = token.loginName;
    //       to.params.Auth = token.Auth;
    //       // to.menuContent=
    //       next()

    //     }
    //   } else {
    //     alert("插旗！");
    //   }
    // }),

    children: [
      //用户路由
      {
        path: 'user-PersonInfo',
        name: 'PersonInfo',
        component:
          PersonInfo,

      },
      {
        path: 'user-ObjInfo',
        name: 'ObjInfo',
        component:
          ObjInfo,

      },
      {
        path: 'user-MyObjInfo',
        name: 'MyObjInfo',
        component:
          MyObjInfo,

      },
      {
        path: 'user-MyLabInfo',
        name: 'MyLabInfo',
        component:
          MyLabInfo,

      },
      {
        path: 'user-MyDeviceInfo',
        name: 'MyDeviceInfo',
        component:
          MyDeviceInfo,

      },
      {
        path: 'user-MyConsumeInfo',
        name: 'MyConsumeInfo',
        component:
          MyConsumeInfo,

      },
      {
        path: 'user-LabInfo',
        name: 'LabInfo',
        component:
          LabInfo,

      },
      {
        path: 'user-DeviceInfo',
        name: 'DeviceInfo',
        component:
          DeviceInfo,

      },
      {
        path: 'user-ConsumeInfo',
        name: 'ConsumeInfo',
        component:
          ConsumeInfo,

      },
    ]
  },




]

const router = new VueRouter({
  routes,
  mode:'history',
})

router.beforeEach((to,from,next)=>{
  next();
  
})

router.afterEach((to,from)=>{
 
 
  
});
export default router



