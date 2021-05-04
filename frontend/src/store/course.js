import * as course from '@/api/course.js';

export default {
  state: {
    courseList: [],
    course: {
      thumbnailList: [
        'https://www.paris.co.kr/wp-content/uploads/200220_%E1%84%89%E1%85%A2%E1%86%BC%E1%84%84%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B5-%E1%84%83%E1%85%B3%E1%86%B7%E1%84%88%E1%85%AE%E1%86%A8-1280x1280.jpg',
        'http://img.vogue.co.kr/vogue/2020/12/style_5fc60ec7705fe.jpg',
        'https://i.pinimg.com/originals/57/c1/01/57c101e2a8ddc9d407076506ade4fbbb.png',
        'https://cdn.class101.net/images/a89a90ff-baa4-4ba2-b7d5-317f09e84a71',
        'https://i.pinimg.com/564x/0e/ef/38/0eef38624526b44f1741e7b663179125.jpg',
        'https://dimg.donga.com/ugc/CDB/WOMAN/Article/5f/f8/06/41/5ff806410c12d2738de6.jpg',
      ],
    },
    studentList: [],
  },
  mutations: {
    SET_COURSE_LIST(state, payload) {
      state.courseList = payload;
    },
    SET_COURSE(state, payload) {
      state.course = payload;
    },
    SET_STUDENT_LIST(state, payload) {
      state.studentList = payload;
    }
  },
  actions: {
    GET_COURSE: function({ commit }, request){
      course.postCourse(request)
        .then(({ data }) => {
          if(data){
            commit('SET_COURSE', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_COURSE: function({ dispatch }, request) {
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
    PUT_COURSE: function({ dispatch }, request) {
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
    DELETE_COURSE: function({ dispatch }, request) {
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
    POST_COURSE_IMAGE: function({ dispatch }, request) {
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
    GET_COURSE_STUDENT_LIST: function({ commit }, request) {
      course.getCourseStudents(request)
        .then(({ data }) => {
          if(data){
            commit('SET_STUDENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
  }
}