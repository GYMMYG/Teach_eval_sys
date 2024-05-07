<template>
  <nav id="main">
    <el-row style=" margin-top: 100px;">
      <el-col :span="15"><div class="grid-content ep-bg-purple" />
        <nav id="left">
          <el-row :gutter="20">
            <el-col :span="17" style="font-size: xxx-large"><div class="grid-content ep-bg-purple" />欢迎来到教学评价系统</el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8"><div class="grid-content ep-bg-purple" />
              <el-button type="danger" round size="large" style="font-size: medium;" @click="gotoLogin">
                <span v-if="getLogined()===0">Log in / 登录</span>
                <span v-if="getLogined()===1">已登录</span>
              </el-button></el-col>
            <el-col :span="10" style="margin-left: -100px; margin-top: 8px;"><div class="grid-content ep-bg-purple" />联系我们：西安交通大学软件学院</el-col>
          </el-row>
        </nav>
      </el-col>
      <el-col :span="6"><div class="grid-content ep-bg-purple-light" />
        <nav id="right">
          <el-image style="width: 500px; height: 300px; margin-left: -200px;" :src="require('../assets/background.jpeg')" fit="scale-down" />
        </nav>
      </el-col>
    </el-row>
  </nav>
</template>

<style lang="scss">
* {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
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
</style>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {},
  methods: {
    gotoLogin() {
      this.$router.push('/login')
    },
    getLogined() {
      // console.log(this.$store.getters.getLogined)
      return this.$store.getters.getLogined
    }
  }
}
</script>
