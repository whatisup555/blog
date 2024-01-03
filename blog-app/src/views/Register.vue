<template>
  <div id="register" v-title data-title="注册 - 好去处">
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
          <source src="../../static/vedio/sea.mp4" type="video/mp4">
      </video>-->

    <div class="me-login-box me-login-box-radius">
      <h1>好去处 注册</h1>

      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item prop="account">
          <el-input placeholder="用户名" v-model="userForm.account"></el-input>
        </el-form-item>

        <el-form-item prop="nickname">
          <el-input placeholder="姓名" v-model="userForm.nickname"></el-input>
        </el-form-item>

        <el-form-item prop="certificateType">
          <el-input placeholder="居民身份证" v-model="userForm.certificateType"></el-input>
        </el-form-item>

        <el-form-item prop="certificateNumber">
          <el-input placeholder="证件号码" v-model="userForm.certificateNumber"></el-input>
        </el-form-item>

        <el-form-item prop="mobilePhoneNumber">
          <el-input placeholder="手机号码" v-model="userForm.mobilePhoneNumber"></el-input>
        </el-form-item>

        <el-form-item>
          <el-cascader
            placeholder="注册城市"
            size = "small"
            :options="pcaTextArr"
            v-model="userForm.registerCity">
          </el-cascader>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" v-model="userForm.password"></el-input>
        </el-form-item>

        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click.native.prevent="register('userForm')">注册</el-button>
        </el-form-item>
      </el-form>

      <div class="me-login-design">
        <p>Designed by
          <strong>
            <router-link to="/" class="me-login-design-color">好去处</router-link>
          </strong>
        </p>
      </div>

    </div>
  </div>
</template>

<script>
  import {register} from '@/api/login'
  import {provinceAndCityData,pcTextArr,regionData,pcaTextArr,codeToText} from "element-china-area-data"
  export default {
    name: 'Register',
    data() {
      return {
        pcaTextArr,
        selectedOptions: [],
        userForm: {
          account: '',
          nickname: '',
          password: '',
          certificateType: '',
          certificateNumber: '',
          mobilePhoneNumber: '',
          registerCity: ''
        },
        rules: {
          account: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          nickname: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          //要求certificateType为1或者0两种
          certificateType: [
            {required: false, message: '居民身份证', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          certificateNumber: [
            {required: false, message: '请输入证件号码', trigger: 'blur'},
            {max: 18, message: '18个字符', trigger: 'blur'}
          ],
          mobilePhoneNumber: [
            {required: false, message: '请输入手机号码', trigger: 'blur'},
            {max: 11, message: '11个字符', trigger: 'blur'}
          ],
        }

      }
    },
    methods: {
      register(formName) {
        let that = this
        this.$refs[formName].validate((valid) => {
          if (valid) {

            that.$store.dispatch('register', that.userForm).then(() => {
              that.$message({message: '注册成功', type: 'success', showClose: true});
              that.$router.push({path: '/'})
            }).catch((error) => {
              if (error !== 'error') {
                that.$message({message: error, type: 'error', showClose: true});
              }
            })

          } else {
            return false;
          }
        });

      }

    }
  }
</script>

<style scoped>
  #login {
    min-width: 100%;
    min-height: 100%;
  }

  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 300px;
    height: 720px;
    background-color: white;
    margin-top: 150px;
    margin-left: -180px;
    left: 50%;
    padding: 30px;
  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

  .me-login-design {
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    font-size: 18px;
  }

  .me-login-design-color {
    color: #5FB878 !important;
  }

  .me-login-button {
    text-align: center;
  }

  .me-login-button button {
    width: 100%;
  }

</style>
