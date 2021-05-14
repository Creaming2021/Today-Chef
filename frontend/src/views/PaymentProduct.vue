<template>
  <div>
    <div class="body-header">
      <img src="@/assets/img/banner/banner-payment.png"/>
    </div>
    <section class="checkout payment payment-container">
      <div class="container">
        <div class="checkout__form">
          <div class="row">
            <Address 
              :paymentInfo="paymentInfo"
              @setPaymentInfo="setPaymentInfo"/>
            <div class="col-lg-4 col-md-6">
              <div class="checkout__order">
                <h4 class="order__title">결제 금액</h4>
                <div class="checkout__order__products">상품 명 <span>상품 금액 X 구매 갯수</span></div>
                <ul class="checkout__total__products">
                  <li v-for="product in cartList" :key="product.cartId">
                    {{product.name}}<span>{{product.price}}원 X {{product.amount}}개</span>
                  </li>
                </ul>
                <ul class="checkout__total__all">
                  <li>총 합계 <span>{{cart.totalPrice}}원</span></li>
                  <li>총 할인 금액 <span>- {{cart.discountPrice}}원</span></li>
                  <li>최종 가격 <span>{{cart.totalPrice - cart.discountPrice}}원</span></li>
                </ul>
                <button class="site-btn" @click="onSubmitPayment">카카오 페이 결제</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import { payment } from '@/api/payment';
import Address from '@/components/payment/Address.vue';

export default {
  data() {
    return {
      paymentInfo: {
        name: '',
        phoneNumber: '',
        address: '',
        zonecode: '',
        detail: '',
        note: '',
      },
      discount : 0,
    }
  },
  created(){
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      })
    }
  },
  computed: {
    ...mapState({
      memberId: state => state.user.memberId,
      cart: state => state.order.cart,
      cartList: state => state.member.cartList,
    }),
  },
  methods: {
    setPaymentInfo(newInfo){
      this.paymentInfo = newInfo;
    },
    // 결제 요청 함수
    onSubmitPayment() {
      // payment(
      //   this.paymentInfo.price - this.discount,
      //   (res) => {  
      //     console.log(res);
      //   },
      //   (err) => {
      //     console.log(err);
      //   }
      // );
      
      // 카카오 페이 성공 뜨면
      // 주문 요청
      let orderDetail = {
        delivery: {
          address: {
            address: this.paymentInfo.address,
            name: this.paymentInfo.name,
            phoneNumber: this.paymentInfo.phoneNumber,
            zoneCode: this.paymentInfo.zonecode,
          },
          company: 'kr.cjlogistics',
          note: this.paymentInfo.note,
          number: '',
        },
        memberId: this.memberId,
        orderDetails: [],
        totalPrice: this.cart.totalPrice - this.cart.discountPrice,
      };

      for(let idx in this.cartList){
        orderDetail.orderDetails.push({
          amount: this.cartList[idx].amount,
          productId: this.cartList[idx].productId,
        })
      }

      // this.$store.dispatch('POST_ORDER', orderDetail);
      // // 쿠폰 사용 요청
      // if(this.cart.selectedCoupon.memberCouponId !== 0){
      //   this.$store.dispatch('PUT_COUPON', {
      //     memberId: this.memberId,
      //     couponId: this.cart.selectedCoupon.memberCouponId,
      //   });
      // }
      // // 장바구니 비우기 요청
      // for(let idx in this.cartList){
      //   this.$store.dispatch('DELETE_CART_LIST', {
      //     memberId: this.memberId,
      //     cartId: this.cartList[idx].cartId,
      //   });
      // }
    }
  },
  components: {
    Address,
  },
}
</script>

<style>
.payment-container{
  margin-top: -50px;
}
</style>
