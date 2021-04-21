<template>
  <div class="col-lg-9 course-detail-main-container">
    <div class="tab">
      <span @click="onChangeType('introduction')">클래스 소개</span> |
      <span @click="onChangeType('kit')">키트 소개</span> |
      <span @click="onChangeType('review')">리뷰</span> |
      <span @click="onChangeType('qna')">QnA</span> |
      <span @click="onChangeType('refund')">한불 반품 정보</span>
    </div>
    <CourseIntroduction v-if="this.type === 'introduction'"/>
    <CourseKit v-else-if="this.type === 'kit'"/>
    <CourseReview 
      v-else-if="this.type === 'review'"
      :reviewList="reviewList"/>
    <CourseReviewDetail
      v-else-if="this.type === 'reviewDetail'"/>
    <CourseQnA v-else-if="this.type === 'qna'"/>
    <CourseRefund v-else-if="this.type === 'refund'"/>
  </div>
</template>


<script>
import CourseIntroduction from '@/components/CourseDetail/CourseIntroduction.vue';
import CourseKit from '@/components/CourseDetail/CourseKit.vue';
import CourseReview from '@/components/CourseDetail/CourseReview.vue';
import CourseReviewDetail from '@/components/CourseDetail/CourseReviewDetail.vue';
import CourseQnA from '@/components/CourseDetail/CourseQnA.vue';
import CourseRefund from '@/components/CourseDetail/CourseRefund.vue';

export default {
  data() {
    return {
      type: 'introduction',
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
      ]
    }
  },
  created() {
    this.checkQuery();
  },
  components: {
    CourseIntroduction,
    CourseKit,
    CourseReview,
    CourseReviewDetail,
    CourseQnA,
    CourseRefund
  },
  methods: {
    onChangeType( clickType ){
			this.$router.push({
				name: 'CourseDetail',
        params: { type: clickType }
			});
    },
    checkQuery() {
      this.type = this.$route.params.type;
    },
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
  margin-bottom: 20px;
}

.course-detail-main-container .tab:hover{
  cursor: pointer;
}
</style>
