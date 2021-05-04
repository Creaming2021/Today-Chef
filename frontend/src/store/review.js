import * as review from '@/api/review.js';

export default {
  state: {
    reviewList: [],
    review: {},
    commentList: [],
  },
  mutations: {
    SET_REVIEW_LIST(state, payload){
      state.reviewList = payload;
    },
    SET_REVIEW(state, payload){
      state.review = payload;
    },
    SET_COMMENT_LIST(state, payload){
      state.commentList = payload;
    }
  },
  actions: {
    GET_REVIEW_LIST: function({ commit }, request){
      review.getReviewList(request)
        .then(({ data }) => {
          if(data){
            commit('SET_REVIEW_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_REVIEW: function({ dispatch }, request){
      review.postReview(request)
        .then(({ data }) => {
          if(data){
            dispatch('SET_REVIEW', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    GET_REVIEW: function({ commit }, request){
      review.getReview(request)
        .then(({ data }) => {
          if(data){
            commit('SET_REVIEW', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    PUT_REVIEW: function({ dispatch }, request){
      review.putReview(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_REVIEW', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    GET_REVIEW_COMMENT_LIST: function({ dispatch }, request){
      review.getReviewComment(request)
        .then(({ data }) => {
          if(data){
            dispatch('SET_COMMENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_REVIEW_COMMENT: function({ dispatch }, request){
      review.postReviewComment(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_REVIEW_COMMENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    PUT_REVIEW_COMMENT: function({ dispatch }, request){
      review.putReviewComment(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_REVIEW_COMMENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    DELETE_REVIEW_COMMENT: function({ dispatch }, request){
      review.deleteReviewComment(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_REVIEW_COMMENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
  },
};