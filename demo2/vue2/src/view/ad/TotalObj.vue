<template>
  <div>

  <span id="date-time">
    <span style="background-color: white;"></span>
    <el-date-picker
          v-model="queryDateSD"
          type="daterange"
          unlink-panels
          range-separator="至"
          start-placeholder="项目开始日期"
          end-placeholder="项目结束日期"
          value-format="yyyy-MM-dd"
          >
        </el-date-picker>
  </span>
    <br/>
    <br/>
 
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

  </div>
</template>
<script>
import axios from 'axios'
import {mapMutations} from 'vuex';

export default {
  name: "ad-totalObj",
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
      queryDateSD:[],

      
      
    };
  },
  computed: {

    selectform(){
      return{
        page: this.info.page,
        pageSize: this.info.pageSize,
        startTime:this.queryDateSD[0],
        endTime:this.queryDateSD[1]
      }
    }
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
      axios.post(`http://localhost:8080/back/ad/recordObj`,this.selectform).then(
        (response)=>{
          if (response.data.code === 2000) {
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            this.info.tableData=response.data.data.tableData;
            this.$message({
                  message: response.data.msg,
                  type: "success",
                  center: true,
                });   
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

      ).finally(() => {
   this.$nextTick(() => {
      this.$refs.multipleTable.doLayout();
    });
  });
        
    },
        ...mapMutations(['SET_CHECK_MENU'])

  },
  watch:{
   
    queryDateSD(){
      this.handlerQuery();
    }

  },
  mounted() {
    this.SET_CHECK_MENU("/home/totalObj");
      this.handlerQuery();
  }
};
</script>
<style scoped>
.el-input,#date-time{
      height: 10%;
      width: 40%;
      margin: 0 10px 10px 0;
      /* padding: 10px;
      margin: 10px; */
      
      background-color: white;
    }
  
</style>