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
            <el-button @click="visiable2=true">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
      
    </div>
    <div class="pic" id="animation2"></div>
     <div id="register">
    <el-dialog :title="dialogtitle2" :visible.sync="visiable2">
      <el-form
        :model="formData2"
        :rules="rules2"
        ref="register_form_node"
        label-width="100px"
        class="demo-formData2"
      >
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formData2.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="formData2.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="againPassword">
          <el-input type="password" v-model="formData2.againPassword"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData2.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formData2.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number="formData2.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData2.email"></el-input>
        </el-form-item>
        <el-form-item label="微信" prop="weChat">
          <el-input v-model="formData2.weChat"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitForm2('register_form_node')">注册</el-button>

        <el-button type="primary" @click="resetForm('register_form_node')"
          >重置</el-button
        >
      </div>
    </el-dialog>
  </div>
  </div>
</template>
<script>


import animationData1 from './anim/bj.json'
import animationData2 from './anim/person.json'
// import {T} from '../public/js/TCaptcha'
import axios from 'axios';
import lottie from 'lottie-web';
import jwtDecode from 'jwt-Decode';
export default {
  name: "login-element",
  
  data() {
    var validateAgain = (rule, value, callback) => {
        console.log(value)
        console.log(this.formData2.password)
      if (value === "") {
          
        callback(new Error("请再次输入密码"));
      } else if (value !== this.formData2.password) {
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
      
          visiable2: false,
      dialogtitle2: "用户注册",
      formData2: {
        userName: "",
        password: "",
        againPassword: "",
        name: "",
        sex: "",
        phone: "",
        email: "",
        weChat: "",
      },
      
      rules2: {
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
      url2: "http://localhost:8080/back/enter/register",
    
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

    }
  },

  methods:{
    
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // this.handlerRequest();
            let captcha = new TencentCaptcha("2092476744", (res) => {
              if (res.ret === 0) {
                this.handlerRequest();
              } else {
                  this.$message({
                message: "认证失败，请重新认证",
                type: "error",
                center: true,
              });
              }
            });
            captcha.show();
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
              let token =jwtDecode(response.data.data.token);
              
              if(token.Auth=='普通用户'){
                this.$router.push({
                  name:'noad-person'
                })
              }
              else if(token.Auth=='管理员'){
                this.$router.push({
                  name:'ad-person'
                })
              }     
                       
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
          handlerRequest2() {
        axios.post(this.url2, this.formData2).then(
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
                name: 'noad-person',
                
                
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
    
    submitForm2(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
            this.handlerRequest2();
            
        } else {
            
          return false;
        }
      });
     
    },
    
    resetForm(formName) {
      this.$refs[formName].resetFields();
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

