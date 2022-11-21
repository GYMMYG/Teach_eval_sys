<template>
<!--  学生界面-->
  <div>
    <div id="student" v-if="usertype==='1'">
<!--      <router-link to="/evaluate">search</router-link>-->
<!--      <router-link to="/search">search</router-link>-->
      <el-space wrap style="margin-top: 100px;">
        <el-card :key="1" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>查看已修课程</div>
            </div>
          </template>
            <el-button class="button" text @click="changeVisible_stu_0">点击查看</el-button>
            <el-dialog
              v-model="dialogvisible_stu_0"
              title="已修课程"
              width="80%"
            >
            <el-collapse v-model="tableData_yixiu" @change="handleChange">
              <el-table :data="tableData_yixiu" stripe>
                <el-table-column prop="lessonid" label="课程编号" width="200">
                </el-table-column>
                <el-table-column prop="teacherid" label="教师编号" width="200">
                </el-table-column>
                <el-table-column prop="lessonname" label="课程名称" width="270">
                </el-table-column>
                <el-table-column prop="credit" label="学分数" width="100">
                </el-table-column>
                <el-table-column prop="hours" label="学时数" width="100">
                </el-table-column>
                <el-table-column prop="score_1" label="成绩" width="100">
                </el-table-column>
                <el-table-column prop="estimated_1" label="是否已评教" width="80">
                </el-table-column>
                <el-table-column prop="estimate" label="评教">
                  <el-button plain :disabled="is_disabled" @click="gotoEvaluate">我要评教</el-button>
                </el-table-column>
              </el-table>
            </el-collapse>
          </el-dialog>
        </el-card>
        <el-card :key="2" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%)">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>查询课程</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_stu_1">点击查询</el-button>
          <el-dialog
            v-model="dialogvisible_stu_1"
            title="请选择查询条件"
            width="80%"
          >
<!--          <el-select v-model="teaid" class="m-2" placeholder="Select" size="large"-->
<!--                     sc>-->
<!--            <el-option-->
<!--              v-for="item in options_1"-->
<!--              :key="item.value"-->
<!--              :label="item.label"-->
<!--              :value="item.value"-->
<!--            />-->
<!--          </el-select>-->
<!--          <el-select v-model="coursename" class="m-2" placeholder="Select" size="large"-->
<!--                     style="margin-right: 50px">-->
<!--            <el-option-->
<!--              v-for="item in options_2"-->
<!--              :key="item.value"-->
<!--              :label="item.label"-->
<!--              :value="item.value"-->
<!--            />-->
<!--          </el-select>-->
            <el-input v-model="teaname" placeholder="教师名称，例：张三" style="margin-bottom: 15px"/>
            <el-input v-model="coursename" placeholder="课程名称，例：软件工程" style="margin-bottom: 15px"/>
            <el-button plain @click="postQuery">提交</el-button>
          </el-dialog>
        </el-card>
        <el-card :key="3" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-left: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>课程推荐</div>
            </div>
          </template>
          <el-button class="button" text @click="poseRecommendation">点击查询</el-button>
        </el-card>
      </el-space>
    </div>
    <!--  教师界面-->
    <div id="teacher" v-if="usertype==='2'">
      <el-space wrap style="margin-top: 100px;">
        <el-card :key="1" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>查看教授课程</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_tea_0">点击查看</el-button>
          <el-dialog
            v-model="dialogvisible_tea_0"
            title="教授课程"
            width="80%"
          >
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-table :data="tableData_tea" stripe @row-click="gotoScore">
                <el-table-column prop="id" label="课程编号" width="200">
                </el-table-column>
                <el-table-column prop="teacherid" label="教师编号" width="200">
                </el-table-column>
                <el-table-column prop="name" label="课程名称" width="300">
                </el-table-column>
                <el-table-column prop="credit" label="学分数" width="100">
                </el-table-column>
                <el-table-column prop="hours" label="学时数" width="100">
                </el-table-column>
<!--                <el-table-column prop="scored_1" label="是否已评分" width="100">-->
<!--                </el-table-column>-->
<!--                <el-table-column prop="estimate" label="评分">-->
<!--                  <el-button plain v-if="scored===false" @click="gotoScore(scope.row)">我要打分</el-button>-->
<!--                </el-table-column>-->
              </el-table>
            </el-collapse>
<!--            <template #footer>-->
<!--            <span class="dialog-footer">-->
<!--              <el-button @click="dialogVisible = false">Cancel</el-button>-->
<!--              <el-button type="primary" @click="dialogVisible = false"-->
<!--              >Confirm</el-button-->
<!--              >-->
<!--            </span>-->
<!--            </template>-->
          </el-dialog>
        </el-card>
        <el-card :key="2" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>查看课程</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_tea_1">点击查询</el-button>
          <el-dialog
            v-model="dialogvisible_tea_1"
            title="请选择查询条件"
            width="80%"
          >

            <el-input v-model="teaname" placeholder="教师名称，例：张三" style="margin-bottom: 15px"/>
            <el-input v-model="coursename" placeholder="课程名称，例：软件工程" style="margin-bottom: 15px"/>
            <el-button plain @click="postQuery">提交</el-button>
          </el-dialog>
        </el-card>
      </el-space>
    </div>
    <!--  管理员界面-->
    <div id="admin" v-if="usertype==='3'">
      <el-space wrap style="margin-top: 100px;">
        <el-card :key="1" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>注册/修改账户</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_adm_0">点击注册/修改</el-button>
          <el-dialog
            v-model="dialogvisible_adm_0"
            title="注册/修改账户"
            width="80%"
          >
            <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
              <h1>注册/修改账户</h1>
              <div class="content">
                <div class="identity" >
                  <el-radio v-model="type" label="1">学生</el-radio>
                  <el-radio v-model="type" label="2">教师</el-radio>
                </div>
                <div>{{message}}</div>
              </div>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-top: 30px;">
                <el-col :span="6"><div class="grid-content1">
                  <span>学号/工号</span>
<!--                  <span v-if="type===2">工号</span>-->
                </div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="userid" placeholder="学号/工号" required:true></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>姓名</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="username" placeholder="用户名"></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>密码</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="passwd" placeholder="密码" show-password></el-input></div></el-col>
                <!--   -->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>学院</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="college" placeholder="学院"></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>联系方式</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="telephone" placeholder="联系方式" maxlength="11" minlength="11" show-word-limit></el-input></div></el-col>
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
                <el-col :span="6"><div class="grid-content">
                  <el-button type="primary" v-model="operation" plain @click="postUserRegister">完成</el-button>
                </div></el-col>
              </el-row>
            </div>
          </el-dialog>
        </el-card>
        <el-card :key="2" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>注册课程</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_adm_1">点击注册</el-button>
          <el-dialog
            v-model="dialogvisible_adm_1"
            title="注册课程"
            width="80%"
          >
            <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
              <h1>注册课程</h1>
              <div class="content">
                <div>{{message}}</div>
              </div>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-top: 30px;">
                <el-col :span="6"><div class="grid-content1">
                  <span>课程编号</span>
                  <!--                  <span v-if="type===2">工号</span>-->
                </div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="lessonid" placeholder="课程编号" required:true></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>教师编号</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="teacherid" placeholder="教师编号"></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>课程名称</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="lessonname" placeholder="课程名称"></el-input></div></el-col>
                <!--   -->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>学分数</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="credit" placeholder="学分数"></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>学时数</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="hours" placeholder="学时数"></el-input></div></el-col>
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
                <el-col :span="6"><div class="grid-content">
                  <el-button type="primary" v-model="operation" plain @click="postCourseRegister">完成</el-button>
                </div></el-col>
              </el-row>
            </div>
          </el-dialog>
        </el-card>
        <el-card :key="2" class="box-card" style="width: 250px; background-color: rgba(255,250,250,50%); margin-right: 50px;">
          <template #header>
            <el-image style="width: 400px; height: 150px; margin-left: -100px;margin-top: -30px;" :src="require('../assets/background.jpeg')" fit="fill" />
            <div class="card-header">
              <div>增加选课记录</div>
            </div>
          </template>
          <el-button class="button" text @click="changeVisible_adm_2">点击增加</el-button>
          <el-dialog
            v-model="dialogvisible_adm_2"
            title="增加选课记录"
            width="80%"
          >
            <div class="register" style="background-color: rgba(255,250,250,20%); height: 500px; padding-top: 30px">
              <h1>增加选课记录</h1>
              <div class="content">
                <div>{{message}}</div>
              </div>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle" style="margin-top: 30px;">
                <el-col :span="6"><div class="grid-content1">
                  <span>记录编号</span>
                  <!--                  <span v-if="type===2">工号</span>-->
                </div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="learnid" placeholder="记录编号" required:true></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>学生编号</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="stuid" placeholder="学生编号"></el-input></div></el-col>
                <!--      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>课程编号</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="lesid" placeholder="课程编号"></el-input></div></el-col>
                <!--   -->
              </el-row>
<!--              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">-->
<!--                <el-col :span="6"><div class="grid-content1"><span>评教记录编号</span></div></el-col>-->
<!--                <el-col :span="6"><div class="grid-content bg-purple-light">-->
<!--                  <el-input v-model="evaid" placeholder="评教记录编号"></el-input></div></el-col>-->
<!--                &lt;!&ndash;      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>&ndash;&gt;-->
<!--              </el-row>-->
              <el-row type="flex" class="row-bg1 row-gap" justify="center" align="middle">
                <el-col :span="6"><div class="grid-content1"><span>得分</span></div></el-col>
                <el-col :span="6"><div class="grid-content bg-purple-light">
                  <el-input v-model="score" placeholder="得分"></el-input></div></el-col>
              </el-row>
              <el-row type="flex" class="row-bg1 row-gap" justify="center" style="margin-top: 30px">
                <el-col :span="6"><div class="grid-content">
                  <el-button type="primary" v-model="operation" plain @click="postLearnInfo">完成</el-button>
                </div></el-col>
              </el-row>
            </div>
          </el-dialog>
        </el-card>
      </el-space>
    </div>
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
</style>

<script>
import axios from 'axios'
export default {
  name: 'HomeView',
  data() {
    return {
      type: 0,
      type_register: 1,
      text: '',
      dialogvisible_stu_0: false,
      dialogvisible_stu_1: false,
      dialogvisible_tea_0: false,
      dialogvisible_tea_1: false,
      dialogvisible_adm_0: false,
      dialogvisible_adm_1: false,
      dialogvisible_adm_2: false,
      username: '',
      logined: 0,
      // userid: '',
      passwd: '',
      college: '',
      telephone: '',
      operation: '',
      lessonid: '',
      teacherid: '',
      lessonname: '',
      credit: '',
      hours: '',
      learnid: '',
      stuid: '',
      lesid: '',
      evaid: '',
      score_1: '',
      score: '',
      estimated: '',
      estimated_1: '',
      // 用于查询
      teaname: '',
      coursename: '',
      scored: '',
      query: '',
      tableData_yixiu: '',
      tableData_tea: '',
      userid: '',
      courseid: '',
      scored_1: '',
      is_disabled: false
    }
  },
  computed: {
    usertype() {
      console.log(this.$store.state.usertype)
      return this.$store.state.usertype
    },
    // userid() {
    //   console.log(this.$store.state.userid)
    //   return this.$store.state.userid
    // },
    options_1() {
      return [
        {
          value: -1,
          label: 'default'
        },
        {
          value: '老师1',
          label: '老师1'
        },
        {
          value: '老师2',
          label: '老师2'
        },
        {
          value: '老师3',
          label: '老师3'
        },
        {
          value: '老师4',
          label: '老师4'
        }
      ]
    },
    options_2() {
      return [
        {
          value: -1,
          label: 'default'
        },
        {
          value: '计算机组成原理',
          label: '计算机组成原理'
        },
        {
          value: '计算机网络',
          label: '计算机网络'
        },
        {
          value: '算法设计与应用',
          label: '算法设计与应用'
        },
        {
          value: '数据结构',
          label: '数据结构'
        }
      ]
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
    gotoEvaluate: function (e) {
      // 获得点击的记录的courseid
      const courseid = e.currentTarget.parentElement.parentElement.parentElement.firstElementChild
        .firstElementChild.innerHTML.slice(7, 20)
      const useid = this.getUserid()
      // get: userid有没有评过courseid，没评过就跳转
      axios.get(this.getIP() + '/learn/query/stu_lesid/' + useid + '/' + courseid).then(response => {
        let a
        // eslint-disable-next-line prefer-const
        a = response.data
        window.console.log(a)
        axios.get(this.getIP() + '/evaluation/query/boolean/learnid/' + a).then(response => {
          window.console.log(response.data)
          let b
          // eslint-disable-next-line prefer-const
          b = response.data
          // eslint-disable-next-line no-empty
          if (b === false) {
            // this.is_disabled = false
            this.$store.commit('setCourseid', courseid)
            this.$router.push('/evaluate')
          } else {
            // this.is_disabled = true
            this.$notify.info({
              title: '已评教',
              message: '已存在评教记录'
            })
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }).catch(function (error) {
        window.console.log(error)
      })
    },
    gotoScore(row) {
      // const query = [row.teaname, row.lessonname]
      // this.$store.commit('setQuery', query)
      this.$router.push('/score')
    },
    // 强制更新测试文本信息框的值
    changeMessage() {
      this.$forceUpdate()
    },
    getUserid() {
      // console.log(this.$store.getters.getUserid)
      return this.$store.getters.getUserid
    },
    async changeVisible_stu_0(dialogvisible) {
      this.userid = this.$store.state.userid
      this.tableData_yixiu = []
      await axios.get(this.getIP() + '/learn/query/stuid/' + this.userid)
        .then(response => {
          // window.console.log(response.data)
          setTimeout(async () => {
            let a
            // let b
            const b = []
            const c = []
            // let c
            // eslint-disable-next-line prefer-const
            a = response.data
            // window.console.log(a)
            for (const item in a) {
              // window.console.log(a[item].id)
              // window.console.log(111111)
              await axios.get(this.getIP() + '/evaluation/query/boolean/learnid/' + a[item].id).then(response => {
                // eslint-disable-next-line prefer-const
                // b[item] = response.data
                b.push(response.data)
                // window.console.log('b:' + b)
              }).catch(function (error) {
                window.console.log(error)
              })
              await axios.get(this.getIP() + '/lesson/query/lesid/' + a[item].lesid).then(response => {
                // window.console.log(response.data)
                c.push(response.data)
                // window.console.log(222)
                // window.console.log(c)
              }).catch(function (error) {
                window.console.log(error)
              })
              // window.console.log('end')
            }
            // a.reverse()
            const length = a.length
            // window.console.log(length)
            let item

            for (item = 0; item < length; item++) {
              const d = {
                lessonid: a[item].lesid,
                teacherid: c[item][0].teacherid,
                lessonname: c[item][0].name,
                credit: c[item][0].credit,
                hours: c[item][0].hours,
                score_1: a[item].score,
                estimated_1: b[item]
              }
              this.estimated = b[item]
              window.console.log(this.estimated)
              this.tableData_yixiu.push(d)
            }
          })
          this.$router.replace('/personal')
        })
        .catch(function (error) {
          window.console.log(error)
        })
      this.dialogvisible_stu_0 = true
    },
    changeVisible_stu_1(dialogvisible) {
      this.dialogvisible_stu_1 = true
    },
    changeVisible_tea_0(dialogvisible) {
      this.userid = this.$store.state.userid
      // window.console.log(this.userid)
      this.tableData_tea = []
      axios.get(this.getIP() + '/lesson/query/teacherid/' + this.userid).then(response => {
        // window.console.log(response.data)
        for (const item in response.data) {
          // window.console.log(response.data[item])
          this.tableData_tea.push(response.data[item])
          // window.console.log(item)
        }
        this.$router.replace('/personal')
      }).catch(function (error) {
        window.console.log(error)
      })
      this.dialogvisible_tea_0 = true
    },
    changeVisible_tea_1(dialogvisible) {
      this.dialogvisible_tea_1 = true
    },
    changeVisible_adm_0(dialogvisible) {
      this.dialogvisible_adm_0 = true
    },
    changeVisible_adm_1(dialogvisible) {
      this.dialogvisible_adm_1 = true
    },
    changeVisible_adm_2(dialogvisible) {
      this.dialogvisible_adm_2 = true
    },
    // 学生
    // 管理员
    postUserRegister() {
      // post注册user信息:type,username,logined,userid,passwd,college,telephone
      const ses = window.sessionStorage
      ses.setItem('id', this.id)
      // window.console.log(sessionStorage.getItem('id'))
      const userinfo = {
        id: parseInt(this.userid),
        name: this.username,
        password: this.passwd,
        college: this.college,
        telephone: parseInt(this.telephone)
      }
      // window.console.log(userinfo)
      if (this.type === '1') {
        axios.post(this.getIP() + '/student/add', userinfo).then(response => {
          // window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '注册成功',
              message: '注册用户成功',
              type: 'success'
            })
            // ses.setItem('ready','true');
            this.$router.replace('/personal')
          } else if (a === 2) {
            this.$notify.info({
              title: '注册失败',
              message: '学生学号已存在'
            })
            this.$router.replace('/personal')
          } else if (a === 3) {
            this.$notify.info({
              title: '注册失败',
              message: '属性不全'

            })
          } else {
            this.$notify.error({
              title: '注册失败',
              message: '未知错误'
            })
            // ses.setItem('ready','false');
            this.$router.replace('/personal')
          }
        }).catch(function (error) {
          window.console.log(error)
        })
      }
      if (this.type === '2') {
        axios.post(this.getIP() + '/teacher/add', userinfo).then(response => {
          // window.console.log(response.data)
          const a = response.data
          if (a === 1) {
            this.$notify.info({
              title: '注册成功',
              message: '注册教师用户成功',
              type: 'success'
            })
            // ses.setItem('ready','true');
            this.$router.replace('/personal')
          } else if (a === 2) {
            this.$notify.info({
              title: '注册失败',
              message: '教师工号已存在'
            })
          } else if (a === 3) {
            this.$notify.info({
              title: '注册失败',
              message: '属性不全'

            })
          } else {
            this.$notify.error({
              title: '注册失败',
              message: '未知错误'
            })
            // ses.setItem('ready','false');
          }
          this.$router.replace('/personal')
        }).catch(function (error) {
          window.console.log(error)
        })
      }
      this.dialogvisible_adm_0 = false
    },
    postCourseRegister() {
      // post注册course信息:lessonid,teacherid,lessonname,credit,hours
      const lesson = {
        id: parseInt(this.lessonid),
        teacherid: parseInt(this.teacherid),
        name: this.lessonname,
        credit: parseInt(this.credit),
        hours: parseInt(this.hours)
      }
      // window.console.log(lesson)
      axios.post(this.getIP() + '/lesson/add', lesson).then(response => {
        // window.console.log(response.data)
        const a = response.data
        if (a === 1) {
          this.$notify.info({
            title: '增加成功',
            message: '增加课程成功',
            type: 'success'
          })
          this.$router.push('/personal')
        } else if (a === 0) {
          this.$notify.info({
            title: '增加失败',
            message: '未知错误'
          })
          this.$router.push('/personal')
        } else if (a === 2) {
          this.$notify.info({
            title: '增加失败',
            message: 'id已存在'
          })
          this.$router.push('/personal')
        } else if (a === 3) {
          this.$notify.info({
            title: '增加失败',
            message: '输入属性不全'
          })
          this.$router.replace('/personal')
        } else if (a === 4) {
          this.$notify.info({
            title: '增加失败',
            message: '教师工号不存在'
          })
          this.$router.replace('/personal')
        }
        this.$router.replace('/personal')
      }).catch(function (error) {
        window.console.log(error)
        this.$notify.error({
          title: '增加失败',
          message: '程序错误'
        })
      })
      this.dialogvisible_adm_1 = false
    },
    postLearnInfo() {
      // post注册learn信息: learnid,stuid,lesid,evaid,score
      const learn = {
        id: parseInt(this.learnid),
        stuid: parseInt(this.stuid),
        lesid: parseInt(this.lesid),
        // evaid: parseInt(this.evaid),
        score: parseInt(this.score)

      }
      // window.console.log(learn)
      axios.post(this.getIP() + '/learn/add', learn).then(response => {
        // window.console.log(response.data)
        const a = response.data
        if (a === 1) {
          this.$notify.info({
            title: '增加成功',
            message: '增加选课成功',
            type: 'success'
          })
          // this.$router.push('/person')
        } else if (a === 2) {
          this.$notify.info({
            title: '增加失败',
            message: '该选课记录已经存在'
          })
        } else if (a === 3) {
          this.$notify.info({
            title: '增加失败',
            message: '输入属性不全'
          })
        } else if (a === 4) {
          this.$notify.info({
            title: '增加失败',
            message: '学号不存在'
          })
        } else if (a === 5) {
          this.$notify.info({
            title: '增加失败',
            message: '课程编号不存在'
          })
        } else {
          this.$notify.info({
            title: '增加失败',
            message: '未知错误'
          })
        }

        this.$router.replace('/personal')
      }).catch(function (error) {
        window.console.log(error)
        this.$notify.error({
          title: '增加失败',
          message: '程序错误'
        })
      })
      this.dialogvisible_adm_2 = false
    },
    postQuery() {
      // 不post查询属性: teaname, coursename；存储全局query
      // const query = [this.teaname, this.coursename]
      if (this.teaname === '') { this.teaname = -1 }
      if (this.coursename === '') { this.coursename = -1 }
      const query = {
        teaname: this.teaname,
        coursename: this.coursename
      }
      this.$store.commit('setQuery', query)
      this.$router.push('/search')
    },
    poseRecommendation() {
      // post query
      this.$router.push('/recommend')
    }
  }
}
</script>
