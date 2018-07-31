// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../static/css/blog.css'/* 引入公共样式 */
import '../static/css/carousel.css'/* 引入公共样式 */
import store from './store/index'
Vue.config.productionTip = false

/* main.js可注册全局性的组件 Vue.component('',demo) */
Vue.use(ElementUI);//饿了么开源组件
/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
