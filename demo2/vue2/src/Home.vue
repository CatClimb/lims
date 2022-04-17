<template>
  <el-container>
    <el-aside class="side" width="auto">
      <Menu :menuData="menuData" :firstMenu="firstMenu"></Menu>
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
      menuData:[],
      firstMenu:''
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
                  index: 2 - 1,
                },
                {
                  path: "/home/lab",
                  title: "用户信息",
                  index: 2 - 2,
                },
              ],
            },
          ];
        } 
        this.menuData=menuData
        
        this.firstMenu=menuData[0].path
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
}
.side {
  /* box-shadow: 2px 8px 5px #888888; */
  /* overflow-x: hidden; */
  /* transition: width ; */
}
</style>