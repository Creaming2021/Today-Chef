<template>
  <div class="thumbnail-container" v-if="images">
		<img 
			v-if="images[0]"
			id="first-image"
			:src="images[0]"/>
		<img
			v-else
			id="first-image"
			src="@/assets/img/empty/image.png"/>

		<img 
			v-if="images[1]"
			id="second-image"
			:src="images[1]"/>
		<img
			v-else
			id="second-image"
			src="@/assets/img/empty/image.png"/>

		<img 
			v-if="images[2]"
			id="third-image"
			:src="images[2]"/>
		<img
			v-else
			id="third-image"
			src="@/assets/img/empty/image.png"/>

		<img 
			v-if="images[3]"
			id="fourth-image"
			:src="images[3]"/>
		<img
			v-else
			id="fourth-image"
			src="@/assets/img/empty/image.png"/>

		<div 
			v-if="remainImageCnt > 0"
			class="remain-image-cnt">+ {{remainImageCnt}}개의<br/>이미지
		</div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      remainImageCnt: 0,
      currentImage: 0,
    }
  },
  props: {
    images: Array,
  },
  mounted() {
    this.setRemainImageCnt();
  },
  methods: {
    setRemainImageCnt(){
			this.images.reduce((acc, cur) => cur !== '' ? acc + 1 : acc , 0);
    },
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
