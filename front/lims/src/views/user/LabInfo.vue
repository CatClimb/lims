<template>
  <div>
    <el-table
      :data="info.tableData"
      class="container"
      height="750"
    >
      <el-table-column label="序号" min-width="30">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column
        min-width="100"
        show-overflow-tooltip
        v-for="(item, index) of info.tableHead"
        :key="index"
        :label="item[1]"
        :prop="item[0]"
      >
      </el-table-column>

      <!-- <el-table-column label="操作" fixed="right" width="150">
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
      </el-table-column> -->
    </el-table>
    <div style="position: fixed; right: 10px; bottom: 10px">
      <!-- <span class="demonstration">完整功能</span> -->
      <el-pagination
        background
        :pager-count="11"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryForm.page"
        :page-sizes="[3, 5, 10, 20, 50, 100]"
        :page-size="queryForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="info.recordCount"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: "LabInfo",
  data() {
    return {
      info: {
        page: 1,
        pageSize: 10,
        recordCount: 0,
        tableHead: [],
        tableData: [],
      },
      formLabelWidth: "120px",
      queryContent: "",
    };
  },
  computed: {
    queryForm() {
      return {
        page: this.info.page,
        pageSize: this.info.pageSize,
        
      
        lgTiming: this.queryContent,
        lgStatus: this.queryContent,
        lgSTime: this.queryContent,
        lgETime: this.queryContent,
        name: this.queryContent,
        lgType: this.queryContent,
        objName: this.queryContent,
      };
    },
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      this.handlerQuery();
    },
    handleCurrentChange(val) {
      this.page = val;
      this.handlerQuery();
    },
    handlerQuery(){
      console.log(this.queryForm)
      axios
        .post("http://localhost:8080/back/user/queryLabGdtTable", this.queryForm)
        .then(
          (response) => {
            if (response.data.code === 2000) {
              this.info.tableHead=response.data.data.tableHead;
              
              this.info.recordCount=response.data.data.count;
              this.info.tableData=response.data.data.tableData;
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
              message: error.response.msg,
              type: "error",
              center: true,
            });
          }
        );
    },
  },
  mounted() {
    this.handlerQuery();
  },
};
</script>
<style scoped>
</style>