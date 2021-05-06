<template>
  <div class="product__item coupon">
    <div class="coupon-name">{{coupon.name}}</div>
    <div class="coupon-discount">{{coupon.discount}}% 할인</div>
    <div class="coupon-date">~ {{coupon.expiredDate}}까지 사용 가능</div>
    <div class="coupon-content">{{coupon.content}}</div>
  </div>
</template>

<script>

  export default {
    data(){
      return {}
    },
    props: {
      coupon: Object,
    },
  }
</script>

<style scoped>
.blog__item__text h6 {
  color: #0d0d0d;
  font-weight: 700; 
}

.coupon { 
  cursor: pointer;
  background-color: #f3f2ee;
  border-radius: 10px;
  padding: 30px;
}

.coupon-name {
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 30px;
}

.coupon-discount {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.coupon-date {
  font-size: 0.75rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.coupon-content {
  font-size: 0.75rem;
}
</style>