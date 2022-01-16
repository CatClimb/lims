<template>
    <div>
              <el-form
        :model="formData"
        :rules="rules"
        ref="register_form_node"
        label-width="100px"
        class="demo-formData"
        
      >
      
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formData.userName" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="formData.password" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formData.sex" placeholder="请选择性别"  :disabled="isRead">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="微信" prop="weChat">
          <el-input v-model="formData.weChat" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="auth">
          <el-input v-model="formData.auth" :readonly="isRead"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-input v-model="formData.role" :readonly="isRead"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="submitForm('register_form_node')">修改</el-button>

        <el-button type="primary" @click="resetForm('register_form_node')"
          >重置</el-button
        >
      </div>
    </div>
</template>
<script>
export default {
    name:'ObjInfo',
    data() {
        return {
            formData:''
        }
    },
}
</script>
<style lang="css" scoped>
    .el-input{
        width:400px;
    }
</style>