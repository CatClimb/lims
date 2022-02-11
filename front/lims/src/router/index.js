import Vue from 'vue'
import VueRouter from 'vue-router'
import jwtDecode from 'jwt-decode'

import Error404 from '../views/Error404';
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
    path: '*',
    redirect: '/error404',

  },
  {
    path: '/',
    redirect: '/login',

  },
  {
    path: '/login',
    name: 'Login',
    meta: { LoginAuth: true, },

    component:
      Login,
    beforeEnter: ((to, from, next) => {

      next();

    }),

  },
  {
    path: '/sysLogin',
    name: 'SysLogin',
    meta: { LoginAuth: true, },
    component: SysLogin
  },
  {
    path: '/sysLogin2',
    name: 'SysLogin2',
    meta: { LoginAuth: true, },
    component: SysLogin
  },
  {
    path: '/error404',
    name: 'Error404',
    meta: { noAuth: true},
    props($route){
      return {
          msg:$route.params.msg,
      }
    },
    component: Error404
  },
  {
    path: '/home',
    name: 'Home',
    component:
      Home,
    meta: {noAuth: true},
    beforeEnter:(function(to,from,next){
      
      if(sessionStorage.getItem("isLogin")){
        next();
      }else{
        next({
          name: 'Error404',
          params: { msg: "未登录，请先登录" },
          redirect: '/error404'
        });
      }
        
  }),
    
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
  mode: 'history',
})
//拦截所有/home下的路径
router.beforeEach((to, from, next) => {
  if (to.meta.noAuth || to.meta.LoginAuth) {
    next();
  } else {
    let str =localStorage.getItem("token");
    if(!str){
      next({
        name: 'Error404',
        params: { msg: "未登录，请先登录"},
        redirect: '/error404'
      });
    }
    let urlVerify = undefined;
    if (to.path.slice(5, 10) === '/user') {
      
      urlVerify = 'http://localhost:8080/back/user/verifyToken'
    } else if (to.path.slice(5, 11) === '/admin') {
      
      urlVerify = 'http://localhost:8080/back/admin/verifyToken'
    } else if (to.path.slice(5, 16) === '/superAdmin') {
      
      urlVerify = 'http://localhost:8080/back/superAdmin/verifyToken'
    }
    axios.get(urlVerify).then(
      response => {
        if (response.data.code === 2000) {
          next();
        } else {
          next({
            name: 'Error404',
            params: { msg: response.data.msg},
            redirect: '/error404'
          });
        }
      },
      error => {
        next({
          name: 'Error404',
          params: { msg: error.response.data.msg },
          redirect: '/error404'
        });
      }
    );
   
  }


});
//对所有登录进行验证token，并跳转
router.afterEach((to, from) => {
  
  if (to.meta.LoginAuth) {
    // router.app.$options.store.isLoading = true;
    
    let str=localStorage.getItem("token");
    if(str){
      //这个抛异常，所以你改token成无法解析抛异常，推测：如果你拿另一个能解析的token，可以通过，但后端验证不通过。 安全呀
      let token = jwtDecode(str);
      let urlVerify = undefined;
      if (token.Auth === '普通用户') {
        urlVerify = 'http://localhost:8080/back/user/verifyToken'
      } else if (token.Auth === '管理员') {
        urlVerify = 'http://localhost:8080/back/admin/verifyToken'
      } else if (token.Auth === '超级管理员') {
        urlVerify = 'http://localhost:8080/back/superAdmin/verifyToken'
      }
      
      axios.get(urlVerify).then(
        response => {
          if (response.data.code === 2000) {
            setTimeout(() => {
              sessionStorage.setItem("isLogin",true);
              router.push({
                name: 'Home',
              });
            }
              , 600);
          } else {
            
            // router.app.$options.store.isLoading = false;
            this.$message({
              message: response.data.msg,
              type: "error",
              center: true,
            });
            
          }
        },
        error => {
          this.$message({
            message: error.response.data.msg,
            type: "error",
            center: true,
          });
         
         
        }
      )
    }
   
  }


});
export default router



