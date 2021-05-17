<template>
  <div>
    <div class="body-header">
      <img src="@/assets/img/banner/banner-payment.png"/>
    </div>
    <section class="checkout payment payment-container">
      <div class="container">
        <div class="checkout__form">
          <div>
            <div class="row">
              <div class="col-lg-4 col-md-6">
                <div class="checkout__order">
                  <h4 class="order__title">결제 금액</h4>
                  <div class="checkout__order__products">결제 강의명 <span>결제 금액</span></div>
                  <ul class="checkout__total__products">
                    <li>{{paymentInfo.courseName}}<span>{{paymentInfo.price}}원</span></li>
                  </ul>
                  <ul class="checkout__total__all">
                    <li>총 할인 금액 <span>{{discount}}원</span></li>
                    <li>최종 가격 <span>{{paymentInfo.price - discount}}원</span></li>
                  </ul>
                  <button class="site-btn" @click="onSubmitPayment">카카오 페이 결제</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </section>
    <b-modal id="modal-scrollable" size="lg" v-model="isSettingAddressOpen" scrollable hide-footer>
      <VueDaumPostcode 
        @complete="onCompleteSettingAddress"
        @close="onCloseSettingAddress"/>
    </b-modal>
  </div>
</template>

<script>
import { VueDaumPostcode } from "vue-daum-postcode";
import { mapState } from 'vuex';
import { payment } from '@/api/payment.js';

export default {
  computed: {
    ...mapState({
      url: state => state.payment.url,
      flag: state => state.payment.flag,
      course: state => state.course.course,
    })
  },
  data() {
    return {
      paymentInfo: {
        courseName: '강의 제목',
        price: 10000,
        courseId: 0,
      },
      discount: 0,
      isSettingAddressOpen: false,
    }
  },
  created() {
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      })
    }

    this.$store.dispatch('GET_COURSE', this.$route.params.id);
    this.paymentInfo.courseName = this.course.name;
    this.paymentInfo.price = this.course.price;
    this.paymentInfo.courseId = this.id;
  },
  methods: {
    // 주소 및 우편번호 설정 관련 함수
    onCompleteSettingAddress(result) {
      this.paymentInfo.address = result.address;
      this.paymentInfo.zonecode = result.zonecode;
      this.isSettingAddressOpen = false;
    },
    onOpenSettingAddress() {
      this.isSettingAddressOpen = true;
    },
    onCloseSettingAddress() {
      this.isSettingAddressOpen = false;
    },
    // 결제 요청 함수
    onSubmitPayment() {
      
      // 결제 완료 후 요청 보낼 것 미리 저장하기
      window.localStorage.setItem('payment_course', JSON.stringify({
        courseId: this.$route.params.id,
        paidPrice: this.paymentInfo.price,
        memberId: this.$store.state.user.memberId,
      }));

      payment(this.paymentInfo.price)
        .then(response => location.href = response.data.url);

    },
  },
  components: {
    VueDaumPostcode,
  },
}
</script>

<style>
.payment-container{
  margin-top: -50px;
}
</style>
