<template>
  <div class="course-qna-container">
    <button 
      class="write-btn"
      @click="onClickOpenModal">QnA 작성하기</button>
    <div class="qna-container" v-for="qna in qnaList" v-bind:key="qna.id">
      <div class="user-info">
        <img :src="qna.profile"/>
        <div>
          <div class="writer">{{qna.writer}}</div>
          <div class="date">{{qna.date}}</div>
        </div>
      </div>
      <div class="content">
        {{qna.content}}
      </div>
      <div class="answer-list-container">
        <div class="answer-container" v-for="answer in qna.answerList" v-bind:key="answer.id">
          <div class="user-info">
            <img :src="answer.profile"/>
            <div>
              <div class="writer">{{answer.writer}}</div>
              <div class="date">{{answer.date}}</div>
            </div>
          </div>
          <div class="content">
            {{answer.content}}
          </div>
        </div>
        <input placeholder="답변을 입력하세요."/> 
      </div>
      <hr/>
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
  props: {
    qnaList: Array,
  },
  data() {
    return {
      openModal: false,
      board: {
        title: '',
        content: '',
      },
    }
  },
  methods: {
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
  }
}
</script>

<style>
.course-qna-container {
  text-align: left;
}

.course-qna-container .user-info {
  margin-bottom: 10px;
}

.course-qna-container .user-info .writer {
  font-weight: bold;
  color: #474747;
}

.course-qna-container .user-info .date {
  color: #666666;
}

.course-qna-container .user-info > img{
  display: inline-block;
  margin: 10px;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  object-fit: cover;
}

.course-qna-container .user-info > div{
  display: inline-block;
  font-size: 1rem;
}

.course-qna-container .answer-list-container{
  margin: 20px 0px 20px 0px;
}

.course-qna-container .answer-list-container .answer-container{
  margin-bottom: 20px;
  background-color: #f3f2ee;
  padding: 20px;
  border-radius: 10px;
}

.course-qna-container .answer-list-container .user-info > img{
  display: inline-block;
  margin: 10px;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  object-fit: cover;
}

.course-qna-container .answer-list-container .user-info > div{
  display: inline-block;
  font-size: 0.8rem;
  font-weight: normal;
  color: #666666;
}

.course-qna-container .answer-list-container > input{
  display: block;
  margin: 20px 0px 20px 0px;
  border-radius: 25px;
  border: 1px solid darkgray;
  width: 100%;
  height: 50px;
  padding-left: 20px;
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
</style>
