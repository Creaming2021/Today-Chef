import course from '@/store/courseActions/course.js';
import courseQna from '@/store/courseActions/courseQna.js';
import courseReview from '@/store/courseActions/courseReview.js';

export default {
  state: {
    courseList: [],
    course: {
      id: 1,
      category: '한식',
      date: '2021.04.16',
      name: '쉽게 배워보는 갈비찜',
      teacher: '김한식',
      price: '50000',
      likeCnt: '123',
      rate: '3.5',
      thumbnailList: [
        'https://www.paris.co.kr/wp-content/uploads/200220_%E1%84%89%E1%85%A2%E1%86%BC%E1%84%84%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B5-%E1%84%83%E1%85%B3%E1%86%B7%E1%84%88%E1%85%AE%E1%86%A8-1280x1280.jpg',
        'http://img.vogue.co.kr/vogue/2020/12/style_5fc60ec7705fe.jpg',
        'https://i.pinimg.com/originals/57/c1/01/57c101e2a8ddc9d407076506ade4fbbb.png',
        'https://cdn.class101.net/images/a89a90ff-baa4-4ba2-b7d5-317f09e84a71',
        'https://i.pinimg.com/564x/0e/ef/38/0eef38624526b44f1741e7b663179125.jpg',
        'https://dimg.donga.com/ugc/CDB/WOMAN/Article/5f/f8/06/41/5ff806410c12d2738de6.jpg',
      ],
      introduction: '클래스 소개',
      kit: '키트 소개',
    },
    studentList: [],
    qnaList: [
      {
        id: 1,
        profile: 'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        content: '질문 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
        answerList: [
          {
            id: 1,
            writer: '답변 작성자',
            date: '2021년 4월 21일',
            profile: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MDZfMjg0/MDAxNTg2MTgwNjMxODE1.B22_kATsB4cyKg2yr9x6GoRk8OuBXutbaQdRG2wxbAMg.ZIGXNEYc2xDiH61A18wLGcNeFNl4RsDTCrK1pMY0cnIg.JPEG.b-seol/46858675_511354619382565_4518962982505897027_n(1).jpg?type=w800',
            content: '답변 내용',
          }
        ]
      },
      {
        id: 2,
        profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        content: '질문 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
        answerList: [
          {
            id: 1,
            writer: '답변 작성자',
            date: '2021년 4월 21일',
            profile: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MDZfMjg0/MDAxNTg2MTgwNjMxODE1.B22_kATsB4cyKg2yr9x6GoRk8OuBXutbaQdRG2wxbAMg.ZIGXNEYc2xDiH61A18wLGcNeFNl4RsDTCrK1pMY0cnIg.JPEG.b-seol/46858675_511354619382565_4518962982505897027_n(1).jpg?type=w800',
            content: '답변 내용',
          },
          {
            id: 2,
            writer: '답변 작성자',
            date: '2021년 4월 21일',
            profile: 'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
            content: '답변 내용',
          }
        ]
      },
      {
        id: 3,
        profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        content: '질문 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
        answerList: [
          {
            id: 1,
            writer: '답변 작성자',
            date: '2021년 4월 21일',
            profile: 'https://i1.sndcdn.com/artworks-WzFnntNp1E7dHw7y-RqzlrQ-t500x500.jpg',
            content: '답변 내용',
          }
        ]
      }
    ],
    qna: {},
    reviewList: [
      {
        reviewId: 1,
        profile:{
          profileImage: 'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
          nickname: '작성자 닉네임',
        },
        title: '리뷰 제목',
        content: '리뷰 내용',
        createdDate: '2021년 4월 21일',
        commentCount: 2 
      },
      {
        reviewId: 2,
        profile:{
          profileImage: 'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          nickname: '작성자 닉네임',
        },
        title: '리뷰 제목',
        content: '리뷰 내용',
        createdDate: '2021년 4월 21일',
        commentCount: 2 
      },
      {
        reviewId: 3,
        profile:{
          profileImage: 'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          nickname: '작성자 닉네임',
        },
        title: '리뷰 제목',
        content: '리뷰 내용',
        createdDate: '2021년 4월 21일',
        commentCount: 2 
      },
      {
        reviewId: 4,
        profile:{
          profileImage: 'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          nickname: '작성자 닉네임',
        },
        title: '리뷰 제목',
        content: '리뷰 내용',
        createdDate: '2021년 4월 21일',
        commentCount: 2 
      },
      {
        reviewId: 5,
        profile:{
          profileImage: 'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          nickname: '작성자 닉네임',
        },
        title: '리뷰 제목',
        content: '리뷰 내용',
        createdDate: '2021년 4월 21일',
        commentCount: 2
      }
    ],
    review: {
      id: 1,
      profile: {
        profileImage: 'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        nickname: '작성자 닉네임',
      },
      title: '리뷰 제목',
      content: '리뷰 내용',
      date: '2021년 4월 21일',
    },
    reviewCommentList: [
      {
        commentId: 1,
        profile: {
          nickname: '댓글 작성자',
          profileImage: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MDZfMjg0/MDAxNTg2MTgwNjMxODE1.B22_kATsB4cyKg2yr9x6GoRk8OuBXutbaQdRG2wxbAMg.ZIGXNEYc2xDiH61A18wLGcNeFNl4RsDTCrK1pMY0cnIg.JPEG.b-seol/46858675_511354619382565_4518962982505897027_n(1).jpg?type=w800',
        },
        date: '2021년 4월 21일',
        content: '댓글 내용',
      },
      {
        commentId: 2,
        profile: {
          nickname: '댓글 작성자',
          profileImage: 'https://i1.sndcdn.com/artworks-WzFnntNp1E7dHw7y-RqzlrQ-t500x500.jpg',
        },
        date: '2021년 4월 21일',
        content: '댓글 내용',
      }
    ],
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