<template>
  <div class="list-container">
    <button 
      v-if="type === 'review'" 
      class="write-btn"
      @click="onClickOpenModal">리뷰 작성하기</button>
    <button 
      v-else-if="type === 'notice'" 
      class="write-btn"
      @click="onClickOpenModal">공지사항 작성하기</button>
    <div 
      class="list-item" 
      v-for="item in list" 
      v-bind:key="item.reviewId"
      @click="type === 'review' 
              ? onGoToReviewDetail(item.reviewId)
              : onGoToNoticeDetail(item.reviewId)">
      <div class="list-item-thumbnail" 
        v-if="type === 'notice'">
          <img :src="item.profile.profileImage"/>
      </div>
      <div class="list-item-left">
        <div class="title">{{item.title}}</div>
        <h6 v-if="type === 'review'">{{item.content}}</h6>
        <div class="detail">{{item.profile.nickname}} | {{item.createdDate}}</div>
      </div>
      <div class="list-item-cnt" 
        v-if="type === 'review'">
          {{item.commentCount}}<div>답변</div></div>
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
    <b-modal v-model="openModal" size="xl" centered hide-footer hide-header>
      <p class="modal-btn" @click="onClickCloseModal">X</p>
      <input 
        class="board-title"
        v-model="board.title" 
        placeholder="제목을 입력하세요."/>
      <editor
        ref="content"
        height="650px"
        editType="wysiwyg"
        placeholder="내용을 작성하세요."
        previewStyle="tab"
      />
      <button 
        @click="submitReview"
        class="write-btn board-submit">
          {{type === 'review' ? '리뷰 작성 완료' : '공지사항 작성 완료'}}</button>
    </b-modal>
  </div>
</template>


<script>
import { mapState } from 'vuex';
import { Editor } from '@toast-ui/vue-editor';

export default {
  components: {
    editor: Editor,
  },
  data(){
    return {
      openModal: false,
      test:'12345',
      board: {
        title: '',
        content: '',
        rating: 0,
      },
      list: [],
      type: '',
      pageTotal: 1,
      currentPage: 1,
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.reviewList,
      product: state => state.product.reviewList,
      userId: state => state.user.userId,
    }),
  },
  created() {
    this.getReviewList();
    this.settingList();
  },
  methods: {
    onGoToReviewDetail(id){
      this.$router.push({
        name: 'ItemDetail',
        params: { 
          item: this.$route.params.item,
          category: this.$route.params.category,
          type: 'reviewDetail', 
          id: id,
        }
      });
    },
    onGoToNoticeDetail(id){
      this.$router.push({
				name: 'NoticeDetail',
        params: { number: id },
      });
    },
    onClickOpenModal(){
      this.openModal = true;
    },
    onClickCloseModal(){
      this.openModal = false;
    },
    getReviewList(){
      let item = this.$route.params.item;
      let id = this.$route.params.id;
      if(item === 'course'){
        this.$store.dispatch('GET_COURSE_REVIEW_LIST', id);
      } else if(item === 'product'){
        this.$store.dispatch('GET_PRODUCT_REVIEW_LIST', id);
      }
    },
    submitReview(){
      this.board.content = this.$refs.content.invoke("getMarkdown");

      let item = this.$route.params.item;
      if(item === 'course'){
        this.$store.dispatch('POST_COURSE_REVIEW', 
          {
            ...this.board,
            courseId: this.$route.params.id,
            memberId: this.userId,
          });
      } else if(item === 'product'){
        this.$store.dispatch('POST_PRODUCT_REVIEW', 
          {
            ...this.board,
            productId: this.$route.params.id,
            memberId: this.userId,
          });
      }
      this.onClickCloseModal();
    },
    settingList(){
      this.type = this.$route.params.type;

      let item = this.$route.params.item;
      this.list = item === 'course' ? this.course : this.product;
    }
  },
}
</script>

<style>
.list-container{
  text-align: left;
}

.write-btn{
  font-size: 0.8rem;
  border: none;
  border-radius: 10px;
  width: 150px;
  height: 40px;
  background-color: #f3f2ee;
  font-weight: bold;

  /* position: absolute;
  right: 20px;
  margin-bottom: 50px; */
}

.write-btn:hover{
  color: #f3f2ee;
  background-color: #e53637;
}

.board-submit {
  margin: 20px 0px 10px 0px;
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

.list-container .list-item-thumbnail{
  float: right;
  display: block;
  width: 70px;
  height: 70px;
}


.list-container .list-item-cnt div{
  font-size: 0.8rem;
  font-weight: lighter;
}

.modal-btn{
  text-align: right;
  cursor: pointer;
}

.board-title{
  width: 100%;
  height: 50px;
  padding-left: 10px;
  margin: 0px auto 20px auto;
  border-radius: 10px;
  border: 1px solid darkgray;

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
