<template>
  <div>
    <el-input
    placeholder="请输入搜索内容"
    prefix-icon="el-icon-search"
    v-model="queryContent">
  </el-input>
  <el-button
            size="medium  "
            type="primary"
            @click="handleAdd()"
            >添加</el-button
          >
    <el-table
      :data="info.tableData"
      style="width: 100%"
      class="container"
      height="750"
      ref="multipleTable"
    >
      <!-- <el-table-column
      label="日期"
      width="180">
      <template slot-scope="scope">
      
        <span style="margin-left: 10px">{{ scope.row.date }}</span>
      </template>
    </el-table-column> -->
      <!-- <el-table-column
        type="selection"
        width="55">
      </el-table-column> -->
      <el-table-column label="序号" min-width=30>
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column min-width=100 show-overflow-tooltip
        v-for="(item, index) of info.tableHead"
        :key="index"
        :label="item[1]"
        :prop="item[0]"
      >
      </el-table-column>

      <el-table-column label="操作" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
      
    </el-table>
    <div style="position: fixed; right: 10px; bottom: 10px;">
      <!-- <span class="demonstration">完整功能</span> -->
      <el-pagination
        background
        :pager-count="11"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="selectform.page"
        :page-sizes="[3, 5, 10, 20, 50, 100]"
        :page-size="selectform.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="info.count"
      >
      </el-pagination>
  </div>
    <el-dialog :title="dialog.dialogTitle" :visible.sync="dialog.visible" @close="closeDialog()">
      <el-form
        :model="dialog.data"
        :rules="dialog.rules"
        ref="form"
        label-width="100px"
        class="demo-formData"
      >
        
        
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="dialog.data.userName"></el-input>
        </el-form-item>
<el-form-item label="密码" prop="password">
          <el-input  v-model="dialog.data.password"></el-input>
        </el-form-item>
<el-form-item label="姓名" prop="name">
          <el-input v-model="dialog.data.name"></el-input>
        </el-form-item>
<el-form-item label="性别" prop="sex">
          <el-select v-model="dialog.data.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number="dialog.data.phone"></el-input>
        </el-form-item>
<el-form-item label="邮箱" prop="email">
          <el-input v-model="dialog.data.email"></el-input>
        </el-form-item>
        <el-form-item label="微信" prop="weChat">
          <el-input v-model="dialog.data.weChat"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="auth">
          <el-select v-model="dialog.data.auth" placeholder="请选择权限">
            <el-option label="普通用户" value="普通用户"></el-option>
            <el-option label="管理员" value="管理员"></el-option>
          </el-select>
        </el-form-item>
        
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitForm('form')">{{dialog.submitTitle}}</el-button>

        <el-button type="primary" @click="resetForm('form')"
          >{{dialog.repealSubmitTitle}}</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import axios from 'axios'
import {mapMutations} from 'vuex'
export default {
  name: "ad-user",
  components:{
    
  },
  data() {
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
      info: {
        tableHead: [],
        page: 1,
        pageSize: 10,
        count: 0,
        tableData: [],
       
      },
      formLabelWidth: "120px",
      queryContent: "",
      dialog:{
        data:{
            id:6,
            userName:"",
            password:"",
            name:"",
            sex:"",
            phone:"",
            email:"",
            weChat:"",
        
            auth:""
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

           
            ],

            
            name: [
            { required: true, message: "请输入姓名",  },
            {
                min: 2,
                max: 20,
                message: "长度在 2 到 20 个字符",
            
            },
            ],
            sex:[
              { required: true, message: "请输入性别",  }
            ],

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
         
            auth:[
              { required: true, message: "请输入权限"  }
            ]
        },
        visible:false,
        dialogTitle:"",
        submitTitle:"",
        repealSubmitTitle:""
      },
      
      
    };
  },
  computed: {
    selectform() {
      return {
        page: this.info.page,
        pageSize: this.info.pageSize,
        userName:this.queryContent,
        password:this.queryContent,
        name:this.queryContent,
        sex:this.queryContent,
        phone:this.queryContent,
        email:this.queryContent,
        weChat:this.queryContent,
      
        auth:this.queryContent,
        
      };
    },
  },
  methods: {
    handleSizeChange(val) {
      this.info.pageSize = val;
      this.handlerQuery();
    },
    handleCurrentChange(val) {
      this.info.page = val;
      this.handlerQuery();
    },
    handlerQuery(){
    console.log(this.selectform)
    axios
      .post("http://localhost:8080/back/ad/queryUserTable", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            this.info.tableData=response.data.data.tableData;
            // this.$message({
            //   message: response.data.msg,
            //   type: "success",
            //   center: true,
            // });
            console.log(response.data.data)
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
      )
      .finally(() => {
   this.$nextTick(() => {
      this.$refs.multipleTable.doLayout();
    });
  });
    },
    handleEdit(index,row){
      //防止地址覆盖 this.dialog.data=row;该句不可用
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.phone=Number(this.dialog.data.phone);
      this.dialog.visible=true;
      this.dialog.dialogTitle="修改用户";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateUser"

    },
    handleAdd(){
      
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加用户";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertUser"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteUser/${row.id}`,).then(
        (response)=>{
          if (response.data.code === 2000) {
            this.$message({
                  message: response.data.msg,
                  type: "success",
                  center: true,
                });
            this.handlerQuery();    
          }else{
            this.$message({
              message:response.data.msg,
              type:"error",
              center:true
            })
          }
        },
        (error)=>{
          this.$message({
            message: error.response.data.msg,
            type: "error",
            center: true,
          });
        }

      )
    },

    submitForm(formName){
    this.$refs[formName].validate((valid) => {
      if (valid) {
          axios.post(this.dialog.url, this.dialog.data).then(
        (response) => {
          if (response.data.code === 2000) {
            this.$message({
              message: response.data.msg,
              type: "success",
              center: true,
            });
            // this.$router.push({
            //   name: 'ad-lab',
            // })
            this.dialog.visible=false;
            this.handlerQuery();
          } else {
            this.$message({
              message: response.data.msg,
              type: "error",
              center: true,
            });
            this.handlerQuery();
          }
        },
        (error) => {
          this.$message({
            message: error.response.data.msg,
            type: "error",
            center: true,
            
          });
          this.handlerQuery();
        }
      );
          
      } else {
        return false;
      }
    });
    
    },
    
    resetForm(formName) { 
        if(this.dialog.repealSubmitTitle=="重置"){
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$refs[formName].clearValidate();
            
        }else{
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$nextTick(() => {
               this.dialog.visible=false
              
            });
            this.$refs[formName].clearValidate();
        }
      
    },
    closeDialog(){
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$nextTick(() => {
              this.$refs['form'].clearValidate();
            });
            
    },
        ...mapMutations(['SET_CHECK_MENU'])

  },
  watch:{
    queryContent(){
                  
                  this.handlerQuery();
                },
  },
  mounted() {
    this.SET_CHECK_MENU("/home/user");
    this.handlerQuery();
  },

};
</script>
<style scoped>
.el-input {
      height: 10%;
      width: 40%;
      margin: 0 10px 10px 0;
      /* padding: 10px;
      margin: 10px; */


    }
</style>