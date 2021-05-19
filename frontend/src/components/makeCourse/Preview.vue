<template>
  <div class="preview-container">
    <PreviewThumbnail :images="courseInfo.images"/>
    <div class="preview-description">
      <viewer :initialValue="courseInfo.descriptions" height="300px"/>
    </div>
    <ItemRightbar :itemDetail="courseInfo"/>
  </div>
</template>

<script>
import { Viewer } from '@toast-ui/vue-editor';
import PreviewThumbnail from '@/components/makeCourse/PreviewThumbnail.vue';
import ItemRightbar from '@/components/itemDetail/ItemRightbar.vue';

  export default {
    components: {
      PreviewThumbnail,
      ItemRightbar,
      'viewer': Viewer
  },
  props: {
    courseInfo : Object,
  },
}
</script>

<style scoped>
.preview-container{
  padding: 30px;
}

.preview-description{
  display: inline-block;
  width: 70%;
}
</style>