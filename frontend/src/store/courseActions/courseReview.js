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
      .then(( data ) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW_LIST', request.courseId);
        }
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE_REVIEW({ commit }, request){
    courseReview.getReview(request)
      .then(({ data }) => {
        commit('SET_COURSE_REVIEW', data);
        commit('SET_COURSE_COMMENT_LIST', data.comments);
      })
      .catch(e => { console.log(e); });
  },  
  PUT_COURSE_REVIEW({ dispatch }, request){
    courseReview.putReview(request)
      .then(( data ) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW', request.courseId);
        }
      })
      .catch(e => { console.log(e); });
  },  
  DELETE_COURSE_REVIEW({ dispatch }, request){
    courseReview.deleteReview(request)
      .then(( data ) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW', request.courseId);
        }
      })
      .catch(e => { console.log(e); });
  }, 
  GET_COURSE_REVIEW_COMMENT_LIST({ commit }, request){
    courseReview.getReviewCommentList(request)
      .then(({ data }) => {
        commit('SET_COURSE_COMMENT_LIST', data.comments);
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE_REVIEW_COMMENT({ dispatch }, request){
    courseReview.postReviewComment(request)
      .then(({ data }) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW_COMMENT_LIST', request.reviewId);
        }
      })
      .catch(e => { console.log(e); });
  },
  PUT_COURSE_REVIEW_COMMENT({ dispatch }, request){
    courseReview.putReviewComment(request)
      .then(({ data }) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW_COMMENT_LIST', request.reviewId);
        }
      })
      .catch(e => { console.log(e); });
  }, 
  DELETE_COURSE_REVIEW_COMMENT({ dispatch }, request){
    courseReview.deleteReviewComment(request)
      .then(({ data }) => {
        if (data) {
          dispatch('GET_COURSE_REVIEW_COMMENT_LIST', request.reviewId);
        }
      })
      .catch(e => { console.log(e); });
  },
};