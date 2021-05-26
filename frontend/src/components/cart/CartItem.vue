<template>
  <tr>
    <td class="product__cart__item">
      <div class="product__cart__item__pic">
        <img src="img/shopping-cart/cart-1.jpg" alt="">
      </div>
      <div class="product__cart__item__text">
        <h6>{{cartDetail.name}}</h6>
        <h5>{{cartDetail.price}}</h5>
      </div>
    </td>      
    <td class="quantity__item">
      <div class="quantity">
        <div class="pro-qty-2">
          <div>{{cartDetail.amount}}</div>
        </div>
      </div>
    </td>
    <td class="cart__price">{{ (cartDetail.price * cartDetail.amount).toLocaleString() }} 원</td>
    <td class="cart__close" @click="deleteCart"><i class="fa fa-close"></i></td>
  </tr>
</template>

<script>
import { mapState } from 'vuex';

export default {
  props: {
    cartDetail: Object,
  },
  computed: {
    ...mapState({
      memberId: state => state.user.memberId,
    }),
  },
  methods: {
    deleteCart(){
      this.$store.dispatch('DELETE_CART_LIST', {
        memberId: this.memberId,
        cartId: this.cartDetail.cartId,
      });
    }
  }
}
</script>

<style>

</style>