import course from '@/store/courseActions/course.js';
import courseQna from '@/store/courseActions/courseQna.js';
import courseReview from '@/store/courseActions/courseReview.js';

export default {
  state: {
    courseList: [],
    course: {
      category: '',
      courseId: 0,
      descriptions: '',
      images: [],
      name: '',
      price: '',
      product: {
        category: '',
        course: [],
        descrption: '',
        productId: '',
        images: [],
        name: '',
        price: '',
        rating: '',
      },
      profile: {
        memberId: '',
        nickname: '',
        phone: '',
        profileImage: '',
      },
      rating: '',
      time: '',
    },
    studentList: [],
    qnaList: [],
    qna: {},
    reviewList: [],
    review: {},
    reviewCommentList: [],
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
    },
    SET_COURSE_QNA_LIST(state, payload){
      state.qnaList = payload;
    },
    SET_COURSE_QNA(state, payload){
      state.qna = payload;
    },
    // SET_COURSE_COMMENT_LIST(state, payload){
    //   state.commentList = payload;
    // },
    SET_COURSE_REVIEW_LIST(state, payload){
      state.reviewList = payload;
    },
    SET_COURSE_REVIEW(state, payload){
      state.review = payload;
    },
    SET_COURSE_COMMENT_LIST(state, payload){
      state.reviewCommentList = payload;
    }
  },
  actions: {
    ...course,
    ...courseQna,
    ...courseReview,
  }
}