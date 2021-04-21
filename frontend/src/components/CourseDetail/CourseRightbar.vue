<template>
  <div class="col-lg-3 course-right-bar-container">
		<div class="course-detail-header">
      <b-badge>{{courseInfo.category}}</b-badge>
      {{courseInfo.date}}</div>
		<h4>{{courseInfo.name}}</h4>
		<div class="row detail-info">
      <h5 class="teacher">{{courseInfo.teacher}}</h5>
		  <h5 class="price">{{courseInfo.price}}원</h5>
    </div>
    <div class="row">
      <div class="like-btn" @click="setLike">
        <b-icon v-if="!likeState" icon="heart"/> 
        <b-icon v-if="likeState" icon="heart-fill"/> 
        <div>{{courseInfo.likeCnt}}</div></div>
      <b-form-rating v-model="courseInfo.rate" readonly no-border/>
    </div>
    <button>클래스 신청하기</button><br/>
  </div>
</template>


<script>

export default {
  data() {
    return {
      likeState: false,
    }
  },
  props: {
    courseInfo: Object,
  },
  methods: {
    setLike() {
      this.likeState = !this.likeState;
    }
  }
}
</script>

<style>
.course-right-bar-container{
  display: inline-block;
  width: 30%;
  height: inherit;
  padding: 10px;

  background-color: lightgrey;
  border-radius: 10px;

  margin-bottom: 100px;
}

.course-detail-header{
  text-align: left;
  margin-bottom: 10px;
}

.course-right-bar-container h4{
  font-weight: bold;
  margin: 20px 0px 20px 0px;
}

.course-right-bar-container .detail-info .teacher{
  width: 50%;
  text-align: left;
  padding-left: 5%;
}

.course-right-bar-container .detail-info .price{
  width: 50%;
  text-align: right;
  padding-right: 5%;
}

.course-right-bar-container .like-btn{
  width: 30%;
}

.course-right-bar-container button,
.course-right-bar-container .like-btn{
  cursor: pointer;
}

.course-right-bar-container button{
  display: block;
  margin: auto;
  border: none;
  border-radius: 10px;
  background-color: rgb(75, 75, 75);
  width: 90%;
  height: 50px;
  font-weight: bold;
}

.course-right-bar-container .row{
  margin: auto;
  margin-bottom: 20px;
}

.course-right-bar-container .b-icon{
  line-height: 100px;
}

.course-right-bar-container .b-rating{
  width: 70%;
  background: none;
}


</style>
