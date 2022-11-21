<template>
  <el-container style="background-color: rgba(255,250,250,20%)">
    <el-main>
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-table :data="tableData" stripe>
          <el-table-column prop="lessonid" label="学生学号" width="300">
          </el-table-column>
          <el-table-column prop="teacherid" label="学生姓名" width="300">
          </el-table-column>
          <el-table-column prop="score" label="成绩" width="550">
            <el-input v-model="score" placeholder="Please input" />
          </el-table-column>
        </el-table>
      </el-collapse>
    </el-main>
  </el-container>
  <div
    class="callback float"
    @click="onClick"
    ref="fu"
  >
    <!-- <p @click="callback">返回</p> -->
    <img @click="callback" src="../assets/exit.png" alt style="width: 50px; margin-left: 1450px; margin-top: 420px" />
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
.dialog-footer button:first-child {
  margin-right: 10px;
}
el-table-column{
  background-color: rgba(255,250,250,20%);
}
</style>

<script>
export default {
  // name: 'HomeView',
  data() {
    return {
      score: ''
    }
  },
  created () {
    const query = this.$store.getters.getQuery
    this.postQuery(query)
  },
  computed: {
    // usertype() {
    //   console.log(this.$store.state.usertype)
    //   return this.$store.state.usertype
    // }
  },
  components: {},
  methods: {
    postQuery(query) {
      // post query并得到结果显示
    },
    gotoRegister() {
      this.$router.push('/register')
    },
    callback() {
      this.$router.go(-1)
    },
    onClick() {
      // 在这里我是作为子组件来使用的
      this.$emit('click')
      this.$router.push('/personal')
    }
  }
}
</script>
