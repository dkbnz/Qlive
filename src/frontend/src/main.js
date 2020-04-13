import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueApexCharts from 'vue-apexcharts'

Vue.component('apexchart', VueApexCharts)

const base = axios.create({
 baseURL: 'api'
});

Vue.prototype.$http = base;
Vue.prototype.axios = base;
Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
