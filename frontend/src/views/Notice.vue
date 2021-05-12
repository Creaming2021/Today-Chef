<template>
  <div class="notice-container">
    <div class="body-header">공지사항</div>
    <div class="list-container">
      <button 
        v-if="role"
        class="write-btn"
        @click="onClickOpenModal">공지사항 작성하기</button>
      <div 
        class="list-item" 
        v-for="item in eventList" 
        :key="item.id"
        @click="onGoToNoticeDetail(item.id)">
          <span class="title">{{item.title}}</span>
          <span class="detail">{{item.date.substring(0,10)}}</span>
        <hr/>
      </div>
      <!-- <div class="row">
        <div class="col-lg-12">
          <div class="product__pagination">
            <a v-for="i in pageTotal" :key="i"
              :class="i === currentPage && 'active'">
              {{i}}
            </a>
          </div>
        </div>
      </div> -->
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
          @click="submitNotice"
          class="write-btn board-submit">공지사항 작성 완료</button>
      </b-modal>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Editor } from '@toast-ui/vue-editor';

export default {
  data(){
    return {
      openModal: false,
      board: {
        title: '',
        content: '',
      }
    }
  },
  created(){
    this.getNoticeList();
  },
  components: {
    editor: Editor,
  },
  computed: {
    ...mapState({
      role: state => state.user.role,
      eventList: state => state.event.eventList,
    }),
  },
  methods: {
    getNoticeList(){
      this.$store.dispatch('GET_EVENT_LIST');
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
    submitNotice(){
      this.board.content = this.$refs.content.invoke("getMarkdown");

      this.$store.dispatch('POST_EVENT', this.board);
      this.onClickCloseModal();
    },
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    } 
  }
}
</script>

<style>
.list-container {
  width: 60%;
  margin: auto;
  padding-bottom: 100px; 
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
}

.list-container .title{
  display: inline-block;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 20px;
  width: 90%;
  padding: 20px 20px 0px 20px;
}

.list-container .detail{
  display: inline-block;
  font-size: 1rem;
  color: darkgray;
  font-weight: bold;
  width: 10%;
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

/* @media screen and (max-width: 1500px) {
  .list-container .list-item-left{
    width: 80%;
  }
}

@media screen and (max-width: 580px) {
  .list-container .list-item-left{
    width: 50%;
  }
} */
</style>