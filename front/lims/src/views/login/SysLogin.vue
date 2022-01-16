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
            <!-- <el-button @click="C_RegisterIndialogVisible">注册</el-button> -->
          </el-form-item>
        </el-form>
      </div>
      
    </div>
    <div class="pic" id="animation2"></div>
    <!-- <Register v-if="RegisterIndialogVisible"></Register> -->
  </div>
</template>
<script>
import {login_huihe,login_huihe2,verifyToken} from '../../mixin/index'
import { mapMutations, mapState } from "vuex";
import Register from '../../components/Register.vue'
export default {
  name: "SysLogin",
  data() {
    return {
      
      url:'http://localhost:8080/back/admin/login',
      urlVerify:'http://localhost:8080/back/admin/verifyToken'
    }
  },
  computed:{
    ...mapState(['RegisterIndialogVisible'])
  },
  methods:{
    
    ...mapMutations(["C_RegisterIndialogVisible"]),

  },
  mixins:[login_huihe,login_huihe2],
  components:{
    Register
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