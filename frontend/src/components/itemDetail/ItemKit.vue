<template>
  <div>
    <viewer :initialValue="kit"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  data(){
    return {
      kit: '',
    }
  },
  components: {
    'viewer': Viewer
  },
  computed: {
    ...mapState({
      course: state => state.course.course,
      product: state => state.product.product,
    }),
  },
  created() {
    this.settingKit();
  },
  methods: {
    settingKit(){
      let item = this.$route.params.item;
      this.kit = item === 'course' ? this.course.kit : this.product.kit;
    },
  }
}
</script>

<style>
</style>
