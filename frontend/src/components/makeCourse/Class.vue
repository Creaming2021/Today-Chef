<template>
  <div>
		<div class="info-header">
			<div class="info-header-main">
				어떤 걸 알려주실 수 있나요?
			</div>
		
			<div class="info-header-sub">
				클래스를 통해 알려주실 것과 완성할 수 있는 것들을 설명해 주세요.
			</div>
		</div>


    <div class="row" style="display : flex; justify-content : center; height : 100vh">
      <div style="height: 100% ; width : 95%;">
        <div id="editor"></div>
      </div>
      <div v-show="imgEditorActivate" id="image-editor-container" style="position : fixed; top:0; left :0; height: 100vh ; width : 100vw; z-index:99999">
      </div>
    </div>
    <!-- <div id="viewer"></div> -->
  </div>
</template>

<script>
// TOAST UI Editor import
import Editor from '@toast-ui/editor';
// import _Editor from '@toast-ui/editor/dist/toastui-editor.js';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import ImageEditor from 'tui-image-editor';
// TOAST UI Editor Plugins
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import 'tui-image-editor/dist/tui-image-editor.css'
export default {
  components: {
  },
  props: {
    creatorData : Object,
  },
	data(){
		return {
      data : '',
      myEditor : '',
      imgEditor : '',
      imgEditorActivate : false,
      images : [],
      image_length : 0,
      currentEditImg : '',
		}
	},
  watch : {
    image_length : function() {
      const imageTags = document.querySelectorAll('.tui-editor-contents img')
      const cmp = this.images[this.images.length-1]
      for (let i=0; i<imageTags.length;i++) {
        if (imageTags[i].getAttribute('src') === cmp) {
          imageTags[i].addEventListener('dblclick', () => {
            this.imgEditorActivate = true
            this.currentEditImg = imageTags[i]
            const imgEditorContainer = document.querySelector("#image-editor-container")
            imgEditorContainer.innerHTML = ''

            const imgEditorDiv = document.createElement('div')
            imgEditorDiv.setAttribute('id','tui-image-editor')
            imgEditorContainer.appendChild(imgEditorDiv)
            this.imgEditor = new ImageEditor(document.querySelector('#tui-image-editor'), {
              includeUI: {
                loadImage : {
                  path : imageTags[i].getAttribute('src'),
                  name : 'Blank'
                },
                menuBarPosition: 'right',
              },
              cssMaxWidth: '1000',
              cssMaxHeight: '800',
              usageStatistics: false,
            });
            document.querySelector('.tui-image-editor-header-logo').remove()
            document.querySelector('.tui-image-editor-header-buttons div').remove()
            const saveBtn = document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn')
            saveBtn.innerText = 'Save'
            saveBtn.replaceWith(saveBtn.cloneNode(true))
            document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn').addEventListener('click', () => {
              this.currentEditImg.setAttribute('src',this.imgEditor.toDataURL())
              this.imgEditorActivate = false
              imgEditorContainer.innerHTML = ''
            })
          })
        }        
      }
    }
  },
  mounted() {
		const editorInitialValue = this.creatorData[this.$route.params.type] ?  this.creatorData[this.$route.params.type].editor : '글과 사진을 통해 클래스를 소개해주세요'
    const _this = this
    const editor = new Editor({
      el: document.querySelector("#editor"),
      initialEditType: "wysiwyg",
      initialValue : editorInitialValue,
      previewStyle: "tab",
      height: "100%",
      plugins: [colorSyntax],
      hooks: {
        addImageBlobHook: function (blob,callback) {
          const reader = new FileReader();
          reader.onload = event => {
            _this.images.push(event.target.result)
            _this.image_length += 1
            callback(event.target.result);
          };
          reader.readAsDataURL(blob);
          return false
        }
      }
    });
    this.myEditor = editor
    document.querySelectorAll('.te-tab')[1].setAttribute('style','display : none')
  },
	methods : {
		onClickSaveBtn() {
			const type = this.$route.params.type
			const data = {
        editor : this.myEditor.getMarkdown()
			}
			this.$emit('data',{type,data})
		},
    getViewer() {
      new Viewer({
        el: document.querySelector('#viewer'),
        initialValue: this.editContent,
      });
    },
    onClickSubmit() {
      this.editContent = this.myEditor.getMarkdown()
      this.getViewer()
    },
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