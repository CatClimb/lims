<template>
  <el-container>
    <el-aside class="side" width="auto">
      <Menu :menuData="menuData" ></Menu>
    </el-aside>
    <el-container>
      <el-header>
        <div @click="SET_COLLAPSE" style="font-size: 26px; cursor: pointer">
          <i :class="isCollapse ? 'el-icon-s-fold' : 'el-icon-s-unfold'"></i>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <!-- <el-footer>Footer</el-footer> -->
    </el-container>
  </el-container>
</template>
<script>
import jwtDecode from "jwt-decode";
import { mapMutations, mapState } from "vuex";
import Menu from "./components/Menu.vue";

export default {
  name: "home-view",
  data() {
    return {
      menuData:[]
      
    };
  },

  components: {
    Menu,
  },
  computed: {
    ...mapState(["isCollapse"]),
  },
  methods: {
    ...mapMutations(["SET_COLLAPSE"]),
  },
  mounted() {
    let str = localStorage.getItem("token");
    if (str) {
      let token = jwtDecode(str);
      let menuData=[];
      console.log(token.Auth);
      if (token.loginName && token.Auth) {
        if (token.Auth === "普通用户") {
          menuData = [
           
            ]
         
        } else if (token.Auth === "管理员") {
         menuData = [
         
            {
              path: "1",
              icon: "el-icon-menu",
              title: "用户管理",
              index: 1,
              children: [
                {
                  path: "/home/person",
                  title: "个人信息",
                  index: 1 - 1,
                },
                {
                  path: "/home/user",
                  title: "用户信息",
                  index: 1 - 2,
                },
              ],
            },
            {
              path: "2",
              icon: "el-icon-menu",
              title: "实验室管理",
              index: 2,
              children:[
                {
                  path: "/home/lab",
                  title: "实验室信息",
                  index: 2 - 1,
                },
                {
                  path: "/home/labGdt",
                  title: "预约信息",
                  index: 2 - 2,
                }
              ]
            },
            {
              path:"3",
              icon: "el-icon-menu",
              title:"项目管理",
               children:[
                 {
                  path: "/home/obj",
                  title: "项目信息",
                  index: 3 - 1,
                },
                {
                  path: "/home/totalObj",
                  title: "项目登记统计",
                  index: 3 - 2,
                },
                {
                  path: "",
                  title: "登记处理",
                  index: 3 - 3,
                },
               ],
               
            },
            {
              path:"4",
              icon: "el-icon-menu",
              title:"仪器管理",
              children:[
                {
                  path:"/home/device",
                  title:"设备仪器信息",
                  index:4 - 1,
                },
                {
                  path:"/home/devLend",
                  title:"借用信息",
                  index:4 - 2,
                },
                {
                  path:"",
                  title:"借用处理",
                  index:4 - 3,
                }
              ]
            },
            {
              path:"5",
              icon: "el-icon-menu",
              title:"易耗品管理",
              children:[
                {
                  path:"/home/com",
                  title:"库存信息",
                  index:5 - 1,
                },
                {
                  path:"/home/device1",
                  title:"入库记录",
                  index:5 - 2,
                },
                {
                  path:"/home/device1",
                  title:"出库记录",
                  index:5 - 3,
                }
              ],
              
            },
            // {
            //   path:"",
            //   icon: "el-icon-menu",
            //   title:"处理日志",
            //   index:6,
            // }
             
          ];
        } 
        this.menuData=menuData
        
        // this.firstMenu=menuData[0].children[0].path
        console.log(this)
      }
    }
    
  },
};
</script>
<style lang="css">
.el-container {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;

  background-image: url("../public/img/bg.png");
  background-size: cover;
  background-attachment: fixed;
}
.el-aside {
   /* opacity: 0.7; */
  background-color: #104156;
 
}
.el-header,
.el-footer {
  background-color: bisque;
}
.el-main {
  /* background-color: blueviolet; */
  margin: 0 30px 0 30px;
 
  /* overflow: hidden; */
  /* width: 1600px; */
}
.side {
  /* box-shadow: 2px 8px 5px #888888; */
  /* overflow-x: hidden; */
  /* transition: width ; */
}
</style>