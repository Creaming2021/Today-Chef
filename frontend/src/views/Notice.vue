<template>
  <div class="notice-container">
    <div class="body">
      <div v-if="view === 'list'">
      <div class="body-header">공지사항</div>
        <List
          :type="type"
          :list="noticeList"
          :pageTotal="pageTotal"
          :currentPage="currentPage"/>
      </div>
      <ListDetail
        v-if="view === 'detail'"
        :type="type"
        :item="noticeInfo"/>
    </div>
  </div>
</template>

<script>
import List from '@/components/common/List.vue';
import ListDetail from '@/components/common/ListDetail.vue';

export default {
  data(){
    return {
      type: 'notice',
      view: 'list',
      pageTotal: 5,
      currentPage: 1,
      noticeList: [
        {
          id: 1,
          profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          readCnt: 2 
        },
        {
          id: 2,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          readCnt: 0
        },
        {
          id: 3,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          readCnt: 0
        },
        {
          id: 4,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          readCnt: 0
        },
        {
          id: 5,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          readCnt: 0
        }
      ],
      noticeInfo: {
        id: 1,
        profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        title: '이벤트 제목',
        content: '이벤트 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
        readCnt: 2,
      },
    }
  },
  components: {
    List,
    ListDetail,
  },
  methods: {
    checkQuery() {
      const id = this.$route.params.number;
      if(id){
        this.getNoticeInfo(this.$route.params.number);
        this.view = 'detail';
      }else{
        this.view = 'list';
      }
    },
    getNoticeInfo(id){
      alert(`${id}로 요청 때립니다.`);
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
.notice-container .body-header{
  text-align: center;
  width: 100%;
  height: 200px;
  background-color: #f3f2ee;
  font-weight: bold;
  font-size: 2rem;
  line-height: 200px;
  margin-bottom: 50px;
}

.notice-container .body{
  width: 60%;
  margin: auto;
  padding-bottom: 100px; 
}
</style>