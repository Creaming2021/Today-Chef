<template>
  <div class="course-review-detail-container">
    <div class="header">
      <div class="review-info">
        <img 
          class="profile"
          :src="reviewInfo.profile"/>
        <div>
          <div class="writer">{{reviewInfo.writer}}</div><br/>
          <div class="date">{{reviewInfo.date}}</div>
        </div>
      </div>
      <div class="title">{{reviewInfo.title}}</div>
      <div class="comment-size">
        <b-icon icon="chat-square"/>
        {{reviewInfo.commentList.length}}</div>
    </div>
    <hr/>
    <div class="content">{{reviewInfo.content}}</div><hr/>
    <div class="comment-container">
      <div>댓글 {{reviewInfo.commentList.length}}개</div>
      <div v-for="comment in reviewInfo.commentList" v-bind:key="comment.id">
        <img 
          class="profile"
          :src="comment.profile"/>
        <div>
          <div class="writer">{{comment.writer}}</div><br/>
          <div class="date">{{comment.date}}</div>
        </div>
        <div class="content">{{comment.content}}</div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  props: {
    reviewInfo: Object,
  }
}
</script>

<style>
.course-review-detail-container{
  padding: 10px;
}

.course-review-detail-container img{
  display: inline-block;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  object-fit: cover;
}

.course-review-detail-container .writer{ 
  display: inline;
  font-size: 1rem;
  font-weight: bold;
  color: #696969;
}

.course-review-detail-container .date{ 
  display: inline;
  font-size: 0.8rem;
  color: #979797;
}

.course-review-detail-container .header{
  text-align: left;
}

.course-review-detail-container .header .review-info{
  float: left;
  display: block;
  height: 80px;
  margin-left: 10px;
}

.course-review-detail-container .header .review-info > div, 
.course-review-detail-container .comment-container > div > div{
  display: inline-block;
  height: 80px;
  margin-left: 10px;
}

.course-review-detail-container .header .comment-size{
  float: right;
  display: block;
  font-weight: bold;
  font-size: 1.25rem;
  color: darkgray
}

.course-review-detail-container .title{
  text-align: center;
  font-weight: bold;
  font-size: 1.5rem;
  color: black
}

.course-review-detail-container .comment-container {
  text-align: left;
}

.course-review-detail-container .comment-container .content{
  text-align: center;
}
</style>
