<template>
  <tr>
    <td class="product__cart__item">
        <div class="product__cart__item__pic">
            <img :src="payment.thumbnail" alt="">
        </div>
        <div class="product__cart__item__text">
            <h6>{{payment.name}}</h6>
            <span>{{payment.teacher}} / 별점 : {{payment.rate}}</span>
        </div>
    </td>
    <td class="quantity__item">
        <div class="quantity">
            <div class="pro-qty-2">
              <h5>{{payment.date}}</h5>
            </div>
        </div>
    </td>
    <td class="cart__price">{{payment.price}}원</td>
    <td class="cart__price"><div class="payment-detail" @click="onClickPaymentDetail">상세 내역</div></td>
  </tr>
</template>

<script>
	export default {
		data(){
			return {}
		},
		props: {
      payment : Object,
    },
    mounted() {
      let price = this.payment.price
      console.log(price)
    },
    methods : {
      onClickPaymentDetail() {
        alert('상세내역보기')
      },
    }
	}
</script>

<style scoped>
.product__cart__item__pic img {
  width : 150px;
  height: 150px;
  object-fit: cover;
}
.payment-detail {
  cursor: pointer;
}
</style>