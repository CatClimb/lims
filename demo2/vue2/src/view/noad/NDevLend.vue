<template>
  <div>
    <el-input
    placeholder="请输入搜索内容"
    prefix-icon="el-icon-search"
    v-model="queryContent">
    
  </el-input>
    <el-table
      :data="info.tableData"
      ref="multipleTable"
      class="container"
      height="750"
    >
     
      <el-table-column min-width=100 show-overflow-tooltip
        v-for="(item, index) of info.tableHead"
        :key="index"
        :label="item[1]"
        :prop="item[0]"
      >
      </el-table-column>

      <el-table-column label="操作" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="success" v-show="scope.row.devUStatus=='可借用'&&scope.row.devStatus=='良好'" @click="handleEdit(scope.$index, scope.row)"
            >借用</el-button
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
        
        <el-form-item label="设备名" prop="devName">
          <el-input v-model="dialog.data.devName" :readonly="true"></el-input> 
        </el-form-item>
        
      
       
       
      <!-- <el-form-item label="开始时间" prop="borrowTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="dialog.data.borrowTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        
        </el-form-item>
      <el-form-item label="结束时间" prop="returnTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="dialog.data.returnTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        
        </el-form-item> -->
<el-form-item label="借用日期" prop="dateSD">
            
         <el-date-picker
          v-model="dialog.data.dateSD"
          type="datetimerange"
          unlink-panels
          range-separator="至"
          start-placeholder="项目开始日期"
          end-placeholder="项目结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          >
        </el-date-picker>
        </el-form-item>
        <el-form-item label="借用原因" prop="devReason">
          <el-input v-model="dialog.data.devReason" type="textarea" maxlength="8000" show-word-limit></el-input>
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
import { mapMutations } from 'vuex';

export default {
  name: "noad-nDevLend",
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
        tmpObj:{
id:"",
devUStatus:"",
userName:"",
borrowTime:"",
returnTime:"",
devReason:"",
devName:"",
devPrice:"",
devStatus:"",
        }
      },
     
      formLabelWidth: "120px",
      queryContent: "",
      dialog:{
        data:{
              id:"",
              devUStatus:"",
              userName:"",
              borrowTime:"",
              returnTime:"",
              devReason  :"",
              devName:'',
              dateSD:["",""],
              returnStatus:"",
        },
        rules: {
          devReason:[
            {required:true,message:"请输入内容"}
          ],
           dateSD: [
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
        ],
          
        },
        visible:false,
        dialogTitle:"",
        submitTitle:"",
        repealSubmitTitle:"",
     
      },
      
      
    };
  },
  computed: {
    selectform() {
      return {
        page: this.info.page,
        pageSize: this.info.pageSize,
        devUStatus:this.queryContent,
        userName:this.queryContent,
        devReason:this.queryContent,
        devName:this.queryContent,
        devPrice:this.queryContent,
        devStatus:this.queryContent,

        
      };
    },
  },
  methods: {
    ...mapMutations(['SET_CHECK_MENU']),
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
      .post("http://localhost:8080/back/noad/mulquerydl", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            this.info.tableData=[];
            response.data.data.tableData.forEach(e => {
              this.info.tmpObj.id=e.devLendEntity.id;
              this.info.tmpObj.devUStatus=e.devLendEntity.devUStatus;
              this.info.tmpObj.userName=e.devLendEntity.userName;
              this.info.tmpObj.borrowTime=e.devLendEntity.borrowTime;
              this.info.tmpObj.returnTime=e.devLendEntity.returnTime;
              this.info.tmpObj.devReason=e.devLendEntity.devReason;
              this.info.tmpObj.returnStatus=e.devLendEntity.returnStatus;
              this.info.tmpObj.devName=e.devName;
              this.info.tmpObj.devPrice=e.devPrice;
              this.info.tmpObj.devStatus=e.devStatus;
              
              this.info.tableData.push(this.info.tmpObj);
              this.info.tmpObj={}

            });
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
       this.dialog.data.dateSD=["",""]
      this.dialog.data.id=Number(this.dialog.data.id);
      if(this.dialog.data.borrowTime&&this.dialog.data.returnTime){
              this.dialog.data.dateSD[0]=this.dialog.data.borrowTime;
      this.dialog.data.dateSD[1]=this.dialog.data.returnTime;
      this.dialog.data.returnStatus=this.dialog.data.returnStatus ? true:false;
      }

      // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
      this.dialog.visible=true;
      
      this.dialog.dialogTitle="借用信息";
      this.dialog.submitTitle="确定";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/noad/handleDevLend"
    },

   

    submitForm(formName){
      
    this.$refs[formName].validate((valid) => {
      if (valid) {
  
        this.dialog.data.borrowTime=this.dialog.data.dateSD[0];
        this.dialog.data.returnTime=this.dialog.data.dateSD[1];

          axios.post(this.dialog.url, this.dialog.data).then(
        (response) => {
          if (response.data.code === 2000) {
            this.$message({
              message: response.data.msg,
              type: "success",
              center: true,
            });
         
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
    }
  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
    queryDate(){
      this.handlerQuery();
    }

  },
  mounted() {
    this.SET_CHECK_MENU("/home/nDevLend");
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