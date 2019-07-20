import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index/Index'
import Create from '@/components/Create/Create'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/create',
      name: 'Create',
      component: Create,
      props: true
    }
  ]
})
