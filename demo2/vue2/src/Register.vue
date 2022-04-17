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
          <el-input v-model.number="formData.phone"></el-input>
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

import { mapMutations} from "vuex";
import axios from 'axios';
export default {
  name: "register-view",

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
    var validatePhoneLength = (rule, value, callback) => {
        console.log(value)
        let l=value.toString().length;
        console.log(l)
      if (l>=6&&l<=20) {
          callback();
      }else{
          callback(new Error("长度在 6 到 20!"));
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
          { required: true, message: "请输入用户名",  },
          //{ validator: handlerValidate, trigger: 'blur' },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            
          },
        ],
        password: [
          { required: true, message: "请输入密码",  },

          {
            min:6,
            max:20,
            message: "长度在 6 到 20 ",
            
          },
        ],

        againPassword: [{ validator: validateAgain,  }],
        name: [
          { required: true, message: "请输入姓名",  },
          {
            min: 2,
            max: 20,
            message: "长度在 2 到 20 个字符",
          
          },
        ],
        sex: [{ required: true, message: "请输入性别",  }],

        phone: [
          { required: true, message: "请输入手机号", },
 
           {validator:validatePhoneLength},
      
           
          
          { type: 'number', message: '请输入数字' },
          
         
        ],
        email: [
          { required: true, message: "请输入邮箱",  },

          { type: "email", message: "请输入正确的邮箱地址",  },
        ],
        weChat: [
          { required: true, message: "请输入微信",  },
          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 个字符 ",
           
          },
        ],
      },
      url: "http://localhost:8080/back/enter/register",
    };
  },
  watch: {
    VisableTmp() {
      this.C_RegisterIndialogVisible();
    },
  },
  methods: {
    ...mapMutations(["C_RegisterIndialogVisible"]),
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
              // sessionStorage.setItem("isLogin",true);
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
    
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
            this.handlerRequest();
            
        } else {
            
          return false;
        }
      });
     
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