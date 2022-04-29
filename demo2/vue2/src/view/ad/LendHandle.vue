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
      >
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="lendPass(scope.$index, scope.row)"
            >批准</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="lendNoPass(scope.$index, scope.row)"
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
import { mapMutations } from 'vuex';

export default {
  name: "ad-lendHandle",
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
returnStatus:"",
devName:"",
devPrice:"",
devStatus:"",
      },
      },

      formLabelWidth: "120px",
      queryContent: "",
      dialog:{
        data:{
              id:"",
              devUStatus:"",
              userName:"",
              borrowTime:"",
              returnTime:"",
              devReason  :"",
              returnStatus:""
           
        },
        rules: {
          id:[
            { type: 'number', message: '请输入数字',  },

            {required:true,message:"请输入设备id"}
          ],
          devPrice:[
            {required:true,message:"请输入设备价格"}
          ],
          devUStatus:[
            {required:true,message:"请选择内容"}
        ],
          devStatus:[
            {required:true,message:"请选择内容"}
          ],
          
         
            // name
            //   {required:true,message:"请输入使用者姓名"}
            // ],
            // lgType:[
            //   {required:true,message:"请输入使用类别"}
            // ],
            // objName:[
              
            // ],
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
      .post("http://localhost:8080/back/ad/mulquerydl", this.selectform)
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
    lendPass(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.id=Number(this.dialog.data.id);
      // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
      this.dialog.url="http://localhost:8080/back/ad/lendPass"
      this.isPass();
    },
    lendNoPass(index,row){
      console.log(index,row);
      for(var i in this.dialog.data){
          this.dialog.data[i]=row[i];
      }
      this.dialog.data.id=Number(this.dialog.data.id);
      // this.dialog.data.lgDate=new Date(this.dialog.data.lgDate).
      this.dialog.url="http://localhost:8080/back/ad/lendNoPass"
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
    this.SET_CHECK_MENU("/home/lendHandle");
    this.handlerQuery();
  },
    created(){
    this.queryContent='申请中'
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