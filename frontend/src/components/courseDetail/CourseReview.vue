<template>
  <div class="course-review-container">
    <div 
      class="review-item" 
      v-for="review in reviewList" 
      v-bind:key="review.id"
      @click="onGoToReviewDetail(review.id)">
      <div class="review-item-left">
        <div class="title">{{review.title}}</div>
        <h6>{{review.content}}</h6>
        <div class="detail">{{review.writer}} | {{review.date}}</div>
      </div>
      <div class="comment-cnt">{{review.commentCnt}}<div>답변</div></div>
      <hr/>
    </div>
  </div>
</template>


<script>
export default {
  props: {
    reviewList: Array,
  },
  methods: {
    onGoToReviewDetail(id){
      this.$router.push({
				name: 'CourseDetail',
        params: { type: 'reviewDetail', id: id }
      });
    }
  }
}
</script>

<style>
.course-review-container{
  text-align: left;
}

.course-review-container .review-item{
  cursor: pointer;
  padding: 20px 20px 0px 20px;
}

.course-review-container .review-item-left{
  display: inline-block;
  width: 90%;
}

.course-review-container .title{
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 20px;
}

.course-review-container .detail{
  font-size: 0.8rem;
  color: darkgray;
}

.course-review-container .comment-cnt{
  float: right;
  display: block;
  border-radius: 50%;
  border: 1.5px solid darkgray;
  width: 70px;
  height: 70px;
  text-align: center;
  font-size: 1.5rem;
  font-weight: bold;
}

.course-review-container .comment-cnt div{
  font-size: 0.8rem;
  font-weight: lighter;
}

@media screen and (max-width: 1500px) {
  .course-review-container .review-item-left{
    width: 80%;
  }
}

@media screen and (max-width: 580px) {
  .course-review-container .review-item-left{
    width: 50%;
  }
}
</style>
