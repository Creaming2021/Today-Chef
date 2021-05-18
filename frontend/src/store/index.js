import Vue from "vue";
import Vuex from "vuex";
import user from './user.js';
import event from './event.js';
import course from './course.js';
import member from './member.js';
import product from './product.js';
import order from './order.js';
import chat from './chat.js';
import socket from './socket.js';
import payment from './payment.js';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user,
    event,
    course,
    member,
    product,
    order,
    chat,
    payment,
    socket,
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      paths: [ 
        'user',
        'event',
        'chat',
      ],
    }),
  ],
});
