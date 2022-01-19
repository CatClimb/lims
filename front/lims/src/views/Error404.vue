<template>
  <div id="animation3" >
    <el-button type="text" @click="$router.replace({name:'Login'})">登&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
  </div>
</template>
<script>
import animationData3 from "../anim/error404.json";
import lottie from "lottie-web";
export default {
  name: "Error404",
  props: ["msg"],
  methods: {
    handlerLottie(idName, anim) {
      lottie.loadAnimation({
        container: document.getElementById(idName), // 包含动画的那个容器
        renderer: "svg", // 可选值svg或canvas
        loop: true,
        autoplay: true, // 是否循环执行
        animationData: anim, // json文件的路径，可以是相对路径或绝对路径
      });
    },
  },
  mounted() {
    this.handlerLottie("animation3", animationData3);
    if (this.msg) {
      this.$message({
        message: this.msg,
        type: "error",
        center: true,
      });
    }


  },
};
</script>
<style scoped>
#animation3{
  text-align: center;
  
}
.el-button{
  margin: 0px;
  padding: 0px;
  z-index: 2;
  position:absolute;
  display: inline-block;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  font-size: 20px;  
  
}
</style>