import * as course from '@/api/course.js';

export default {
  GET_COURSE({ commit }, request){
    course.getCourse(request)
      .then(({ data }) => {
        if(data){
          commit('SET_COURSE', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE({ dispatch }, request) {
    course.postCourse(request)
      .then(({ data }) => {
        if(data){
          dispatch('GET_COURSE', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
  PUT_COURSE({ dispatch }, request) {
    course.putCourse(request)
      .then(({ data }) => {
        if(data){
          dispatch('GET_COURSE', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
  DELETE_COURSE({ dispatch }, request) {
    course.deleteCourse(request)
      .then(({ data }) => {
        if(data){
          dispatch('', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
  POST_COURSE_IMAGE({ dispatch }, request) {
    course.postCourseImage(request)
      .then(({ data }) => {
        if(data){
          dispatch('GET_COURSE', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
  GET_COURSE_STUDENT_LIST({ commit }, request) {
    course.getCourseStudentList(request)
      .then(({ data }) => {
        if(data){
          commit('SET_STUDENT_LIST', data);
        } else {
          console.log(data);
        }
      })
      .catch(e => { console.log(e); });
  },
};