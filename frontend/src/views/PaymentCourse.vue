<template>
  <div>
    <div class="body-header">
      <img src="@/assets/img/banner/banner-payment.png"/>
    </div>
    <section class="checkout payment payment-container">
      <div class="container">
        <div class="checkout__form">
          <div class="row">
            <div class="col-lg-8 col-md-6 checkout-course-detail row">
              <img class="col-lg-6 col-md-6" :src="course.images.length > 0 && course.images[0].imageUrl"/>
              <div class="col-lg-6 col-md-6 payment-course-detail-container">
                <img class="image-preview" :src="course.images.length > 0 && course.images[1].imageUrl"/>
                <b-badge variant="primary">{{course.category}}</b-badge>
                <span><img src="@/assets/img/icon/calendar.png" alt="">{{course.date}}</span>
                <h4>{{course.name}}</h4>
                <b-form-rating v-model="course.rating" color="orange" size="xl" readonly no-border/>
              </div>
            </div>
            <div class="col-lg-4 col-md-6">
              <div class="checkout__order">
                <h4 class="order__title">결제 금액</h4>
                <div class="checkout__order__products">결제 강의명</div>
                <ul class="checkout__total__products">
                  <li>{{course.name}}</li>
                </ul>
                <ul class="checkout__total__all">
                  <li>최종 가격 <span>{{course.price}}원</span></li>
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
import { mapState } from 'vuex';
import { payment } from '@/api/payment.js';

export default {
  computed: {
    ...mapState({
      url: state => state.payment.url,
      flag: state => state.payment.flag,
      course: state => state.course.course,
      memberId: state => state.user.memberId,
    })
  },
  created() {
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      })
    }

    this.$store.dispatch('GET_COURSE', this.$route.params.id);
  },
  methods: {
    // 결제 요청 함수
    onSubmitPayment() {
      
      // 결제 완료 후 요청 보낼 것 미리 저장하기
      window.localStorage.setItem('payment_course', JSON.stringify({
        courseId: this.$route.params.id,
        paidPrice: this.course.price,
        memberId: this.memberId,
      }));

      payment(this.course.price)
        .then(response => location.href = response.data.url);

    },
  },
}
</script>

<style>
.payment-container{
  margin-top: -50px;
}
.checkout-course-detail {
  background-color: #f3f2ee;
  padding-top: 15px;
}
.checkout-course-detail img{
  display: inline-block;
  width: 350px;
  height: 350px;
  object-fit: cover;
}
.payment-course-detail-container {
  display: inline-block;
}
.payment-course-detail-container img{
  width: 18px;
  height: 18px;
  margin: 0px 10px 0px 20px;
}
.payment-course-detail-container .image-preview{
  width: 100%;
  height: 50%;
  object-fit: cover;
  margin: 0px 0px 20px 0px;
}
.payment-course-detail-container h4{
  font-weight: bold;
  margin: 20px 0px 20px 0px;
}
</style>
