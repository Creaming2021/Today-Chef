<template>
  <div>
    <div class="product__item">
        <img class="product__item set-bg" :src="coupon.thumbnail"/>
        <h6>{{coupon.name}}</h6>
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
		}
	}
</script>

<style scoped>
.blog__item__text h6 {
	color: #0d0d0d;
	font-weight: 700; 
}
</style>