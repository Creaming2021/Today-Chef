import * as order from '@/api/order.js';

export default {
  state: {
    orderList: [],
    order: {},
    cart: {},
  },
  mutations: {
    SET_ORDER_LIST(state, payload) {
      state.orderList = payload;
    },
    SET_ORDER(state, payload) {
      state.order = payload;
    },
    SET_CART(state, payload) {
      state.cart = payload;
    },
  },
  actions: {
    SET_CART({ commit }, data){
      commit('SET_CART', data);
    },
    GET_ORDER_LIST({ commit }, request){
      order.getOrderList(request)
        .then(({ data }) => {
          commit('SET_ORDER_LIST', data);
        })
        .catch(e => { console.log(e); });
    },
    POST_ORDER({ commit }, request){
      order.postOrdert(request)
        .then(({ data }) => {
          commit('SET_ORDER', data);
        })
        .catch(e => { console.log(e); });
    },
    DELETE_ORDER({ commit }, request){
      order.deleteOrder(request)
        .then(({ data }) => {
          commit('', data);
        })
        .catch(e => { console.log(e); });
    },
  }
};