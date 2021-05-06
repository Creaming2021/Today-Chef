<template>
  <!-- 결제 내역 Section Start -->
  <div class="row">
    <div class="col-lg-12">
      <div class="shopping__cart__table">
        <span @click="changeType('course')">강의</span> | 
        <span @click="changeType('product')">밀키트</span>
        <table>
          <thead>
              <tr>
                <th v-if="type === 'course'">강의 정보</th>
                <th v-else-if="type === 'product'">주문 번호</th>
                <th v-if="type === 'course'">결제일</th>
                <th v-else-if="type === 'product'">결제 내역</th>
                <th>가격</th>
              </tr>
          </thead>
          <tbody>
            <template v-if="type === 'course'">
              <template v-for="payment in paymentList">
                <CoursePayment :payment="payment" :type="type" :key="payment.registerId"/>
              </template>
            </template>
            <template v-else-if="type === 'product'">
              <template v-for="payment in paymentList">
                <ProductPayment :payment="payment" :type="type" :key="payment.orderId"/>
              </template>
            </template>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <!-- 결제 내역 Section End -->
</template>


<script>
import { mapState } from 'vuex';
import CoursePayment from '@/components/mypage/CoursePayment.vue';
import ProductPayment from '@/components/mypage/ProductPayment.vue';

export default {
  components: {
    ProductPayment,
    CoursePayment,
  },
  data(){
    return {
      type: 'course',
      paymentList: [],
    };
  },
  computed : {
    ...mapState({
      user: state => state.user,
      coursePaymentList: state => state.member.paymentList,
      productPaymentList: state => state.order.orderList,
    }),
  },
  created() {
    this.setPaymentList();
  },
  methods: {
    changeType(newType) {
      this.type = newType;
    },
    setPaymentList() {
      if(this.type === 'course'){
        this.getCoursePaymentList();
      } else if(this.type === 'product'){
        this.getProductPaymentList();
      }
    },
    getCoursePaymentList() {
        // this.$store.dispatch('GET_REGISTER_LIST', user.memberId);
        this.$store.dispatch('GET_REGISTER_LIST', 4);
        this.paymentList = this.coursePaymentList;
    },
    getProductPaymentList() {
        // this.$store.dispatch('GET_ORDER_LIST', user.memberId);
        this.$store.dispatch('GET_ORDER_LIST', 6);
        this.paymentList = this.productPaymentList;
    },
  },
  watch: {
    type: function() {
      this.setPaymentList();
    }
  }
}
</script>

<style>
</style>