<template>
  <div>
  <editor
    :initialValue="editorText"
    :options="editorOptions"
    height="500px"
    initialEditType="wysiwyg"
    previewStyle="vertical"
  />
  </div>
</template>


<script>
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';


export default {
  components : {
    editor: Editor
  },
  data() {
    return {
      editorText: 'This is initialValue.',
      editorOptions: {
        hideModeSwitch: true
      }
    };
  },
  mounted() {
  }
}
</script>

<style>
</style>
