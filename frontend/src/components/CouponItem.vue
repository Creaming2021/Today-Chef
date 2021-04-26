<template>
  <div>
    <div class="product__item coupon">
        <img @click="onClickImage" class="product__item set-bg" :src="coupon.thumbnail"/>
        <!-- <h6>{{coupon.name}}</h6> -->
    </div>
  </div>
</template>

<script>

	export default {
		data(){
			return {}
		},
		props: {
			coupon: Object,
		},
		methods : {
			onClickImage() {
				alert('쿠폰')
			}
		},
	}
</script>

<style scoped>
.blog__item__text h6 {
	color: #0d0d0d;
	font-weight: 700; 
}
.coupon { 
	cursor: pointer;
}
</style>