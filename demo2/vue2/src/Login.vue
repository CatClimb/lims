<template>
  <div class="bg" id="animation1">
    <div class="login-body">
      <div class="form">
        <h3>实验室管理系统</h3>
        <el-form
          :model="formData"
          :rules="rules"
          ref="login_form_node"
          label-width="100px"
          status-icon
          class="demo-ruleForm"
        >
          <el-form-item prop="userName">
            <el-input
              v-model="formData.userName"
              type="text"
              placeholder="用户名"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="formData.password"
              type="password"
              placeholder="密码"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('login_form_node')"
              >登录</el-button
            >
            <el-button @click="RegisterIndialogVisible=true">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
      
    </div>
    <div class="pic" id="animation2"></div>
    <Register v-if="RegisterIndialogVisible"></Register>
  </div>
</template>
<script>

import Register from './Register.vue'
import animationData1 from './anim/bj.json'
import animationData2 from './anim/person.json'

import axios from 'axios';
import lottie from 'lottie-web';
export default {
  name: "login-element",
  components:{
    Register
  },
  data() {
    return {
      
        url:'http://localhost:8080/back/enter/login',
        
        formData: {
          loginName: "",
          password: "",
          },
        rules: {
          userName: [
            { required: true, message: "请输入用户名", trigger: "blur" },
            //{ validator: handlerValidate, trigger: 'blur' },
            {
              min: 1,
              max: 20,
              message: "长度在 1 到 20 个字符",
              trigger: "blur",
            },
          ],
          password: [
            { required: true, message: "请输入密码", trigger: "blur" },
            {
              min: 6,
              max: 20,
              message: "长度在 6 到 20 个字符",
              trigger: "blur",
            },
          ],
        },
        RegisterIndialogVisible:false
    }
  },
  computed:{
  },
  methods:{
    
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.handlerRequest();
            // let captcha = new TencentCaptcha("123123", (res) => {
            //   if (res.ret === 0) {
            //     this.handlerRequest();
            //   } else {
            //       this.$message({
            //     message: "认证失败，请重新认证",
            //     type: "error",
            //     center: true,
            //   });
            //   }
            // });
            // captcha.show();
          } else {
  
            return false;
          }
        });
      },
      handlerLottie(idName, anim) {
        lottie.loadAnimation({
          container: document.getElementById(idName), // 包含动画的那个容器
          renderer: "svg", // 可选值svg或canvas
          loop: true,
          autoplay: true, // 是否循环执行
          animationData: anim, // json文件的路径，可以是相对路径或绝对路径
        });
      },
      
      handlerRequest() {
        axios.post(this.url, this.formData).then(
          (response) => {
            if (response.data.code === 2000) {
              this.$message({
                message: response.data.msg,
                type: "success",
                center: true,
              });
              localStorage.setItem("token", response.data.data.token);
              sessionStorage.setItem("isLogin",true);
              this.$router.push({
                name: 'ad-person',
                
                
              })
              
            } else {
              this.$message({
                message: response.data.msg,
                type: "error",
                center: true,
              });
            }
          },
          (error) => {
            this.$message({
              message: error.response.data.msg,
              type: "error",
              center: true,
            });
            
          }
        );
      },

  },
  
  mounted(){
    this.handlerLottie("animation1", animationData1);
    this.handlerLottie("animation2", animationData2);
  }
};
</script>
<style lang="css" scoped>
.bg {
  z-index: -1;
}
.pic {
  z-index: 1;
  position: absolute;
  left: 850px;
  top: 350px;
}
.login-body {
  z-index: 2;

  background-color: rgba(255, 255, 255, 0.3);
  width: 400px;
  height: 400px;
  position: absolute;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: none;
  border-radius: 10%;
  backdrop-filter: blur(3px);
  border-left: 2px solid rgba(255, 255, 255, 0.3);
  border-top: 2px solid rgba(255, 255, 255, 0.3);
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
  text-align: center;
}
.form {
  position: absolute;
  top: 100px;
  right: 100px;
}
h3 {
  position: absolute;
  left: 130px;
  top: -50px;
  text-align: center;
  color: white;
  font-size: 20px;
  font-weight: 600;
  font-family: "Berlin Sans FB";
  margin: 10px 0;

  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  opacity: 0.9;
}
</style>