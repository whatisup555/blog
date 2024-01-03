<template>
  <div id="userInfo" v-title data-title="个人空间 - For Fun">
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
          <source src="../../static/vedio/sea.mp4" type="video/mp4">
      </video>-->

    <div class="me-login-box me-login-box-radius">
      <h1>用户信息</h1>

      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item prop="id">
          用户ID： {{$store.state.id}}
        </el-form-item>

        <el-form-item prop="account">
          用户名： {{$store.state.account}}
        </el-form-item>

        <el-form-item prop="nickname">
          姓名： {{$store.state.name}}
        </el-form-item>

        <el-form-item prop="certificateType">
          证件类型： 居民身份证
        </el-form-item>

        <el-form-item prop="certificateNumber">
          证件号码： {{$store.state.certificateNumber}}
        </el-form-item>

        <el-form-item prop="registerCity">
          注册城市： {{$store.state.registerCity}}
        </el-form-item>

        <el-form-item prop="mobilePhoneNumber">
          手机号码： <el-input placeholder="手机号码" v-model="userForm.mobilePhoneNumber"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          密码： <el-input type="password" placeholder="密码" v-model="userForm.password"></el-input>
        </el-form-item>

        <el-form-item prop="userInfo">
          用户简介： <el-input placeholder="用户简介" v-model="userForm.userInfo"></el-input>
        </el-form-item>

        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click.native.prevent="updateInfo('userForm')">修改信息</el-button>
        </el-form-item>
      </el-form>

      <div class="me-login-design">
        <p>Designed by
          <strong>
            <router-link to="/" class="me-login-design-color">ForFun</router-link>
          </strong>
        </p>
      </div>

    </div>
  </div>
</template>



<script>


  export default {
    name: 'userInfo',
    data() {
      return {
        userForm: {
          id: this.$store.state.id,
          account: this.$store.state.account,
          nickname: this.$store.state.name,
          password: this.$store.state.password,
          certificateType: this.$store.state.certificateType,
          certificateNumber: this.$store.state.certificateNumber,
          mobilePhoneNumber: this.$store.state.mobilePhoneNumber,
          registerCity: this.$store.state.registerCity,
          userInfo: this.$store.state.userInfo
        },
        rules: {
          password: [
            {required: false, message: '请输入密码', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          mobilePhoneNumber: [
            {required: false, message: '请输入手机号码', trigger: 'blur'},
            {max: 11, message: '11个字符', trigger: 'blur'}
          ],
          userInfo: [
            {required: false, message: '请输入用户简介', trigger: 'blur'},
            {max: 100, message: '不能大于100个字符', trigger: 'blur'}
          ]
        }

      }
    },
    methods: {
      updateInfo(formName) {
        let that = this
        this.$refs[formName].validate((valid) => {
          if (valid) {

            that.$store.dispatch('updateUserInfo', that.userForm).then(() => {
              that.$message({message: '修改成功', type: 'success', showClose: true});
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
    height: 800px;
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
