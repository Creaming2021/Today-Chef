<template>
  <div>
    
  </div>
</template>

<script>

export default {
  created() {
    alert('결제 실패, 메인 화면으로 이동합니다.');

    window.localStorage.setItem('payment_course', '');
    window.localStorage.setItem('payment_product', '');

    this.$router.push('/');
  },
}
</script>

<style>

</style>