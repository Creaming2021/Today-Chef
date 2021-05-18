<template>
  <div class="thumbnail-container" v-if="imageList">
    <template v-if="imageList.length > 0" >
      <img 
        id="first-image"
        @click="onOpenThumbnailModal"
        :src="imageList[0].imageUrl"/>
      <img 
        id="second-image"
        @click="onOpenThumbnailModal"
        :src="(imageList[1] && imageList[1].imageUrl) || imageList[0].imageUrl"/>
      <img 
        id="third-image"
        @click="onOpenThumbnailModal"
        :src="(imageList[2] && imageList[2].imageUrl) || imageList[0].imageUrl"/>
      <img 
        id="fourth-image"
        @click="onOpenThumbnailModal"
        :src="(imageList[3] && imageList[3].imageUrl) || imageList[0].imageUrl"/>
      <div 
        v-if="imageList.length > 4"
        class="remain-image-cnt"
        @click="onOpenThumbnailModal">+ {{remainImageCnt}}개의<br/>이미지
      </div>
    </template>    
    <b-modal v-model="openThumbnailModal" size="lg" hide-footer hide-header>
      <p class="thumbnail-modal-btn" @click="onCloseThumbnailModal">X</p>
      <span class="thumbnail-modal-btn" @click="onClickDecreaseImageIdx">이전</span>
      <img class="modalImage" :src="imageList.length > 0 && imageList[currentImage].imageUrl || imageList[currentImage]"/>
      <span class="thumbnail-modal-btn" @click="onClickIncreaseImageIdx">다음</span>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data(){
    return {
      remainImageCnt: 0,
      openThumbnailModal: false,
      currentImage: 0,
    }
  },
  computed: {
    ...mapState({
       courseImageList: state => state.course.course.images,
       productImageList: state => state.product.product.images,
      }),
    imageList: function(){
      return this.settingItemList();
    },
  },
  props: {
    images: Array,
  },
  created() {
    this.settingItemList();
  },
  mounted() {
    this.setRemainImageCnt();
    console.log(this.imageList);
  },
  methods: {
    setRemainImageCnt(){
      this.remainImageCnt = this.imageList.length - 4;
    },
    onOpenThumbnailModal(){
      this.currentImage = 0;
      this.openThumbnailModal = true;
    },
    onCloseThumbnailModal(){
      this.openThumbnailModal = false;
    },
    onClickIncreaseImageIdx(){
      this.currentImage = (this.currentImage + 1) % this.imageList.length;
    },
    onClickDecreaseImageIdx(){
      this.currentImage = (this.currentImage - 1 + this.imageList.length) % this.imageList.length;
    },
    settingItemList(){
      let item = this.$route.params.item;
      return this.images || (item === 'course' ? this.courseImageList : this.productImageList);
    },
  },
  watch: {
    imageList: function(){
      this.setRemainImageCnt();
    }
  },
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

.thumbnail-modal-btn{
  text-align: right;
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
