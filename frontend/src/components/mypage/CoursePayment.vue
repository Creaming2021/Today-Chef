<template>
  <tr>
    <td class="product__cart__item">
      <div class="product__cart__item__pic">
        <img :src="payment.course.image" alt="">
      </div>
      <div class="product__cart__item__text">
        <h6>{{payment.course.name}}</h6>
        <span>{{payment.course.nickname}}</span>
        <b-form-rating v-model="payment.course.rating" color="orange" size="lg" readonly no-border/>
      </div>
    </td>
    <td class="quantity__item" colspan="2">
      <div class="quantity centered">
        <h5>{{payment.paidDate.substring(0,10)}}</h5>
      </div>
    </td>
    <td class="cart__price centered">{{payment.paidPrice}}원</td>
  </tr>
</template>

<script>
  export default {
    props: {
      payment : Object,
    },
  }
</script>

<style scoped>
.product__cart__item__pic img {
  width : 150px;
  height: 150px;
  object-fit: cover;
}
.product__cart__item__text h6{
  padding-left: 25px;
}
.centered{
  text-align: center;
}
</style>