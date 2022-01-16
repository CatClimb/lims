<template>
  <el-container>
    <el-aside class="side" width="auto">
      <Menu :menuData="menuData"></Menu>
    </el-aside>
    <el-container>
      <el-header>
        <div @click="SET_COLLAPSE" style="font-size: 26px; cursor: pointer">
          <i
            :class="isCollapse ? 'el-icon-s-fold' : 'el-icon-s-unfold'"
          ></i>
        </div>
        
      </el-header>
      <el-main >
        <router-view/>
      </el-main>
      <!-- <el-footer>Footer</el-footer> -->
    </el-container>
  </el-container>
</template>
<script>
import { mapMutations, mapState } from "vuex";
import Menu from './components/Menu.vue'
export default {
  
  name: "Home",
  data() {
    return {
     menuData:[
            {
              path: '/home/user-PersonInfo',
              icon: 'el-icon-menu',
              title: '个人信息',
              index: 1,
            },
            {
              path: '2',
              icon: 'el-icon-menu',
              title: '实验室',
              index: 2,
              children: [
                {
                  path: '/home/user-LabInfo',
                  title: '可预约实验室',
                  index: 2 - 1,
                },
                {
                  path: '/home/user-MyLabInfo',
                  title: '我的预约信息',
                  index: 2 - 2,
                }
              ]
            },
            {
              path: '3',
              icon: 'el-icon-menu',
              title: '设备',
              index: 3,
              
              children: [
                {
                  path: '/home/user-DeviceInfo',
                  title: '可借用设备',
                  index: 3 - 1,
                },
                {
                  path: '/home/user-MyDeviceInfo',
                  title: '我的借用信息',
                  index: 3 - 2,
                }
              ]
            },
            {
              path: '4',
              icon: 'el-icon-menu',
              title: '项目',
              index: 4,
              
              children: [
                {
                  path: '/home/user-ObjInfo',
                  title: '登记项目',
                  index: 4 - 1,
                },
                {
                  path: '/home/user-MyObjInfo',
                  title: '已登记的项目',
                  index: 4 - 2,
                }
              ]
            },
            {
              path: '5',
              icon: 'el-icon-menu',
              title: '易耗品',
              index: 5,
              
              children: [
                {
                  path: '/home/user-ConsumeInfo',
                  title: '查看库存',
                  index: 5 - 1,
                },
                {
                  path: '/home/user-ConsumeInfo',
                  title: '我的使用信息',
                  index: 5 - 2,
                }
              ]
            },
          ]
    };
  },
  
  components: {
    Menu
  },
  computed: {
    ...mapState(["isCollapse"]),
  },
  methods: {
    ...mapMutations(["SET_COLLAPSE"]),
   
  },
  mounted(){
    
  }
};
</script>
<style lang="css">
.el-container {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  background-color: aqua;
}
.el-aside {
  background-color: #104156;
}
.el-header,
.el-footer {
  background-color: bisque;
}
.el-main {
  background-color: blueviolet;
}
.side {
  box-shadow: 2px 8px 5px #888888;
  /* overflow-x: hidden; */
  /* transition: width ; */
}
</style>