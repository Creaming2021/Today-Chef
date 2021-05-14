<template>
  <!-- Shopping Cart Section Begin -->
  <section class="shopping-cart shopping-cart-container">
    <div class="body-header">장바구니</div>
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
          <div @click="goToProductList" class="continue__btn">
            쇼핑 계속하기
          </div>
        </div>
        <div class="col-lg-4">
          <CouponList
            :couponList="filteredCouponList"
            :selectedCoupon="selectedCoupon"
            @setCoupon="setCoupon"/>
          <div class="cart__total">
            <h6>Cart total</h6>
            <ul>
              <li>전체 금액
                <span>{{totalPrice}} 원</span>
              </li>
              <li>할인 금액
                <span>- {{discountPrice}} 원</span>
              </li>
              <li>결제 예상 금액
                <span>{{totalPrice - discountPrice}}원</span>
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
import CartItem from '@/components/cart/CartItem.vue';
import CouponList from '@/components/cart/CouponList.vue';

export default{
  data(){
    return {
      selectedCoupon: {
        memberCouponId: '',
        name: '사용 가능한 쿠폰 확인하기',
        discount: 0,
      },
      discountPrice: 0,
    }
  },
  mounted(){
    this.$store.dispatch("GET_CART_LIST", this.memberId);
    this.$store.dispatch("GET_COUPON_LIST", this.memberId);
  },
  computed: {
    ...mapState({
      cartList: state => state.member.cartList,
      memberId: state => state.user.memberId,
      couponList: state => state.member.couponList,
    }),
    totalPrice: function(){
      return this.cartList.reduce((acc, curr) => 
        (acc + (curr.price * curr.amount)), 0);
    },
    filteredCouponList() {
      return this.couponList.filter(coupon => coupon.couponStats === 'AVAILABLE');
    }
  },
  components: {
    CartItem,
    CouponList,
  },
  created(){
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      });
    }
  },
  methods:{
    goToPayment(){
      this.$store.dispatch('SET_CART', {
        selectedCoupon: this.selectedCoupon,
        discountPrice: this.discountPrice,
        totalPrice: this.totalPrice,
      });

      this.$router.push({
        name: 'PaymentProduct',
      });
    },
    goToProductList(){
      this.$router.push({
        name: 'ItemList',
        params: {
          item: 'product',
          category : 'all',
        }
      });
    },
    setCoupon( coupon ){
      this.selectedCoupon = {
        memberCouponId: coupon.id,
        name: coupon.name,
        discount: coupon.discount,
      };
    }
  },
  watch: {
    selectedCoupon: function(){
      this.discountPrice = this.totalPrice * this.selectedCoupon.discount * 0.01;
    },
  }
}
</script>

<style scoped>
.shopping-cart-container{
  margin-bottom: 50px;
}
.continue__btn,
.go-to-payment{
  cursor: pointer;
}
.cart__total h6{
  font-weight: bold;
}
</style>