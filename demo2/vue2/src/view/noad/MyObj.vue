<template>
  <div>

    

  <el-button id="button"
            size="medium  "
            type="primary"
            @click="handleAdd()"
            >项目申请</el-button
          >
    <el-table
      :data="info.tableData"
      ref="multipleTable"
      class="container"
      height="750"
    >
    
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
          
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            v-show="scope.row.objStatus=='审批中'"
            >取消</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="position: fixed; right: 10px; bottom: 10px">
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
        
        
        <el-form-item label="项目名" prop="objName">
          <el-input v-model="dialog.data.objName"></el-input>
        </el-form-item>
        <el-form-item label="项目描述" prop="objDescription">
          <el-input v-model="dialog.data.objDescription" type="textarea" maxlength="8000" show-word-limit></el-input>
        </el-form-item>


        <el-form-item label="项目周期" prop="DateSD">
            
         <el-date-picker
          v-model="dialog.data.DateSD"
          type="daterange"
          unlink-panels
          range-separator="至"
          start-placeholder="项目开始日期"
          end-placeholder="项目结束日期"
          value-format="yyyy-MM-dd"
          >
        </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="项目始" prop="objSTime">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="dialog.data.objSTime" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
          </el-col>
         
        </el-form-item>
        <el-form-item label="项目终" prop="objETime">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="dialog.data.objETime" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
          </el-col>         
        </el-form-item> -->
        
        <el-form-item label="申请原因" prop="objReason">
          <el-input v-model="dialog.data.objReason" type="textarea" maxlength="8000" show-word-limit></el-input>
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
import {mapMutations} from 'vuex';
import jwtDecode from 'jwt-decode';

export default {
  name: "ad-myObj",
  components:{
    
  },
  data() {
    
    return {
      info: {
        tableHead: [],
        page: 1,
        pageSize: 10,
        count: 0,
        tableData: [],
       
      },
      
      formLabelWidth: "120px",
      queryContent:'',
      dialog:{
        data:{
              id:0,
              objName:"",
              objDescription:"",
              objStatus:"",
              userName:"",
              objSTime:"",
              objETime:"",
              objReason:"",
              recordTime:"",
              DateSD:["",""],
        },
        rules: {
          
          objName:[{
            required:true, message:"请输入项目名"
          }],
          objDescription:[{
                        required:true, message:"请输入内容"

          }],
         
         
           objReason:[{
                        required:true, message:"请输入内容"

          }],
          
      
 DateSD: [
          {
            type: 'array',
            required: true,
            trigger: "请选择日期区间",
            
            fields: {
              //tpye类型试情况而定,所以如果返回的是date就改成date
              0: { type: 'string', required: true, message: '请选择开始日期' },
              1: { type: 'string', required: true, message: '请选择结束日期' }
            }
          }
        ]

          
         
        },
        visible:false,
        dialogTitle:"",
        submitTitle:"",
        repealSubmitTitle:"",
        url:''
      },
      
      
    };
  },
  computed: {
    selectform() {
      return {
        page: this.info.page,
        pageSize: this.info.pageSize,
        objName:this.queryContent,
        objDescription:this.queryContent,
        objStatus:this.queryContent,
        userName:this.queryContent,
        objReason:this.queryContent,
       
        
      };
    },
  },
  methods: {
    handleSizeChange(val) {
      this.info.pageSize = val;
      
        this.handlerQuery();
  
      
      // 
    },
    handleCurrentChange(val) {
      this.info.page = val;
     
        this.handlerQuery();

    },
    handlerQuery(){
    console.log(this.selectform)
    axios
      .post("http://localhost:8080/back/noad/queryObjTable", this.selectform)
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
      ).finally(() => {
   this.$nextTick(() => {
      this.$refs.multipleTable.doLayout();
    });
  });
    },

    handleAdd(){
      this.dialog.visible=true;
     
      this.dialog.dialogTitle="添加项目信息";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      //  this.dialog.data.objStatus='审批中';
      this.dialog.url="http://localhost:8080/back/noad/registerObj"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/noad/nRegisterObj/${row.id}`,).then(
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
          this.dialog.data.objSTime=this.dialog.data.DateSD[0]
          this.dialog.data.objETime=this.dialog.data.DateSD[1]
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
          this.$refs[formName].resetFields();
          this.dialog.visible=false;           
    },
    closeDialog(){
              this.$refs['form'].resetFields();    
    },...mapMutations(['SET_CHECK_MENU'])

  },

  created(){
     let token =jwtDecode(localStorage.getItem("token"));
    if(token.loginName){
      this.queryContent=token.loginName;
    }
  },
  mounted() {
    this.SET_CHECK_MENU("/home/myObj");
    this.handlerQuery();
   
  },

};
</script>
<style scoped>
#button{
      height: 10%;
      margin: 0 10px 10px 0;

     
    }
  
</style>