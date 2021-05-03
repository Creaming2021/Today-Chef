import * as course from '@/api/course.js';

export default {
  state: {
    courseList: null,
    course: null,
  },
  mutations: {
    setCourseList(state, payload) {
      state.courseList = payload;
    },
    setCourse(state, payload) {
      state.course = payload;
    },
  },
  actions: {
    postCourse: function(context, request) {
      course.postCourse(request)
        .then((result) => {
          if(result){
            context.dispatch('', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    putCourse: function(context, request) {
      course.putCourse(request)
        .then((result) => {
          if(result){
            context.dispatch('', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    deleteCourse: function(context, request) {
      course.deleteCourse(request)
        .then((result) => {
          if(result){
            context.dispatch('', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    postCourseImage: function(context, request) {
      course.postCourseImage(request)
        .then((result) => {
          if(result){
            context.dispatch('', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    getCourseStudents: function(context, request) {
      course.getCourseStudents(request)
        .then((result) => {
          if(result){
            context.dispatch('', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
  }
}