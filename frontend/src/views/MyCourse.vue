<template>
  <div class="my-course-container">
    <div class="body-header">내가 만든 강의</div>
    <div class="my-course-button">
      <button class="button" @click="goToCreator">강의 제작하기</button>
    </div>
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
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      })
    }
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
.my-course-container{
  margin-bottom: 50px;
}

.my-course-button{
  padding: 0 10% 0 10%;
  margin: auto;
}

.my-course-list{
  margin: auto;
}

.button{
  padding: 20px;
  margin: -20px 10px 30px 10px;
  border-radius: 10px;
  border: none;
  background: #f3f2ee;
  font-weight: bold;
}

.button:hover{
  cursor: pointer;
  background: #e53637;
  color: white;
}
</style>