<template>
  <div class="course-refund-container">
    <b-table class="table" :items="items"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          '환불 요청일': '밀키트 배송 전',
          '환불 금액': '결제 대금 전액',
        },
        {
          '환불 요청일': '밀키트 배송 후',
          '환불 금액': '배송비 제외 전액',
        },
        {
          '환불 요청일': '클래스 시작 전 날',
          '환불 금액': '결제 대금의 1/3',
        },
        {
          '환불 요청일': '클래스 시작 당일',
          '환불 금액': '결제 대금의 1/2',
        },
        {
          '환불 요청일': '클래스 진행 후',
          '환불 금액': '환불 불가',
        },
      ]
    }
  }
}
</script>

<style>
.course-refund-container table{
  margin: 20px auto 0px auto;
  width: 70%;
}

.course-refund-container table th{
  background-color: #f3f2ee;
}
</style>
