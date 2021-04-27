<template>
  <div>
  <div id="editor"></div>
  <button id="submit">제출</button>
  <div id="viewer"></div>
  <div style="height: 100vh ; width : 100vw;">
    <div id="tui-image-editor" ></div>
  </div>
  <button id="imgsubmit">제출</button>

  </div>
</template>


<script>

// TOAST UI Editor import
import Editor from '@toast-ui/editor';
import _Editor from '@toast-ui/editor/dist/toastui-editor.js';
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
      hooks: {
        addImageBlobHook: function (blob,callback) {
          console.log('커스텀했어요')
          console.log('blob',blob)
          console.log('callback',callback)
          const reader = new FileReader();
          reader.onload = event => {
            callback(event.target.result);
          };
          reader.readAsDataURL(blob);

          return false
        }
    },
    });
    const instance = new ImageEditor(document.querySelector('#tui-image-editor'), {
      includeUI: {
        initMenu: 'filter',
        menuBarPosition: 'right',
      },
      cssMaxWidth: '1000',
      cssMaxHeight: '800',
      usageStatistics: false,

    });
    this.imgEditor = instance;
    const saveBtn = document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn')
    saveBtn.innerText = 'Save'
    var myblob = new Blob([instance.toDataURL().split(',')[1]], {
    type: 'text/plain'
    });
    console.log(editor.options.hooks.addImageBlobHook)
    console.dir(editor.options.hooks.addImageBlobHook)
    saveBtn.addEventListener('click', () => {
      editor.options.hooks.addImageBlobHook(myblob,(url, text) => _Editor._proto._applyImage(url, text))
    })
    // document.querySelector("#submit").addEventListener("click", () => {
    //   this.editContent = editor.getMarkdown()
    //   this.getViewer()
    // })
    // document.querySelector("#imgsubmit").addEventListener("click", () => {
    //   console.log('instance.toDataURL()',instance.toDataURL())
    // })

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
.tui-image-editor-save-btn {
    position: absolute;
    left: 0;
    right: 0;
    display: inline-block;
    top: 0;
    bottom: 0;
    width: 100%;
    cursor: pointer;
    opacity: 1;
}
</style>
