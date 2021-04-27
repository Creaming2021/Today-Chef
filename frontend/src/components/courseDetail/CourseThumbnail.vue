<template>
  <div class="thumbnail-container" v-if="thumbnailList">
    <img 
      id="first-image"
      @click="onOpenThumbnailModal"
      :src="thumbnailList[0]"/>
    <img 
      id="second-image"
      @click="onOpenThumbnailModal"
      :src="thumbnailList[1]"/>
    <img 
      id="third-image"
      @click="onOpenThumbnailModal"
      :src="thumbnailList[2]"/>
    <img 
      id="fourth-image"
      @click="onOpenThumbnailModal"
      :src="thumbnailList[3]"/>
    <div 
      v-if="thumbnailList.length > 4"
      class="remain-image-cnt"
      @click="onOpenThumbnailModal">+ {{remainImageCnt}}개의<br/>이미지
    </div>
    <b-modal 
      size="lg" 
      v-model="openThumbnailModal" 
      hide-footer hide-header>
      <p class="thumbnail-modal-btn" @click="onCloseThumbnailModal">X</p>
      <span class="thumbnail-modal-btn" @click="onClickDecreaseImageIdx">이전</span>
      <img class="modalImage" :src="thumbnailList[currentImage]"/>
      <span class="thumbnail-modal-btn" @click="onClickIncreaseImageIdx">다음</span>
    </b-modal>
  </div>
</template>


<script>
export default {
  data(){
    return {
      remainImageCnt: 0,
      openThumbnailModal: false,
      currentImage: 0,
    }
  },
  mounted() {
    this.setRemainImageCnt();
  },
  props: {
    thumbnailList: Array,
  },
  methods: {
    setRemainImageCnt(){
      this.remainImageCnt = this.thumbnailList.length - 4;
    },
    onOpenThumbnailModal(){
      this.currentImage = 0;
      this.openThumbnailModal = true;
    },
    onCloseThumbnailModal(){
      this.openThumbnailModal = false;
    },
    onClickIncreaseImageIdx(){
      this.currentImage = (this.currentImage + 1) % this.thumbnailList.length;
    },
    onClickDecreaseImageIdx(){
      this.currentImage = (this.currentImage - 1 + this.thumbnailList.length) % this.thumbnailList.length;
    },
  }
}
</script>

<style>
.thumbnail-container{
  position: relative;
  width: 100%;
  height: 500px;
  margin-bottom: 30px;
}

.thumbnail-container img{
  float: left;
  padding: 0px 8px 8px 0px;
  border-radius: 5px;
}

.thumbnail-container img:hover,
.remain-image-cnt:hover,
.thumbnail-modal-btn:hover{
  cursor: pointer;
}

#first-image{
  width: 50%;
  height: 100%;
  object-fit: cover;
}

#second-image{
  width: 50%;
  height: 50%;
  object-fit: cover;
}

#third-image{
  width: 25%;
  height: 50%;
  object-fit: cover;
}

#fourth-image{
  width: 25%;
  height: 50%;
  object-fit: cover;
}

.remain-image-cnt{
  position: absolute;
  bottom: 0px;
  right: 0px;
  background-color: black;
  opacity: 0.55;
  width: 25%;
  height: 50%;
  color: white;
  font-weight: bold;
  font-size: 1.5rem;
  line-height: 40px;
  letter-spacing: 2px;
  padding-top: 80px;
}

.modalImage{
  width: 660px;
  height: 600px;
  margin: 0px 20px 20px 20px;
  object-fit: cover;
  border-radius: 10px;
}
</style>
