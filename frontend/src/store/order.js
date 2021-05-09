import * as order from '@/api/order.js';

export default {
  state: {
    orderList: [],
    order: {},
  },
  mutations: {
    SET_ORDER_LIST(state, payload) {
      state.orderList = payload;
    },
    SET_ORDER(state, payload) {
      state.order = payload;
    },
  },
  actions: {
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