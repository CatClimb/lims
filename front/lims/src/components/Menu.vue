<template>
  <div>
    <el-menu
      default-active=""
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      :unique-opened="true"
      text-color="#fff"
      active-text-color="#5a9cf8"
      background-color="#104156"
      router
    >
      
      <template v-for="item in menuData" >
          <el-menu-item-group>
              <el-menu-item :index="item.path" v-if="item.children == undefined">
                <template slot="title">
                    <i :class="item.icon"></i>
                    <span>{{item.title}}</span>
                </template>
              </el-menu-item>
          </el-menu-item-group>
              <el-submenu :index="item.path" v-if="item.children != undefined" >
                <template slot="title">
                    <i :class="item.icon"></i>
                    <span slot="title">{{item.title}}</span>
                </template>
                <template v-for="childItem in item.children" >
                <el-menu-item-group>
                  <el-menu-item :index="childItem.path" v-if="childItem.children == undefined">{{childItem.title}}</el-menu-item>
                </el-menu-item-group>
                </template>
              </el-submenu>
            </template> 

    </el-menu>
  </div>
</template>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  name: "Menu",
  components: {},
  props: ["menuData"],

  data() {
    return {};
  },
  computed: {
    ...mapState(["isCollapse"]),
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>