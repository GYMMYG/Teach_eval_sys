<template>
<!--  <el-row :gutter="20" style="position: absolute; z-index: 10;">-->
<!--  可用但不好用-->
  <el-row :gutter="20" style="background: rgba(70,130,180,50%); margin-bottom: 0; padding-bottom: 30px;">
    <el-col :span="3"><div class="grid-content ep-bg-purple" />
      <router-link to="/" class="topic" style="text-decoration: none; font-size: x-large;" >教学评价系统</router-link></el-col>
    <el-col :span="16" style="margin-top: -25px; margin-left: 180px; margin-right: -150px;"><div class="grid-content ep-bg-purple" />
      <el-row :gutter="16">
        <el-col :span="3"><div class="grid-content ep-bg-purple" />
          <router-link to="/" style="text-decoration: none;">Home</router-link></el-col>
        <el-col :span="3"><div class="grid-content ep-bg-purple" />
          <router-link to="/about" style="text-decoration: none;">About</router-link></el-col>
        <el-col :span="3"><div class="grid-content ep-bg-purple" />
          <router-link to="/personal" style="text-decoration: none;">Personal</router-link></el-col>
        <el-col :span="4"><div class="grid-content ep-bg-purple" />
          <router-link to="/" style="text-decoration: none;">Contact Us</router-link></el-col>
        <el-col :span="10"><div class="grid-content ep-bg-purple" />
<!--          若用户未登录，logined==0-->
        <router-link to="/login" v-if="getLogined()===0" style="text-decoration: none; margin-left: 250px; background-color: #F56C6C; padding: 13px; border-radius: 20px;">
          Log in / 登录</router-link></el-col>

        <el-col :span="3" v-if="getLogined()===1" style="margin-left: -250px; margin-top: 35px;">欢迎,{{this.username}}</el-col>
        <img @click="exit" v-if="getLogined()===1" src="./assets/exit.png" alt style="width: 20px; height: 20px; margin-top: 35px;" />
      </el-row>
    </el-col>
<!--    <el-col :span="3"><div class="grid-content ep-bg-purple" />-->
<!--      <el-button type="danger" round size="large" style="font-size: medium;">Sign in / Register</el-button>-->
<!--      <router-link to="/" style="text-decoration: none; background-color: #F56C6C;">Contact Us</router-link>-->
<!--    </el-col>-->
  </el-row>
  <nav id="app" class="background">
    <nav style="align-items:center; margin-top: -30px;">
    </nav>
  </nav>
  <router-view/>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  //z-index: -1;
}
.background {
  background: transparent url("./assets/background1-0.jpg");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
  //overflow: visible;
  //-webkit-filter: blur(5px);
  z-index: -1;
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
export default {
  data() {
    return {
      logined: 0,
      ip: 'http://10.180.46.34:8080'
    }
  },
  components: {},
  mounted () {
    this.$store.commit('setLogined', 0)
    // this.username = this.$store.state.username
  },
  computed: {
    username() {
      return this.$store.state.username
    }
  },
  created () {
    this.setIP()
  },
  methods: {
    exit() {
      this.$store.commit('setLogined', 0)
      this.$router.push('/')
    },
    gotoRegister() {
      this.$router.push('/register')
    },
    getLogined() {
      // console.log(this.$store.getters.getLogined)
      return this.$store.getters.getLogined
    },
    setIP() {
      this.$store.commit('setIP', this.ip)
      console.log(this.$store.getters.getIP)
    }
    // getUserName() {
    //   console.log(this.$store.getters.getUserName)
    //   return this.$store.getters.getUserName
    // }
  }
}
</script>
