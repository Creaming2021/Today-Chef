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
      <div class="col-lg-12 thumbnail-img main-img" @click="onClickAddImg(0)">
        <img :src="courseInfo.images[0] || 'https://static.thenounproject.com/png/187803-200.png'" alt="add-img">
      </div>
      <div v-show="imgEditorActivate" class="thumbnail-image-editor-bg">
        <div class="thumbnail-image-editor-container">
          <div id="thumbnail-image-editor"></div>
        </div>
      </div>
      <div class="col-lg-12 thumbnail-img-main-describe">
        커버 이미지가 첫 인상을 결정해요. <br/>
        어떤 이미지가 좋을지 커버 이미지 가이드를 확인해 보세요!
      </div>
    </div>
    <div class="row" style="margin-bottom : 30px">
      <span class="col-lg-12 thumbnail-img-sub-describe">
        클래스를 듣고 완성할 수 있는 작품 혹은 얻을 수 있는 결과를 알려주세요. <br/>
        이미지 사이즈는 4:3 가로형을 권장합니다. 최소 3장의 이미지를 업로드 해주세요.<br/>
        비율이 맞지 않을 경우 이미지가 잘려서 노출될 수 있어요 :)
      </span>
    </div>
    <div class="row">
      <div v-for="i in [1,2,3,4,5,6,7,8,9]" :key="i" 
        class="col-lg-4 thumbnail-img side-img" 
        @click="onClickAddImg(i)">
        <img :src="courseInfo.images[i] || 'https://static.thenounproject.com/png/187803-200.png'" alt="add-img"/>
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
      const imgSrc = this.courseInfo.images[idx] ? this.courseInfo.images[idx] : ''
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
            this.courseInfo.images[idx] = currentImgEditor.toDataURL();
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

.main-img img{
  width: 100%;
  height: 570px;
  object-fit: cover;
}

.side-img img{
  width: 100%;
  height: 180px;
  object-fit: cover;
  margin: 15px 0px 10px 0px;
}

.thumbnail-img-main-describe{
  text-align: center;
  font-size: 1.5rem;
  font-weight: bold;
  margin-top: 80px;
  margin-bottom: 30px;
  line-height: 2.5rem;
}

.thumbnail-img-sub-describe{
  text-align: center;
  font-size: 1.1rem;
  line-height: 2.5rem;
  margin-bottom: 50px;
}

</style>