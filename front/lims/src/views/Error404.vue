<template>
  <div id="animation3"></div>
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

    // setTimeout(() => {
    //     // this.$router.replace({
    //     //     name:'Login'
    //     // })
    // }, 2000);
  },
};
</script>
<style scoped>
</style>