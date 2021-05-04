<template>
  <div class="course-detail-container">
    <ItemThumbnail/>
    <div class="row">
      <ItemDetailMain/>
      <ItemRightbar 
        :role="teacher"
        :state="state"/>
      <ItemRightbar 
        :role="student"
        :state="stateBefore"/>
      <ItemRightbar 
        :role="student"
        :state="stateWaiting"/>
      <ItemRightbar 
        :role="student"
        :state="stateStreaming"/>
      <ItemRightbar 
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
      id: '',
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
      this.id = this.$route.params.id;
      // this.getCourseDetailInfo();
    },
    getCourseDetailInfo(){
      if(this.item === 'course'){
        this.$store.dispatch('GET_COURSE', this.id);
      } else if(this.item === 'product'){
        this.$store.dispatch('GET_PRODUCT', this.id);
      }
    }
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
