<template>
  <div class="item-detail-container">
    <div class="header">
      <div class="back" @click="goBack()">뒤로 가기</div>
      <div class="item-info">
        <img 
          class="profile"
          :src="detail.profile.profileImage"/>
        <div>
          <div class="writer">{{detail.profile.nickname}}</div><br/>
          <div class="date">{{detail.date}}</div>
        </div>
      </div>
      <div class="title">{{detail.title}}</div>
      <div v-if="type === 'reviewDetail'" class="comment-size">
        <b-icon icon="chat-square"/>
          {{commentList.length}}
      </div>
    </div>
    <hr/>
    <viewer 
      :initialValue="detail.content" 
      height="500px"/>
    <div v-if="type === 'reviewDetail'" class="comment-container">
      <hr/>
      <div>댓글 {{commentList.length}}개</div>
      <div v-for="comment in commentList" v-bind:key="comment.commentId">
        <img 
          class="profile"
          :src="comment.profile.profileImage"/>
        <div>
          <div class="writer">{{comment.profile.nickname}}</div><br/>
          <div class="date">{{comment.date}}</div>
        </div>
        <div class="content">{{comment.content}}</div>
      </div>
      <input v-model="comment" placeholder="댓글을 입력하세요."/>
    </div>
  </div>
</template>


<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    // Viewer,
    'viewer': Viewer
  },
  data() {
    return {
      item: '',
      type: '',
      detail: {},
      commentList: [],
      comment: '',
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.review,
      courseComment: state => state.course.reviewCommentList,
      product: state => state.product.review,
      productComment: state => state.product.reviewCommentList,
      userId: state => state.user.userId,
    }),
  },
  created() {
    this.getReview();
    this.settingItem();
  },
  methods: {
    goBack(){
      this.$router.go(-1);
    },
    getReview(){
      let item = this.$route.params.item;
      let id = this.$route.params.id;
      if(item === 'course'){
        this.$store.dispatch('GET_COURSE_REVIEW', id);
        this.$store.dispatch('GET_COURSE_REVIEW_COMMENT_LIST', id);
      } else if(item === 'product'){
        this.$store.dispatch('GET_PRODUCT_REVIEW', id);
        this.$store.dispatch('GET_PRODUCT_REVIEW_COMMENT_LIST', id);
      }
    },
    settingItem(){
      this.type = this.$route.params.type;

      let item = this.$route.params.item;
      this.detail = item === 'course' ? this.course : this.product;
      this.commentList = item === 'course' ? this.courseComment : this.productComment;
    }
  }
}
</script>

<style>
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