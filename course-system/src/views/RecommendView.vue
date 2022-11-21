<template>
  <el-container style="background-color: rgba(255,250,250,20%)">
    <el-main>
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-table :data="tableData" stripe>
          <el-table-column prop="lessonid" label="课程编号" width="300">
          </el-table-column>
          <el-table-column prop="teacherid" label="教师编号" width="300">
          </el-table-column>
          <el-table-column prop="lessonname" label="课程名称" width="550">
          </el-table-column>
          <el-table-column prop="credit" label="学分数" width="100">
          </el-table-column>
          <el-table-column prop="hours" label="学时数" width="100">
          </el-table-column>
          <el-table-column prop="detail" label="详情">
            <el-button class="button" plain @click="changeVisible">点击查看</el-button>
            <el-dialog
              v-model="dialogvisible"
              title="课程详情"
              width="80%"
            >
              <el-collapse v-model="activeNames" @change="handleChange">
                <el-table :data="tableData_1" stripe>
                  <el-table-column prop="id" label="课程编号" width="200">
                  </el-table-column>
                  <el-table-column prop="homework" label="作业量" width="200">
                  </el-table-column>
                  <el-table-column prop="content" label="教授内容" width="300">
                  </el-table-column>
                  <el-table-column prop="teaching" label="教学质量" width="300">
                </el-table-column>
                  <el-table-column prop="comment" label="评语" width="300">
                  </el-table-column>
                </el-table>
              </el-collapse>
            </el-dialog>
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
import axios from 'axios'
export default {
  // name: 'HomeView',
  data() {
    return {
      isLoading: false,
      flags: false, // 控制使用
      position: {
        x: 0,
        y: 0
      },
      nx: '',
      ny: '',
      dx: '',
      dy: '',
      xPum: '',
      yPum: '',
      tableData: '',
      lessonid: '',
      teacherid: '',
      lessonname: '',
      credit: '',
      hours: '',
      teaname: '',
      coursename: '',
      tableData_1: '',
      dialogvisible: false,
      teachername: '',
      userid: '',
      lessonid_1: '',
      id: ''
    }
  },
  created () {
    const userid = this.$store.getters.getUserid
    this.postUserid(userid)
  },
  computed: {
    // usertype() {
    //   console.log(this.$store.state.usertype)
    //   return this.$store.state.usertype
    // }
  },
  components: {},
  methods: {
    getIP() {
      return this.$store.getters.getIP
    },
    getUserid() {
      console.log(this.$store.getters.getUserid)
      return this.$store.getters.getUserid()
    },
    // getcourse() {
    //   console.log(this.$store.getters.getcourse)
    //   return this.$store.getters.getcourse()
    // },
    async postUserid() {
      // post query并得到结果显示
      this.userid = this.$store.state.userid
      window.console.log(this.userid)
      window.console.log(this.getIP())
      this.tableData = []
      // const keyword = input
      window.console.log(1)
      await axios.get(this.getIP() + '/evaluation/reco/' + this.userid)
        .then(async response => {
          let a
          const b = []
          // eslint-disable-next-line prefer-const
          a = response.data
          window.console.log(a)
          for (const item in a) {
            window.console.log(a[item].id)
            window.console.log(111111)
            await axios.get(this.getIP() + '/student/allidname/' + a[item].teacherid).then(response => {
              // eslint-disable-next-line prefer-const
              // b[item] = response.data
              b.push(response.data)
              window.console.log('b:' + b)
            }).catch(function (error) {
              window.console.log(error)
            })
          }
          const length = a.length
          window.console.log(length)
          let item
          for (item = 0; item < length; item++) {
            // window.console.log(a[item])
            // window.console.log(item)
            // window.console.log('c[item]:')
            // window.console.log(c[item][0])
            // window.console.log('b[item]:')
            // window.console.log(b[item])
            const d = {
              lessonid: a[item].id,
              teacherid: a[item].teacherid,
              teachername: b[item],
              lessonname: a[item].name,
              credit: a[item].credit,
              hours: a[item].hours
            }
            window.console.log(d)
            this.tableData.push(d)
            // window.console.log(response.data)
            // for (const item in response.data) {
          //   this.tableData.push(response.data[item])
          //   window.console.log(item)
          }
          // window.console.log(a);
        })
        .catch(function (error) {
          window.console.log(error)
        })
    },
    gotoRegister() {
      this.$router.push('/register')
    },
    callback() {
      this.$router.go(-1)
    },
    onRefresh() {
      // window.location.reload();
      setTimeout((res) => {
        console.log(res)
        this.isLoading = false
      }, 1000)
    },
    down() {
      this.flags = true
      let touch
      if (event.touches) {
        touch = event.touches[0]
      } else {
        touch = event
      }
      this.position.x = touch.clientX
      this.position.y = touch.clientY
      this.dx = this.$refs.fu.offsetLeft
      this.dy = this.$refs.fu.offsetTop
    },
    move() {
      if (this.flags) {
        let touch
        if (event.touches) {
          touch = event.touches[0]
        } else {
          touch = event
        }
        this.nx = touch.clientX - this.position.x
        this.ny = touch.clientY - this.position.y
        this.xPum = this.dx + this.nx
        this.yPum = this.dy + this.ny
        const width = window.innerWidth - this.$refs.fu.offsetWidth // 屏幕宽度减去自身控件宽度
        const height = window.innerHeight - this.$refs.fu.offsetHeight // 屏幕高度减去自身控件高度
        this.xPum < 0 && (this.xPum = 0)
        this.yPum < 0 && (this.yPum = 0)
        this.xPum > width && (this.xPum = width)
        this.yPum > height && (this.yPum = height)
        // if (this.xPum >= 0 && this.yPum >= 0 && this.xPum<= width &&this.yPum<= height) {
        this.$refs.fu.style.left = this.xPum + 'px'
        this.$refs.fu.style.top = this.yPum + 'px'
        // }
        // 阻止页面的滑动默认事件
        document.addEventListener(
          'touchmove',
          function () {
            event.preventDefault()
          },
          false
        )
      }
    },
    // 鼠标释放时候的函数
    end() {
      this.flags = false
    },
    onClick() {
      // 在这里我是作为子组件来使用的
      this.$emit('click')
      this.$router.push('/personal')
    },
    changeVisible: function (e) {
      const courseid = e.currentTarget.parentElement.parentElement.parentElement.firstElementChild
        .firstElementChild.innerHTML.slice(7, 20)
      this.tableData_1 = []
      window.console.log(courseid)
      axios.get(this.getIP() + '/evaluation/query/lesid/' + courseid)
        .then(response => {
          window.console.log(response.data)
          for (const item in response.data) {
            this.tableData_1.push(response.data[item])
            window.console.log(item)
          }
        })
        .catch(function (error) {
          window.console.log(error)
        })
      this.dialogvisible = true
    }
  }
}
</script>
