<template>
  <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
    <h1>评价本课程情况</h1>
    <div style="margin-bottom: 10px">评分标准：可输入1-10，1表示很不满意，10表示非常满意</div>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-left: -300px">
      <el-col :span="1"><div class="grid-content1"><span>作业量</span></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input-number v-model="homework" :min="1" :max="10" @change="handleChange" /></div></el-col>
      <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-left: -300px">
      <el-col :span="1"><div class="grid-content1"><span>上课内容</span></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input-number v-model="content" :min="1" :max="10" @change="handleChange" /></div></el-col>
      <!--   -->
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-left: -300px">
      <el-col :span="1"><div class="grid-content1"><span>教学质量</span></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input-number v-model="teaching" :min="1" :max="10" @change="handleChange" /></div></el-col>
      <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-left: -300px">
      <el-col :span="1"><div class="grid-content1"><span>建议/评论</span></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light">
        <el-input v-model="comment" placeholder="还有其他的建议吗？" type="text" style="width: 700px" maxlength="80" show-word-limit></el-input></div></el-col>
    </el-row>
    <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
      <el-col :span="6"><div class="grid-content">
        <el-button type="primary" v-model="operation" plain @click="postEvaluate">完成</el-button>
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
// const ip = this.$store.state.ip
export default {
  name: 'HomeView',
  data() {
    return {
      homework: '',
      content: '',
      teaching: '',
      comment: ''
    }
  },
  components: {},
  methods: {
    getIP() {
      return this.$store.getters.getIP
    },
    gotoRegister() {
      this.$router.push('/register')
    },
    // 强制更新测试文本信息框的值
    changeMessage() {
      this.$forceUpdate()
    },
    postEvaluate() {
      const lesid = this.$store.state.courseid
      const stuid = this.$store.state.userid
      let a
      window.console.log(lesid)
      window.console.log(stuid)
      // 通过stuid和lesid取learnid
      // axios.get('http://192.168.144.195:8080/learn/query/stu_lesid/' + stuid + '/' + lesid).then(response => {
      axios.get(this.getIP() + '/learn/query/stu_lesid/' + stuid + '/' + lesid).then(response => {
        window.console.log(response.data)
        a = response.data
        window.console.log(a)
        const eva = {
          id: null,
          learnid: a,
          homework: parseInt(this.homework),
          content: parseInt(this.content),
          teaching: parseInt(this.teaching),
          comment: this.comment

        }
        window.console.log(eva)
        axios.post('http://10.131.191.118:8080/evaluation/add', eva).then(response => {
          window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '评价成功',
              message: '增加评价成功',
              type: 'success'
            })
            this.$router.push('/personal')
          } else {
            this.$notify.error({
              title: '评价失败',
              message: '评价已存在/未知错误'
            })
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }).catch(function (error) {
        window.console.log(error)
      })
    }
  }
}
</script>
