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
  name: "Home",
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
      if (token.loginName && token.Auth) {
        if (token.Auth === "普通用户") {
          menuData = [
            {
              path: "/home/user-PersonInfo",
              icon: "el-icon-menu",
              title: "个人信息",
              index: 1,
            },
            {
              path: "2",
              icon: "el-icon-menu",
              title: "实验室",
              index: 2,
              children: [
                {
                  path: "/home/user-LabInfo",
                  title: "可预约实验室",
                  index: 2 - 1,
                },
                {
                  path: "/home/user-MyLabInfo",
                  title: "我的预约信息",
                  index: 2 - 2,
                },
              ],
            },
            {
              path: "3",
              icon: "el-icon-menu",
              title: "设备",
              index: 3,

              children: [
                {
                  path: "/home/user-DeviceInfo",
                  title: "可借用设备",
                  index: 3 - 1,
                },
                {
                  path: "/home/user-MyDeviceInfo",
                  title: "我的借用信息",
                  index: 3 - 2,
                },
              ],
            },
            {
              path: "4",
              icon: "el-icon-menu",
              title: "项目",
              index: 4,

              children: [
                {
                  path: "/home/user-ObjInfo",
                  title: "登记项目",
                  index: 4 - 1,
                },
                {
                  path: "/home/user-MyObjInfo",
                  title: "已登记的项目",
                  index: 4 - 2,
                },
              ],
            },
            {
              path: "5",
              icon: "el-icon-menu",
              title: "易耗品",
              index: 5,

              children: [
                {
                  path: "/home/user-ConsumeInfo",
                  title: "查看库存",
                  index: 5 - 1,
                },
                {
                  path: "/home/user-ConsumeInfo",
                  title: "我的使用信息",
                  index: 5 - 2,
                },
              ],
            },
          ];
        } else if (token.Auth === "管理员") {
          menuData = [
            {
              path: "/home/admin-PersonInfo",
              icon: "el-icon-menu",
              title: "个人信息",
              index: 1,
            },
            {
              path: "2",
              icon: "el-icon-menu",
              title: "分配角色",
              index: 2,
              children: [
                {
                  path: "/home/admin-updateRole",
                  icon: "el-icon-menu",
                  title: "修改用户角色",
                  index: 2 - 1,
                },
                {
                  path: "/home/admin-ApprovalRole",
                  icon: "el-icon-menu",
                  title: "处理角色申请",
                  index: 2 - 2,
                },
              ],
            },
            {
              path: "3",
              icon: "el-icon-menu",
              title: "设备处理",
              index: 3,
              children: [
                {
                  path: "/home/admin-ApprovalDevice",
                  icon: "el-icon-menu",
                  title: "借用审批",
                  index: 3 - 1,
                },
                {
                  path: "/home/admin-isBreakDevice",
                  icon: "el-icon-menu",
                  title: "批准设备损坏",
                  index: 3 - 2,
                },
                {
                  path: "/home/admin-fixDevice",
                  icon: "el-icon-menu",
                  title: "维修设备",
                  index: 3 - 3,
                },
              ],
            },
            {
              path: "/home/admin-ApprovalObj",
              icon: "el-icon-menu",
              title: "项目审批",
              index: 4,
            },
            {
              path: "5",
              icon: "el-icon-menu",
              title: "易耗品处理",
              index: 5,
              children: [
                {
                  path: "/home/admin-ComsumerInfo",
                  icon: "el-icon-menu",
                  title: "查看库存",
                  index: 5 - 1,
                },
                {
                  path: "/home/admin-ApprovalComsumer",
                  icon: "el-icon-menu",
                  title: "使用审批",
                  index: 5 - 2,
                },
              ],
            },
          ];
        } else if (token.Auth === "超级管理员") {
          menuData = [
            {
              path: "/home/superadmin-PersonInfo",
              icon: "el-icon-menu",
              title: "个人信息",
              index: 1,
            },
            {
              path: "/home/superadmin-manUser",
              icon: "el-icon-menu",
              title: "用户管理",
              index: 2,
            },
            {
              path: "/home/superadmin-manLab",
              icon: "el-icon-menu",
              title: "实验室管理",
              index: 3,
            },
            {
              path: "/home/superadmin-manDevice",
              icon: "el-icon-menu",
              title: "设备管理",
              index: 4,
            },
            {
              path: "/home/superadmin-manObj",
              icon: "el-icon-menu",
              title: "项目管理",
              index: 5,
            },
            {
              path: "/home/superadmin-manComsumer",
              icon: "el-icon-menu",
              title: "易耗品管理",
              index: 6,
            },
            {
              path: "7",
              icon: "el-icon-menu",
              title: "日志管理",
              index: 7,
              children: [
                {
                  path: "/home/superadmin-manStoreRecord",
                  icon: "el-icon-menu",
                  title: "库存记录",
                  index: 7-1,
                },
                {
                  path: "/home/superadmin-adviceRecord",
                  icon: "el-icon-menu",
                  title: "通知记录",
                  index: 7-1,
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