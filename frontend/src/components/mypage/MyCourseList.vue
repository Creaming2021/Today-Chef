<template>
  <div>
    <div class="row">
      <div 
        class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
        v-for="(course,idx) in myCourseList" :key="idx">
          <CourseItem :course="course" :idx="idx" :type="type" />
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
</template>


<script>
import CourseItem from '@/components/common/CourseItem.vue';

export default {
  components: {
    CourseItem,
  },
  props: {
    myCourseList: Array,
    type: String
  }
}
</script>

<style>
</style>
