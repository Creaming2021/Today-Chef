<template>
  <div class="my-course-list-container">
    <template v-if="courseList.length > 0">
      <div class="row">
        <div 
          class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
          v-for="(course, idx) in courseList" :key="idx">
            <ItemCard :itemDetail="course" :type="type" />
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="product__pagination">
            <a class="active" href="#">1</a>
            <a href="#">2</a>
          </div>
        </div>
      </div>
    </template>
    <template v-else>
      <img src="@/assets/img/empty/course.png"/>
    </template>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ItemCard from '@/components/common/ItemCard.vue';

export default {
  components: {
    ItemCard,
  },
  props: {
    type: String
  },
  computed: {
    ...mapState({
      user: state => state.user,
      courseList: state => state.member.courseList,
    }),
  },
  created() {
    this.checkQuery();
  },
  methods: {
    checkQuery() {
      this.type = this.$route.params.type || this.type;
      this.getCourseList();
    },
    getCourseList(){
      if(this.type === 'student'){
        this.$store.dispatch('GET_REGISTERED_COURSE_LIST', this.user.memberId);
      }else if(this.type === 'teacher'){
        this.$store.dispatch('GET_TEACHED_COURSE_LIST', this.user.memberId);
      }
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
.my-course-list-container{
  text-align: center;
}
.my-course-list-container img{
  width: 500px;
}
</style>
