import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueSweetalert2 from 'vue-sweetalert2';
import io from 'socket.io-client';
import moment from 'moment';

window.Kakao.init('3a4c716678b023fe5358b12888ad66a7');

Vue.use(BootstrapVue);
Vue.use(VueSweetalert2);

Vue.config.productionTip = false;
Vue.config.ignoredElements = ['ion-icon', /^ion-/];
Vue.prototype.moment = moment;

let socket = null;

if (process.env.NODE_ENV === 'development') {
    socket = io('https://k4b204.p.ssafy.io');
} else {
    socket = io('/');
}

store.dispatch('assignSocket', socket);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
