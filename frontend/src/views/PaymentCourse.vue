<template>
  <div>
    <div class="body-header">결제하기</div>
    <section class="checkout payment payment-container">
      <div class="container">
          <div class="checkout__form">
              <div>
                  <div class="row">
                      <!-- <div class="col-lg-8 col-md-6">
                          <h4 class="checkout__title">배송 정보</h4>
                          <div class="row">
                              <div class="col-lg-6">
                                  <div class="checkout__input">
                                      <p>받으시는 분<span>*</span></p>
                                      <input 
                                        type="text"
                                        v-model="paymentInfo.userName"
                                        placeholder="">
                                  </div>
                              </div>
                              <div class="col-lg-6">
                                  <div class="checkout__input">
                                      <p>휴대폰 번호<span>*</span></p>
                                      <input 
                                        type="text"
                                        v-model="paymentInfo.phoneNumber"
                                        placeholder="01012345678">
                                  </div>
                              </div>
                          </div>
                          <div class="checkout__input">
                            <p>배송 주소<span>*</span></p>
                            <div class="row">
                              <div class="col-lg-8">
                                <input 
                                  type="text" 
                                  class="sample6_address" 
                                  @click="onOpenSettingAddress" 
                                  v-model="paymentInfo.zonecode" 
                                  placeholder="우편번호" readonly>
                              </div>
                              <div class="col-lg-4"> 
                                <input 
                                  type="button" 
                                  @click="onOpenSettingAddress" 
                                  value="우편번호 찾기">
                              </div>
                            </div>
                            <div class="checkout__input">
                              <input 
                                type="text" 
                                class="sample6_address" 
                                @click="onOpenSettingAddress" 
                                v-model="paymentInfo.address" 
                                placeholder="주소" readonly>
                            </div>
                            <div class="checkout__input">
                              <input 
                                type="text" 
                                id="sample6_detailAddress" 
                                v-model="paymentInfo.detail" 
                                placeholder="상세주소">
                            </div>
                          </div>
                          <div class="checkout__input">
                            <p>배송 요청사항<span>*</span></p>
                            <input 
                              type="text"
                              placeholder="예) 경비실에 맡겨주세요">
                          </div>
                      </div> -->
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

      this.$store.dispatch('GET_PAYMENT_URL', this.paymentInfo.price);
      // this.$store.commit('SET_PAYMENT', this.paymentInfo);
    },
  },
  components: {
    VueDaumPostcode,
  },
  watch: {
    // flag: function() {
    //   console.log(this.flag);
    //   this.$router.push('payment/success');
    // },
    // newWindow: {
    //     deep: true,
    //     handler() {
    //         console.log(`data change ${this.newWindow}`)
    //     },
    // },
    url() {
      if (this.url.length > 0) {
        location.href = this.url;
      }
    }
  },
}
</script>

<style>
.payment-container{
  margin-top: -50px;
}
</style>
