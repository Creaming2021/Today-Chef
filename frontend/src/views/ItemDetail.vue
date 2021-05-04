<template>
  <div class="course-detail-container">
    <ItemThumbnail
      :item="item"/>
    <div class="row">
      <ItemDetailMain/>
      <ItemRightbar 
        :item="item"
        :courseInfo="courseInfo"
        :role="teacher"
        :state="state"/>
      <ItemRightbar 
        :item="item"
        :courseInfo="courseInfo"
        :role="student"
        :state="stateBefore"/>
      <ItemRightbar 
        :item="item"
        :courseInfo="courseInfo"
        :role="student"
        :state="stateWaiting"/>
      <ItemRightbar 
        :item="item"
        :courseInfo="courseInfo"
        :role="student"
        :state="stateStreaming"/>
      <ItemRightbar 
        :item="item"
        :courseInfo="courseInfo"
        :role="student"
        :state="stateAfter"/>
    </div>
  </div>
</template>

<script>
import ItemThumbnail from '@/components/itemDetail/ItemThumbnail.vue';
import ItemDetailMain from '@/components/itemDetail/ItemDetailMain.vue';
import ItemRightbar from '@/components/itemDetail/ItemRightbar.vue';

export default {
  created () {
    this.checkQuery();
  },
  data(){
    return {
      item: '',
      courseInfo : {
        id: 1,
        category: '한식',
        date: '2021.04.16',
        name: '쉽게 배워보는 갈비찜',
        teacher: '김한식',
        price: '50000',
        likeCnt: '123',
        rate: '3.5',
      },
      student: 'student',
      teacher: 'teacher',
      stateBefore: 'before',
      stateWaiting: 'waiting',
      stateStreaming: 'streaming',
      stateAfter: 'after',
    }
  },
  components: {
    ItemThumbnail,
    ItemDetailMain,
    ItemRightbar,
  },
  methods: {
    checkQuery() {
      this.item = this.$route.params.item;
      this.getCourseDetailInfo();
    },
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    } 
  },
}
</script>

<style>
.course-detail-container{
  width: 70%;
  text-align: center;
  margin: auto;
}
</style>
