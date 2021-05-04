import * as courseQna from '@/api/courseQna.js';

export default {
  state: {
    qnaList: [],
    qna: {},
    commentList: [],
  },
  mutations: {
    SET_QNA_LIST(state, payload){
      state.qnaList = payload;
    },
    SET_QNA(state, payload){
      state.qna = payload;
    },
    SET_COMMENT_LIST(state, payload){
      state.commentList = payload;
    },
  },
  actions: {
    GET_QNA_LIST: function({ commit }, request){
      courseQna.getQnaList(request)
       .then(({ data }) => {
         if(data){
          commit('SET_QNA_LIST', data);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    POST_QNA: function({ dispatch }, request){
      courseQna.postQna(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    PUT_QNA: function({ dispatch }, request){
      courseQna.putQna(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    DELETE_QNA: function({ dispatch }, request){
      courseQna.deleteQna(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    GET_QNA_COMMENT_LIST: function({ commit }, request){
      courseQna.getQnaCommentList(request)
       .then(({ data }) => {
         if(data){
          commit('SET_COMMENT_LIST', data);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    POST_QNA_COMMENT: function({ dispatch }, request){
      courseQna.postQnaComment(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    PUT_QNA_COMMENT: function({ dispatch }, request){
      courseQna.putQnaComment(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    DELETE_QNA_COMMENT: function({ dispatch }, request){
      courseQna.deleteQnaComment(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
  },
};