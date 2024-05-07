<template>
  <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
    <h1>修改密码</h1>
    <div class="content">
      <div class="identity">
        <el-radio v-model="userType" label="student">学生</el-radio>
        <el-radio v-model="userType" label="teacher">教师</el-radio>
      </div>
      <div>{{message}}</div>
    </div>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-top: 30px;">
      <el-col :span="6"><div class="grid-content1"><span>用户ID</span></div></el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <el-input v-model="userId" placeholder="请输入用户ID" required></el-input>
        </div>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
      <el-col :span="6"><div class="grid-content1"><span>旧密码</span></div></el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <el-input v-model="oldPassword" placeholder="请输入旧密码" show-password></el-input>
        </div>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
      <el-col :span="6"><div class="grid-content1"><span>新密码</span></div></el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <el-input v-model="newPassword" placeholder="请输入新密码" show-password></el-input>
        </div>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
      <el-col :span="6">
        <div class="grid-content">
          <el-button type="primary" @click="changePassword">提交修改</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      userType: '',
      userId: '',
      oldPassword: '',
      newPassword: '',
      message: ''
    }
  },
  methods: {
    getIP() {
      return this.$store.getters.getIP
    },
    changePassword() {
      const baseURL = this.getIP() // 确保此方法返回正确的API基地址
      // 根据用户类型构建URL，同时包括用户ID、旧密码和新密码作为路径变量
      const endpoint = `${baseURL}/${this.userType === 'student' ? 'student' : 'teacher'}/change-password/${this.userId}/${this.oldPassword}/${this.newPassword}`

      axios.get(endpoint) // 注意: 看起来你的后端是通过GET请求处理的
        .then(response => {
          console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify({
              title: '成功',
              message: '密码修改成功',
              type: 'success'
            })
          } else {
            this.$notify.error({
              title: '错误',
              message: '密码修改失败'
            })
          }
        })
        .catch(error => {
          console.log('请求失败:', error)
          this.$notify.error({
            title: '请求错误',
            message: `请求失败: ${error.message}`
          })
        })
    }
  }
}
</script>

<style scoped>
/* 根据需要添加或修改样式 */
</style>
