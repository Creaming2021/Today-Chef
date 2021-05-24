<template>
  <div>
  </div>
</template>

<script>

export default {
  created() {
    this.$swal.fire({
      icon: 'error',
      title: '결제 실패',
      text: '메인 화면으로 이동합니다.',
    });

    window.localStorage.setItem('payment_course', '');
    window.localStorage.setItem('payment_product', '');

    this.$router.push({
      name: 'Home'
    });
  },
}
</script>

<style>

</style>