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
import Consume from '../view/ad/Consume.vue'
import InRecord from '../view/ad/InRecord.vue'
import OutRecord from '../view/ad/OutRecord.vue'

import NPerson from '../view/noad/NPerson.vue'
import LabOrder from '../view/noad/LabOrder.vue'
import LabOrderRes from '../view/noad/LabOrderRes.vue'
import MyObj from '../view/noad/MyObj.vue'
import NDevLend from '../view/noad/NDevLend.vue'
import DevLendRes from '../view/noad/DevLendRes.vue'
import NConsume from '../view/noad/NConsume.vue'
import ConsumeRes from '../view/noad/ConsumeRes.vue'
import OutHandle from '../view/ad/OutHandle.vue'


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
            path:'labGdt',
            name:'ad-labGdt',
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
          },
          {
            path:'consume',
            name:'ad-consume',
            component:Consume
          },
          {
            path:'inRecord',
            name:'ad-inRecord',
            component:InRecord
          },
          {
            path:'outRecord',
            name:'ad-outRecord',
            component:OutRecord
          },
          {
            path:'outHandle',
            name:'ad-outHandle',
            component:OutHandle
          },




          {
            path:'nPerson',
            name:'noad-person',
            component:NPerson
          },
          {
            path:'labOrder',
            name:'noad-labOder',
            component:LabOrder
          },
          {
            path:'labOrderRes',
            name:'noad-labOderRes',
            component:LabOrderRes
          },
          {
            path:'myObj',
            name:'noad-myObj',
            component:MyObj
          },
          {
            path:'ndevLend',
            name:'noad-nDevLend',
            component:NDevLend
          },
          {
            path:'devLendRes',
            name:'noad-devLendRes',
            component:DevLendRes
          },
          {
            path:'nConsume',
            name:'noad-nConsume',
            component:NConsume
          },
          {
            path:'consumeRes',
            name:'noad-consumeRes',
            component:ConsumeRes
          },

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