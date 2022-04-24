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
       
      },
      formLabelWidth: "120px",
      queryContent: "",
      queryDate:"",
      order:{
        data:{
            id:6,
            labId:"",
            lgTiming:"",
            lgDate:"",
            lgStatus:"",
            userName:"",
            
            lgType:"",
           
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
        lgTiming:this.queryContent,
        lgDate:this.queryDate,
        lgStatus:this.queryContent,
        userName:this.queryContent,
        lgType:this.queryContent,
        
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
      .post("http://localhost:8080/back/noad/queryLabGdtTable", this.selectform)
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
    handleOrder(index,row){
      console.log(index,row);
      for(var i in this.order.data){
          this.order.data[i]=row[i];
      }
      this.order.data.labId=Number(this.order.data.labId);
      this.order.data.lgStatus='被预约';
      // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
      axios.post("http://localhost:8080/back/noad/updateLabGdt",this.order.data).then(
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