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
      <b-form-input 
        v-model="courseInfo.name" 
        placeholder="컨셉이 잘 드러난 제목을 지어주세요"/>
    </div>

    <div class="row" style="margin-bottom : 30px">
      <div class="col-lg-6 thumbnail-img" @click="onClickAddImg(0)">
        <img :src="courseInfo.imageUrl[0] || '../../assets/img/add-photo-portrait.png'" alt="add-img">
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
    <div class="row" style="margin-bottom : 30px">
      <span class="col-lg-12">
        클래스를 듣고 완성할 수 있는 작품 혹은 얻을 수 있는 결과를 알려주세요. <br>
        이미지 사이즈는 4:3 가로형을 권장합니다. 최소 3장의 이미지를 업로드 해주세요.<br>
        비율이 맞지 않을 경우 이미지가 잘려서 노출될 수 있어요 :)
      </span>
    </div>
    <div class="row">
      <div v-for="i in [1,2,3,4,5,6,7,8,9]" :key="i" 
        class="col-lg-2 thumbnail-img" 
        @click="onClickAddImg(i)">
        <img :src="courseInfo.imageUrl[i] || '../../assets/img/add-photo-portrait.png'" alt="add-img"/>
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
    }
  },
  // mounted () {
  //   this.data = this.creatorData[this.$route.params.type] ?  this.creatorData[this.$route.params.type] : this.data
  //   const imgArray = document.querySelectorAll('.thumbnail-img img');
  //   for (let i=0;i<this.courseInfo.images.length;i++) {
  //     if (this.courseInfo.images[i]) {
  //       imgArray[i].setAttribute('src',this.courseInfo.images[i])
  //     }
  //   }
  // },
  props: {
    courseInfo : Object,
  },
  methods : {
    onClickAddImg(idx) {
      this.imgEditorActivate = true;
      const imgSrc = this.courseInfo.imageUrl[idx] ? this.courseInfo.imageUrl[idx] : ''
      const currentImgEditor = new ImageEditor(document.querySelector('#thumbnail-image-editor'), {
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
      
      document.querySelector('.tui-image-editor-header-logo').remove();
      const saveBtn = document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn');
      saveBtn.innerText = 'Save';
      saveBtn.replaceWith(saveBtn.cloneNode(true));
      document.querySelector('.tui-image-editor-header-buttons .tui-image-editor-download-btn')
        .addEventListener('click', () => {
          if (currentImgEditor.toDataURL().length > 2000) {
            console.log(currentImgEditor);
            this.courseInfo.imageUrl[idx] = currentImgEditor.toDataURL();
            document.querySelectorAll('.thumbnail-img img')[idx].setAttribute('src',currentImgEditor.toDataURL());
          }
          this.imgEditorActivate = false;
        });
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