<template>
  <div>
    <div class="row">
    <nav class="coupon-nav header__menu mobile-menu tab">
      <div 
        :class="couponFilter === 'AVAILABLE' ? 'active' : 'inactive'"
        @click="onClickCouponFilter('AVAILABLE')">사용 가능 쿠폰</div>
      <div 
        :class="couponFilter === 'USED' ? 'active' : 'inactive'"
        @click="onClickCouponFilter('USED')">사용 완료 쿠폰</div>
      <div 
        :class="couponFilter === 'EXPIRED' ? 'active' : 'inactive'"
        @click="onClickCouponFilter('EXPIRED')">사용 만료 쿠폰</div>
    </nav>
    </div>
    <div class="row">
      <template v-if="filteredCouponList.length > 0">
        <div 
          class="col-lg-4 col-md-6 col-sm-6 mix new-arrivals" 
          v-for="(coupon,idx) in filteredCouponList" :key="idx">
            <CouponItem :coupon="coupon" :idx="idx" />
        </div>
      </template>
      <template v-else>
        <img class="img" src="@/assets/img/empty/coupon.png"/>
      </template>
    </div>
  </div>
</template>


<script>
import { mapState } from 'vuex';
import CouponItem from '@/components/common/CouponItem.vue';

export default {
  data(){
    return {
      couponFilter: 'AVAILABLE',
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
    filteredCouponList: function(){
      return this.setCouponList();
    }
  },
  created() {
    this.getCouponList();
  },
  methods: {
    onClickCouponFilter(state) {
      this.couponFilter = state;
    },
    getCouponList(){
      this.$store.dispatch('GET_COUPON_LIST', this.user.memberId);
    },
    setCouponList(){
      return this.couponList.filter(coupon => coupon.couponStatus === this.couponFilter);
    }
  }
}
</script>

<style scoped>
.img{
  margin: auto;
  width: 500px;
  height: auto;
}

.tab{
  display: table;
  width: 100%;
}

.tab div{
  display: table-cell;
}

.active{
  display: inline-block;
  width: 200px;
  border-radius: 10px 10px 0px 0px;
  border-width: 3px 3px 0px 3px;
  border-style: solid;
  border-color: #f3f2ee;
  padding: 5px 10px 5px 10px;
}

.inactive{
  display: inline-block;
  width: 200px;
  border-width: 0px 0px 3px 0px;
  border-style: solid;
  border-color: #f3f2ee;
  margin: 0px -3px 0px -3px;
  padding: 5px 10px 5px 10px;
}
</style>