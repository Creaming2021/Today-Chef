<template>
  <div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      register: {
        memberId: 0,
        paidPrice: 0,
        courseId: 0,
      },
    }
    
  },
  computed: {
    ...mapState({
      memberId: state => state.user.memberId,
      // cartList: state => state.member.cartList,
      // cart: state => state.order.cart,
    })
  },
  created() {
    const paymentProductString = window.localStorage.getItem('payment_product');
    const paymentCourseString = window.localStorage.getItem('payment_course');
    if (paymentCourseString) {
      const paymentInfo = JSON.parse(paymentCourseString);
      this.$store.dispatch('POST_REGISTER', paymentInfo);
      window.localStorage.removeItem('payment_course');
    } else if (paymentProductString) {
      const paymentInfo = JSON.parse(paymentProductString);
      this.$store.dispatch('POST_ORDER', paymentInfo);
      window.localStorage.removeItem('payment_product');

      // 장바구니 비우기 요청
      const cartListString = window.localStorage.getItem('payment_cart_list');
      const cartString = window.localStorage.getItem('payment_cart');
      const cartList = JSON.parse(cartListString);
      const cart = JSON.parse(cartString);
      console.log(cartList);
      console.log(cart);
      window.localStorage.removeItem('payment_cart_list');
      window.localStorage.removeItem('payment_cart');
      for(let idx in cartList){
        this.$store.dispatch('DELETE_CART_LIST', {
          memberId: this.memberId,
          cartId: this.cartList[idx].cartId,
        });
      }

      // 쿠폰 사용 요청
      if(cart.selectedCoupon.memberCouponId !== 0){
        this.$store.dispatch('PUT_COUPON', {
          memberId: this.memberId,
          couponId: cart.selectedCoupon.memberCouponId,
        });
      }
    }

    this.$swal.fire({
      icon: 'success',
      title: '결제 완료',
      text: '마이페이지로 이동합니다.',
    });
    
    this.$router.push({
      name: 'Profile',
      params: {
        type: 'info',
      }
    });
  },
}
</script>

<style>

</style>