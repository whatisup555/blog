import Vuex from 'vuex'
import Vue from 'vue'
import {getToken, setToken, removeToken} from '@/request/token'
import {login, getUserInfo, logout, register, updateUserInfo,updateMoneyInfo} from '@/api/login'

Vue.use(Vuex);

export default new Vuex.Store({
  articleId: '',
  startTime: '',
  endTime: '',
  city: '',
  moneydata: '',
  moneyResult:[],
  state: {
    id: '',
    account: '',
    name: '',
    avatar: '',
    certificateType: '',
    certificateNumber: '',
    mobilePhoneNumber: '',
    registerCity: '',
    userInfo: '',
    token: getToken(),
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_ACCOUNT: (state, account) => {
      state.account = account
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_CERTIFICATETYPE: (state, certificateType) => {
      state.certificateType = certificateType
    },
    SET_CERTIFICATENUMBER: (state, certificateNumber) => {
      state.certificateNumber = certificateNumber
    },
    SET_MOBILEPHONENUMBER: (state, mobilePhoneNumber) => {
      state.mobilePhoneNumber = mobilePhoneNumber
    },
    SET_REGISTERCITY: (state, registerCity) => {
      state.registerCity = registerCity
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
    }
  },
  actions: {
    login({commit}, user) {
      return new Promise((resolve, reject) => {
        login(user.account, user.password).then(data => {
          commit('SET_TOKEN', data.data['Oauth-Token'])
          setToken(data.data['Oauth-Token'])
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    getUserInfo({commit, state}) {
      let that = this
      return new Promise((resolve, reject) => {
        getUserInfo().then(data => {
          if (data.data) {
            commit('SET_ACCOUNT', data.data.account)
            commit('SET_NAME', data.data.nickname)
            commit('SET_AVATAR', data.data.avatar)
            commit('SET_ID', data.data.id)
            commit('SET_CERTIFICATETYPE', data.data.certificateType)
            commit('SET_CERTIFICATENUMBER', data.data.certificateNumber)
            commit('SET_MOBILEPHONENUMBER', data.data.mobilePhoneNumber)
            commit('SET_REGISTERCITY', data.data.registerCity)
            commit('SET_USERINFO', data.data.userInfo)
          } else {
            commit('SET_ACCOUNT', '')
            commit('SET_NAME', '')
            commit('SET_AVATAR', '')
            commit('SET_ID', '')
            commit('SET_CERTIFICATETYPE', '')
            commit('SET_CERTIFICATENUMBER', '')
            commit('SET_MOBILEPHONENUMBER', '')
            commit('SET_REGISTERCITY', '')
            commit('SET_USERINFO', '')
            removeToken()
          }
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 退出
    logout({commit, state}) {
      return new Promise((resolve, reject) => {
        logout().then(data => {

          commit('SET_TOKEN', '')
          commit('SET_ACCOUNT', '')
          commit('SET_NAME', '')
          commit('SET_AVATAR', '')
          commit('SET_ID', '')
          commit('SET_CERTIFICATETYPE', '')
          commit('SET_CERTIFICATENUMBER', '')
          commit('SET_MOBILEPHONENUMBER', '')
          commit('SET_REGISTERCITY', '')
          commit('SET_USERINFO', '')
          removeToken()
          resolve()

        }).catch(error => {
          reject(error)
        })
      })
    },
    // 前端 登出
    fedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ACCOUNT', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ID', '')
        commit('SET_CERTIFICATETYPE', '')
        commit('SET_CERTIFICATENUMBER', '')
        commit('SET_MOBILEPHONENUMBER', '')
        commit('SET_REGISTERCITY', '')
        commit('SET_USERINFO', '')
        removeToken()
        resolve()
      }).catch(error => {
        reject(error)
      })
    },
    register({commit}, user) {
      return new Promise((resolve, reject) => {
        register(user.account, user.nickname, user.password,user.certificateType,user.certificateNumber,user.mobilePhoneNumber,user.registerCity).then((data) => {
          commit('SET_TOKEN', data.data['Oauth-Token'])
          setToken(data.data['Oauth-Token'])
          resolve()
        }).catch((error) => {
          reject(error)
        })
      })
    },
    updateUserInfo({commit}, user) {
      return new Promise((resolve, reject) => {
        updateUserInfo(user).then((data) => {
          commit('SET_PASSWORD', user.password)
          commit('SET_MOBILEPHONENUMBER', user.mobilePhoneNumber)
          commit('SET_USERINFO', user.userInfo)
          resolve()
        }).catch((error) => {
          reject(error)
        })
      })
    },

    updateMoneyInfo({commit},money) {
      return new Promise((resolve, reject) => {
        updateMoneyInfo(money.startTime, money.endTime,money.city).then(data => {
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
})
