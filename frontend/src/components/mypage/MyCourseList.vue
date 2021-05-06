<template>
  <div>
    <div v-if="courseList.length > 0">
      <div class="row">
        <div 
          class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
          v-for="course in courseList" :key="course.courseId">
            <CourseItem :course="course" :type="type" />
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
    </div>
    <div v-else>
      강의가 없습니다.
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import CourseItem from '@/components/common/CourseItem.vue';

export default {
  components: {
    CourseItem,
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
        // this.$store.dispatch('GET_REGISTERED_COURSE_LIST', this.user.memberId);
        this.$store.dispatch('GET_REGISTERED_COURSE_LIST', 4);
      }else if(this.type === 'teacher'){
        // this.$store.dispatch('GET_TEACHED_COURSE_LIST', this.user.memberId);
        this.$store.dispatch('GET_TEACHED_COURSE_LIST', 6);
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
</style>
