<template>
  <div>
    <div class="row">
    <nav class="coupon-nav header__menu mobile-menu">
      <ul>
        <li @click="onClickCouponFilter('AVAILABLE')">사용 가능 쿠폰</li>
        <li @click="onClickCouponFilter('USED')">사용 완료 쿠폰</li>
        <li @click="onClickCouponFilter('EXPIRED')">사용 만료 쿠폰</li>
      </ul>
    </nav>
    </div>
    <div class="row">
      <div v-if="filteredCouponList.length > 0">
        <div 
          class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
          v-for="(coupon,idx) in filteredCouponList" :key="idx">
            <CouponItem :coupon="coupon" :idx="idx" />
        </div>
      </div>
      <div v-else>
        쿠폰이 없습니다.
      </div>
    </div>
  </div>
</template>


<script>
import { mapState } from 'vuex';
import CouponItem from '@/components/common/CouponItem.vue';

export default {
  data(){
    return {
      couponAtiveFilter : '',
      filteredCouponList: [],
    }
  },
  components: {
    CouponItem,
  },
  computed : {
    ...mapState({
      user: state => state.user,
      couponList: state => state.member.couponList,
    }),
  },
  created() {
    this.getCouponList();
  },
  mounted() {
    this.initActiveFilter();
    this.setClickEventOfFilter(document.querySelector('.coupon-nav').querySelectorAll('li'),'couponAtiveFilter');
    this.onClickCouponFilter('AVAILABLE');
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
      this.filteredCouponList = this.couponList.filter(coupon => coupon.couponStatus === state);
    },
    getCouponList(){
      this.$store.dispatch('GET_COUPON_LIST', this.user.memberId);
    },
  }
}
</script>

<style>
</style>