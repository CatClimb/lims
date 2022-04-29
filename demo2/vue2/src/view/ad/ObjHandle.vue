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

      <el-table-column label="操作" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="objPass(scope.$index, scope.row)"
            >批准</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="objNoPass(scope.$index, scope.row)"
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
      },
      formLabelWidth: "120px",
      queryContent:"",
     
    

      
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
    objPass(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.url="http://localhost:8080/back/ad/objPass"
      this.isPass();

    },
    objNoPass(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }

      this.dialog.url="http://localhost:8080/back/ad/objNoPass"
     this.isPass();

    },
   isPass(){
   
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
  
  },
  mounted() {
    
    this.SET_CHECK_MENU("/home/objHandle");
    this.handlerQuery();
  },
    created(){
    this.queryContent='审批中'
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