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
      <el-table-column min-width=50 show-overflow-tooltip
        v-for="(item, index) of info.tableHead"
        :key="index"
        :label="item[1]"
        :prop="item[0]"
      >
      </el-table-column>

      <el-table-column label="操作" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="applyForOut(scope.$index, scope.row)"
            >出库申请</el-button
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
        

        <el-form-item label="易耗品名字" prop="smeName">
          <el-input v-model="dialog.data.smeName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="易耗品数量" prop="smeCount">
          <el-input v-model.number="dialog.data.smeCount" ></el-input>
        </el-form-item>
      
        <el-form-item label="出库数量" prop="outCount">
          <el-input v-model.number="dialog.data.outCount"></el-input>
        </el-form-item>
        <el-form-item label="出库原因" prop="outReason">
          <el-input v-model="dialog.data.outReason" type="textarea" maxlength="100" show-word-limit></el-input>
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
  name: "noad-nConsume",
  components:{
    
  },
  data() {
    var validateOutCount=(rule, value, callback) => {    
    if (value<=0) {
      
      callback(new Error("请输入合法数值"));
    }else if(value>this.dialog.data.smeCount){
            callback(new Error("数量超出"));

      }
      else{
      callback();
    }
    }
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
              smeName:"",
              smeCount:"",
              outCount:"",
              outReason:"",
              smeId:"",
              
           
        },
        rules: {
          
          smeName:[{
            required:true, message:"请输入易耗品名"
          }],
          smeCount:[{
              required:true, message:"请输入内容"

          },{ type: 'number', message: '请输入数字',  },
          ],
          outCount:[
            {required:true, message:"请输入内容"},
            { type: 'number', message: '请输入数字',  },
            { validator: validateOutCount, }
            ],
          outReason:[
              {required:true, message:"请输入内容"
}
            ]
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
    console.log("this.selectform")
    axios
      .post("http://localhost:8080/back/noad/queryConsumeTable", this.selectform)
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
    applyForOut(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.id=Number(this.dialog.data.id);
      this.dialog.data.smeCount=Number(this.dialog.data.smeCount);
      this.dialog.visible=true;
      this.dialog.dialogTitle="出库信息";
      this.dialog.submitTitle="确定";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/noad/applyForOutStore"

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
            // this.dialog.visible=false;
            // console.log("ssssssssssss")
            // this.handlerQuery();
            this.$router.push({
              name:'noad-consumeRes'
            })
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
    },
    ...mapMutations(["SET_CHECK_MENU"])

  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
  
  },
  mounted() {
    this.SET_CHECK_MENU("/home/nConsume");
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