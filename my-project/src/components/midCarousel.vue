<template>
  <div class="block">
    <span class="demonstration" style="font-size: x-large;color:#909399 ">hot</span>
    <p class="clear"></p>
    <el-carousel height="500px">
      <el-carousel-item v-for="item in listDir" :key="item">
        <img :src="item" width="740" height="542">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
div>span{
float: left;
margin-bottom: 0px;
padding-bottom: 0px;
}
.clear{
  clear: both;
  margin-bottom: 0px;
padding-bottom: 0px;
}
</style>

<script type="text/javascript">

  export default {
  name: 'midCarousel',
  data () {
    var dir='../static/'
    var listDir=new Array(4); 
    for (var i = 0; i < listDir.length; i++) {
      var temp=dir+i+'.jpg'

      listDir[i]=temp
    }
    return {
     listDir
    }
  },
  methods: {
  }
}
</script>