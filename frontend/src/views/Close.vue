<template>
  <div>
    결제 창 닫기
  </div>
</template>

<script>
export default {
  created() {
    // this.$store.dispatch('GET_PAYMENT_SUCCESS');
    console.log(opener);
    console.log(opener.localStorage.getItem('payment'));
    opener.localStorage.setItem('payment', true);
    // window.open('about:blank','_self').self.close();

  }
}
</script>

<style>

</style>