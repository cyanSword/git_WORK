<template>
  <div id="app">
    <div class="container">
    <img src="./assets/logo.png">
    <masthed/>
    <router-view/>
    </div>
  </div>
</template>

<script>
import masthed from '@/components/masthed' /*  此处可注册局部性的组件 先导入对象，再注册 */
// import Footer from '@/components/footer'
// import homeContent from '@/components/homeContent'
export default {
  name: 'App',
  components: {
    masthed,
  }
}
</script>

<style>
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
