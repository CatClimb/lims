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
    
      <el-table-column label="序号" min-width=30>
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column min-width=50 show-overflow-tooltip
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
        

        <el-form-item label="入库数量" prop="inCount">
          <el-input v-model.number="dialog.data.inCount"></el-input>
        </el-form-item>
        <el-form-item label="操作人" prop="userName">
          <el-input v-model="dialog.data.userName"></el-input>
        </el-form-item>
        <el-form-item label="入库时间" prop="inTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择时间" v-model="dialog.data.inTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="易耗品id" prop="smeId">
          <el-input v-model.number="dialog.data.smeId" ></el-input>
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
  name: "ad-inRecord",
  components:{
    
  },
  data() {
    var validateOutRecord=(rule, value, callback) => {    
    if (value<=0) {
    
      callback(new Error("请输入合法数值"));
    }else{
        callback();
    }
    
  };  
    return {
      info: {
        tableHead: [],
        page: 1,
        pageSize: 10,
        count: 0,
        tableData: [],
        tmpObj:{
          id:"",inCount:"",userName:"",inTime:"",smeName:"",smeCount:"",smeId:"",
        }
       
      },
      
      formLabelWidth: "120px",
      queryContent:"",
      dialog:{
        data:{
          id:0,
          inCount:"",
          userName:"",
          inTime:"",
          smeId:"",  
        },
        rules: {
          
          inCount:[
            {required:true, message:"请输入内容"},
            { type: 'number', message: '请输入数字',  },
            { validator: validateOutRecord, trigger: "blur" }
            ],
          userName:[
            {required:true, message:"请输入内容"},
          ],
          inTime:[
            {required:true, message:"请输入时间"},
          ],
          smeId:[
            {required:true,message:"请输入内容"},
            { type: 'number', message: '请输入数字',  }
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
        inCount:this.queryContent,
        smeId:this.queryContent,
        smeName:this.queryContent,
        smeCount:this.queryContent,
        
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
      .post("http://localhost:8080/back/ad/mtqueryi", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableData=[];
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            response.data.data.tableData.forEach(e => {
              console.log(e);
              console.log("this",this)
              e.inRecordEntities.forEach(el=>{
                console.log("el.id",el.id)
              this.info.tmpObj.id=el.id;
              this.info.tmpObj.inCount=el.inCount;
              this.info.tmpObj.userName=el.userName;
              this.info.tmpObj.inTime=el.inTime;
              this.info.tmpObj.smeId=el.smeId;
              this.info.tmpObj.smeName=e.smeName;
              this.info.tmpObj.smeCount=e.smeCount;
              
              this.info.tableData.push(this.info.tmpObj);
              this.info.tmpObj={}
              });
            });
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
      this.dialog.data.inCount=Number(this.dialog.data.inCount);
      this.dialog.visible=true;
      this.dialog.dialogTitle="修改易耗品信息";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateInRecord"

    },
    handleAdd(){
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加易耗品信息";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertInRecord"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteInRecord/${row.id}`,).then(
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
      console.log("submitForm1");
    this.$refs[formName].validate((valid) => {
      console.log("submitForm");
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
    ...mapMutations(["SET_CHECK_MENU"])

  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
  
  },
  mounted() {
    this.SET_CHECK_MENU("/home/com");
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