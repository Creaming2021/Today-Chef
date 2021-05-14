<template>
  <div>
    결제 완료
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
      cartList: state => state.member.cartList,
      cart: state => state.order.cart,
    })
  },
  created() {

    const paymentProductString = window.localStorage.getItem('payment_product');
    const paymentCourseString = window.localStorage.getItem('payment_course');
    if (paymentCourseString != '') {
      const paymentInfo = JSON.parse(paymentCourseString);
      this.$store.dispatch('POST_REGISTER', paymentInfo);
      window.localStorage.setItem('payment_course', '');
    } else if (paymentProductString != '') {
      const paymentInfo = JSON.parse(paymentProductString);
      this.$store.dispatch('POST_ORDER', paymentInfo);
      window.localStorage.setItem('payment_product', '');

      // 장바구니 비우기 요청
      for(let idx in this.cartList){
        this.$store.dispatch('DELETE_CART_LIST', {
          memberId: this.memberId,
          cartId: this.cartList[idx].cartId,
        });
      }

      // 쿠폰 사용 요청
      if(this.cart.selectedCoupon.memberCouponId !== 0){
        this.$store.dispatch('PUT_COUPON', {
          memberId: this.memberId,
          couponId: this.cart.selectedCoupon.memberCouponId,
        });
      }
    }

    alert('마이페이지로 이동');
    this.$router.push('/me/info');
  },
}
</script>

<style>

</style>