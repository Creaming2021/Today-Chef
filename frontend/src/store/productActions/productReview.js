import * as productReview from '@/api/productReview.js';

export default {
  GET_PRODUCT_REVIEW_LIST({ commit }, request) {
    productReview.getReviewList(request)
      .then(({ data }) => {
        commit('SET_COURSE_REVIEW_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  POST_PRODUCT_REVIEW({ dispatch }, request) {
    productReview.postReview(request)
      .then(({ data }) => {
        dispatch('GET_PRODUCT_REVIEW', data);
      })
      .catch(e => { console.log(e); });
  },  
  PUT_PRODUCT_REVIEW({ commit }, request){
    productReview.putReview(request)
      .then(({ data }) => {
        commit('SET_PRODUCT_REVIEW', data);
      })
      .catch(e => { console.log(e); });
  },  
  DELETE_PRODUCT_REVIEW({ dispatch }, request){
    productReview.deleteReview(request)
      .then(({ data }) => {
        dispatch('GET_PRODUCT_REVIEW_LIST', data);
      })
      .catch(e => { console.log(e); });
  }, 
};