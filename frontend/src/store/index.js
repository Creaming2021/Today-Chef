import Vue from "vue";
import Vuex from "vuex";
import home from './home.js';
import user from './user.js';
import event from './event.js';
import course from './course.js';
import member from './member.js';
import product from './product.js';
import order from './order.js';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    home,
    user,
    event,
    course,
    member,
    product,
    order,
  }
});
