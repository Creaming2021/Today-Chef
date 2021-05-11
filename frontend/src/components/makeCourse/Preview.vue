<template>
  <div class="preview-container">
    <ItemThumbnail :images="courseInfo.images"/>
    <div class="preview-description">
      <viewer :initialValue="courseInfo.descriptions" height="300px"/>
    </div>
    <ItemRightbar :itemDetail="courseInfo"/>
  </div>
</template>

<script>
import { Viewer } from '@toast-ui/vue-editor';
import ItemThumbnail from '@/components/itemDetail/ItemThumbnail.vue';
import ItemRightbar from '@/components/itemDetail/ItemRightbar.vue';

  export default {
    components: {
      ItemThumbnail,
      ItemRightbar,
      'viewer': Viewer
  },
  data(){
    return {
    }
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