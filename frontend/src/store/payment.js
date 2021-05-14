import { payment } from '@/api/payment.js';

export default {
  state: {
    url: '',
  },
  mutations: { 
    SET_PAYMENT_URL(state, payload) {
      state.url = payload;
    },
  },
  actions: {
    GET_PAYMENT_URL({ commit }, request) {
      payment(request)
        .then(response => {
          commit('SET_PAYMENT_URL', response.data.url);
        });
    },
  },
}

