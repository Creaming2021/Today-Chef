import * as courseReview from '@/api/courseReview.js';

export default {
  GET_COURSE_REVIEW_LIST({ commit }, request) {
    courseReview.getReviewList(request)
      .then(({ data }) => {
        commit('SET_COURSE_REVIEW_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE_REVIEW({ dispatch }, request) {
    courseReview.postReview(request)
      .then(({ data }) => {
        dispatch('GET_COURSE_REVIEW', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE_REVIEW({ commit }, request){
    courseReview.getReview(request)
      .then(({ data }) => {
        commit('SET_COURSE_REVIEW', data);
      })
      .catch(e => { console.log(e); });
  },  
  PUT_COURSE_REVIEW({ commit }, request){
    courseReview.putReview(request)
      .then(({ data }) => {
        commit('SET_COURSE_REVIEW', data);
      })
      .catch(e => { console.log(e); });
  },  
  DELETE_COURSE_REVIEW({ dispatch }, request){
    courseReview.deleteReview(request)
      .then(({ data }) => {
        dispatch('GET_COURSE_REVIEW_LIST', data);
      })
      .catch(e => { console.log(e); });
  }, 
  GET_COURSE_REVIEW_COMMENT_LIST({ dispatch }, request){
    courseReview.getReviewCommentList(request)
      .then(({ data }) => {
        dispatch('SET_COURSE_COMMENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE_REVIEW_COMMENT_LIST({ dispatch }, request){
    courseReview.postReviewComment(request)
      .then(({ data }) => {
        dispatch('GET_COURSE_REVIEW_COMMENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  PUT_COURSE_REVIEW_COMMENT({ dispatch }, request){
    courseReview.putReviewComment(request)
      .then(({ data }) => {
        dispatch('GET_COURSE_REVIEW_COMMENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  }, 
  DELETE_COURSE_REVIEW_COMMENT({ dispatch }, request){
    courseReview.deleteReviewComment(request)
      .then(({ data }) => {
        dispatch('GET_COURSE_REVIEW_COMMENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
};