<template>
  <div>
    <p>
      <span @click="onChangeType('introduction')">클래스 소개</span> |
      <span @click="onChangeType('kit')">키트 소개</span> |
      <span @click="onChangeType('review')">리뷰</span> |
      <span @click="onChangeType('qna')">QnA</span> |
      <span @click="onChangeType('refund')">한불 반품 정보</span>
    </p>
    <CourseIntroduction v-if="this.type === 'introduction'"/>
    <CourseKit v-if="this.type === 'kit'"/>
    <CourseReview v-if="this.type === 'review'"/>
    <CourseQnA v-if="this.type === 'qna'"/>
    <CourseRefund v-if="this.type === 'refund'"/>
  </div>
</template>


<script>
import CourseIntroduction from '@/components/CourseDetail/CourseIntroduction.vue';
import CourseKit from '@/components/CourseDetail/CourseKit.vue';
import CourseReview from '@/components/CourseDetail/CourseReview.vue';
import CourseQnA from '@/components/CourseDetail/CourseQnA.vue';
import CourseRefund from '@/components/CourseDetail/CourseRefund.vue';

export default {
  data() {
    return {
      type: 'introduction',
    }
  },
  components: {
    CourseIntroduction,
    CourseKit,
    CourseReview,
    CourseQnA,
    CourseRefund
  },
  methods: {
    onChangeType( clickType ){
      this.type = clickType;
    }
  }
}
</script>

<style>
</style>
