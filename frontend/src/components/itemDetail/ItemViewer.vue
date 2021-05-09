<template>
  <div>
    <viewer ref="viewer"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    'viewer': Viewer
  },
  mounted(){
    this.settingViewerValue();
  },
  computed:{
    ...mapState({
      course: state => state.course.course.descriptions,
      product: state => state.product.product.descriptions,
    }),
    content: function(){
      return this.settingContent();
    }
  },
  methods: {
    settingContent() {
      let item = this.$route.params.item;
      return item === 'course' ? this.course : this.product;
    },
    settingViewerValue(){
      this.$refs.viewer.invoke('setMarkdown', this.content);
    }
  },
  watch: {
    content: function(){
      this.settingViewerValue();
    }
  }
}
</script>

<style>
</style>
