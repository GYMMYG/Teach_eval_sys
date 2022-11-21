import { createStore } from 'vuex'

export default createStore({
  state: {
    // 全局参数
    username: {},
    userImgURL: {},
    logined: {},
    usertype: {},
    query: {},
    userid: {},
    courseid: {},
    ip: {}
  },
  getters: {
    // get方法
    getUserName: state => state.username,
    getLogined: state => state.logined,
    getUserType: state => state.usertype,
    getUserid: state => state.userid,
    getQuery: state => state.query,
    getCourseid: state => state.courseid,
    getIP: state => state.ip
  },
  mutations: {
    // set方法
    setUserName(state, username) {
      state.username = username
    },
    setLogined(state, logined) {
      state.logined = logined
    },
    setUserType(state, usertype) {
      state.usertype = usertype
    },
    setQuery(state, query) {
      state.query = query
    },
    setUserid(state, userid) {
      state.userid = userid
    },
    setCourseid(state, courseid) {
      state.courseid = courseid
    },
    setIP(state, ip) {
      state.ip = ip
    }
  },
  actions: {
  },
  modules: {
  }
})

// this.$store.commit('setDemoValue', value);
// this.$store.getters. getDemoValue
