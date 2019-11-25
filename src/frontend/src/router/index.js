import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/Index/Index'
import Create from '../components/Create/Create'
import ViewResults from '../components/View/ViewResults'
import Vote from "../components/Vote/Vote";

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
      component: Create
    },
    {
      path: '/view',
      name: 'ViewResults',
      component: ViewResults,
      props: true
    },
    {
      path: '/vote',
      name: 'Vote',
      component: Vote,
      props: true
    }
  ]
})
