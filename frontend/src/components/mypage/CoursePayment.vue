<template>
  <tr>
    <td class="product__cart__item">
        <div class="product__cart__item__pic">
            <img :src="payment.course.image" alt="">
        </div>
        <div class="product__cart__item__text">
            <h6>{{payment.course.name}}</h6>
            <span>{{payment.course.nickname}} / 별점 : {{payment.course.rating}}</span>
        </div>
    </td>
    <td class="quantity__item">
        <div class="quantity">
            <div class="pro-qty-2">
              <h5>{{payment.paidDate}}</h5>
            </div>
        </div>
    </td>
    <td class="cart__price">{{payment.paidPrice}}원</td>
    <td class="cart__price">
    </td>
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
</style>