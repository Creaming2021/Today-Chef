import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';
import home from './home.js';
import user from './user.js';
import event from './event.js';
import review from './review.js';
import course from './course.js';
import member from './member.js';
import product from './product.js';
import order from './order.js';
import payment from './payment.js';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    home,
    user,
    event,
    review,
    course,
    member,
    product,
    order,
    payment,
  },
  plugins: [
    createPersistedState({
      paths: [
        'user',
        'order',
        'member',
        'payment',
      ]
    }),
  ],
});
