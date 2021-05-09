<template>
  <div class="col-lg-3 course-right-bar-container">
    <div class="course-detail-header">
      <b-badge variant="primary">{{itemInfo.category}}</b-badge>
      <span  v-if="item === 'course'">{{itemInfo.time}}</span>
    </div>
    <h4>{{itemInfo.name}}</h4>
    <div class="row detail-info">
      <h5 v-if="item === 'course'" class="teacher">{{itemInfo.profile.nickname}}</h5>
      <h5 class="price">{{itemInfo.price}}원</h5>
    </div>
    <b-form-rating v-model="itemInfo.rating" readonly no-border/>
    <div class="cart-btn" @click="addCart">
      <b-icon icon="cart4"/> 
      <div>장바구니에 추가하기</div>
    </div>
    <div v-if="item === 'course' && user === 'item.profile.memberId'">
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
      item: '',
      likeState: false,
    }
  },
  created() {
    this.settingItemInfo();
  },
  computed: {
    ...mapState({
      user: state => state.user.memberId,
      course: state => state.course.course,
      product: state => state.product.product,
    }),
    itemInfo: function(){
      return this.settingItemInfo();
    }
  },
  methods: {
    addCart() {
      alert("장바구니 이동");
    },
    settingItemInfo(){
      this.item = this.$route.params.item;
      return this.item === 'course' ? this.course : this.product;
    }
  },
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

.course-right-bar-container .cart-btn{
  width: 100%;
}

.course-right-bar-container button,
.course-right-bar-container .cart-btn{
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
  width: 100%;
  background: none;
}
</style>
