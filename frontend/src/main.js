import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// chat
import io from 'socket.io-client';
import moment from 'moment';
import axios from 'axios';

window.Kakao.init('3a4c716678b023fe5358b12888ad66a7');

Vue.use(BootstrapVue);

Vue.config.productionTip = false;
// chat start //
Vue.config.ignoredElements = ['ion-icon', /^ion-/];
Vue.prototype.moment = moment;

let socket = null;

/** Socket IO Client - Store in Vuex State for use in components */
if (process.env.NODE_ENV === 'development') {
    // origin
    socket = io('https://k4b204.p.ssafy.io');
    // local
    // socket = io('http://localhost:5000');
} else {
    socket = io('/');
}

store.dispatch('assignSocket', socket);

/** Check for auth token on refresh and set authorization header for incoming requests */
// if (localStorage.authToken) {
//     setAuthToken(localStorage.authToken);
// } else {
//     setAuthToken(null);
// }

/** Axios Request Intercept */
axios.interceptors.request.use(
    function(config) {
        return config;
    },
    function(err) {
        return Promise.reject(err);
    }
);

/** Axios Response Intercept */
// => 기존에 있는거랑 같이 할 수 있는지 보기
axios.interceptors.response.use(
    function(response) {
        return response;
    },
    function(err) {
        if (err.response.status === 401) {
            localStorage.removeItem('authToken');
            store.dispatch('toggleAuthState', false);
            router.push({
                name: 'Home',
                params: { message: 'Session has expired, please login again' }
            });
        }
        return Promise.reject(err);
    }
);
// chat end //

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
