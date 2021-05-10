import * as course from '@/api/course.js';

export default {
  GET_COURSE_TOP_LIST({ commit }, request){
    course.getTopList( request )
      .then(({ data }) => {
        commit('SET_COURSE_TOP_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE_LIST({ commit }){
    course.getCourseList()
      .then(({ data }) => {
        commit('SET_COURSE_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE({ commit }, request){
    course.getCourse(request)
      .then(({ data }) => {
        commit('SET_COURSE', data);
        return data;
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE({ commit }, request) {
    course.postCourse(request)
      .then(({ data }) => {
        commit('GET_COURSE', data);
      })
      .catch(e => { console.log(e); });
  },
  PUT_COURSE({ dispatch }, request) {
    course.putCourse(request)
      .then(({ data }) => {
        dispatch('GET_COURSE', data);
      })
      .catch(e => { console.log(e); });
  },
  DELETE_COURSE({ dispatch }, request) {
    course.deleteCourse(request)
      .then(({ data }) => {
        dispatch('', data);
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE_STUDENT_LIST({ commit }, request) {
    course.getCourseStudentList(request)
      .then(({ data }) => {
        commit('SET_STUDENT_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
};