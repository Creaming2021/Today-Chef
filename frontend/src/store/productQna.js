import * as productQna from '@/api/productQna.js';

export default {
  state: {
    qnaList: [],
    qna: {},
  },
  mutations: {
    SET_PRODUCT_QNA_LIST(state, payload){
      state.qnaList = payload;
    },
    SET_PRODUCT_QNA(state, payload){
      state.qna = payload;
    },
  },
  actions: {
    GET_PRODUCT_QNA_LIST({ commit }, request){
      productQna.getQnaList(request)
        .then(({ data }) => {
          if(data){
            commit('SET_PRODUCT_QNA_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_PRODUCT_QNA({ dispatch }, request){
      productQna.postQna(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_PRODUCT_QNA_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    PUT_PRODUCT_QNA({ dispatch }, request){
      productQna.putQna(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_PRODUCT_QNA_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    DELETE_PRODUCT_QNA({ commit }, request){
      productQna.deleteQna(request)
        .then(({ data }) => {
          if(data){
            commit('', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
  },
}