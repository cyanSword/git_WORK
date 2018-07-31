import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import index from '@/components/index'
Vue.use(VueRouter)

const routes = [
  {path: '/home', name: 'home', component: Home},
  {path: '/', component: index}
]
export default new VueRouter({
  // mode: 'history',
  scrollBehavior: function (to, from, savedPosition) {
    return savedPosition
  },
  routes
})
