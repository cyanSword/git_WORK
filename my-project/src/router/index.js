import Vue from 'vue'
import VueRouter from 'vue-router'
import features from '@/components/newfeatures'
import index from '@/components/index'
Vue.use(VueRouter)

const routes = [
  {path: '/features', name: 'features', component: features},
  {path: '/', component: index}
]
export default new VueRouter({
  // mode: 'history',
  scrollBehavior: function (to, from, savedPosition) {
    return savedPosition
  },
  routes
})
