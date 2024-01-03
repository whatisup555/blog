import request from '@/request'

export function login(account, password) {
  const data = {
    account,
    password
  }
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}

export function getUserInfo() {
  return request({
    url: '/users/currentUser',
    method: 'get'
  })
}

export function register(account, nickname, password,certificateType,certificateNumber,mobilePhoneNumber,registerCity) {
  const data = {
    account,
    nickname,
    password,
    certificateType,
    certificateNumber,
    mobilePhoneNumber,
    registerCity
  }
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function updateUserInfo(user) {
  return request({
    url: '/users/update',
    method: 'post',
    data: user
  })
}
export function updateMoneyInfo(startTime, endTime,city) {
  const data = {
    startTime,
    endTime,
    city
  }
  return request({
    url: '/search',
    method: 'post',
    data
  })
}

export function getMoneyInfo() {
  return request({
    url: '/searchend',
    method: 'get',
  })
}
