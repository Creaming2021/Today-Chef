<template>
  <div class="course-qna-container">
    <button 
      class="write-btn"
      @click="onClickOpenModal">QnA 작성하기</button>
    <div class="qna-container" v-for="qna in computedQnaList" v-bind:key="qna.id">
      <ItemQnaDetail :qna="qna"/>
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
        @click="submitQna"
        class="write-btn board-submit">QnA 작성 완료</button>
    </b-modal>

  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Editor } from '@toast-ui/vue-editor';
import ItemQnaDetail from '@/components/itemDetail/ItemQnaDetail.vue';

export default {
  components: {
    editor: Editor,
    ItemQnaDetail,
  },
  data() {
    return {
      openModal: false,
      board: {
        title: '',
        content: '',
      },
      comment: '',
      qnaList: [],
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.qnaList,
      product: state => state.product.qnaList,
      memberId: state => state.user.memberId,
    }),
    computedQnaList() {
      return this.settingQnaList();
    }
  },
  created() {
    this.getQnaList();
    this.settingQnaList();
  },
  methods: {
    onClickOpenModal(){
      this.openModal = true;
    },
    onClickCloseModal(){
      this.openModal = false;
    },
    settingQnaList(){
      let item = this.$route.params.item;
      return item === 'course' ? this.course : this.product;
    },
    getQnaList(){
      let item = this.$route.params.item;
      let id = this.$route.params.id;
      if(item === 'course'){
        this.$store.dispatch('GET_COURSE_QNA_LIST', id);
      } else if(item === 'product'){
        this.$store.dispatch('GET_PRODUCT_QNA_LIST', id);
      }
    },
    submitQna(){
      this.board.content = this.$refs.content.invoke("getMarkdown");
      
      let item = this.$route.params.item;
      let id = Number(this.$route.params.id);
      if(item === 'course'){

        this.$store.dispatch('POST_COURSE_QNA', 
          {
            ...this.board,
            memberId: this.memberId,
            courseId: id,
            isSecret: false,
          });
      } else if(item === 'product'){
        this.$store.dispatch('POST_PRODUCT_QNA', 
          {
            ...this.board,
            memberId: this.memberId,
            productId: id,
            isSecret: false,
          });
      }
      this.settingQnaList();
      this.onClickCloseModal();
    },
  },
}
</script>

<style scoped>
.course-qna-container {
  text-align: left;
  margin-bottom: 100px;
}

.course-qna-container .user-info {
  margin-bottom: 10px;
}

.course-qna-container .user-info .title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #474747;
}

.course-qna-container .user-info .writer {
  font-size: 1rem;
  font-weight: bold;
  color: #474747;
}

.course-qna-container .user-info .date {
  font-size: 0.8rem;
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

  /* position: absolute; */
  /* right: 20px; */
  margin-bottom: 50px;
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
