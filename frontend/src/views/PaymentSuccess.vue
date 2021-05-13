<template>
  <div>
    결제 완료
  </div>
</template>

<script>
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
  created() {

    const paymentProductString = window.localStorage.getItem('payment_product');
    const paymentCourseString = window.localStorage.getItem('payment_course');
    if (paymentCourseString != '') {
      const paymentInfo = JSON.parse(paymentCourseString);
      console.log(paymentInfo);
      this.$store.dispatch('POST_REGISTER', paymentInfo);
      window.localStorage.setItem('payment_course', '');
    } else if (paymentProductString != '') {
      const paymentInfo = JSON.parse(paymentProductString);
      console.log(paymentInfo);
      this.$store.dispatch('POST_ORDER', paymentInfo);
      window.localStorage.setItem('payment_product', '');
    }

    alert('마이페이지로 이동');
    this.$router.push('/me/info');
  },
}
</script>

<style>

</style>