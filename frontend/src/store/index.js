import Vue from "vue";
import Vuex from "vuex";
import home from './home.js';
import user from './user.js';
import event from './event.js';
import review from './review.js';
import course from './course.js';
import courseQna from './courseQna.js';
import member from './member.js';
import product from './product.js';
import productQna from './productQna.js';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    home,
    user,
    event,
    review,
    course,
    courseQna,
    member,
    product,
    productQna,
  }
});
