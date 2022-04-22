import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../Home.vue'
import Login from '../Login.vue'
import Person from '../view/ad/Person.vue'
import Lab from '../view/ad/Lab.vue'
import User from '../view/ad/User.vue'
import LabGdt from '../view/ad/LabGdt.vue'
import Obj from '../view/ad/Obj.vue'
import Device from '../view/ad/Device.vue'
import TotalObj from '../view/ad/TotalObj.vue'
import DevLend from '../view/ad/DevLend.vue'


const routes = [
      // {
      //   path: '*',
      //   redirect: '/error404',
    
      // },
      {
        path: '/',
        redirect: '/login',
    
      },
      {
        path: '/login',
        name: 'login-view',
        component:Login
      },
      {
        path:'/home',
        name: 'home-view',
        component:Home,
        children:[
          {
            path:'person',
            name:'ad-person',
            component:Person
          },
          {
            path:'user',
            name:'ad-user',
            component:User
          },
          {
            path:'lab',
            name:'ad-lab',
            component:Lab
          },
          {
            path:'labgdt',
            name:'ad-labgdt',
            component:LabGdt
          },
          {
            path:'obj',
            name:'ad-obj',
            component:Obj
          },
          {
            path:'totalObj',
            name:'ad-totalObj',
            component:TotalObj
          },
          {
            path:'device',
            name:'ad-device',
            component:Device
          },
          {
            path:'devLend',
            name:'ad-devLend',
            component:DevLend
          }
        ]
      }
    
];


Vue.use(VueRouter)
const router = new VueRouter({
    routes,
    mode: 'history',
  });

  // const routerPush = router.prototype.push
  // router.prototype.push = function push(location) {
  // return routerPush.call(this, location).catch(error=> error)}

  export default router;