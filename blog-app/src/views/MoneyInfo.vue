<template>
  <div id="moneyInfo" v-title data-title="查询金额 - 好去处">
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
            <source src="../../static/vedio/sea.mp4" type="video/mp4">
        </video>-->

    <div class="me-login-box me-login-box-radius">
      <h1>查询条件</h1>

      <el-form ref="moneyForm" :model="moneyForm" :rules="rules">
        <el-form-item prop="startTime">
          开始时间：
          <el-input
            placeholder="例：202311"
            v-model="moneyForm.startTime"
          ></el-input>
        </el-form-item>

        <el-form-item prop="endTime">
          结束时间：
          <el-input
            placeholder="例：202312"
            v-model="moneyForm.endTime"
          ></el-input>
        </el-form-item>

        <el-form-item>
          城市：
          <el-cascader
            placeholder="注册城市"
            size = "small"
            :options="pcaTextArr"
            v-model="moneyForm.city">
          </el-cascader>
        </el-form-item>

        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click.native.prevent="updateInfo('moneyForm')">查询</el-button>
        </el-form-item>
      </el-form>
      <template>
        <div ref="chart" style="height: 300px;"></div>
      </template>
      <!-- 折线图来展示MoneyResult -->
      <div id="moneyInfo" style="width: 100%;height: 400px;">
        <el-card>
          <div class="clearfix">
            <span>查询结果</span>
          </div>
          <div>
            <el-table :data="moneyResult" style="width: 100%">
              <el-table-column prop="year" label="年份" width="180"></el-table-column>
              <el-table-column prop="month" label="月份" width="180"></el-table-column>
              <el-table-column prop="city" label="城市" width="180"></el-table-column>
              <el-table-column prop="money" label="金额" width="180"></el-table-column>
            </el-table>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import {provinceAndCityData,pcTextArr,regionData,pcaTextArr,codeToText} from "element-china-area-data"
import echarts from 'echarts'
export default {
  name: "moneyInfo",
  data() {
    return {
      pcaTextArr,
      selectOptions: [],
      moneyForm: {
        startTime: this.$store.startTime,
        endTime: this.$store.endTime,
        city: this.$store.city,
      },
      option:{
        title:{
          text:'金额统计'
        },
        xAxis:{
          type:'category',
          data: []
        },
        yAxis:{
          type:'value'
        },
        series:[{
          data:[],
          type:'line'
        }]
      },
      moneyResult:[
        {
          year: '2023',
          month: '10',
          city: '北京市海淀区',
          money: 0
        },
        {
          year: '2023',
          month: '11',
          city: '北京市海淀区',
          money: 0
        },
        {
          year: '2023',
          month: '12',
          city: '北京市海淀区',
          money: 2
        },
      ],
      rules: {
        startTime: [
          { required: false, message: "请输入开始时间：202311", trigger: "blur" },
          { max: 10, message: "202312", trigger: "blur" },
        ],
        endTime: [
          { required: false, message: "请输入结束时间：202312", trigger: "blur" },
          { max: 11, message: "11个字符", trigger: "blur" },
        ],
        city: [
          { required: false, message: "请输入城市：山东省济南市", trigger: "blur" },
          { max: 100, message: "不能大于100个字符", trigger: "blur" },
        ],
      },
    };
  },
  mounted(){
    const tb = this.$refs.chart;
    const mc = echarts.init(tb);
    let sd = [];
    let xd = [];
    this.moneyResult.forEach((item)=>{
      sd.push(item.money);
      xd.push(item.year+item.month);
    })
    this.option.xAxis.data = xd;
    this.option.series[0].data = sd;
    console.log(this.option)
    mc.setOption(this.option);

  },
  methods: {
    //moneydata是返回值，是一个数组，数组内的元素是一个对象，对象有year，month，city，money四个属性
    updateInfo(formName) {
      getMoneyInfo(this.moneyForm).then((res) => {
        alert("查询成功");
        this.moneyResult = res.data;
      }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '获取统计失败!', showClose: true})
          }
      });
    },
  },
};
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
  width: 1200px;
  height: 800px;
  background-color: white;
  margin-top: 150px;
  margin-left: 150px;
  left: auto;
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
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
}

.me-login-design-color {
  color: #5fb878 !important;
}

.me-login-button {
  text-align: center;
}

.me-login-button button {
  width: 100%;
}
</style>
