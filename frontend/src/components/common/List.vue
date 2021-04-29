<template>
  <div class="list-container">
    <div 
      class="list-item" 
      v-for="item in list" 
      v-bind:key="item.id"
      @click="type === 'review' 
              ? onGoToReviewDetail(item.id)
              : onGoToNoticeDetail(item.id)">
      <div class="list-item-left">
        <div class="title">{{item.title}}</div>
        <h6 v-if="type === 'review'">{{item.content}}</h6>
        <div class="detail">{{item.writer}} | {{item.date}}</div>
      </div>
      <div class="list-item-cnt" v-if="type === 'review'">
        {{item.commentCnt}}<div>답변</div></div>
      <div class="list-item-cnt" v-else-if="type === 'notice'">
        {{item.readCnt}}<div>조회 수</div></div>
      <hr/>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="product__pagination">
          <a v-for="i in pageTotal" :key="i"
            v-bind:class="i === currentPage && 'active'">
            {{i}}
          </a>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  props: {
    type: String,
    list: Array,
    pageTotal: Number,
    currentPage: Number,
  },
  methods: {
    onGoToReviewDetail(id){
      this.$router.push({
        name: 'CourseDetail',
        params: { type: 'reviewDetail', id: id }
      });
    },
    onGoToNoticeDetail(id){
      this.$router.push({
				name: 'NoticeDetail',
        params: { number: id }
      });
    },
  },
}
</script>

<style>
.list-container{
  text-align: left;
}

.list-container .list-item{
  cursor: pointer;
  padding: 20px 20px 0px 20px;
}

.list-container .list-item-left{
  display: inline-block;
  width: 90%;
}

.list-container .title{
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 20px;
}

.list-container .detail{
  font-size: 0.8rem;
  color: darkgray;
}

.list-container .list-item-cnt{
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

.list-container .list-item-cnt div{
  font-size: 0.8rem;
  font-weight: lighter;
}

@media screen and (max-width: 1500px) {
  .list-container .list-item-left{
    width: 80%;
  }
}

@media screen and (max-width: 580px) {
  .list-container .list-item-left{
    width: 50%;
  }
}
</style>
