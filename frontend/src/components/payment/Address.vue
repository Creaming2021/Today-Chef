<template>
  <div class="col-lg-8 col-md-6">
    <h4 class="checkout__title">배송 정보</h4>
    <div class="row">
      <div class="col-lg-6">
        <div class="checkout__input">
          <p>받으시는 분<span>*</span></p>
          <input 
            type="text"
            v-model="paymentInfo.name"
            placeholder="받으시는 분">
        </div>
      </div>
      <div class="col-lg-6">
        <div class="checkout__input">
          <p>휴대폰 번호<span>*</span></p>
          <input 
            type="text"
            v-model="paymentInfo.phoneNumber"
            placeholder="010-1234-5678">
        </div>
      </div>
    </div>
    <div class="checkout__input">
      <p>배송 주소<span>*</span></p>
      <div class="row">
        <div class="col-lg-8">
          <input 
            type="text" 
            id="sample6_address" 
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
          id="sample6_address" 
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
        v-model="paymentInfo.note"
        placeholder="예) 경비실에 맡겨주세요">
    </div>
    <b-modal id="modal-scrollable" size="lg" v-model="isSettingAddressOpen" 
      scrollable hide-footer hide-header centered>
      <VueDaumPostcode 
        @complete="onCompleteSettingAddress"
        @close="onCloseSettingAddress"/>
    </b-modal>
  </div>
</template>

<script>
import { VueDaumPostcode } from "vue-daum-postcode";

export default{
  props:{
    paymentInfo: Object,
    setPaymentInfo: Function,
  },
  components:{
    VueDaumPostcode,
  },
  data(){
    return {
      isSettingAddressOpen: false,
    };
  },
  methods: {
    // 주소 및 우편번호 설정 관련 함수
    onCompleteSettingAddress(result) {
      this.$emit('setPaymentInfo', {
        ...this.paymentInfo,
        address: result.address,
        zonecode: result.zonecode,
      });
      this.isSettingAddressOpen = false;
    },
    onOpenSettingAddress() {
      this.isSettingAddressOpen = true;
    },
    onCloseSettingAddress() {
      this.isSettingAddressOpen = false;
    },
  }
}
</script>

<style>

</style>