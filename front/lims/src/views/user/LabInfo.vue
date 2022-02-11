<template>
  <div>
    <el-table
      :data="info.tableData.responsedata"
      
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
        v-for="(item, index) of info.tableLabel"
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
        :total="recardCount"
      >
      </el-pagination>
  </div>
  </div>
</template>
<script>
export default {
  name: "LabInfo",
  data() {
    return {
      info: {
        tableLabel: [],
        tableData: {
          page: 1,
          pageSize: 10,
          recardCount: 0,
          responsedata: [],
        },
      },
      formLabelWidth: "120px",
      queryContent: "",
    };
  },
  computed: {
    selectform() {
      return {
        page: 1,
        pageSize: 20,
        id: this.queryContent,
        userName: this.queryContent,
        password: this.queryContent,
        name: this.queryContent,
        sex: this.queryContent,
        phone: this.queryContent,
        email: this.queryContent,
        weixin: this.queryContent,
        auth: this.queryContent,
        role: this.queryContent,
      };
    },
    methods: {
      handleSizeChange(val) {
        this.selectform.pageSize = val;
        this.handlerQuery();
      },
      handleCurrentChange(val) {
        this.selectform.page = val;
        this.handlerQuery();
      },
    },
  },
};
</script>
<style scoped>
</style>