import * as qna from '@/api/qna.js';

export default {
  state: {
    qnaList: null,
    qna: null,
    commentList: null,
  },
  mutations: {
    setQnaList(state, payload){
      state.qnaList = payload;
    },
    setQna(state, payload){
      state.qna = payload;
    },
    setCommentList(state, payload){
      state.commentList = payload;
    },
  },
  actions: {
    getQnaList: function(context, request){
      qna.getQnaList(request)
       .then((result) => {
         if(result){
          context.commit('setQnaList', result);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    postQna: function(context, request){
      qna.postQna(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    putQna: function(context, request){
      qna.putQna(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    deleteQna: function(context, request){
      qna.deleteQna(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    postQnaComment: function(context, request){
      qna.postQnaComment(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    putQnaComment: function(context, request){
      qna.putQnaComment(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
    deleteQnaComment: function(context, request){
      qna.deleteQnaComment(request)
       .then((result) => {
         if(result){
          context.dispatch('getQnaList', request);
         } else {
          console.log(result);
         }
       })
       .catch(e => { console.log(e); })
    },
  }
}