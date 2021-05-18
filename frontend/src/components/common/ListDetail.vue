<template>
  <div class="item-detail-container">
    <div class="header">
      <div class="back" @click="goBack()">뒤로 가기</div>
      <template v-if="computedDetail.profile">
        <div class="item-info row">
          <img 
            class="profile"
            :src="computedDetail.profile.profileImage || 'https://www.edmundsgovtech.com/wp-content/uploads/2020/01/default-picture_0_0.png'"/>
          <div>
            <div class="writer">{{computedDetail.profile.nickname}}</div><br/>
            <div class="date">{{computedDetail.createdDate.substring(0, 10)}}</div>
          </div>
        </div>
        <div class="title">{{computedDetail.title}}</div>
        <div v-if="type === 'reviewDetail' && item === 'course'" class="comment-size">
          <b-icon icon="chat-square"/>
            {{computedComments.length}}
        </div>
      </template>
    </div>

    <br>
    <hr/>
    <viewer ref="viewer"/>

    <div v-if="type === 'reviewDetail' && item === 'course'" class="comment-container">
      <hr/>
      <div>댓글 {{computedComments.length}}개</div>
      <template v-if="computedComments.length > 0">
        <div v-for="comment in computedComments" v-bind:key="comment.commentId">
          <img 
            class="profile"
            :src="comment.profile.profileImage  || 'https://www.edmundsgovtech.com/wp-content/uploads/2020/01/default-picture_0_0.png'"/>
          <div>
            <div class="writer">{{comment.profile.nickname}}</div><br/>
            <div class="date">{{comment.date}}</div>
          </div>
          <div class="content">{{comment.content}}</div>
        </div>
      </template>
      <template v-else>
        댓글이 아직 없어요
      </template>
      <input 
        v-if="memberId !== ''"
        @keyup.enter="submitComment"
        v-model="comment"
        placeholder="댓글을 입력하세요."/> 
    </div>
  </div>
</template>


<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    Viewer,
  },
  data() {
    return {
      item: '',
      type: '',
      detail: {},
      comments: [],
      comment: '',
      content: '',
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.review,
      courseComment: state => state.course.reviewCommentList,
      product: state => state.product.review,
      // productComment: state => state.product.reviewCommentList,
      memberId: state => state.user.memberId,
    }),
    computedDetail() {
      return this.settingDetail();
    },
    computedComments() {
      return this.settingComments();
    },
  },
  created() {
    this.getReview();
    
  },
  mounted() {
    this.settingDetail();
    this.settingComments();
    this.settingViewerValue();
  },
  methods: {
    goBack(){
      this.$router.go(-1);
    },
    getReview(){
      this.item = this.$route.params.item;
      let id = this.$route.params.reviewId;
      if(this.item === 'course'){
        this.$store.dispatch('GET_COURSE_REVIEW', id);
        // this.$store.dispatch('GET_COURSE_REVIEW_COMMENT_LIST', id);
      } else if(this.item === 'product'){
        this.$store.dispatch('GET_PRODUCT_REVIEW', id);
        // this.$store.dispatch('GET_PRODUCT_REVIEW_COMMENT_LIST', id);
      }
    },
    settingDetail(){
      this.type = this.$route.params.type;
      let item = this.$route.params.item;
      const result = item === 'course' ? this.course : this.product;
      this.content = result.content;
      return result;
    },
    settingComments() {
      this.type = this.$route.params.type;
      let item = this.$route.params.item;
      return item === 'course' && this.courseComment;
    },
    submitComment() {
      let item = this.$route.params.item;
      if(item === 'course'){
        this.$store.dispatch('POST_COURSE_REVIEW_COMMENT', 
          {
            reviewId: this.$route.params.reviewId,
            memberId: this.memberId,
            content: this.comment,
            courseId: this.courseId,
          });
      } else if(item === 'product'){
        this.$store.dispatch('POST_PRODUCT_REVIEW_COMMENT', 
          {
            reviewId: this.$route.params.reviewId,
            memberId: this.memberId,
            content: this.comment,
            courseId: this.courseId,
          });
      }
      this.comment = '';
    },
    settingViewerValue() {
      this.$refs.viewer && this.$refs.viewer.invoke('setMarkdown', this.computedDetail.content);
    },
  },
  watch: {
    computedDetail: function(){
      this.settingViewerValue();
    },
  },
}
</script>

<style scoped>
.item-detail-container{
  padding: 10px;
}

.item-detail-container img{
  display: inline-block;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  object-fit: cover;
}

.item-detail-container .writer{ 
  display: inline;
  font-size: 1rem;
  font-weight: bold;
  color: #696969;
}

.item-detail-container .date{ 
  display: inline;
  font-size: 0.8rem;
  color: #979797;
}

.item-detail-container .header{
  text-align: left;
}

.item-detail-container .back{
  cursor: pointer;
  margin-bottom: 20px;
}

.item-detail-container .header .item-info{
  float: left;
  display: block;
  height: 80px;
  margin-left: 10px;
}

.item-detail-container .header .item-info > div, 
.item-detail-container .comment-container > div > div{
  display: inline-block;
  height: 80px;
  margin-left: 10px;
}

.item-detail-container .header .comment-size{
  float: right;
  display: block;
  font-weight: bold;
  font-size: 1.25rem;
  color: darkgray
}

.item-detail-container .title{
  text-align: center;
  font-weight: bold;
  font-size: 1.5rem;
  color: black
}

.item-detail-container .comment-container {
  text-align: left;
}

.item-detail-container .comment-container > input{
  display: block;
  margin: 20px 0px 20px 0px;
  border-radius: 25px;
  border: 1px solid darkgray;
  width: 100%;
  height: 50px;
  padding-left: 20px;
}

.item-detail-container .comment-container .content{
  text-align: center;
}
</style>