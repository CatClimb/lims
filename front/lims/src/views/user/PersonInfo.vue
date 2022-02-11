<template>
  <div id="container">
    <!-- <el-form
        :model="formData"
        :rules="rules"
        ref="register_form_node"
        label-width="100px"
        class="demo-formData"
        
      >
      
        <el-form-item label="用户名" prop="userName" >
          <el-input v-model="formData.userName" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formData.sex" placeholder="请选择性别"  :disabled="isRead">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="微信" prop="weChat">
          <el-input v-model="formData.weChat" :readonly="isRead"></el-input>
        </el-form-item>
        
        <el-form-item label="角色" prop="role">
          <el-input v-model="formData.role" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="auth">
          <el-input v-model="formData.auth" :readonly="isRead"></el-input>
        </el-form-item>
         <el-form-item>
        <el-button @click="submitForm('register_form_node')">修改</el-button>

        <el-button type="primary" @click="resetForm('register_form_node')"
          >重置</el-button
        >
         </el-form-item>
         <el-form-item label="密码" prop="password">
          <el-input type="text" v-model="formData.password" :readonly="isRead"></el-input>
        </el-form-item>
      </el-form> -->

    <ul>
      <li>
        <span>用 户 名</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.userName}}</span>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button type="text" @click="dialogVisibleUpdateInfo=true"><span>修改资料</span></el-button>
      </li>
      <li>
        <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.name }}</span>
      </li>
      <li>
        <span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button type="text" @click="dialogVisibleUpdatePwd=true"><span>修改密码</span></el-button>
      </li>
      <li>
        <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.sex }}</span>
      </li>
      <li>
        <span>手 机 号</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.phone }}</span>
      </li>
      <li>
        <span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.email }}</span>
      </li>
      <li>
      <li>
        <span>微&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.weChat }}</span>
      </li>
      <li>
        <span>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.role }}</span>
        
      </li>
      <li>
        <span>权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span> {{info.auth }}</span>
      </li>
    </ul>

    <!-- 更新个人资料 -->
    <el-dialog :title="dialogtitleUpdateInfo" :visible.sync="dialogVisibleUpdateInfo">
        <el-form
          :model="info"
          ref="infoNode"
          :rules="rulesUpdateInfo"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item
            label="用户名"
            :label-width="formLabelWidth"
            prop="userName"
          >
            <el-input v-model="info.userName" ></el-input>
          </el-form-item>
          <el-form-item
            label=" 姓名"
            :label-width="formLabelWidth"
            prop="name"
          >
            <el-input v-model="info.name" ></el-input>
          </el-form-item>
          
          <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
            <el-select v-model="info.sex" placeholder="请选择活动区域">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item
            label="手机号"
            :label-width="formLabelWidth"
            prop="phone"
          >
            <el-input v-model="info.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
            <el-input v-model="info.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="微信"
            :label-width="formLabelWidth"
            prop="weChat"
          >
            <el-input v-model="info.weChat" autocomplete="off"></el-input>
          </el-form-item>
          
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleUpdateInfo = false">取 消</el-button>

          <el-button type="primary" @click="handlerUpdateInfo('infoNode')"
            >添 加</el-button
          >
        </div>
      </el-dialog>
    <!-- 更新密码 -->
      <el-dialog :title="dialogtitleUpdatePwd" :visible.sync="dialogVisibleUpdatePwd">
        <el-form
          :model="pwdForm"
          ref="pwdNode"
          :rules="rulesUpdatePwd"
          label-width="100px"
          class="demo-ruleForm">
        <el-form-item label="原密码" prop="password">
          <el-input type="password" v-model="pwdForm.password"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="passwordN">
          <el-input type="password" v-model="pwdForm.passwordN"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="againPassword">
          <el-input type="password" v-model="pwdForm.againPassword"></el-input>
        </el-form-item>
        </el-form>
         <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleUpdatePwd = false">取 消</el-button>

          <el-button type="primary" @click="handlerUpdatePwd('pwdNode')"
            >添 加</el-button
          >
        </div>
      </el-dialog>
    
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "PersonInfo",
  data() {
    // var validateOrigin=(rule, value, callback) => {
        
    //   if (value === "") {
          
    //     callback(new Error("请输入密码"));
    //   } else if (value !== this.info.password) {
    //     callback(new Error("密码错误"));
    //   }else{
    //       callback();
    //   }
    // };
    var validateAgain = (rule, value, callback) => {
        
      if (value === "") {
          
        callback(new Error("请输入密码"));
      } else if (value !== this.pwdForm.passwordN) {
        callback(new Error("两次输入密码不一致"));
      }else{
          callback();
      }
    };
    return {
      pwdForm:{
        id:'',
        password:'',
        passwordN:'',
        againPassword:''
      },
      info: {
        id:'',
        userName: "",
        password: "",
        name: "",
        sex: "",
        phone: "",
        email: "",
        weChat: "",
        auth: "",
        role: "",
        loginTime: '',
        
      },
      formLabelWidth: "120px",
      
      dialogtitleUpdateInfo:'修改资料',
      dialogtitleUpdatePwd:'修改密码',
      
      dialogVisibleUpdateInfo:false,
      dialogVisibleUpdatePwd:false,
      rulesUpdateInfo:{
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
      rulesUpdatePwd:{
        
        password: [{ required: true, message: "请输入密码", trigger: "blur" },
        {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 ",
            trigger: "blur",
          },],
        
         passwordN: [
          { required: true, message: "请输入密码", trigger: "blur" },

          {
            min: 6,
            max: 20,
            message: "长度在 6 到 20 ",
            trigger: "blur",
          },
        ],

        againPassword: [{ validator: validateAgain, trigger: "blur" },{ required: true, message: "请输入密码", trigger: "blur" },],
        
       
      }
    };
  },
 
  methods: {
    handlerShowInfo() {
      axios.get("http://localhost:8080/back/user/byUserName").then(
        (response) => {
          if (response.data.code === 2000) {
            this.info = response.data.data;
            this.pwdForm.id=response.data.data.id
           
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
            message: error.response.msg,
            type: "error",
            center: true,
          });
        }
      );
    },
    handlerUpdateInfo(formName){
        this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post("http://localhost:8080/back/user/updateUser",this.info).then(
        (response) => {
          
          if (response.data.code === 2000) {
            this.$message({
              message: response.data.msg,
              type: "success",
              center: true,
            });
            this.dialogVisibleUpdateInfo=false;
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
            message: error.response.msg,
            type: "error",
            center: true,
          });
        }
      );
        } else {
          
          return false;
        }
      });
    },
    handlerUpdatePwd(formName){
       this.$refs[formName].validate((valid) => {
        if (valid) {
           
          axios.post("http://localhost:8080/back/user/updatePwd",this.pwdForm).then(
        (response) => {
          if (response.data.code === 2000) {
            this.$message({
              message: response.data.msg,
              type: "success",
              center: true,
            });
            this.dialogVisibleUpdatePwd=false;
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
            message: error.response.msg,
            type: "error",
            center: true,
          });
        }
      );
        } else {
          
          return false;
        }
      });
    },
    
    
  },
  mounted(){
    this.handlerShowInfo();
   
  }
};
</script>
<style lang="css" scoped>
.el-input {
  width: 400px;
}
#container {
  background-color: rgba(255, 255, 255);
  width: 100%;
  height: 100%px;

  
  border-radius: 10px;
  padding: 2px;
  margin: 2px;
  border-radius: 20px;
}
ul {
  list-style: none;
}
ul li {
  line-height: 24px;
  font-size: 20px;
  font-weight: 20px;
  padding: 24px;
}
</style>