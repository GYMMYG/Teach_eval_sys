<template>
  <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
    <h1>登录</h1>
    <div class="content">
      <div class="identity" >
        <el-radio v-model="type" label="1">学生</el-radio>
        <el-radio v-model="type" label="2">教师</el-radio>
        <el-radio v-model="type" label="3">管理员</el-radio>
      </div>
      <div>{{message}}</div>
    </div>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-top: 30px;">
      <el-col :span="6"><div class="grid-content1">
        <span>账号</span>
<!--        <span v-if="type!==1">工号</span>-->
      </div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input v-model="userid" placeholder="学号/工号" required:true></el-input></div></el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
      <el-col :span="6"><div class="grid-content1"><span>密码</span></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input v-model="passwd" placeholder="密码" show-password></el-input></div></el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
      <el-col :span="6"><div class="grid-content">
        <el-button type="primary" v-model="operation" plain @click="postUserRegister">完成</el-button>
        <el-button type="primary" @click="gotoChangePassword">修改密码</el-button>
<!--        <el-button type="primary" v-model="operation" plain @click="getIP">完成</el-button>-->
      </div></el-col>
    </el-row>
  </div>
</template>

<style lang="scss">
* {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  align-items: center;
}
body{
  margin:0;
}
nav {
  padding: 30px;
  z-index: 1;
  //position: absolute;
  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-radio__input.is-checked + .el-radio__label {
  color: #fd3624 !important;
}
.el-radio__input.is-checked .el-radio__inner {
  background: #fd3624 !important;
  border-color: #fd3624 !important;
}
</style>

<script>
import axios from 'axios'
export default {
  name: 'HomeView',
  data() {
    return {
      type: 0,
      logined: 0,
      userid: '',
      passwd: '',
      operation: '',
      message: ''
    }
  },
  created () {
    // window.console.log(111111)
    // window.console.log(this.ip)
    // const ip = this.$store.state.ip
  },
  components: {},
  methods: {
    getIP() {
      return this.$store.getters.getIP
    },
    gotoPersonal() {
      this.$router.push('/personal')
    },
    // 强制更新测试文本信息框的值
    changeMessage() {
      this.$forceUpdate()
    },
    postUserRegister() {
      const ses = window.sessionStorage
      ses.setItem('userid', this.userid)
      window.console.log(sessionStorage.getItem('userid'))
      window.console.log(this.type)
      if (this.type === '1') {
        // axios.get('http://10.131.191.118:8080/student/login/' + this.userid + '/' + this.passwd).then(response => {
        axios.get(this.getIP() + '/student/login/' + this.userid + '/' + this.passwd).then(response => {
          window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '成功',
              message: '登录成功',
              type: 'success'
            })
            // let username=根据userid找到的userid
            axios.get(this.getIP() + '/student/idname/' + this.userid).then(response => {
              window.console.log(response.data)
              this.username = response.data
              this.$store.commit('setUserName', this.username)
              this.$store.commit('setUserid', this.userid)
              window.console.log(this.username)
            }).catch(function (error) {
              window.console.log(error)
            })
            this.$store.commit('setUserType', this.type)
            this.$store.commit('setLogined', 1)
            this.$router.push('personal')
            console.log(this.$store.state.logined)
            console.log(this.$store.state.username)
            console.log(this.$store.state.usertype)
          } else if (a === 2) {
            this.$notify.error({
              title: '登录失败',
              message: '密码不匹配'
            })
          } else {
            this.$notify.error({
              title: '登录失败',
              message: '未注册用户'
            })
            // ses.setItem('ready', 'false')
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }
      if (this.type === '2') {
        axios.get(this.getIP() + '/teacher/login/' + this.userid + '/' + this.passwd).then(response => {
          window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '成功',
              message: '登录成功',
              type: 'success'
            })
            // let username=根据userid找到的userid
            axios.get(this.getIP() + '/teacher/idname/' + this.userid).then(response => {
              window.console.log(response.data)
              this.username = response.data
              this.$store.commit('setUserName', this.username)
              this.$store.commit('setUserid', this.userid)
              window.console.log(this.username)
            }).catch(function (error) {
              window.console.log(error)
            })
            this.$store.commit('setUserType', this.type)
            this.$store.commit('setLogined', 1)
            this.$router.push('personal')
            console.log(this.$store.state.logined)
            console.log(this.$store.state.username)
            console.log(this.$store.state.usertype)
          } else if (a === 2) {
            this.$notify.error({
              title: '登录失败',
              message: '密码不匹配'
            })
          } else {
            this.$notify.error({
              title: '登录失败',
              message: '未注册用户'
            })
            // ses.setItem('ready', 'false')
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }
      if (this.type === '3') {
        axios.get(this.getIP() + '/administrator/login/' + this.userid + '/' + this.passwd).then(response => {
          window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '成功',
              message: '登录成功',
              type: 'success'
            })
            // let username=根据userid找到的userid
            this.$store.commit('setUserName', '管理员')
            this.$store.commit('setUserType', this.type)
            this.$store.commit('setLogined', 1)
            this.$router.push('personal')
            console.log(this.$store.state.logined)
            console.log(this.$store.state.username)
            console.log(this.$store.state.usertype)
          } else if (a === 2) {
            this.$notify.info({
              title: '失败',
              message: '密码不匹配',
              type: 'success'
            })
          } else {
            this.$notify.error({
              title: '登录失败',
              message: '未注册用户'
            })
            // ses.setItem('ready', 'false')
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }
    },
    gotoChangePassword() {
      // 使用Vue Router进行页面跳转
      this.$router.push('/change-password')
    }
  }
}
</script>
