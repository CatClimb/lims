<template>
  <div>
    <el-input
    placeholder="请输入搜索内容"
    prefix-icon="el-icon-search"
    v-model="queryContent">
    
  </el-input>
  <el-date-picker
      v-model="queryDate"
      type="date"
      value-format="yyyy-MM-dd"
      placeholder="选择日期">
    </el-date-picker>
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
          <el-button size="mini" v-show="scope.row.userName==null" type="success" @click="handleOrder(scope.$index, scope.row)"
            >预约</el-button
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
  name: "noad-labOrder",
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
          id:"",labId:"",lgTiming:"",lgDate:"",lgStatus:"",userName:"",labType:""
        },
      },
      formLabelWidth: "120px",
      queryContent: "",
      queryDate:"",
     
      order:{
        data:{
            id:0,
            labId:"",
            lgTiming:"",
            lgDate:"",
            lgStatus:"",
            userName:"",
          
           
        },
    
       
      },
      
      
    };
  },
  computed: {
    selectform() {
      return {
        page: this.info.page,
        pageSize: this.info.pageSize,
        labId:this.queryContent,
        labType:this.queryContent,
        lgTiming:this.queryContent,
        lgStatus:this.queryContent,
        userName:this.queryContent,
        lgDate:this.queryDate,
        

        
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
      .post("http://localhost:8080/back/noad/mulquerylg", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            console.log("tableData")
            this.info.tableData=[];
            response.data.data.tableData.forEach(e => {
              console.log(e);
              console.log("this",this)
              e.labGdtEntities.forEach(el=>{
                console.log("el.id",el.id)
              this.info.tmpObj.id=el.id;
              this.info.tmpObj.labId=el.labId;
              this.info.tmpObj.lgTiming=el.lgTiming;
              this.info.tmpObj.lgDate=el.lgDate;
              this.info.tmpObj.lgStatus=el.lgStatus;
              this.info.tmpObj.userName=el.userName;
              this.info.tmpObj.labType=e.labType;
              
              this.info.tableData.push(this.info.tmpObj);
              this.info.tmpObj={}
              });
            });
            console.log(response.data.data)
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
    // handleEdit(index,row){
    //   console.log(index,row);
    //   for(var i in this.order.data){
    //       this.order.data[i]=row[i];
    //   }
    //   this.order.data.labId=Number(this.order.data.labId);
      
    //   // this.order.data.lgDate=new Date(this.order.data.lgDate).
    //   // this.order.visible=true;
    //   // this.order.dialogTitle="预约信息";
    //   // this.dialog.submitTitle="确定";
    //   // this.dialog.repealSubmitTitle="取消";
    //   this.order.data.lgStatus='被预约';
    //   this.order.url="http://localhost:8080/back/noad/orderLab"

    // },
    handleOrder(index,row){
      console.log(index,row);
      for(var i in this.order.data){
          this.order.data[i]=row[i];
      }
      this.order.data.labId=Number(this.order.data.labId);
      // this.order.data.lgStatus='被预约';
      // this.order.data.lgDate=new Date(this.order.data.lgDate).
      axios.post("http://localhost:8080/back/noad/orderLab",this.order.data).then(
 (response) => {
          if (response.data.code === 2000) {
            this.$message({
              message: response.data.msg,
              type: "success",
              center: true,
            });
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
     

    },
        submitForm(formName){
    this.$refs[formName].validate((valid) => {
      if (valid) {
          axios.post(this.order.url, this.order.data).then(
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
            this.order.visible=false;
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
    
   
    
    

        ...mapMutations(['SET_CHECK_MENU'])
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

    this.SET_CHECK_MENU("/home/labOrder");
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