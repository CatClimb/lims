<template>
  <div id="register">
    <el-dialog :title="dialogtitle" :visible.sync="VisableTmp">
      <el-form
        :model="formData"
        :rules="rules"
        ref="register_form_node"
        label-width="100px"
        class="demo-formData"
      >
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formData.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="formData.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="againPassword">
          <el-input type="password" v-model="formData.againPassword"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formData.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email"></el-input>
        </el-form-item>
        <el-form-item label="微信" prop="weChat">
          <el-input v-model="formData.weChat"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitForm('register_form_node')">注册</el-button>

        <el-button type="primary" @click="resetForm('register_form_node')"
          >重置</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { login_huihe2 } from "../mixin/index";
import { mapMutations, mapState } from "vuex";
export default {
  name: "Register",
mixins: [login_huihe2],
  data() {
    var validateAgain = (rule, value, callback) => {
        console.log(value)
      if (value === "") {
          
        callback(new Error("请再次输入密码"));
      } else if (value !== this.formData.password) {
        callback(new Error("两次输入密码不一致!"));
      }else{
          callback();
      }
    };
    return {
      VisableTmp: true,
      dialogtitle: "用户注册",
      formData: {
        userName: "",
        password: "",
        againPassword: "",
        name: "",
        sex: "",
        phone: "",
        email: "",
        weChat: "",
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
            message: "长度在 6 到 20 ",
            trigger: "blur",
          },
        ],

        againPassword: [{ validator: validateAgain, trigger: "blur" }],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            min: 2,
            max: 20,
            message: "长度在 2 到 20 个字符",
            trigger: "blur",
          },
        ],
        sex: [{ required: true, message: "请输入性别", trigger: "blur" }],

        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },

          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 ",
            trigger: "blur",
          },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },

          { type: "email", message: "请输入正确的邮箱地址", trigger: ["blur"] },
        ],
        weChat: [
          { required: true, message: "请输入微信", trigger: "blur" },
          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 个字符 ",
            trigger: "blur",
          },
        ],
      },
      url: "http://localhost:8080/back/user/register",
    };
  },
  watch: {
    VisableTmp() {
      this.C_RegisterIndialogVisible();
    },
  },
  methods: {
    ...mapMutations(["C_RegisterIndialogVisible"]),
    
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
          window.aa=valid;
        if (valid) {
            this.handlerRequest();
            
        } else {
            
          return false;
        }
      });
      console.log(window.aa);
    },
    
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  
};
</script>
<style lang="css" scoped>
/* #register{
    background-color: rgba(255,255,255,.3);
	width: 1000px;
	height: 800px;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%,-50%);
	border:none;
	border-radius: 10%;
	backdrop-filter: blur(3px);
	border-left: 2px solid rgba(255,255,255,.3);
	border-top: 2px solid rgba(255,255,255,.3);
	box-shadow: 2px 2px 10px rgba(0,0,0,.2);
	text-align: center;
} */
</style>