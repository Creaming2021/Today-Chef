<template>
  <div>
    <CourseThumbnail/>
    <CourseDetailMain/>
    <CourseRightbar/>
  </div>
</template>


<script>
import CourseThumbnail from '@/components/CourseDetail/CourseThumbnail.vue';
import CourseDetailMain from '@/components/CourseDetail/CourseDetailMain.vue';
import CourseRightbar from '@/components/CourseDetail/CourseRightbar.vue';

export default {
  components: {
    CourseThumbnail,
    CourseDetailMain,
    CourseRightbar,
  }
}
</script>

<style>
</style>
