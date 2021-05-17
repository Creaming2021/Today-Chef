import * as courseQna from '@/api/courseQna.js';

export default {
  GET_COURSE_QNA_LIST({ commit }, request){
    courseQna.getQnaList(request)
     .then(({ data }) => {
       if(data){
        commit('SET_COURSE_QNA_LIST', data);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  POST_COURSE_QNA({ dispatch }, request){
    courseQna.postQna(request)
      .then(({ data }) => {
        alert('QNA 작성 완료')
        if(data){ 
          console.log(data);
          dispatch('GET_COURSE_QNA_LIST', request.courseId);
        } else {
        console.log(data);
        }
      })
     .catch(e => { console.log(e); });
  },
  PUT_COURSE_QNA({ dispatch }, request){
    courseQna.putQna(request)
     .then(({ data }) => {
       alert('QNA 수정 완료');
       if(data){
        dispatch('GET_COURSE_QNA_LIST', request.courseId);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  DELETE_COURSE_QNA({ dispatch }, request){
    courseQna.deleteQna(request)
     .then(({ data }) => {
       alert('QnA 삭제 완료');
       if(data){
        dispatch('GET_COURSE_QNA_LIST', request.courseId);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  GET_COURSE_QNA_COMMENT_LIST ({ commit }, request){
    courseQna.getQnaCommentList(request)
     .then(({ data }) => {
       if(data){
        commit('SET_COURSE_COMMENT_LIST', data);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  POST_COURSE_QNA_COMMENT({ dispatch }, request){
    courseQna.postQnaComment(request)
     .then(({ data }) => {
       if(data){
        dispatch('GET_COURSE_QNA_LIST', request.courseId);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  PUT_COURSE_QNA_COMMENT({ dispatch }, request){
    courseQna.putQnaComment(request)
     .then(({ data }) => {
       if(data){
        dispatch('GET_COURSE_QNA_LIST', request.courseId);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
  DELETE_COURSE_QNA_COMMENT({ dispatch }, request){
    courseQna.deleteQnaComment(request)
     .then(({ data }) => {
       if(data){
        dispatch('GET_COURSE_QNA_LIST', request.courseId);
       } else {
        console.log(data);
       }
     })
     .catch(e => { console.log(e); });
  },
};