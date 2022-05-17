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

      <el-table-column label="操作" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleAllocation(scope.$index, scope.row)"
            >分配角色</el-button
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
        <template>
  <el-checkbox-group v-model="checkList">
    <el-checkbox v-for="(item) in roleList"
      :key="item[0]"
      :label="item[1]"
    >
    </el-checkbox>

  </el-checkbox-group>
  </template>
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
  name: "ad-ur",
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
        diffFormatTableDataTmp:[],
        tmpObj:{
          userId:0,userName:"",roles:[]
        },
        roleList:[]
      },
      
      formLabelWidth: "120px",
      queryContent:"",
      dialog:{
        data:{
          userId:0,
          userName:"",
          roleId:0,
          roleName:"",
         
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
      .post("http://localhost:8080/back/ad/mtqueryr", this.selectform)
      .then(
        (response) => {
          if (response.data.code === 2000) {
            this.info.tableData=[];
            this.info.tableHead=response.data.data.tableHead;
            this.info.page=response.data.data.page;
            this.info.count=response.data.data.count;
            response.data.data.tableData.forEach(e => {
              this.info.tmpObj.userId=e.id;
              this.info.tmpObj.userName=e.userName;
              let role={roleId:0,roleName:""};
              let roles=[];
              let diffRolesFormatTmp="";
              e.roleEntities.forEach(el=>{
                role.roleId=el.id;
                role.roleName=el.roleName;
                diffRolesFormatTmp+=" "+el.roleName;
                roles.push(role);
              });
              this.info.tmpObj.roles=roles;
              this.info.diffFormatTableDataTmp.push(this.info.tmpObj);
              this.info.tmpObj.roles=diffRolesFormatTmp;
              this.info.tableData.push(this.info.tmpObj);
              this.info.tmpObj={}
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
    handleAllocation(index,row){
      axios.p
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.outCount=Number(this.dialog.data.outCount);
      this.dialog.data.smeId=Number(this.dialog.data.smeId);
      this.dialog.visible=true;
      this.dialog.dialogTitle="修改易耗品信息";
      this.dialog.submitTitle="修改";
      this.dialog.repealSubmitTitle="取消";
      this.dialog.url="http://localhost:8080/back/ad/updateOutRecord"

    },
    handleAdd(){
      this.dialog.visible=true;
      this.dialog.dialogTitle="添加易耗品信息";
      this.dialog.submitTitle="添加";
      this.dialog.repealSubmitTitle="重置";
      this.dialog.url="http://localhost:8080/back/ad/insertOutRecord"
    },
    handleDelete(index,row){
      console.log(index,row);
            axios.post(`http://localhost:8080/back/ad/deleteOutRecord/${row.id}`,).then(
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
            this.dialog.visible=false;
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
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$refs[formName].clearValidate();
            
        }else{
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$nextTick(() => {
               this.dialog.visible=false
              
            });
            this.$refs[formName].clearValidate();
        }
      
    },
    closeDialog(){
            for(var i in this.dialog.data){
              this.dialog.data[i]="";
            }
            this.$nextTick(() => {
              this.$refs['form'].clearValidate();
            });
            
    },
    ...mapMutations(["SET_CHECK_MENU"])

  },
  watch:{
    queryContent(){
      this.handlerQuery();
    },
  
  },
  mounted() {
    this.SET_CHECK_MENU("/home/ur");
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