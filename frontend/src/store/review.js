import * as review from '@/api/review.js';

export default {
  state: {
    reviewList: null,
    review: null,
    commentList: null,
  },
  mutations: {
    setReviewList(state, payload){
      state.reviewList = payload;
    },
    setReview(state, payload){
      state.review = payload;
    },
    setCommentList(state, payload){
      state.commentList = payload;
    }
  },
  actions: {
    getReviewList: function(context, request){
      review.getReviewList(request)
        .then((result) => {
          if(result){
            context.commit('setReviewList', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    postReview: function(context, request){
      review.postReview(request)
        .then((result) => {
          if(result){
            context.dispatch('getReview', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    getReview: function(context, request){
      review.getReview(request)
        .then((result) => {
          if(result){
            context.commit('setReview', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    putReview: function(context, request){
      review.putReview(request)
        .then((result) => {
          if(result){
            context.dispatch('getReview', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    getReviewComment: function(context, request){
      review.getReviewComment(request)
        .then((result) => {
          if(result){
            context.dispatch('setCommentList', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    postReviewComment: function(context, request){
      review.postReviewComment(request)
        .then((result) => {
          if(result){
            context.dispatch('getReviewComment', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    putReviewComment: function(context, request){
      review.putReviewComment(request)
        .then((result) => {
          if(result){
            context.dispatch('getReviewComment', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    deleteReviewComment: function(context, request){
      review.deleteReviewComment(request)
        .then((result) => {
          if(result){
            context.dispatch('getReviewComment', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
  }
}