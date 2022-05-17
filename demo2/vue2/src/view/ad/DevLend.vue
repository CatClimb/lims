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
      <!-- <el-table-column label="序号" min-width=30>
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column> -->
      <el-table-column min-width=100 show-overflow-tooltip
        v-for="(item, index) of info.tableHead"
        v-if="index!=8"
       
        :key="index"
        :label="item[1]"
        :prop="item[0]"  

      >
      </el-table-column>
      <el-table-column min-width=100 show-overflow-tooltip
      :formatter="formatBoolean"
        :label="info.tableHead[8] ? info.tableHead[8][1] : ''"
        :prop="info.tableHead[8] ? info.tableHead[8][0] : ''"  
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
        
        <el-form-item label="设备id" prop="id">
          <el-input v-model.number="dialog.data.id" :readonly="dialog.readonly"></el-input> 
        </el-form-item>
        <el-form-item label="借用状态" prop="devUStatus">
          <el-select v-model="dialog.data.devUStatus" placeholder="请选择">
            <el-option label="不可用" value="不可用"></el-option>
            <el-option label="可借用" value="可借用"></el-option>
            <el-option label="申请中" value="申请中"></el-option>
            <el-option label="被借用" value="被借用"></el-option>
          </el-select>
        </el-form-item>
      
        <el-form-item label="借用人" prop="userName">
          <el-input v-model.number="dialog.data.userName"></el-input> 
        </el-form-item>
      <el-form-item label="开始时间" prop="borrowTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="dialog.data.borrowTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        
        </el-form-item>
      <el-form-item label="结束时间" prop="returnTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="dialog.data.returnTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-col>
        
        </el-form-item>

        <el-form-item label="借用原因" prop="devReason">
          <el-input v-model="dialog.data.devReason" type="textarea" maxlength="8000" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="是否归还" prop="returnStatus">
          <el-select v-model="dialog.data.returnStatus" placeholder="请选择">
            <el-option label="是" :value="true"></el-option>
            <el-option label="否" :value="false"></el-option>

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
import { mapMutations } from 'vuex';

export default {
  name: "ad-DevLend",
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
returnStatus:"",
devName:"",
devPrice:"",
devStatus:"",
      },
      },
      thTmp:["",""],
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
              returnStatus:"",
        },
        rules: {
          id:[
            { type: 'number', message: '请输入数字',  },

            {required:true,message:"请输入设备id"}
          ],
          devPrice:[
            {required:true,message:"请输入设备价格"}
          ],
          devUStatus:[
            {required:true,message:"请选择内容"}
        ],
          devStatus:[
            {required:true,message:"请选择内容"}
          ],
        },
        visible:false,
        dialogTitle:"",
        submitTitle:"",
        repealSubmitTitle:"",
         readonly:true,
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
        
      };
    },
    
  },
  methods: {
    ...mapMutations(['SET_CHECK_MENU']),
     /*布尔值格式化：cellValue为后台返回的值
*/
            formatBoolean: function (row, column, cellValue) {
              // console.log("cellvalue",cellValue)
                var ret = ''  //你想在页面展示的值
                if (cellValue) {
                    ret = "是"  //根据自己的需求设定
                } else {
                    ret = "否"
                }
                return ret;
            },
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
      .post("http://localhost:8080/back/ad/mulquerydl", this.selectform)
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
     /*布尔值格式化：cellValue为后台返回的值
*/
  
    handleEdit(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.id=Number(this.dialog.data.id);
      
      // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
      this.dialog.visible=true;
      
      this.dialog.dialogTitle="修改设备仪器信息";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateDevLend"
      this.dialog.readonly=true;
    },
    handleAdd(){
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加设备仪器信息";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertDevLend"
      this.dialog.readonly=false;
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteDevLend/${row.id}`,).then(
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
    this.SET_CHECK_MENU("/home/devLend");
    this.handlerQuery();
    // let a=this.info.tableHead;
    

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