<template>
  <div>
    <viewer :initialValue="course.introduction"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    'viewer': Viewer
  },
  computed: {
    ...mapState({
      course: state => state.course.course,
    }),
  },
}
</script>

<style>
</style>
