import * as memberItem from '@/api/memberItem.js';

export default{
  SET_COURSE_LIKE({ commit }, request){
    memberItem.setCourseLike(request)
      .then(({ data }) => {
        commit('', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_LIKE_COURSE_LIST({ commit }, request){
    memberItem.getLikeCourseList(request)
      .then(({ data }) => {
        commit('SET_COURSE_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_REGISTERED_COURSE_LIST({ commit }, request){
    memberItem.getRegisteredCourseList(request)
      .then(({ data }) => {
        commit('SET_COURSE_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_TEACHED_COURSE_LIST({ commit }, request){
    memberItem.getTeachedCourseList(request)
      .then(({ data }) => {
        commit('SET_COURSE_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_REGISTER_LIST({ commit }, request){
    memberItem.getRegisterList(request)
      .then(({ data }) => {
        commit('SET_PAYMENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  POST_REGISTER({ commit }, request){
    memberItem.postRegister(request)
      .then(({ data }) => {
        commit('SET_PAYMENT', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_REGISTER({ commit }, request){
    memberItem.getRegister(request)
      .then(({ data }) => {
        commit('SET_PAYMENT', data);
      })
      .catch(e => { console.log(e); });
  },
  DELETE_REGISTER({ commit }, request){
    memberItem.deleteRegister(request)
      .then(({ data }) => {
        commit('SET_PAYMENT', data);
      })
      .catch(e => { console.log(e); });
  },
  
}