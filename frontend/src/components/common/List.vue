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
      },
    }
  },
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
        params: { 
          type: 'reviewDetail', 
          id: id 
        }
      });
    },
    onGoToNoticeDetail(id){
      this.$router.push({
				name: 'NoticeDetail',
        params: { number: id }
      });
    },
    onClickOpenModal(){
      this.openModal = true;
    },
    onClickCloseModal(){
      this.openModal = false;
    },
    submitReview(){
      this.board.content = this.$refs.content.invoke("getMarkdown");
      alert("작성요청 던지기");
      this.onClickCloseModal();
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
