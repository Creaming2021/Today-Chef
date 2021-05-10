<template>
  <div>
    <button @click="goToCreator">강의 제작하기</button>
    <MyCourseList
      class="col-lg-9 my-course-list"
      :type="'teacher'"/>
  </div>	
</template>

<script>
import MyCourseList from '@/components/mypage/MyCourseList.vue';
export default {
  components: {
    MyCourseList,
  },
  data(){
    return {
      mode: '',
      type: '',
    };
  },  
  created() {
    this.checkQuery();
  },
  methods: {
    checkQuery() {
      this.mode = this.$route.params.mode;
      this.type = this.$route.params.item;
    },
    goToCreator(){
      this.$router.push({
        name: 'Creator',
        params: {
          mode : 'create',
          type: 'info',
        }
      })
    }
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    },
  }
}
</script>

<style scoped>
.my-course-list{
  margin: auto;
}
</style>