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
    <el-dialog :title="dialog.dialogTitle" :visible.sync="dialog.visible">
      <el-form
        :model="dialog.data"
        :rules="dialog.rules"
        ref="form"
        label-width="100px"
        class="demo-formData"
      >
        <el-form-item label="实验室编号" prop="labId">
          <el-input v-model.number="dialog.data.labId"></el-input>
        </el-form-item>
        <el-form-item label="实验室类别" prop="labType">
          <el-input  v-model="dialog.data.labType"></el-input>
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

export default {
  name: "LabInfo",
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
      dialog:{
        data:{
          id:0,
          labId:"",
          labType:""
        },
        rules: {
           labId: [
             { required: true, message: '请输入编号',  },
            { type: 'number', message: '请输入数字',  }
          ],
           labType: [
            { type: 'string', required: true, message: '请输入类别',  }
          ],
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
        page: 1,
        pageSize: 10,
        labId:this.queryContent,
        labType:this.queryContent
      };
    },
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
    handlerQuery(){
    console.log(this.selectform)
    axios
      .post("http://localhost:8080/back/ad/queryLabTable", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableHead=response.data.data.tableHead;
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
            message: error.response.msg,
            type: "error",
            center: true,
          });
        }
      );
    },
    handleEdit(index,row){
      console.log(index,row);
      this.dialog.data=row;
      this.dialog.data.labId=Number(this.dialog.data.labId);
      this.dialog.visible=true;
      this.dialog.dialogTitle="修改实验室";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateLab"

    },
    handleAdd(){
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加实验室";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertLab"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteLab/${row.id}`,).then(
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
            message: error.response.msg,
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
            this.$refs[formName].resetFields();
        }else{
            this.dialog.visible=false
        }
      
    },
  },
  watch:{
    queryContent(){
                  this.handlerQuery();
                }
  },
  mounted() {
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