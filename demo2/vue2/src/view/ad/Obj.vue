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
      ref="multipleTable"
      class="container"
      height="750"
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
        <el-form-item label="项目状态" prop="objStatus">
          <el-select v-model="dialog.data.objStatus" placeholder="请选择">
            <el-option label="审批中" value="审批中"></el-option>
            <el-option label="已审批" value="已审批"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="负责人" prop="userName">
          <el-input v-model="dialog.data.userName"></el-input>
        </el-form-item>
        
        <el-form-item label="项目始" prop="objSTime">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="dialog.data.objSTime" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
          </el-col>
         
        </el-form-item>
        <el-form-item label="项目终" prop="objETime">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="dialog.data.objETime" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
          </el-col>
         
        </el-form-item>
        <el-form-item label="登记日期" prop="recordTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="dialog.data.recordTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        </el-form-item>
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

export default {
  name: "ad-obj",
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
      queryContent:"",
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
           
        },
        rules: {
          
          objName:[{
            required:true, message:"请输入项目名"
          }],
          objDescription:[{
                        required:true, message:"请输入内容"

          }],
          objStatus:[{
            required:true, message:"请选择项目状态"
          }],
          userName:[{
                        required:true, message:"请输入负责人"

          }],

           objReason:[{
                        required:true, message:"请输入内容"

          }],
           objSTime:[{
                        required:true, message:"请选择开始日期"

          }],
           objETime:[{
                        required:true, message:"请选择结束日期"

          }],
            // name
            //   {required:true,message:"请输入使用者姓名"}
            // ],
            // lgType:[
            //   {required:true,message:"请输入使用类别"}
            // ],
            // objName:[
              
            // ],
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
      .post("http://localhost:8080/back/ad/queryObjTable", this.selectform)
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
    handleEdit(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.visible=true;
      this.dialog.dialogTitle="修改项目信息";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateObj"

    },
    handleAdd(){
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加项目信息";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertObj"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteObj/${row.id}`,).then(
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
            
    },...mapMutations(['SET_CHECK_MENU'])

  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
  
  },
  mounted() {
    
    this.SET_CHECK_MENU("/home/obj");
    this.handlerQuery();
  },

};
</script>
<style scoped>
.el-input{
      height: 10%;
      width: 40%;
      margin: 0 10px 10px 0;
      /* padding: 10px;
      margin: 10px; */
      
     
    }
  
</style>