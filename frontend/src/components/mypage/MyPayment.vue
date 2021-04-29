<template>
  <!-- 결제 내역 Section Start -->
  <div class="row">
    <div class="col-lg-12">
      <div class="shopping__cart__table">
        <table>
          <thead>
              <tr>
                <th>강의</th>
                <th>결제일</th>
                <th>가격</th>
              </tr>
          </thead>
          <tbody v-for="(payment,idx) in myPaymentList" :key="idx">
            <PaymentItem :payment="payment" :idx="idx" />
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <!-- 결제 내역 Section End -->
</template>


<script>
import PaymentItem from '@/components/common/PaymentItem.vue';

export default {
  components: {
    PaymentItem,
  },
  props: {
    myPaymentList: Array,
  }
}
</script>

<style>
</style>