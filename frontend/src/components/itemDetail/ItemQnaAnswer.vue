<template>
  <div class="answer-list-container">
    <div class="answer-container" v-for="comment in comments" v-bind:key="comment.commentId">
      <div class="user-info">
        <img :src="comment.profile.profileImage || 'https://www.edmundsgovtech.com/wp-content/uploads/2020/01/default-picture_0_0.png'"/>
        <div>
          <div class="writer">{{comment.profile.nickname}}</div>
          <div class="date">{{comment.date.substring(0,10)}}</div>
        </div>
      </div>
      <div class="content">
        {{comment.content}}
      </div>
    </div>
    <input 
      v-if="memberId !== ''"
      @keyup.enter="submitComment"
      v-model="comment"
      placeholder="답변을 입력하세요."/> 
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default{
  props: {
    comments: Array,
    qnaId: Number,
    courseId: Number,
  },
  data() {
    return {
      comment: '',
    };
  },
  computed: {
    ...mapState({
      memberId: state => state.user.memberId,
    }),
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
      this.comment = '';
    },
  }
}
</script>

<style scoped>
.answer-list-container{
  margin: 20px 0px 20px 0px;
}

.answer-list-container .answer-container{
  margin-bottom: 20px;
  background-color: #f3f2ee;
  padding: 20px;
  border-radius: 10px;
}

.answer-list-container .user-info > img{
  display: inline-block;
  margin: 10px;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  object-fit: cover;
}

.answer-list-container .user-info > div{
  display: inline-block;
  font-size: 0.8rem;
  font-weight: normal;
  color: #666666;
}

.answer-list-container > input{
  display: block;
  margin: 20px 0px 20px 0px;
  border-radius: 25px;
  border: 1px solid darkgray;
  width: 100%;
  height: 50px;
  padding-left: 20px;
}

.user-info {
  margin-bottom: 30px;
}

.user-info img{
  float: left;
  display: block;
  width: 70px;
  height: 70px;
  border-radius: 50%;
  margin: 0px 30px 20px 0px;
}
</style>