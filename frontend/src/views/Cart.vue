<template>
  <!-- Shopping Cart Section Begin -->
  <section class="shopping-cart spad">
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
          <div class="shopping__cart__table">
            <table>
              <thead>
                <tr>
                  <th>Product</th>
                  <th>Quantity</th>
                  <th>Total</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <template v-for="cartDetail in cartList">
                  <CartItem :cartDetail="cartDetail" :key="cartDetail.cartId"/>
                </template>
              </tbody>
            </table>
          </div>
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div click="goToProductList" class="continue__btn">
                쇼핑 계속하기
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="cart__discount">
            <h6>쿠폰 사용</h6>
            <form action="#">
              <input type="text" placeholder="Coupon code">
              <button type="submit">Apply</button>
            </form>
          </div>
          <div class="cart__total">
            <h6>Cart total</h6>
            <ul>
              <li>Total 
                <span>{{cartList.reduce((acc, curr) => (
                 acc + (curr.price * curr.amount)), 0)}} 원
                </span>
              </li>
            </ul>
            <div @click="goToPayment" class="primary-btn go-to-payment">결제 하러 가기</div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import CartItem from '@/components/cart/cartItem.vue';

export default{
  mounted(){
    this.$store.dispatch("GET_CART_LIST", this.memberId);
  },
  computed: {
    ...mapState({
      cartList: state => state.member.cartList,
      memberId: state => state.user.memberId,
    }),
  },
  components: {
    CartItem,
  },
  methods:{
    goToPayment(){
      this.$router.push({
        name: 'PaymentProduct',
      })
    },
    goToProductList(){
      this.$router.push({
        name: 'ItemList',
        params: {
          item: 'product',
          category : 'all',
        }
      })
    }
  }
}
</script>

<style>
.continue__btn,
.go-to-payment{
  cursor: pointer;
}
</style>