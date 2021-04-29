<template>
  <div class="item-detail-container">
    <div class="header">
      <div class="back" @click="goBack()">뒤로 가기</div>
      <div class="item-info">
        <img 
          class="profile"
          :src="item.profile"/>
        <div>
          <div class="writer">{{item.writer}}</div><br/>
          <div class="date">{{item.date}}</div>
        </div>
      </div>
      <div class="title">{{item.title}}</div>
      <div v-if="type === 'reviewDetail'" class="comment-size">
        <b-icon icon="chat-square"/>
        {{item.commentList.length}}</div>
      <div v-if="type === 'notice'" class="comment-size">
        <b-icon icon="chat-square"/>{{item.readCnt}}</div>
    </div>
    <hr/>
    <div class="content">{{item.content}}</div>
    <div v-if="type === 'reviewDetail'" class="comment-container">
      <hr/>
      <div>댓글 {{item.commentList.length}}개</div>
      <div v-for="comment in item.commentList" v-bind:key="comment.id">
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
    item: Object,
		type: String,
  },
  methods: {
    goBack(){
      this.$router.go(-1);
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

.item-detail-container .comment-container .content{
  text-align: center;
}
</style>