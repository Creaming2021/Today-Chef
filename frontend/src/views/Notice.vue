<template>
  <div class="notice-container">
    <div class="body-header">공지사항</div>
    <div class="body">
      <div v-if="view === 'list'">
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
          imageUrl: 'https://lh3.googleusercontent.com/proxy/Fpk7E8Oc7gPcDsmdsYGN-i48HlRb0FAHdRO5KPQb6jbc9JayNkGtnWOiKDvBBtSYM8YOcWUXHiHP8_66c6nV0XJHeJvyFK7T4OZcqVuM1DGqbelzpVAA',
        },
        {
          id: 2,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          imageUrl: 'https://i.pinimg.com/originals/ad/e2/b3/ade2b3d4952f21339792c153b8fea8f0.png',
        },
        {
          id: 3,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          imageUrl: 'https://lh3.googleusercontent.com/proxy/UskfKbmMMCFSb1DW1k1QEfckkUZNo4RdqbTfjb7MwUnmUI6Y_cDTvljo8pW0h9S9wu0xeg5TPxtReGQVHBKl9Gch_SPRnf75GY1-oNRt8OfPKRp_7SP6LvHKBCowGo97YIuj53owjtOrRqyjS04HHUpaArq31h-EE97EIP4zA8HPPxWcivQ',
        },
        {
          id: 4,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          imageUrl: 'http://imagescdn.gettyimagesbank.com/500/201807/jv11177023.jpg',
        },
        {
          id: 5,
          profile:'https://t1.daumcdn.net/liveboard/styleade/b27fc94234c34dbc9240f1943cb2f0b5.png',
          title: '이벤트 제목',
          writer: '작성자 닉네임',
          date: '2021년 4월 21일',
          imageUrl: 'https://cdn.imweb.me/upload/S20191001749a3c1500682/2c7eec1aec69e.png',
        }
      ],
      noticeInfo: {
        id: 1,
        profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
        title: '이벤트 제목',
        content: '이벤트 내용',
        writer: '작성자 닉네임',
        date: '2021년 4월 21일',
          imageUrl: 'https://www.kyeonggi.com/news/photo/201907/2138026_928105_1930.jpg',
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

.notice-container .body{
  width: 60%;
  margin: auto;
  padding-bottom: 100px; 
}
</style>