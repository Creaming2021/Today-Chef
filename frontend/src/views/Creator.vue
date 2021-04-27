<template>
  <div>
  <div id="editor"></div>
  <button id="submit">제출</button>
  <div id="viewer"></div>
  <div id="tui-image-editor" style="height: 100vh; width : 100vw;"></div>

  </div>
</template>


<script>
// TOAST UI Editor import
import Editor from '@toast-ui/editor';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import ImageEditor from 'tui-image-editor';
// TOAST UI Editor Plugins
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import 'tui-image-editor/dist/tui-image-editor.css'

export default {
  data() {
    return {
      editContent : '',
      imgEditor : '',
    }
  },
  mounted() {
    const editor = new Editor({
      el: document.querySelector("#editor"),
      initialEditType: "wysiwyg",
      previewStyle: "tab",
      height: "500px",
      plugins: [colorSyntax],
    });

    const instance = new ImageEditor(document.querySelector('#tui-image-editor'), {
      includeUI: {
        // loadImage: {
        //   path: 'img/sampleImage.jpg',
        //   name: 'SampleImage',
        // },
        initMenu: 'filter',
        menuBarPosition: 'bottom',
      },
      cssMaxWidth: '1000',
      cssMaxHeight: '800',
      usageStatistics: false,

    });

    this.imgEditor = instance;

    document.querySelector("#submit").addEventListener("click", () => {
      this.editContent = editor.getMarkdown()
      this.getViewer()
    })

  },
  methods : {
    onEditorChange() {
      console.log('onEditorChange')
    },
    onEditorStateChange() {
      console.log('onEditorStateChange')
    },
    getHtml() {
      let html = this.$refs.toastuiEditor.invoke('getHtml');
      console.log(html)
    },
    getViewer() {
      new Viewer({
        el: document.querySelector('#viewer'),
        initialValue: this.editContent,
      });
    }
  }
}
</script>

<style scoped>
.imageEditorApp {
  width: 1000px;
  height: 800px;
}
</style>
