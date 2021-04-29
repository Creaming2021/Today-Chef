import Vue from "vue";
import Vuex from "vuex";
import home from './home.js';
import user from './user.js';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    home,
    user,
  }
});
