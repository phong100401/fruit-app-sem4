import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import CKEditor from 'ckeditor4-vue'

Vue.use(Antd);


Vue.config.productionTip = false
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
Vue.use( CKEditor );
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import {faAccessibleIcon} from "@fortawesome/free-brands-svg-icons";
import {faAngry} from '@fortawesome/free-regular-svg-icons'
import './css/style.css'
/*import "./assets/base.scss"*/
library.add(faTwitter, faUserSecret,faAngry,faAccessibleIcon)
Vue.component('font-awesome-icon', FontAwesomeIcon)

new Vue({
  render: h => h(App),
  router
 /* el: '#app',
  components: { App },
  template: ''*/
}).$mount('#app')