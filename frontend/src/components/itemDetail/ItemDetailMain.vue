<template>
  <div class="col-lg-9 course-detail-main-container">
    <div class="tab">
      <span @click="onChangeType('introduction')">클래스 소개</span> |
      <span @click="onChangeType('kit')">키트 소개</span> |
      <span @click="onChangeType('review')">리뷰</span> |
      <span @click="onChangeType('qna')">QnA</span> |
      <span @click="onChangeType('refund')">한불 정책</span>
    </div>
    <ItemIntroduction v-if="this.type === 'introduction'"/>
    <ItemKit v-else-if="this.type === 'kit'"/>
    <List 
      v-else-if="this.type === 'review'"
      :type="type"
      :list="reviewList"
      :pageTotal="pageTotal"
      :currentPage="currentPage"/>
    <ListDetail
      v-else-if="this.type === 'reviewDetail'"
      :type="type"
      :item="reviewInfo"/>
    <ItemQnA 
      v-else-if="this.type === 'qna'"
      :qnaList="qnaList"/>
    <ItemRefund 
      v-else-if="this.type === 'refund'"/>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ItemIntroduction from '@/components/itemDetail/ItemIntroduction.vue';
import ItemKit from '@/components/itemDetail/ItemKit.vue';
import List from '@/components/common/List.vue';
import ListDetail from '@/components/common/ListDetail.vue';
import ItemQnA from '@/components/itemDetail/ItemQnA.vue';
import ItemRefund from '@/components/itemDetail/ItemRefund.vue';

export default {
  data() {
    return {
      id: null,
      type: 'introduction',
      pageTotal: 8,
      currentPage: 1,
      reviewList: [
        {
          id: 1,
          profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
          title: '리뷰 제목',
          content: '리뷰 내용',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          commentCnt: 2 
        },
        {
          id: 2,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '리뷰 제목',
          content: '리뷰 내용',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          commentCnt: 0
        },
        {
          id: 3,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '리뷰 제목',
          content: '리뷰 내용',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          commentCnt: 0
        },
        {
          id: 4,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '리뷰 제목',
          content: '리뷰 내용',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          commentCnt: 0
        },
        {
          id: 5,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '리뷰 제목',
          content: '리뷰 내용',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          commentCnt: 0
        }
      ],
      reviewInfo: {
        id: 1,
        profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        title: '리뷰 제목',
        content: '리뷰 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
        commentList: [
          {
            id: 1,
            writer: '댓글 작성자',
            date: '2021년 4월 21일',
            profile: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MDZfMjg0/MDAxNTg2MTgwNjMxODE1.B22_kATsB4cyKg2yr9x6GoRk8OuBXutbaQdRG2wxbAMg.ZIGXNEYc2xDiH61A18wLGcNeFNl4RsDTCrK1pMY0cnIg.JPEG.b-seol/46858675_511354619382565_4518962982505897027_n(1).jpg?type=w800',
            content: '댓글 내용',
          },
          {
            id: 2,
            writer: '댓글 작성자',
            date: '2021년 4월 21일',
            profile: 'https://i1.sndcdn.com/artworks-WzFnntNp1E7dHw7y-RqzlrQ-t500x500.jpg',
            content: '댓글 내용',
          }
        ],
      },
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
    }
  },
  computed: {
    ...mapState(['course']),
  },
  created() {
    this.checkQuery();
  },
  components: {
    ItemIntroduction,
    ItemKit,
    List,
    ListDetail,
    ItemQnA,
    ItemRefund
  },
  methods: {
    ...mapActions(['getCourse']),
    onChangeType( clickType ){
      this.$router.push({
        name: 'CourseDetail',
        params: { type: clickType }
      });
    },
    checkQuery() {
      this.type = this.$route.params.type;
      this.id = this.$route.params.id;
      this.getCourseDetailInfo();
    },
    getCourseDetailInfo() {
      if(this.id) {
        console.log(`${this.id}로 강의정보 요청 떄림`);
        this.getCourse(this.id);
      }else{
        alert("잘못된 접근 입니다.");
      }
    }
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    } 
  }
}
</script>

<style>
.course-detail-main-container{
  display: inline-block;
  width: 70%;
  height: inherit;
  padding: 10px;
}

.course-detail-main-container .tab{
  word-spacing: 5px;
  margin-bottom: 40px;
}

.course-detail-main-container .tab:hover{
  cursor: pointer;
}
</style>
