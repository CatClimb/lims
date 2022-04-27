<template>
  <div>
    <el-input style="visibility:hidden"
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

      <el-table-column label="操作" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="outPass(scope.$index, scope.row)"
            >批准</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="outNoPass(scope.$index, scope.row)"
            >不批准</el-button
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
  </div>
</template>
<script>
import axios from 'axios'
import {mapMutations} from 'vuex';

export default {
  name: "ad-outHandle",
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
          id:"",outCount:"",userName:"",outTime:"",smeName:"",smeCount:"",smeId:"",
        }
       
      },
      
      formLabelWidth: "120px",
      queryContent:"",
      dialog:{
        data:{
          id:0,
          outCount:"",
          userName:"",
          outStatus:"",
          outReason:"",
          smeId: "",
          smeName:"",
          smeCount:""
        },
        rules: {
          
          outCount:[
            {required:true, message:"请输入内容"},
            { type: 'number', message: '请输入数字',  },
            { validator: validateOutRecord, trigger: "blur" }
            ],
          userName:[
            {required:true, message:"请输入内容"},
          ],
          outStatus:[
            {required:true, message:"请选择"},
          ],
          outReason:[
            {required:true, message:"请输入内容"},
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
        outCount:this.queryContent,
        outStatus:this.queryContent,
        smeId:this.queryContent,
        smeName:this.queryContent,
        smeCount:this.queryContent,
        outReason:this.queryContent,
        
        
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
      .post("http://localhost:8080/back/ad/mtqueryo", this.selectform)
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
              e.outRecordEntities.forEach(el=>{
                console.log("el.id",el.id)
              this.info.tmpObj.id=el.id;
              this.info.tmpObj.outCount=el.outCount;
              this.info.tmpObj.userName=el.userName;
              this.info.tmpObj.outTime=el.outTime;
              this.info.tmpObj.smeId=el.smeId;
              this.info.tmpObj.outStatus=el.outStatus;
              this.info.tmpObj.outReason=el.outReason;
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
    outPass(index,row){

      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.outCount=Number(this.dialog.data.outCount);
      this.dialog.data.smeId=Number(this.dialog.data.smeId);
   
      this.dialog.url="http://localhost:8080/back/ad/outPass"
      axios.post(this.dialog.url,this.dialog.data).then(
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
      );
 
   
    },
    outNoPass(index,row){
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.outCount=Number(this.dialog.data.outCount);
      this.dialog.data.smeId=Number(this.dialog.data.smeId);
   
      this.dialog.url="http://localhost:8080/back/ad/outNoPass"
      axios.post(this.dialog.url,this.dialog.data).then(
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
      );
 
    },


  
    ...mapMutations(["SET_CHECK_MENU"])

  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
  
  },
  mounted() {
    this.SET_CHECK_MENU("/home/outHandle");
    this.handlerQuery();
  },
    created(){
    this.queryContent='出库中'
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