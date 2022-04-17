import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../Home.vue'
import Login from '../Login.vue'
import Person from '../view/ad/Person.vue'
import Lab from '../view/ad/Lab.vue'
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
            path:'lab',
            name:'ad-lab',
            component:Lab
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