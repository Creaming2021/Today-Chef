<template>
  <div>
    <div class="row">
    <nav class="coupon-nav header__menu mobile-menu">
      <ul>
        <li @click="onClickCouponFilter('possible')">사용 가능 쿠폰</li>
        <li @click="onClickCouponFilter('done')">사용 완료 쿠폰</li>
        <li @click="onClickCouponFilter('impossible')">사용 만료 쿠폰</li>
      </ul>
    </nav>
    </div>
    <div class="row">
    <div 
      class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
      v-for="(coupon,idx) in couponList" :key="idx">
        <CouponItem :coupon="coupon" :idx="idx" />
    </div>
    </div>
  </div>
</template>


<script>
import CouponItem from '@/components/common/CouponItem.vue';

export default {
  data(){
    return {
      couponAtiveFilter : '',
    }
  },
  components: {
    CouponItem,
  },
  props: {
    couponList: Array,
  },
  mounted() {
    this.initActiveFilter();
    this.setClickEventOfFilter(document.querySelector('.coupon-nav').querySelectorAll('li'),'couponAtiveFilter');
  },
  methods: {
    initActiveFilter() {
      this.couponAtiveFilter = 0;
      const mypageCouponActiveArray = document.querySelector('.coupon-nav').querySelectorAll('li');
      mypageCouponActiveArray[this.couponAtiveFilter].classList.toggle('active');
    },
    setClickEventOfFilter(array,type) {
      array.forEach((element,idx) => {
        element.addEventListener('click',() => {
          array[this[type]].classList.toggle('active');
          this[type] = idx;
          array[this[type]].classList.toggle('active');
        })
      });
    },
    onClickCouponFilter(state) {
      if (state === 'done') {// 완료 쿠폰
        alert('사용완료쿠폰 정렬')
      } else if (state === 'possible') { //사용 가능 쿠폰
        alert('사용가능쿠폰 정렬')
      } else { //사용 만료 쿠폰
        alert('사용만료쿠폰 정렬')
      }
    },
  }
}
</script>

<style>
</style>