import * as courseQna from '@/api/courseQna.js';

export default {
  state: {
    qnaList: [
      {
        id: 1,
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
    // commentList: [],
  },
  mutations: {
    SET_COURSE_QNA_LIST(state, payload){
      state.qnaList = payload;
    },
    SET_COURSE_QNA(state, payload){
      state.qna = payload;
    },
    // SET_COMMENT_LIST(state, payload){
    //   state.commentList = payload;
    // },
  },
  actions: {
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
         if(data){
          dispatch('GET_COURSE_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    PUT_COURSE_QNA({ dispatch }, request){
      courseQna.putQna(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_COURSE_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    DELETE_COURSE_QNA({ dispatch }, request){
      courseQna.deleteQna(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_COURSE_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
    // GET_COURSE_QNA_COMMENT_LIST ({ commit }, request){
    //   courseQna.getQnaCommentList(request)
    //    .then(({ data }) => {
    //      if(data){
    //       commit('SET_COURSE_COMMENT_LIST', data);
    //      } else {
    //       console.log(data);
    //      }
    //    })
    //    .catch(e => { console.log(e); });
    // },
    POST_COURSE_QNA_COMMENT({ dispatch }, request){
      courseQna.postQnaComment(request)
       .then(({ data }) => {
         if(data){
          dispatch('GET_COURSE_QNA_LIST', request);
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
          dispatch('GET_COURSE_QNA_LIST', request);
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
          dispatch('GET_COURSE_QNA_LIST', request);
         } else {
          console.log(data);
         }
       })
       .catch(e => { console.log(e); });
    },
  },
};