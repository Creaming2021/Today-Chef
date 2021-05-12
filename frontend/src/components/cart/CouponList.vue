<template>
  <div class="cart__discount">
    <h6>쿠폰 사용</h6>
    <div 
      v-bind:class="open ? 'nice-select open coupon-list' : 'nice-select coupon-list'"
      @click="openCategory">
      <span class="current">{{selectedCoupon.name}}</span>
      <ul class="list">
        <li
          @click="() => clickCoupon('0', '쿠폰 사용 안 함', 0)"
          v-bind:class="selectedCoupon.memberCouponId === '0' ? 'focus selected option' : 'option'"
          >쿠폰 사용 안 함</li>
        <li 
          v-for="coupon in couponList" v-bind:key="coupon.memberCouponId"
          @click="() => clickCoupon(coupon.memberCouponId, coupon.name, coupon.discount)"
          v-bind:class="selectedCoupon.memberCouponId === coupon.memberCouponId ? 'focus selected option' : 'option'">
            {{coupon.name}} / {{coupon.discount}}% 할인 쿠폰
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      open: false,
    }
  },
  props:{
    couponList: Array,
    selectedCoupon: Object,
  },
  methods: {
    openCategory() { 
      this.open = !this.open;
    },
    clickCoupon(id, name, discount){
      this.$emit('setCoupon', { id, name, discount });
    }
  }
}
</script>

<style scoped>
.coupon-list,
.list{
  width: 100%;
}
</style>