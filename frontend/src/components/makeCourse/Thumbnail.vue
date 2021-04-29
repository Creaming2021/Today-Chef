<template>
  <div>
		<div class="info-header">
			<div class="info-header-main">
				클래스의 컨셉이 잘 드러난 제목과 이미지를 보여주세요
			</div>
		
			<div class="info-header-sub">
				감성적이면서도 클래스를 잘 표현하는 제목과 이미지를 등록해 주세요.
			</div>
		</div>
		<div class="category">
			<span>클래스 제목</span>
			<b-form-input v-model="data.className" placeholder="컨셉이 잘 드러난 제목을 지어주세요"></b-form-input>
		</div>

		<div class="row">
			<div class="col-lg-6 thumbnail-img" @click="onClickAddImg">
				<img src="../../assets/img/add-photo-portrait.png" alt="add-img">
			</div>
			<div v-show="imgEditorActivate" class="thumbnail-image-editor-bg">
				<div class="thumbnail-image-editor-container">
      		<div id="thumbnail-image-editor"></div>
				</div>
			</div>
			<div class="col-lg-6 thumbnail-img-describe">
				커버 이미지가 첫 인상을 결정해요. 어떤 이미지가 좋을지 
				커버 이미지 가이드
				를 확인해 보세요!
			</div>
		</div>
  </div>
</template>

<script>
import ImageEditor from 'tui-image-editor';
// TOAST UI Editor Plugins
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import 'tui-color-picker/dist/tui-color-picker.css';
import 'tui-image-editor/dist/tui-image-editor.css'
	export default {
		components: {
	},
	data(){
		return {
			imgEditorActivate : false,
			imgEditor : '',
			data : {
				className : '',
				imgEditor : '',
			},
		}
	},
  mounted () {
		this.data = this.creatorData[this.$route.params.type] ?  this.creatorData[this.$route.params.type] : this.data
  },
	props: {
		 creatorData : Object,
	},
	methods : {
		onClickSaveBtn() {
			const type = this.$route.params.type
			const data = this.data
			this.$emit('data',{type,data})
		},
		onClickAddImg() {
			this.imgEditorActivate = true;
			const imgSrc = this.imgEditor ? this.imgEditor.toDataURL() : ''
			this.imgEditor = new ImageEditor(document.querySelector('#thumbnail-image-editor'), {
				includeUI: {
					initMenu: 'crop',
					menuBarPosition: 'right',
						loadImage : {
							path : imgSrc,
							name : 'Blank'
						},
				},
				cssMaxWidth: '400',
				cssMaxHeight: '200',
				usageStatistics: false,
			});
			// const cropBtns = document.querySelectorAll('.tie-crop-preset-button div')
			// cropBtns.forEach((btn) => {
			// 	if (!btn.classList.contains('preset-4-3')) {
			// 		btn.remove()
			// 	}
			// 	else {
			// 		btn.classList.add('active')
			// 	}
			// });
			
			document.querySelector('.tui-image-editor-header-logo').remove()
			const saveBtn = document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn')
			saveBtn.innerText = 'Save'
			saveBtn.replaceWith(saveBtn.cloneNode(true))
			document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn').addEventListener('click', () => {
				// this.currentEditImg.setAttribute('src',this.imgEditor.toDataURL())
				if (this.imgEditor.toDataURL().length > 2000) document.querySelector('.thumbnail-img img').setAttribute('src',this.imgEditor.toDataURL())
				this.imgEditorActivate = false
			})
		}
	}
}
</script>

<style scoped>
.thumbnail-image-editor-bg {
	background-color: rgba(0,0,0,0.8);
	width: 100vw;
	height: 100vh;
	position: fixed;
	left: 0;
	top: 0;
	z-index: 99999;
}
.thumbnail-image-editor-container {
   position: fixed;
   margin: auto;
   left: 0;
   right: 0;
	 top : 0;
	 bottom : 0;
	 width: 800px;
	 height: 400px;
}
.thumbnail-img {
	display : flex; 
	justify-content : center;
	cursor: pointer;
	width: 100%;
	height: 75%;
}

</style>