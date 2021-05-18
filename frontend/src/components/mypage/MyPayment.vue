<template>
  <!-- 결제 내역 Section Start -->
  <div class="row">
    <div class="col-lg-12">
      <div class="shopping__cart__table">
        <span :class="type==='course' && 'active'" @click="changeType('course')">강의</span> | 
        <span :class="type==='product' && 'active'" @click="changeType('product')">밀키트</span>
        <table>
          <thead>
              <tr>
                <template v-if="type === 'course'">
                  <th>강의 정보</th>
                  <th colspan="2">결제일</th>
                </template>
                <template v-else-if="type === 'product'">
                  <th>주문 번호</th>
                  <th>결제 내역</th>
                </template>
                <th>가격</th>
              </tr>
          </thead>
          <tbody>
            <template v-if="paymentList.length > 0">
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
            </template>
            <template v-else>
              <tr>
                <td colspan="3" class="empty-payment">
                  <img src="@/assets/img/empty/payment.png"/>
                </td>
              </tr>
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
    };
  },
  computed : {
    ...mapState({
      user: state => state.user,
      coursePaymentList: state => state.member.paymentList,
      productPaymentList: state => state.order.orderList,
    }),
    paymentList: function(){
      return this.setPaymentList();
    },
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
        return this.getCoursePaymentList();
      } else if(this.type === 'product'){
        return this.getProductPaymentList();
      }
    },
    getCoursePaymentList() {
      this.$store.dispatch('GET_REGISTER_LIST', this.user.memberId);
      return this.coursePaymentList.sort((o1, o2) => {
        if(o1.paidDate < o2.paidDate) return 1;
        if(o1.paidDate > o2.paidDate) return -1;
        if(o1.paidDate === o2.paidDate) return 0;
      });
    },
    getProductPaymentList() {
      this.$store.dispatch('GET_ORDER_LIST', this.user.memberId);
      return this.productPaymentList.sort((o1, o2) => {
        if(o1.date < o2.date) return 1;
        if(o1.date > o2.date) return -1;
        if(o1.date === o2.date) return 0;
      });
    },
  },
  watch: {
    type: function() {
      this.setPaymentList();
    }
  }
}
</script>

<style scoped>
.shopping__cart__table span{
  cursor: pointer;
}
.empty-payment{
  width: 100%;
  text-align: center;
}
.empty-payment img{
  width: 500px;
  height: auto;
}
table{
  margin-top: 30px;
}
th{
  text-align: center;
}
</style>