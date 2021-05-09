<template>
  <div class="answer-list-container">
    <div class="answer-container" v-for="comment in comments" v-bind:key="comment.commentId">
      <div class="user-info">
        <img :src="comment.profile.profileImage"/>
        <div>
          <div class="writer">{{comment.profile.nickname}}</div>
          <div class="date">{{comment.date}}</div>
        </div>
      </div>
      <div class="content">
        {{comment.content}}
      </div>
    </div>
    <input 
      @keyup.enter="submitComment"
      v-model="comment"
      placeholder="답변을 입력하세요."/> 
  </div>
</template>

<script>
export default{
  props: {
    comments: Array,
    qnaId: Number,
    courseId: Number,
    memberId: Number,
  },
  data() {
    return {
      comment: '',
    };
  },
  methods: {
    submitComment(){
      let item = this.$route.params.item;
      if(item === 'course'){
        this.$store.dispatch('POST_COURSE_QNA_COMMENT', 
          {
            qnaId: this.qnaId,
            memberId: this.memberId,
            content: this.comment,
            courseId: this.courseId,
          });
      } else if(item === 'product'){
        this.$store.dispatch('POST_PRODUCT_QNA_COMMENT', 
          {
            qnaId: this.qnaId,
            memberId: this.memberId,
            content: this.comment,
            courseId: this.courseId,
          });
      }
    },
  }
}
</script>

<style>

</style>