<template>
  <div>
    <viewer :initialValue="course.kit"/>
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
    ...mapState(['course']),
  },
}
</script>

<style>
</style>
