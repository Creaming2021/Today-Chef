<template>
  <div>
    <viewer :initialValue="content"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    'viewer': Viewer
  },
  computed:{
    ...mapState({
      course: state => state.course.course.descriptions,
      product: state => state.product.product.descriptions,
    }),
    content: function(){
      console.log(this.settingContent());
      return this.settingContent();
    }
  },
  methods: {
    settingContent() {
      let item = this.$route.params.item;
      return item === 'course' ? this.course : this.product;
    }
  }
}
</script>

<style>
</style>
