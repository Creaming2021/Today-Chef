<template>
  <div>
    <div class="product__item">
        <div class="product__item__pic set-bg" @click="onClickHomeItem">
          <span class="label">Coming Soon</span>
        </div>
        <div class="product__item__text">
            <h6>{{item.name}}</h6>
            <span href="#" class="add-cart" @click="onClickZzim">+ 강의 찜하기</span>
            <div class="rating">
              좋아요 : {{item.likeCnt}} 평점 : {{item.rate}}
            </div>
            <h5>${{item.price}}</h5>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeItem",
  components: {
  },
  props : {
    item : Object,
    idx : Number
  },
  mounted() {
    const itemPics = document.querySelectorAll('.product__item__pic')
    const itemPic = itemPics[this.idx]
    itemPic.style.backgroundImage = `url(${this.item.thumbnail})`
  },
  methods : {
    onClickZzim() {
      alert('찜하기')
    },
    onClickHomeItem() {
      alert('Home Item Clicked')
    }
  }
};
</script>

<style scoped>
.product__item__pic {
  cursor: pointer;
}
.product__item:hover .product__item__text span {
	top: 22px;
	opacity: 1;
	visibility: visible;
}

.product__item__text span {
	font-size: 15px;
	color: #e53637;
	font-weight: 700;
	position: absolute;
	left: 0;
	top: 0;
	opacity: 0;
	visibility: hidden;
	-webkit-transition: all, 0.3s;
	-o-transition: all, 0.3s;
	transition: all, 0.3s;
  cursor: pointer;
}
</style>