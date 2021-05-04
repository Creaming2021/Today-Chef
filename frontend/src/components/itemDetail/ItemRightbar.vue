<template>
  <div class="col-lg-3 course-right-bar-container">
    <div class="course-detail-header">
      <b-badge variant="primary">{{itemInfo.category}}</b-badge>
      {{itemInfo.date}}</div>
    <h4>{{itemInfo.name}}</h4>
    <div class="row detail-info">
      <h5 class="teacher">{{itemInfo.teacher}}</h5>
      <h5 class="price">{{itemInfo.price}}원</h5>
    </div>
    <div class="row">
      <div class="like-btn" @click="setLike">
        <b-icon v-if="likeState" icon="heart-fill"/> 
        <b-icon v-else icon="heart"/> 
        <div>{{itemInfo.likeCnt}}</div></div>
      <b-form-rating v-model="itemInfo.rate" readonly no-border/>
    </div>
    <div v-if="role=='student'">
      <button v-if="state=='before'">클래스 신청하기</button>
      <button v-if="state=='waiting'">클래스 준비중</button>
      <button v-if="state=='streaming'">클래스 참여하기</button>
      <button v-if="state=='after'">리뷰 작성하기</button>
      <br/>
    </div>
    <div v-else>
      <br/>
      <button @click="goToManageCourse">강의 정보 관리</button><br/>
      <button @click="startStreaming">수업 시작하기</button><br/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      likeState: false,
      itemInfo: {}
    }
  },
  props: {
    state: String,
    role: String,
  },
  created() {
    this.settingItemInfo();
  },
  computed: {
    ...mapState({
      course: state => state.course.course,
      product: state => state.product.product,
    }),
  },
  methods: {
    setLike() {
      this.likeState = !this.likeState;
    },
    goToManageCourse(){
      alert("강의 관리 페이지 이동");
    },
    startStreaming(){
      alert("수업 방송 시작");
    },
    settingItemInfo(){
      let item = this.$route.params.item;
      this.itemInfo = item === 'course' ? this.course : this.product ;
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

  background-color: #f3f2ee;
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
  background-color: white;
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
