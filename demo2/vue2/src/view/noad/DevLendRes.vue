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
      ></el-table-column>
      <el-table-column label="操作" fixed="right" width="150">
        <template slot-scope="scope">
         
          <el-button
            size="mini"
            type="danger"
            @click="cancelDevLend(scope.$index, scope.row)"
            v-show="scope.row.devStatus=='良好'&&scope.row.devUStatus=='申请中'"
            >取消</el-button>

            <el-button
            size="mini"
            style="background-color:LightGreen"
            @click="cancelDevLend(scope.$index, scope.row)"
            v-show="scope.row.devStatus=='良好'&&scope.row.devUStatus=='被借用'&&new Date(scope.row.returnTime).getTime()>new Date().getTime()"
            >提前归还</el-button>
            <el-button
            size="mini"
            style="background-color:orange"
            @click="cancelDevLend(scope.$index, scope.row)"
            v-show="scope.row.devStatus=='良好'&&scope.row.devUStatus=='被借用'&&new Date(scope.row.returnTime).getTime()<new Date().getTime()"
            >逾期归还</el-button>
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
import { mapMutations } from 'vuex';
import jwtDecode from 'jwt-decode';

export default {
  name: "noad-devLendRes",
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
returnStatus:"",
        }
      },
     
      formLabelWidth: "120px",
      queryContent: "",
      cannel:{
        data:{
              id:"",
              devUStatus:"",
              userName:"",
              borrowTime:"",
              returnTime:"",
              devReason  :"",
              devName:'',
              returnStatus:"",
        },
       

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
    // handleEdit(index,row){
    //   console.log(index,row);
    //   for(var i in this.dialog.data){
    //       this.dialog.data[i]=row[i];
    //   }
    //    this.dialog.data.dateSD=["",""]
    //   this.dialog.data.id=Number(this.dialog.data.id);
    //   this.dialog.data.dateSD[0]=this.dialog.data.borrowTime;
    //   this.dialog.data.dateSD[1]=this.dialog.data.returnTime;
    //   // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
    //   this.dialog.visible=true;
      
    //   this.dialog.dialogTitle="借用信息";
    //   this.dialog.submitTitle="确定";
    //   this.dialog.repealSubmitTitle="取消";
    //   this.dialog.url="http://localhost:8080/back/noad/handleDevLend"
    //   this.dialog.readonly=true;
    // },
  
    cancelDevLend(index,row){
      console.log(index,row);
       for(var i in this.cannel.data){
          this.cannel.data[i]=row[i];
      }
      this.cannel.data.id=Number(this.cannel.data.id);
      // 
            axios.post("http://localhost:8080/back/noad/cancelDevLend",this.cannel.data).then(
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

    


  },
  created(){
     let token =jwtDecode(localStorage.getItem("token"));
    if(token.loginName){
      this.queryContent=token.loginName;
    }
  },
  mounted() {
    this.SET_CHECK_MENU("/home/devLendRes");
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